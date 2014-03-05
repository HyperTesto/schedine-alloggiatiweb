import java.io.InputStream;
/**
 * 
 * @author Enrico Testori
 *
 */
final public class ResourceLoader {
	 
	/**
	  * Semplice metodo statico per la compatibilità in lettura tra jar e non jar
	  * @param path
	  * @return ImputStream
	  */
	public static InputStream loader (String path) {

		InputStream input = ResourceLoader.class.getResourceAsStream (path);

		if (input == null) {
			input = ResourceLoader.class.getResourceAsStream ("/" + path);
		}

		return input;
	}

}
