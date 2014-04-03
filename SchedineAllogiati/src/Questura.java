import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
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
		
		exceptions  = new ArrayList<FormatException>();
		List<Record> records = new ArrayList<Record>();

		try{
			/*
			InputStream in = ResourceLoader.loader(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			*/
			FileInputStream in = new FileInputStream(new File(path));
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			//Read File Line By Line
			int i=1;
			while ((strLine = br.readLine()) != null)   {
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
		}catch (Exception e){//Catch exception if any
			System.err.println("Errore nell'apertura/lettura del file: " + e.getMessage());
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
		// TODO Auto-generated method stub
		return exceptions;
	}

	private String formatRecord(Record record) throws SQLException{
		String riga="";
		//QueryQuestura q = new QueryQuestura();

		/*
		 * parte da implementare per gestire la formattazione corretta
		 * per i record da inviare tramite file alla questura
		 */

		/*
		 * Campi alloggiato
		 */
		//campo tipo (da sistemare coni codici
		riga+=q.getAlloggiatoByName(record.getTipoAlloggiato());
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
		
		//comune di nascita
		riga+= padRight(q.getComuneByName(record.getComuneNascita()),9);
		
		//provincia di nascita
		riga+=padRight(record.getProvinciaNascita(),2);
		
		//stato di nascita
		riga+=q.getStatoByName(record.getStatoNascita());
		
		//cittadinanza
		riga+=q.getCittadinanzaByName(record.getCittadinanza());
		
		//

		/*
		 * Dati documento
		 */
		
		//tipo
		riga+=padRight(q.getDocumentoByName(record.getTipoDocumento()),5);
		
		//numero
		riga+=padRight(record.getNumeroDocumento(), 20);
		
		//luogo rilascio
		riga+=padRight(q.getLuogoRilascioByName(record.getRilascioDocumento()),9);

		return riga;
	}

	private Record readRecord(String riga, int index){
		String nome,cognome,tipoAlloggiato,dataArrivo,dataNascita,sesso,cittadinanza,statoNascita,comuneNascita,tipoDoc,numDoc,rilascioDoc;
		int permanenza;
		Record record;
		
		nome = this.readName(riga);
		if(nome.equals(null)){exceptions.add(new FormatException("NOME MANCANTE", index));}
		cognome = this.readCognome(riga);
		if(cognome.equals(null)){exceptions.add(new FormatException("COGNOME MANCANTE", index));}
		tipoAlloggiato = this.readTipoAlloggiato(riga);
		if(tipoAlloggiato.equals(null)){exceptions.add(new FormatException("TIPO ALLOGGIATO MANCANTE", index));}
		dataArrivo = this.readDataArrivo(riga);
		if(dataArrivo.equals(null)){exceptions.add(new FormatException("DATA ARRIVO MANCANTE", index));}
		dataNascita = this.readDataNascita(riga);
		if(dataNascita.equals(null)){exceptions.add(new FormatException("DATA NASCITA MANCANTE", index));}
		sesso = this.readSesso(riga);
		if(sesso.equals(null)){exceptions.add(new FormatException("SESSO MANCANTE", index));}
		cittadinanza = this.readCittadinanza(riga);
		if(cittadinanza.equals(null)){exceptions.add(new FormatException("CITTADINANZA MANCANTE", index));}
		statoNascita = this.readStatoNascita(riga);
		if(statoNascita.equals(null)){exceptions.add(new FormatException("STATO NASCITA MANCANTE", index));}
		comuneNascita = this.readComuneNascita(riga);
		if(comuneNascita.equals(null)){exceptions.add(new FormatException("COMUNE NASCITA MANCANTE", index));}
		tipoDoc = this.readTipoDoc(riga);
		if(tipoDoc.equals(null)){exceptions.add(new FormatException("TIPO DOCUMENTO MANCANTE", index));}
		numDoc = this.readNumeroDoc(riga);
		if(numDoc.equals(null)){exceptions.add(new FormatException("NUMERO DOCUMENTO MANCANTE", index));}
		rilascioDoc = this.readRilascioDoc(riga);
		if(rilascioDoc.equals(null)){exceptions.add(new FormatException("RILASCIO DOCUMENTO MANCANTE", index));}
		permanenza = this.readPermanenza(riga);
		if(permanenza==0){exceptions.add(new FormatException("PERMANENZA MANCANTE", index));}
		
		record = new Record(tipoAlloggiato, dataArrivo, permanenza, nome, cognome, dataNascita, sesso, cittadinanza, statoNascita, comuneNascita, tipoDoc, numDoc, rilascioDoc);
		
		return record;
	}
	
	/**
	 * Ritorna il nome dell'alloggiato opportunamente trimmato
	 * @param riga
	 * @return String
	 */
	private String readName(String riga){

		return riga.substring(64, 93).trim();
	}
	
	/**
	 * 
	 * @param riga
	 * @return String: cognome alloggiato trimmato
	 */
	private String readCognome(String riga){

		return riga.substring(14, 63).trim();
	}
	
	/**
	 * 
	 * @param riga
	 * @return String: sesso alloggiato (M o F)
	 */
	private String readSesso(String riga){
		String sesso = riga.substring(94, 94);
		if(sesso.equals("1")){
			return "M";
		}else if(sesso.equals("2")){
			return "F";
		}else{
			//problema: sesso errato!
			return null;
		}
	}

	/**
	 * 
	 * @param riga
	 * @return String stato cittadinanza
	 */
	private String readCittadinanza(String riga){

		//per debug ritorna direttamente il nome esteso
		try {
			return q.getCittadinanzaByCode(riga.substring(125, 133));
		} catch (SQLException e) {
			return null;
		}
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
		return riga.substring(95, 104);
	}

	/**
	 * 
	 * @param riga
	 * @return String: stato di nascita (manca query)
	 */
	private String readStatoNascita(String riga){

		try {
			return q.getStatoByCode(riga.substring(116, 124));
		} catch (SQLException e) {
			return "";
		}
	}

	/**
	 * 
	 * @param riga
	 * @return String: comune di nascita (manca query)
	 */
	private String readComuneNascita(String riga){
		// acquisire anche la provinincia è superfluo visto che è già codificata nel DB
		try {
			return q.getComuneByCode(riga.substring(105, 113));
		} catch (SQLException e) {
			return "";
		}
	}

	/**
	 * 
	 * @param riga
	 * @return String tipo alloggiato
	 */
	private String readTipoAlloggiato(String riga){
				
		try {
			return q.getAlloggiatoByCode(riga.substring(0, 1));
		} catch (SQLException e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 
	 * @param riga
	 * @return String: data di arrivo
	 */
	private String readDataArrivo(String riga){

		return riga.substring(2, 11);
	}

	/**
	 * 
	 * @param riga
	 * @return int: permanenza
	 */
	private int readPermanenza(String riga){
		return Integer.parseInt(riga.substring(12, 13));
	}

	/**
	 * 
	 * @param riga
	 * @return String: tipo documento trimmato (manca query)
	 */
	private String readTipoDoc(String riga){

		try {
			return q.getDocumentoByCode(riga.substring(134, 138).trim());
		} catch (SQLException e) {
			return "";
		}
	}

	/**
	 * 
	 * @param riga
	 * @return String: numero doc
	 */
	private String readNumeroDoc(String riga){

		return riga.substring(139, 158).trim();
	}
	
	/**
	 * 
	 * @param riga
	 * @return String: luogo di rilascio (manca query)
	 */
	private String readRilascioDoc(String riga){

		try {
			return q.getLuogoRilascioByCode(riga.substring(159, 167).trim());
		} catch (SQLException e) {
			return "";
		}
	}
	
	/*
	 * Metodi per la creazione del file alloggiati
	 */
	
	
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
		return String.format("%02d", n);
	}
	private String padRight(String s, int n) {
	     return String.format("%1$-" + n + "s", s);  
	}

	private String padLeft(String s, int n) {
	    return String.format("%1$" + n + "s", s);  
	}
}