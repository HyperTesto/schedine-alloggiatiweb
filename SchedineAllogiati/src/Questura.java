import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Enrico Testori
 *
 */
public class Questura implements FileManager {

	/*
	 * Variabili statiche per la modalità del check
	 */
	public static int STRICT = 0; 		//controllo integrità sottogruppi e che data arrivo sia nelle 48 ore precedenti (per invio immediato a questura)
	public static int SEMI_STRICT = 1;	//controllo integrità sottogruppi e data arrivo non differisca più di 48 h da un record all'altro
	public static int PERMISSIVE = 2;	//controllo integrità sottogruppi

	private List<FormatException> exceptions;
	private QueryQuestura q;

	public Questura(){
		q = new QueryQuestura();
	}

	@Override
	public List<Record> loadFile(String path) {
		try {
			q.connect();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		exceptions  = new ArrayList<FormatException>();
		List<Record> records = new ArrayList<Record>();

		try{
			FileInputStream in = new FileInputStream(new File(path));
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			
			int i=1;
			while ((strLine = br.readLine()) != null)   {
				Debug.print("[FINAL-RECORD]" + strLine);
				if(strLine.length()!=170){
					exceptions.add(new FormatException("Lunghezza della riga errata. Proveremo a leggere il leggibile", i));
				}
				Record temp = readRecord(strLine, i);
				if (temp != null){
					records.add(temp);
				}else{
					exceptions.add(new FormatException("Null record", i));
				}
				i++;
			}
			//Close the input stream
			in.close();
			q.disconnect();
		}catch (Exception e){
			System.err.println("[Sono qui] Errore nell'apertura/lettura del file: " + e.getMessage());
		}
		return records;
	}

	@Override
	public boolean writeFile(List<Record> records, String saveTo) {
		try {
			q.connect();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter(saveTo));
			for(Record temp : records){
				bw.write(formatRecord(temp)+"\n");
			}
			bw.close();
			q.disconnect();
			return true;


		}catch(Exception e){
			System.out.println("!Errore nella apertura/scrittura del file!: " +e.getMessage());
			e.printStackTrace();
			return false;
		}


	}

	@Override
	public List<FormatException> getErrors() {
		return exceptions;
	}

