import org.eclipse.swt.custom.TableTree;

/**
 * @author hypertesto
 *
 */
public interface FileGenerator {
	
	public boolean loadFile(String path);
	
	public boolean writeFile(TableTree records);
}
