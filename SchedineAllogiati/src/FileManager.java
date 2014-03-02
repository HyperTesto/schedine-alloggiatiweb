import java.util.List;

/**
 * @author Enrico Testori
 * @version 1.0
 *
 */
public interface FileManager {
	
	public List<Record> loadFile(String path);
	
	public boolean writeFile(Record[] records, String saveTo);
}