	/**
	 * Ritorna una riga formattata per l'invio tramite file
	 * @param record
	 * @return
	 * @throws SQLException
	 */
	private String formatRecord(Record record) throws SQLException{
		String riga="";
		
		/*
		 * Campi alloggiato
		 */
		String allog = record.getTipoAlloggiato();
		//campo tipo (da sistemare con i codici
		riga+=q.getAlloggiatoByName(allog);
		
		//campo data di arrivo
		riga+=record.getDataArrivo();
		
		//campo permanenza
		riga+=this.padPermanenza(record.getPermanenza());


		/*
		 * Dati personali alloggiato
		 */

		//campo nome
		riga+=padRight(record.getCognome(), 50);

		//campo cognome
		riga+=padRight(record.getNome(), 30);

		//campo sesso
		if(record.getSesso().equals("M")){
			riga+="1";
		}else{
			riga+="2";
		}

		//data di nascita
		riga+=record.getDataNascita();

		//stato di nascita
		String stato = record.getStatoNascita();

		/*
		 * Entro nel controllo:
		 * se stato di nascita ITALIA allora riempio anche comune e provincia altrimenti riempio con blank
		 */
		if(stato.equals("ITALIA")){
			String comune = record.getComuneNascita();
			riga += padRight(q.getComuneByName(comune),9);
			String provincia = record.getProvinciaNascita();
			riga += padRight(provincia,2);

		}else{
			riga += padRight("",9);
			riga += padRight("",2);
		}
		riga += q.getStatoByName(stato);

		//cittadinanza
		riga+=q.getCittadinanzaByName(record.getCittadinanza());

		/*
		 * Dati documento
		 */
		if (allog.equals(Alloggiato.CAPO_FAMIGLIA) || allog.equals(Alloggiato.CAPO_GRUPPO) || allog.equals(Alloggiato.OSPITE_SINGOLO)){
			//tipo
			riga+=padRight(q.getDocumentoByName(record.getTipoDocumento()),5);

			//numero
			riga+=padRight(record.getNumeroDocumento(), 20);

			//luogo rilascio
			riga+=padRight(q.getLuogoRilascioByName(record.getRilascioDocumento()),9);

		} else{
			riga += padRight("", 34);
		}

		return riga;
	}
	/**
	 * ritorna il record istanziato con i dati letti dal file (tutto il possibile)
	 * @param riga
	 * @param index
	 * @return
	 */
	private Record readRecord(String riga, int index){
		String nome,cognome,tipoAlloggiato,dataArrivo,dataNascita,sesso,cittadinanza,statoNascita,comuneNascita,tipoDoc,numDoc,rilascioDoc;
		int permanenza;
		Record record;

		/*
		 * Campi prenotazione
		 */

		Debug.print("Leggo il tipo di alloggiato...");
		tipoAlloggiato = this.readTipoAlloggiato(riga);
		if(tipoAlloggiato.equals(null)){exceptions.add(new FormatException("TIPO ALLOGGIATO MANCANTE", index));}

		Debug.print("Leggo la data di arrivo...");
		dataArrivo = this.readDataArrivo(riga);
		if(dataArrivo.equals(null)){exceptions.add(new FormatException("DATA ARRIVO MANCANTE", index));}

		Debug.print("Leggo la permanenza...");
		permanenza = this.readPermanenza(riga);
		if(permanenza==0){exceptions.add(new FormatException("PERMANENZA MANCANTE", index));}

		/*
		 * Campi personali alloggiato
		 */

		Debug.print("Leggo il cognome...");
		cognome = this.readCognome(riga);
		if(cognome.equals(null)){exceptions.add(new FormatException("COGNOME MANCANTE", index));}

		Debug.print("Leggo il nome...");
		nome = this.readName(riga);
		if(nome.equals(null)){exceptions.add(new FormatException("NOME MANCANTE", index));}

		Debug.print("Leggo il sesso...");
		sesso = this.readSesso(riga);
		if(sesso.equals(null)){exceptions.add(new FormatException("SESSO MANCANTE", index));}

		Debug.print("Leggo la data di nascita...");
		dataNascita = this.readDataNascita(riga);
		if(dataNascita.equals(null)){exceptions.add(new FormatException("DATA NASCITA MANCANTE", index));}
		
		
		Debug.print("Leggo lo stato di nascita...");
		statoNascita = this.readStatoNascita(riga);
		if(statoNascita.equals(null)){exceptions.add(new FormatException("STATO NASCITA MANCANTE", index));}
		
		if(statoNascita.equals("ITALIA")){
			Debug.print("Leggo il comune di nascita...");
			comuneNascita = this.readComuneNascita(riga);		//per la formattazione del DB i comune contiene già la provincia
			if(comuneNascita.equals(null)){exceptions.add(new FormatException("COMUNE NASCITA MANCANTE", index));}
		} else {
			Debug.print("Salto comune e provincia");
			comuneNascita = null;
		}
		
		Debug.print("Leggo la cittadinanza...");
		cittadinanza = this.readCittadinanza(riga);
		if(cittadinanza.equals(null)){exceptions.add(new FormatException("CITTADINANZA MANCANTE", index));}


		/*
		 * Campi del documento
		 */

		if(tipoAlloggiato.equals(Alloggiato.CAPO_FAMIGLIA) || tipoAlloggiato.equals(Alloggiato.CAPO_GRUPPO) || tipoAlloggiato.equals(Alloggiato.OSPITE_SINGOLO)){
			Debug.print("Leggo il tipo del documento...");
			tipoDoc = this.readTipoDoc(riga);
			if(tipoDoc.equals(null)){exceptions.add(new FormatException("TIPO DOCUMENTO MANCANTE", index));}

			Debug.print("Leggo il numero del documento...");
			numDoc = this.readNumeroDoc(riga);
			if(numDoc.equals(null)){exceptions.add(new FormatException("NUMERO DOCUMENTO MANCANTE", index));}

			Debug.print("Leggo il luogo di rilascio del documento...");
			rilascioDoc = this.readRilascioDoc(riga);
			if(rilascioDoc.equals(null)){exceptions.add(new FormatException("RILASCIO DOCUMENTO MANCANTE", index));}
		} else{
			Debug.print("Salto i campi documento...");
			tipoDoc = null;
			numDoc = null;
			rilascioDoc = null;
		}

		/*
		 * Istanzio il record
		 */

		record = new Record(tipoAlloggiato, dataArrivo, permanenza, nome, cognome, dataNascita, sesso, cittadinanza, statoNascita, comuneNascita, tipoDoc, numDoc, rilascioDoc);

		return record;
	}

