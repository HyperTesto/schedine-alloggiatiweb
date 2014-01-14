import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;

final public class ResourceLoader {
	
	public static InputStream loader (String path) {
		
		InputStream input = ResourceLoader.class.getResourceAsStream (path);
		
		if (input == null) {
			input = ResourceLoader.class.getResourceAsStream ("/" + path);
		}
		
		return input;
	}

	public static ArrayList<String> succhiaComuni () {

		ArrayList<String> array;

		array = new ArrayList<String> ();
		
		try {

			BufferedReader br = new BufferedReader (new FileReader (
					"/home/hypertesto/Scrivania/comuni.txt"));

			String line = br.readLine ();

			while (line != null) {

				array.add (line);

				line = br.readLine ();
			}

			br.close ();
		
		} catch (Exception w) {

		}

		return array;
	}
}
