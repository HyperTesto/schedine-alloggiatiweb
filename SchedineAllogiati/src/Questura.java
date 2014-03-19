import java.io.BufferedReader;
import java.io.BufferedWriter;
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

	private List<Exception> exceptions;

	@Override
	public List<Record> loadFile(String path) {
		
		exceptions  = new ArrayList<Exception>();
		List<Record> records = new ArrayList<Record>();

		try{	
			InputStream in = ResourceLoader.loader(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			//Read File Line By Line
			int i=1;
			while ((strLine = br.readLine()) != null)   {
				Record temp = readRecord(strLine);
				if (temp != null){
					records.add(temp);
				}else{
					exceptions.add(new Exception("Errore alla riga " + i + ": dati malformati."));
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
		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter(saveTo));
			for(Record temp : records){
				bw.write(formatRecord(temp));
			}
			bw.close();
			return true;


		}catch(Exception e){
			System.out.println("Errore nella apertura/scrittura del file!: " +e.getMessage());
			return false;
		}

	}
	
	@Override
	public List<Exception> getErrors() {
		// TODO Auto-generated method stub
		return exceptions;
	}

	private String formatRecord(Record record) throws SQLException{
		String riga="";
		QueryQuestura q = new QueryQuestura();

		/*
		 * parte da implementare per gestire la formattazione corretta
		 * per i record da inviare tramite file alla questura
		 */

		/*
		 * Campi alloggiato
		 */
		//campo tipo (da sistemare coni codici
		riga+=record.getTipoAlloggiato();
		//campo data di arrivo
		riga+=record.getDataArrivo();
		//campo permanenza
		riga+=record.getPermanenza();


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
		riga+= padRight(q.getCodiceComune(record.getComuneNascita()),9);
		
		//provincia di nascita
		riga+=padRight(record.getProvinciaNascita(),2);
		
		//stato di nascita
		riga+=q.getCodiceStato(record.getStatoNascita());
		
		//cittadinanza
		riga+=q.getCodiceStatoCittadinanza(record.getCittadinanza());
		
		//

		/*
		 * Dati documento
		 */

		return riga;
	}

	private Record readRecord(String riga){
		String nome,cognome,tipoAlloggiato,dataArrivo,dataNascita,sesso,cittadinanza,statoNascita,comuneNascita,tipoDoc,numDoc,rilascioDoc;
		int permanenza;
		Record record;
		/*
		 * StringTokenizer t = new StringTokenizer(riga,";");
		 */
		/* questa parte andrà reimplementata 
		 * per maneggiare i record formattati per l'invio tramite file alla questura
		 * molto probabilmente la strada migliore sarà utilizzare il metodo substring e trimmare il tutto
		 */


		//record = new Record(t.nextToken(),t.nextToken(),Integer.parseInt(t.nextToken()),t.nextToken(),t.nextToken(),t.nextToken(),t.nextToken(),t.nextToken(),t.nextToken(),t.nextToken(),t.nextToken(),t.nextToken(),t.nextToken());

		// da cambiare il return con record quando tutto sarà implementato
		return null;
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
			return "";
		}
	}

	/**
	 * 
	 * @param riga
	 * @return String stato cittadinanza (manca query)
	 */
	private String readCittadinanza(String riga){

		//per debug ritorna direttamente il nome esteso
		return riga.substring(125, 133);
	}

	/**
	 * 
	 * @param riga
	 * @return String: data di nascita
	 */
	private String readDataNascita(String riga){

		return riga.substring(95, 104);
	}

	/**
	 * 
	 * @param riga
	 * @return String: stato di nascita (manca query)
	 */
	private String readStatoNascita(String riga){

		return riga.substring(116, 124);
	}

	/**
	 * 
	 * @param riga
	 * @return String: comune di nascita (manca query)
	 */
	private String readComuneNascita(String riga){
		// acquisire anche la provinincia è superfluo visto che è già codificata nel DB
		return riga.substring(105, 113);
	}

	/**
	 * 
	 * @param riga
	 * @return String tipo alloggiato
	 */
	private String readTipoAlloggiato(String riga){
		String tipo = riga.substring(0, 1);
		
		if (tipo.equals("16")){
			
			return Alloggiato.OSPITE_SINGOLO;
			
		}else if (tipo.equals("17")){
			
			return Alloggiato.CAPO_FAMIGLIA;
			
		}else if (tipo.equals("18")){
			
			return Alloggiato.CAPO_GRUPPO;
			
		}else if (tipo.equals("19")){
			
			return Alloggiato.MEMBRO_FAMIGLIA;
			
		}else if (tipo.equals("20")){
			
			return Alloggiato.MEMBRO_GRUPPO;
			
		}else{
			
			//PROBLEM DETECTED!
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

		return riga.substring(134, 138).trim();
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

		return riga.substring(159, 167).trim();
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
	
	private String padRight(String s, int n) {
	     return String.format("%1$-" + n + "s", s);  
	}

	private String padLeft(String s, int n) {
	    return String.format("%1$" + n + "s", s);  
	}
}