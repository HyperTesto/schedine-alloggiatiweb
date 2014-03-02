import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

/**
 * 
 */

/**
 * @author Alberto Bonizzi
 *
 */
public class AlloggiatoTree extends Tree {

	protected TreeColumn type, arrival, stay, surname, name, sex, birthday,
				birthState, birthDistrict, citizienship, docType, docNumber, birthPlace;
	
	public AlloggiatoTree(Composite parent, int params) {
		super (parent, params);
		
		
		
		// Lets make visible the column heathers with the column names
		setHeaderVisible (true);
		
		type = new TreeColumn (this, SWT.CENTER);
		type.setText ("Tipo Alloggiato");
		type.setWidth (100);
		
		arrival = new TreeColumn (this, SWT.CENTER);
		arrival.setText ("Arrivo");
		arrival.setWidth (100);
		
		stay = new TreeColumn (this, SWT.CENTER);
		stay.setText ("Permanenza");
		stay.setWidth (100);
		
		surname = new TreeColumn (this, SWT.CENTER);
		surname.setText ("Cognome");
		surname.setWidth (100);
		
		name = new TreeColumn (this, SWT.CENTER);
		name.setText ("Nome");
		name.setWidth (100);
		
		sex = new TreeColumn (this, SWT.CENTER);
		sex.setText ("Sesso");
		sex.setWidth (100);
		
		birthday = new TreeColumn (this, SWT.CENTER);
		birthday.setText ("Data d nascita");
		birthday.setWidth (100);
		
		birthState = new TreeColumn (this, SWT.CENTER);
		birthState.setText ("Stato di nascita");
		birthState.setWidth (100);
		
		birthDistrict = new TreeColumn (this, SWT.CENTER);
		birthDistrict.setText ("Comune di nascita");
		birthDistrict.setWidth (100);
		
		citizienship = new TreeColumn (this, SWT.CENTER);
		citizienship.setText ("Cittadinanza");
		citizienship.setWidth (100);
		
		docType = new TreeColumn (this, SWT.CENTER);
		docType.setText ("Tipo Documento");
		docType.setWidth (100);
		
		docNumber = new TreeColumn (this, SWT.CENTER);
		docNumber.setText ("Numero documento");
		docNumber.setWidth (100);
		
		birthPlace = new TreeColumn (this, SWT.CENTER);
		birthPlace.setText ("Luogo di nascita");
		birthPlace.setWidth (100);
		
		
		
		/*
		for (int i = 0; i < 4; i++) {
			TreeItem item = new TreeItem(this, SWT.NONE);
			item.setText(new String[] { "item " + i, "abc", "defghi" });
			for (int j = 0; j < 4; j++) {
				TreeItem subItem = new TreeItem(item, SWT.NONE);
				subItem.setText(new String[] { "subitem " + j, "jklmnop", "qrs" });
				for (int k = 0; k < 4; k++) {
					TreeItem subsubItem = new TreeItem(subItem, SWT.NONE);
					subsubItem.setText(new String[] { "subsubitem " + k, "tuv", "wxyz" });
				}
			}
		}
		*/
		
		
	}
	
	public void insertRecords (Record[] people) {
		
		TreeItem item, subItem;
		int i;
		
		i = 0;
		
		while (i < people.length) {
			
			item = new TreeItem (this, SWT.NONE);
			item.setText (new String[] {
					
					//TODO: inserire i campi
					
					
			});
			
			item.setImage (resize (new Image (Display.getDefault (), "/home/alberto/Desktop/IconeSchedine/1393793258_administrator.png"), 16, 16));
			
			i++;
		}
		
	}
	
	private Image resize (Image image, int width, int height) {
		
		Image scaled; 
		GC gc;
		
		scaled = new Image (Display.getDefault (), width, height);
		gc = new GC (scaled);
		
		//gc.setAntialias (SWT.ON);
		
		gc.drawImage (image, 0, 0, image.getBounds ().width,
				image.getBounds ().height, 0, 0, width, height);
		
		gc.dispose ();
		image.dispose ();
		
		return scaled;
	}
	
	@Override
	protected void checkSubclass () {

	}
	
	
	/**
	 * @param args
	 */
	public static void main (String[] args) {
		
		Display display;
		Shell shell;
		AlloggiatoTree tree;
		
		display = new Display ();
		shell = new Shell (display);
		shell.setText ("AllogiatoTree");
		shell.setSize (200, 200);
		
		GridLayout gl_shell = new GridLayout (1, false);
		gl_shell.marginWidth = 0;
		gl_shell.horizontalSpacing = 0;
		gl_shell.marginHeight = 0;
		gl_shell.verticalSpacing = 0;
		shell.setLayout (gl_shell);

		tree = new AlloggiatoTree (shell, SWT.CHECK);
		tree.insertRecords (new Record[] {null});
		GridData gd_tree = new GridData (SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_tree.heightHint = 146;
		tree.setLayoutData (gd_tree);
		
		shell.open ();
		//shell.pack ();
		
		while (!shell.isDisposed ()) {

			if (!display.readAndDispatch ()) {
				display.sleep ();
			}
		}

	}

}
