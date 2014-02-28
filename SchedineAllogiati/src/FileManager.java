/**
 * @author Enrico Testori
 * @version 1.0
 *
 */
public interface FileManager {
	
	public boolean loadFile(String path);
	
	public boolean writeFile(Record[] records);
}
