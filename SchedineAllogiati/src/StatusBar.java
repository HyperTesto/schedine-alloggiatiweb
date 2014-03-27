import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
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
		
		Font defaultFont;
		int fontHeight;
		
		//layout = new FillLayout ();
		//layout.type = SWT.HORIZONTAL;
		//setLayout (layout);
		
		setLayout (new GridLayout (2, false));
		
		image = new Label (this, SWT.NONE);
		
		label = new Label (this, SWT.NONE);
		label.setLayoutData (new GridData (SWT.FILL, SWT.FILL, true, false, 1,
				1));
		
		defaultFont = parent.getDisplay ().getSystemFont ();
		//fontHeight = defaultFont.getFontData ()[0].getHeight ();
		fontHeight = 16;
		
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
		
		//image.setImage (errorImage);
		//label.setText ("This is the LoL");
		
		//pack ();
	}
	
	public void setGenericMessage (String message) {
		
		image.setVisible (false);
		label.setText (message);
	}
	
	public void setErrorMessage (String message) {
		
		image.setVisible (true);
		image.setImage (errorImage);
		label.setText (message);
	}
	
	public void setWarningMessage (String message) {
		
		image.setVisible (true);
		label.setText (message);
		image.setImage (warningImage);
	}
	
	public void setInfoMessage (String message) {
		
		image.setVisible (true);
		image.setImage (infoImage);
		label.setText (message);
	}
	
	public void setWorkingMessage (String message) {
		
		image.setVisible (true);
		image.setImage (workingImage);
		label.setText (message);
	}
	
	public void clear () {
		
		image.setVisible (false);
		image.setImage (null);
		
		label.setText ("");
	}
	
	@Override
	protected void checkSubclass () {
		
	}
	
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main (String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		final Display display;
		Shell shell;
		final StatusBar bar;
		
		display = new Display ();
		shell = new Shell (display);
		shell.setText ("StatusBar");
		shell.setSize (250, 70);
		
		shell.setLayout (new GridLayout (1, false));
		
		bar = new StatusBar (shell, SWT.NONE);
		bar.setLayoutData (new GridData (SWT.FILL, SWT.CENTER, true, true, 1, 1));
		
		new Runnable () {
			
			private int type = 0;
			
			@Override
			public void run () {
				// TODO Auto-generated method stub
				
				switch (type) {
				
					case 0:
						
						bar.setWarningMessage ("Warning Message");
						break;
					
					case 1:
						
						bar.setErrorMessage ("Error Message");
						break;
					
					case 2:
						
						bar.setWorkingMessage ("Working Message");
						break;
					
					case 3:
						
						bar.setInfoMessage ("Info Message");
						break;
						
					case 4:
						
						bar.setGenericMessage ("Generic message");
						break;
						
					case 5:
						
						bar.clear ();
						break;
				
				}
				
				type = (type +1) % 6;
				
				display.timerExec (5000, this);
			}
			
			
		}.run ();
		
		shell.open ();
		
		while (!shell.isDisposed ()) {

			if (!display.readAndDispatch ()) {
				display.sleep ();
			}
		}
	}
	
	
}