	/**
	 * Ritorna il nome dell'alloggiato opportunamente trimmato
	 * @param riga
	 * @return String
	 */
	private String readName(String riga){
		String res = riga.substring(64, 93).trim();
		Debug.print("nome = " + res);
		return res;
	}

	/**
	 * 
	 * @param riga
	 * @return String: cognome alloggiato trimmato
	 */
	private String readCognome(String riga){
		String res = riga.substring(14, 63).trim();
		Debug.print("cognome = " + res);
		return res;
	}

	/**
	 * 
	 * @param riga
	 * @return String: sesso alloggiato (M o F)
	 */
	private String readSesso(String riga){
		String sesso = riga.substring(94, 95);
		String res;
		if(sesso.equals("1")){
			res = "M";
			//return "M";
		}else if(sesso.equals("2")){
			res = "F";
			//return "F";
		}else{
			//problema: sesso errato!
			res = null;
			//return null;
		}
		Debug.print("sesso = " + res);
		return res;
	}

	/**
	 * 
	 * @param riga
	 * @return String stato cittadinanza
	 */
	private String readCittadinanza(String riga){
		String res;
		try {
			res = q.getCittadinanzaByCode(riga.substring(125, 134));
		} catch (SQLException e) {
			res = null;
		}
		Debug.print("cittadinanza = " + res);
		return res;
	}

	/**
	 * 
	 * @param riga
	 * @return String: data di nascita
	 */
	private String readDataNascita(String riga){
		/*
		 * TODO: fare il controllo se la data è formtattata correttamente, altrimenti null
		 */
		String res;
		res = riga.substring(95, 105);
		Debug.print("data di nascita = " + res);
		return res;
	}

	/**
	 * 
	 * @param riga
	 * @return String: stato di nascita (manca query)
	 */
	private String readStatoNascita(String riga){
		String res;
		try {
			res = q.getStatoByCode(riga.substring(116, 125));
		} catch (SQLException e) {
			res = "";
		}
		Debug.print("stato di nascita = " + res);
		return res;
	}

	/**
	 * 
	 * @param riga
	 * @return String: comune di nascita (manca query)
	 */
	private String readComuneNascita(String riga){
		// acquisire anche la provinincia è superfluo visto che è già codificata nel DB
		String res;
		try {
			res = q.getComuneByCode(riga.substring(105, 114));
		} catch (SQLException e) {
			res = "";
		}
		Debug.print("comune di nascita = " + res);
		return res;
	}

	/**
	 * 
	 * @param riga
	 * @return String tipo alloggiato
	 */
	private String readTipoAlloggiato(String riga){
		String res;
		try {
			res = q.getAlloggiatoByCode(riga.substring(0, 2));

		} catch (SQLException e) {
			res = "";
		}
		Debug.print("tipo alloggiato = " + res);
		return res;

	}

	/**
	 * 
	 * @param riga
	 * @return String: data di arrivo
	 */
	private String readDataArrivo(String riga){
		String res = riga.substring(2, 12);
		Debug.print("data di arrivo = " + res);
		return res;
	}

	/**
	 * 
	 * @param riga
	 * @return int: permanenza
	 */
	private int readPermanenza(String riga){
		int res = Integer.parseInt(riga.substring(12, 14));
		Debug.print("permanenza = " + res);
		return res;
	}

	/**
	 * 
	 * @param riga
	 * @return String: tipo documento trimmato (manca query)
	 */
	private String readTipoDoc(String riga){
		String res;
		try {
			res = q.getDocumentoByCode(riga.substring(134, 139).trim());
		} catch (SQLException e) {
			res = "";
		}
		Debug.print("tipo documento = " + res);
		return res;
	}

