import java.io.IOException;
import java.util.List;

/**
 * @author Enrico Testori
 * @version 1.0
 *
 */
public interface FileManager{
	
	/**
	 * Metodo per la lettura di file che ritorna una lista di Record
	 * @param path
	 * @return List<Record>
	 */
	public List<Record> loadFile(String path) throws IOException;
	
	/**
	 * Metodo per la scrittura di file. Ritorna true se l'operazione
	 * è andata a buon fine, falso alrimenti.
	 * @param records
	 * @param saveTo
	 * @return boolean
	 */
	public boolean writeFile(List<Record> records, String saveTo) throws IOException;
}
