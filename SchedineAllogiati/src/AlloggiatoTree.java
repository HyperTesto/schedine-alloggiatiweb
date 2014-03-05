import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
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
			birthState, birthDistrict, citizienship, docType, docNumber,
			birthPlace;
	
	protected static final String menuItems[] = {
	
	"Modifica" };
	
	protected static final String singleIconFile = "res/files/single.png";
	protected static final String groupIconFile = "res/files/group.png";
	protected static final String familyIconFile = "";
	
	public AlloggiatoTree (Composite parent, int params) {
		super (parent, params);
		
		Menu treeMenu;
		MenuItem temp;
		
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
		
		treeMenu = new Menu (parent.getShell (), SWT.POP_UP);
		
		for (String item : menuItems) {
			
			temp = new MenuItem (treeMenu, SWT.NONE);
			temp.setText (item);
			
			temp.addSelectionListener (new MenuListener (this));
		}
		
		setMenu (treeMenu);
	}
	
	public void insertRecords (Record[] people) {
		
		TreeItem item, subItem;
		Image singleImage, groupImage, familyImage, tempImage;
		
		/*
		 * addListener (SWT.MeasureItem, new Listener () {
		 * 
		 * @Override public void handleEvent (Event arg0) { // TODO
		 * Auto-generated method stub
		 * 
		 * System.out.println ("altezza-riga: " + arg0.height); }
		 * 
		 * 
		 * });
		 */
		
		tempImage = new Image (Display.getDefault (),
				ResourceLoader.loader (singleIconFile));
		singleImage = new Image (Display.getDefault (), tempImage
				.getImageData ().scaledTo (16, 16));
		tempImage.dispose ();
		
		tempImage = new Image (Display.getDefault (),
				ResourceLoader.loader (groupIconFile));
		groupImage = new Image (Display.getDefault (), tempImage
				.getImageData ().scaledTo (16, 16));
		tempImage.dispose ();
		
		int i;
		
		i = 0;
		
		while (i < people.length) {
			
			item = new TreeItem (this, SWT.NONE);
			item.setText (new String[] {
			
			// TODO: inserire i campi
					"Gruppo"
			
			});
			
			item.setImage (groupImage);
			
			// System.out.println (item.getBounds ());
			
			subItem = new TreeItem (item, SWT.NONE);
			subItem.setText (new String[] {
			
					"Singolo" 
			});
			
			subItem.setImage (singleImage);
			
			i++;
		}
		
	}
	
	@Override
	protected void checkSubclass () {
		
	}
	
	private class MenuListener implements SelectionListener {
		
		private Tree tree;
		
		public MenuListener (Tree tree) {
			
			this.tree = tree;
		}
		
		@Override
		public void widgetDefaultSelected (SelectionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void widgetSelected (SelectionEvent arg0) {
			// TODO Auto-generated method stub
			
			System.out.print ("[menu] pressed \""
					+ ((MenuItem) arg0.widget).getText () + "\", ");
			System.out.println ("selected element \""
					+ tree.getSelection ()[0].getText () + "\"");
		}
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
		shell.setSize (500, 200);
		
		GridLayout gl_shell = new GridLayout (1, false);
		gl_shell.marginWidth = 0;
		gl_shell.horizontalSpacing = 0;
		gl_shell.marginHeight = 0;
		gl_shell.verticalSpacing = 0;
		shell.setLayout (gl_shell);
		
		tree = new AlloggiatoTree (shell, SWT.NONE);
		tree.insertRecords (new Record[] { null });
		GridData gd_tree = new GridData (SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_tree.heightHint = 146;
		tree.setLayoutData (gd_tree);
		
		shell.open ();
		// shell.pack ();
		
		while (!shell.isDisposed ()) {
			
			if (!display.readAndDispatch ()) {
				display.sleep ();
			}
		}
		
	}
	
}