	/**
	 * 
	 * @param riga
	 * @return String: numero doc
	 */
	private String readNumeroDoc(String riga){
		String res;
		res = riga.substring(139, 158).trim();
		Debug.print("numero ducumento = " + res);
		return res;
	}

	/**
	 * 
	 * @param riga
	 * @return String: luogo di rilascio (manca query)
	 */
	private String readRilascioDoc(String riga){
		String res;
		try {
			res = q.getLuogoRilascioByCode(riga.substring(159, 168).trim());
		} catch (SQLException e) {
			res = "";
		}
		Debug.print("luogo di rilascio = " + res);
		return res;
	}

	/*
	 * Utilità varie
	 */
	public static boolean check(List<Record> records, int mod){

		return false;

	}

	private static boolean checkSubGroups(List<Record> records){
		boolean isSingle, isFamily, isGroup;
		isSingle=isGroup=isFamily=false;

		/*
		 * RULES:
		 * - ogni sottogruppo deve cominciare con un osite singolo, un capogruppo o u capo famiglia
		 * - ogni sottogruppo ospite singolo è composto solo da un osite
		 * - ogni sottogruppo gruppo deve contenere almeno un capogruppo e un membro
		 * - ogni sottogruppo famiglia deve avere almeno un capofamiglia e un membro famiglia
		 */
		for(Record temp : records){

			//trovo di che sottogruppo si tratta
			if(temp.getTipoAlloggiato().equals(Alloggiato.CAPO_FAMIGLIA)){
				isFamily = true;
				isGroup = false;
				isSingle=false;

			}else if(temp.getTipoAlloggiato().equals(Alloggiato.CAPO_GRUPPO)){
				isGroup = true;
				isFamily=false;
				isSingle=false;

			}else if(temp.getTipoAlloggiato().equals(Alloggiato.OSPITE_SINGOLO)){
				isSingle = true;
				isFamily = false;
				isGroup = false;

			}else{
				/*
				 * Entro nel controllo dei membri gruppo/famiglia
				 */
				if(temp.getTipoAlloggiato().equals(Alloggiato.MEMBRO_GRUPPO) && isGroup==true){
					//OK
				}else if(temp.getTipoAlloggiato().equals(Alloggiato.MEMBRO_FAMIGLIA) && isFamily==true){
					//OK
				}else{
					//PROBLEM DETECTED!
					return false;

				}

			}


		}

		return true;
	}

	private static List<FormatException> checkDateInterval(List<Record> records, int mod){

		List<FormatException> dateExceptions = new ArrayList<FormatException>();

		if(mod==Questura.PERMISSIVE){
			return null;
		}else if(mod==Questura.SEMI_STRICT){
			for(Record temp : records){
				// controlli per la modalità semi-strict 
			}

		}else if(mod==Questura.STRICT){
			for(Record temp : records){
				// controlli per la modalità strict 
			}
		}else{
			// parametro errato
		}

		return null;
	}
	private String padPermanenza (int n){
		String res = String.format("%02d", n);
		return res;
	}
	private String padRight(String s, int n) {
		return String.format("%1$-" + n + "s", s);  
	}

	private String padLeft(String s, int n) {
		return String.format("%1$" + n + "s", s);  
	}


	public static void main(String args[]) throws IOException{
		/*
		 * main di test
		 */
		Csv c = new Csv();
		List<Record> records = null;
		records = c.loadFile("/home/hypertesto/cacca.csv");

		for(Record record : records){
			System.out.print("*********** RECORD LETTO *************\n");
			System.out.print(record);
			System.out.print("**************************************\n\n");
		}

		Questura q = new Questura();
		System.out.print("********************************** GENERA FILE **********************************\n");
		q.writeFile(records, "/home/hypertesto/alloggiati.questura");
		System.out.print("**********************************************************************************\n\n");

		System.out.print("********************************** LEGGO FILE **********************************\n");
		List<Record> recs= new ArrayList<Record>(q.loadFile("/home/hypertesto/alloggiati.questura"));
		System.out.print("********************************************************************************\n\n");

		for(Record record : recs){
			System.out.print("*********** RECORD LETTO *************\n");
			System.out.print(record);
			System.out.print("**************************************\n\n");
		}

	}
}