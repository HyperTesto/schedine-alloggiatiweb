import org.eclipse.swt.custom.TableTree;

/**
 * @author hypertesto
 * @version 1.0
 *
 */
public interface FileGenerator {
	
	public boolean loadFile(String path);
	
	public boolean writeFile(TableTree records);
}
