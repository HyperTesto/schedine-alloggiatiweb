import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Enrico Testori
 *
 */
public class Questura implements FileManager {


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

	private String formatRecord(Record record){
		String riga="";

		/*
		 * parte da implementare per gestire la formattazione corretta
		 * per i record da inviare tramite file alla questura
		 */

		/*
		 * Campi alloggiato
		 */


		/*
		 * Dati personali alloggiato
		 */


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
	
	
	private String readCognome(String riga){

		return riga.substring(14, 63).trim();
	}

	private String readSesso(String riga){

		return riga.substring(94, 94);
	}

	private String readCittadinanza(String riga){

		return null;
	}

	private String readDataNascita(String riga){

		return riga.substring(95, 104);
	}

	private String readStatoNascita(String riga){

		return null;
	}

	private String readComuneNascita(String riga){

		return riga.substring(105, 113);
	}

	private String readTipoAlloggiato(String riga){

		return riga.substring(0, 1);
	}

	private String readDataArrivo(String riga){

		return riga.substring(2, 11);
	}

	private int readPermanenza(String riga){
		return Integer.parseInt(riga.substring(12, 13));
	}

	private String readTipoDoc(String riga){

		return null;
	}

	private String readNumeroDoc(String riga){

		return null;
	}
	
	private String readRilascioDoc(String riga){

		return null;
	}
	
	/*
	 * Metodi per la creazione del file alloggiati
	 */
	
	
	/*
	 * Utilità varie
	 */
	public boolean checkCongruence(List<Record> records){
		
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
			if(temp.getTipoAlloggiato()=="CAPO FAMIGLIA"){
				isFamily = true;
				isGroup = false;
				isSingle=false;
				
			}else if(temp.getTipoAlloggiato()=="CAPOGRUPPO"){
				isGroup = true;
				isFamily=false;
				isSingle=false;
				
			}else if(temp.getTipoAlloggiato()=="OSPITE SINGOLO"){
				isSingle = true;
				isFamily = false;
				isGroup = false;
				
			}else{
				/*
				 * Entro nel controllo dei membri gruppo/famiglia
				 */
				if(temp.getTipoAlloggiato()=="MEMBRO GRUPPO"&&isGroup==true){
					//OK
				}else if(temp.getTipoAlloggiato()=="MEBRO FAMIGLIA" && isFamily==true){
					//OK
				}else{
					//PROBLEM DETECTED!
					return false;
					
				}
				
			}
			
			
		}
		
		return true;
	}
}