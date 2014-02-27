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

}
