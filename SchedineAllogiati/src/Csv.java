import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Csv implements FileManager {

	@Override
	public Record[] loadFile(String path) {
		
		Record[] records = null;

		try{	
			InputStream in = ResourceLoader.loader(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			//Read File Line By Line
			int i=0;
			while ((strLine = br.readLine()) != null)   {
				records[i] = readRecord(strLine);
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
		// TODO Auto-generated method stub
		return false;
	}

	private void writeRecord(Record record){

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
