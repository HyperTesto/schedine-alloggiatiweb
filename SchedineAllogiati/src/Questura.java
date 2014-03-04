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


	@Override
	public List<Record> loadFile(String path) {

		List<Record> records = new ArrayList<Record>();

		try{	
			InputStream in = ResourceLoader.loader(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			//Read File Line By Line
			while ((strLine = br.readLine()) != null)   {
				Record temp = readRecord(strLine);
				if (temp != null){
					records.add(temp);
				}
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
}