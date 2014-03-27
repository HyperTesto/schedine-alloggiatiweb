import java.util.Calendar;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

/**
 * 
 * @author Alberto Bonizzi
 * 
 */

public class FormDateChooser extends Composite {
	
	protected DateText text;
	protected Shell calendarShell;
	protected DateTime dateTime;
	protected Button button;
	
	/*
	 * The used icon has been downloaded from
	 * https://www.iconfinder.com/iconsets/fugue#readme and is under the
	 * Creative Commons 3 licence
	 */
	
	private final static String imageFile = "res/files/1393800387_calendar-month.png";
	
	/**
	 * 
	 * Constructor method for the composite
	 * 
	 * @param parent
	 * @param style
	 */
	public FormDateChooser (final Composite parent, int style) {
		super (parent, style);
		
		GridLayout gl_composite;
		Point textSize;
		Image icon;
		ImageData iconImageData;
		
		Calendar cal;
		int day, month, year;
		
		GridData gd_button;
		
		gl_composite = new GridLayout (2, false);
		
		gl_composite.marginHeight = 0;
		gl_composite.verticalSpacing = 0;
		gl_composite.marginWidth = 0;
		gl_composite.horizontalSpacing = 0;
		
		setLayout (gl_composite);
		
		text = new DateText (this, SWT.BORDER);
		textSize = text.computeSize (SWT.DEFAULT, SWT.DEFAULT);
		
		text.setLayoutData (new GridData (SWT.FILL, SWT.CENTER, true, false, 1,
				1));
		
		cal = Calendar.getInstance ();
		
		day = cal.get (Calendar.DAY_OF_MONTH);
		month = cal.get (Calendar.MONTH) + 1;
		year = cal.get (Calendar.YEAR);
		
		text.setText (day / 10 + "" + day % 10 + "/" + month / 10 + "" + month
				% 10 + "/" + year);
		
		text.setSelection (0);
		text.setToolTipText ("Inserire una data del tipo GG/MM/AAAA");
		
		calendarShell = new Shell (parent.getDisplay (), SWT.ON_TOP);
		calendarShell.setLayout (new FillLayout ());
		
		dateTime = new DateTime (calendarShell, SWT.CALENDAR);
		
		button = new Button (this, SWT.NONE);
		button.addSelectionListener (new SelectionAdapter () {
			
			@Override
			public void widgetSelected (SelectionEvent e) {
				
				if (calendarShell.isVisible ())
					calendarShell.setVisible (false);
				else
					showCalendar (parent);
				
			}
		});
		
		gd_button = new GridData (SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		
		gd_button.heightHint = textSize.y;
		button.setLayoutData (gd_button);
		
		icon = new Image (parent.getDisplay (),
				ResourceLoader.loader (imageFile));
		iconImageData = icon.getImageData ();
		
		button.setImage (new Image (parent.getDisplay (), iconImageData
				.scaledTo (textSize.y - 10,
						((textSize.y - 10) * iconImageData.width)
								/ iconImageData.height)));
		icon.dispose ();
		
		button.setAlignment (SWT.CENTER);
		button.setToolTipText ("Visualizza un calendario\nmediante il quale è possibile\nselezionare una data con il mouse");
		
		setTabList (new Control[] { text });
		
		dateTime.addSelectionListener (new SelectionListener () {
			
			@Override
			public void widgetSelected (SelectionEvent e) {
				
			}
			
			@Override
			public void widgetDefaultSelected (SelectionEvent e) {
				
				int day, month, year;
				
				day = dateTime.getDay ();
				month = dateTime.getMonth () + 1;
				year = dateTime.getYear ();
				
				text.setText (day / 10 + "" + day % 10 + "/" + month / 10 + ""
						+ month % 10 + "/" + year);
				
				calendarShell.setVisible (false);
			}
			
		});
		
		text.addModifyListener (new ModifyListener () {
			
			@Override
			public void modifyText (ModifyEvent e) {
				
				if (text.isDateSet ()) {
					
					dateTime.setDay (text.getDay ());
					dateTime.setMonth (text.getMonth ());
					dateTime.setYear (text.getYear ());
				}
			}
		});
		
		dateTime.addListener (SWT.FocusOut, new Listener () {
			
			public void handleEvent (Event event) {

				parent.getDisplay ().asyncExec (new Runnable () {

					public void run () {
						
						Control control;
							
						if (!parent.getDisplay ().isDisposed ()) {
							
							control = parent.getDisplay ().getFocusControl ();
							
							if (control != button || control != text) {
								
								calendarShell.setVisible (false);
							}
							
						} else {
							
							calendarShell.setVisible (false);
						}
					}
				});
			}
		});
		
		dateTime.addListener (SWT.KeyDown, new Listener () {

			public void handleEvent (Event event) {

				if (event.keyCode == SWT.ESC) {
					calendarShell.setVisible (false);
				}
			}
		});
		
		parent.getShell ().addListener (SWT.Move, new Listener () {

			public void handleEvent (Event event) {

				calendarShell.setVisible (false);
			}
		});
		
	}
	
	private void showCalendar (Composite parent) {
		
		Rectangle parentBounds;
		Point calendarSize;
		Rectangle buttonSize;
		
		calendarSize = dateTime.computeSize (SWT.DEFAULT, SWT.DEFAULT);
		buttonSize = button.getBounds ();
		
		parentBounds = parent.getDisplay ().map (parent, null, getBounds ());
		
		calendarShell
				.setBounds (
						(parentBounds.x + +text.getBounds ().width + buttonSize.width / 2)
								- calendarSize.x / 2, parentBounds.y
								+ parentBounds.height, calendarSize.x,
						calendarSize.y);
		
		calendarShell.setVisible (true);
		calendarShell.setFocus ();
		//text.setFocus ();
	}
	
	/**
	 * 
	 * Returns the {@link org.eclipse.swt.widgets.DateTime} widget included in
	 * the FormDateChooser
	 * 
	 * @return the {@link org.eclipse.swt.widgets.DateTime} widget
	 */
	
	public DateTime getDateTime () {
		
		return dateTime;
	}
	
	/**
	 * 
	 * Returns the {@link org.eclipse.swt.widgets.Text} widget included in the
	 * FormDateChooser
	 * 
	 * @return the {@link org.eclipse.swt.widgets.Text} widget
	 */
	
	public Text getText () {
		
		return text;
	}
	
	public static void main (String[] args) {
		
		Display display;
		Shell shell;
		FormDateChooser date;
		
		display = new Display ();
		shell = new Shell (display);
		shell.setText ("CompletedText");
		shell.setImage (new Image (display, ResourceLoader.loader (imageFile)));
		
		shell.setLayout (new GridLayout (1, false));
		
		date = new FormDateChooser (shell, SWT.NONE);
		date.setLayoutData (new GridData (SWT.FILL, SWT.CENTER, true, true, 1,
				1));
		
		shell.setSize (100, 100);
		shell.open ();
		shell.pack ();
		
		while (!shell.isDisposed ()) {
			
			if (!display.readAndDispatch ()) {
				display.sleep ();
			}
		}
	}
}