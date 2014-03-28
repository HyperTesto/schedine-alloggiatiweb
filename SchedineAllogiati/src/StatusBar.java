import java.awt.SystemColor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;


public class StatusBar extends Composite {
	
	protected Label text;
	protected Label image;
	
	protected Image warningImage;
	protected Image errorImage;
	protected Image infoImage;
	protected Image workingImage;
	
	public StatusBar (Composite parent, int style) {
		super (parent, style);
		
		Font defaultFont;
		int fontHeight;
		
		
		GridLayout gridLayout = new GridLayout (3, false);
		gridLayout.marginLeft = 5;
		gridLayout.verticalSpacing = 0;
		gridLayout.marginWidth = 0;
		gridLayout.marginHeight = 0;
		setLayout (gridLayout);
		
		image = new Label (this, SWT.NONE);
		image.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Label label = new Label(this, SWT.SEPARATOR | SWT.VERTICAL);
		
		text = new Label (this, SWT.NONE);
		text.setAlignment(SWT.CENTER);
		text.setLayoutData (new GridData (SWT.FILL, SWT.CENTER, true, false, 1,
				1));
		
		defaultFont = parent.getDisplay ().getSystemFont ();
		fontHeight = defaultFont.getFontData ()[0].getHeight () +5;
		//fontHeight = 16;
		
		//SystemColor.MENU;
		
		
		setBackgroundMode (SWT.INHERIT_DEFAULT);
		
		//setBackground (parent.getDisplay ().getSystemColor (SWT.COLOR_LIST_SELECTION));
		
		//systemColor.
		//setBackground ();
		
		/*
		addPaintListener (new PaintListener () {

			@Override
			public void paintControl (PaintEvent event) {
				// TODO Auto-generated method stub
				
				Color systemColor;
				Rectangle r;
				
				systemColor = event.display.getSystemColor (SWT.COLOR_LIST_SELECTION);
				
				
				event.gc.setBackground (systemColor);
				event.gc.setAlpha (80);
				r = ((Composite) event.widget).getBounds ();
				
				event.gc.fillRectangle (10, 10, 10, 10);
				
				//event.gc.setBackground (systemColor);
				
				System.out.println (r);
				
				event.gc.dispose();
			}
			
			
		});
		*/
		
		
		GridData layoutData = new GridData ();
		layoutData.heightHint = fontHeight;
		label.setLayoutData (layoutData);
		
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
		
		image.setVisible (false);
		text.setText (message);
	}
	
	public void setErrorMessage (String message) {
		
		image.setVisible (true);
		image.setImage (errorImage);
		text.setText (message);
	}
	
	public void setWarningMessage (String message) {
		
		image.setVisible (true);
		text.setText (message);
		image.setImage (warningImage);
		
		
	}
	
	public void setInfoMessage (String message) {
		
		image.setVisible (true);
		image.setImage (infoImage);
		text.setText (message);
	}
	
	public void setWorkingMessage (String message) {
		
		image.setVisible (true);
		image.setImage (workingImage);
		text.setText (message);
	}
	
	public void clear () {
		
		image.setVisible (false);
		image.setImage (null);
		
		text.setText ("");
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
		GridLayout gridLayout;
		
		display = new Display ();
		shell = new Shell (display);
		shell.setText ("StatusBar");
		shell.setSize (250, 70);
		
		shell.setLayout (gridLayout = new GridLayout (1, false));
		
		gridLayout.verticalSpacing = 0;
		gridLayout.horizontalSpacing = 0;
		gridLayout.marginWidth = 0;
		gridLayout.marginHeight = 0;
		
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
