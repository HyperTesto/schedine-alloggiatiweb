import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Csv implements FileManager {

	@Override
	public List<Record> loadFile(String path) {

		List<Record> records = new ArrayList<Record>();

		try{	
			InputStream in = ResourceLoader.loader(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			//Read File Line By Line
			int i=0;
			while ((strLine = br.readLine()) != null)   {
				records.add(readRecord(strLine));
			}
			//Close the input stream
			in.close();
		}catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
		return records;
	}

	@Override
	public boolean writeFile(Record[] records, String saveTo) {
		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter(saveTo));
			for(int i=0; i<records.length;i++){
				bw.write(formatRecord(records[i]));
			}
			bw.close();
			return true;
			

		}catch(Exception e){
			System.out.println("Error!");
			return false;
		}
		
	}

	private String formatRecord(Record record){
		String riga="";
		/*
		 * Campi alloggiato
		 */
		riga+=record.getTipoAlloggiato()+";";
		riga+=record.getDataArrivo()+";";
		riga+=record.getPermanenza()+";";
		/*
		 * Dati personali alloggiato
		 */
		riga+=record.getNome()+";";
		riga+=record.getCognome()+";";
		riga+=record.getSesso()+";";
		riga+=record.getDataNascita()+";";
		riga+=record.getStatoNascita()+";";
		riga+=record.getComuneNascita()+";";
		riga+=record.getCittadinanza()+";";
		/*
		 * Dati documento
		 */
		riga+=record.getTipoDocumento()+";";
		riga+=record.getNumeroDocumento()+";";
		riga+=record.getRilascioDocumento();
		return riga;
	}

	private Record readRecord(String riga){
		Record record;
		StringTokenizer t = new StringTokenizer(riga,";");
		/*
		 * Istanzio il record leggendo direttamente dalla riga, questa parte sarà da raffianare
		 * per renderlo più flessibile e meno sensibile ai bug
		 */
		record = new Record(t.nextToken(),t.nextToken(),Integer.parseInt(t.nextToken()),t.nextToken(),t.nextToken(),t.nextToken(),t.nextToken(),t.nextToken(),t.nextToken(),t.nextToken(),t.nextToken(),t.nextToken(),t.nextToken());

		return record;
	}

}
