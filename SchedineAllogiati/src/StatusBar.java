import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;


public class StatusBar extends Composite {
	
	protected Label label;
	protected Label image;
	
	protected Image warningImage;
	protected Image errorImage;
	protected Image infoImage;
	protected Image workingImage;
	
	public StatusBar (Composite parent, int style) {
		super (parent, style);
		// TODO Auto-generated constructor stub
		
		FillLayout layout;
		Font defaultFont;
		int fontHeight;
		
		layout = new FillLayout ();
		layout.type = SWT.HORIZONTAL;
		setLayout (layout);
		
		image = new Label (this, SWT.NONE);
		label = new Label (this, SWT.NONE);
		
		defaultFont = parent.getDisplay ().getSystemFont ();
		fontHeight = defaultFont.getFontData ()[0].getHeight ();
		
		errorImage = new Image (parent.getDisplay (), parent.getDisplay ()
				.getSystemImage (SWT.ICON_ERROR).getImageData ()
				.scaledTo (fontHeight, fontHeight));
		warningImage = new Image (parent.getDisplay (), parent.getDisplay ()
				.getSystemImage (SWT.ICON_WARNING).getImageData ()
				.scaledTo (fontHeight, fontHeight));
		infoImage = new Image (parent.getDisplay (), parent.getDisplay ()
				.getSystemImage (SWT.ICON_INFORMATION).getImageData ()
				.scaledTo (fontHeight, fontHeight));
		workingImage = new Image (parent.getDisplay (), parent.getDisplay ()
				.getSystemImage (SWT.ICON_WORKING).getImageData ()
				.scaledTo (fontHeight, fontHeight));
	}
	
	public void setGenericMessage (String message) {
		
		image.setImage (null);
		label.setText (message);
	}
	
	public void setErrorMessage (String message) {
		
		image.setImage (errorImage);
		label.setText (message);
	}
	
	public void setWarningMessage (String message) {
		
		label.setText (message);
		image.setImage (warningImage);
	}
	
	public void setInfoMessage (String message) {
		
		image.setImage (infoImage);
		label.setText (message);
	}
	
	public void setWorkingMessage (String message) {
		
		image.setImage (workingImage);
		label.setText (message);
	}
	
	public void clear () {
		
		label.setText ("");
	}
	
	@Override
	protected void checkSubclass () {
		
	}
	
	/**
	 * @param args
	 */
	public static void main (String[] args) {
		// TODO Auto-generated method stub
		
		Display display;
		Shell shell;
		StatusBar bar;
		
		display = new Display ();
		shell = new Shell (display);
		shell.setText ("StatusBar");
		shell.setSize (250, 70);
		
		shell.setLayout (new GridLayout (1, false));
		
		bar = new StatusBar (shell, SWT.NONE);
		bar.setLayoutData (new GridData (SWT.FILL, SWT.CENTER, true, true, 1, 1));
		
		bar.setWarningMessage ("Exploding now!");
		
		shell.open ();
		
		while (!shell.isDisposed ()) {

			if (!display.readAndDispatch ()) {
				display.sleep ();
			}
		}
	}
	
	
}
