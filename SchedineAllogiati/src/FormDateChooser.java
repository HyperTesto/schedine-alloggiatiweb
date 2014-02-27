import java.util.Calendar;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;


public class FormDateChooser extends Composite {
	
	protected DateText text;
	protected Shell calendarShell;
	protected DateTime dateTime;
	
	public FormDateChooser(final Composite parent, int style) {
		super (parent, style);
		
		GridLayout gl_composite = new GridLayout(2, false);
		
		
		gl_composite.marginHeight = 0;
		gl_composite.verticalSpacing = 0;
		gl_composite.marginWidth = 0;
		gl_composite.horizontalSpacing = 0;
		setLayout(gl_composite);
		
		text = new DateText(this, SWT.BORDER);
		text.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				
				//showCalendar (parent);
			}
		});
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		text.setBounds(0, 0, 419, 25);
		//text.setEditable(false);
		
		Calendar cal;
		int day, month, year;
		
		cal = Calendar.getInstance ();
		
		day = cal.get (Calendar.DAY_OF_MONTH);
		month = cal.get (Calendar.MONTH) +1;
		year = cal.get (Calendar.YEAR);
		
		text.setText (day/10 + "" + day%10 + "/" + month/10 + "" + month%10 + "/" + year);
		text.setSelection (0);
		
		calendarShell = new Shell (parent.getDisplay (), SWT.ON_TOP);
		calendarShell.setLayout (new FillLayout ());
		
		dateTime = new DateTime (calendarShell, SWT.CALENDAR);
		
		Button button = new Button (this, SWT.NONE);
		button.addSelectionListener (new SelectionAdapter () {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				showCalendar (parent);
			}
		});
		
		GridData gd_button = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		
		gd_button.heightHint = text.getSize ().y;
		
		button.setLayoutData(gd_button);
		//button.setImage (new Image (parent.getDisplay (), ResourceLoader.loader("files/calendar.png")));		
		setTabList(new Control[]{text});
		
		dateTime.addFocusListener (new FocusListener () {

			@Override
			public void focusGained (FocusEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void focusLost (FocusEvent e) {
				// TODO Auto-generated method stub
				
				calendarShell.setVisible (false);
				//System.out.println ("Lost");
			}
			
		});
		
		dateTime.addSelectionListener (new SelectionListener () {

			@Override
			public void widgetSelected (SelectionEvent e) {
				// TODO Auto-generated method stub
				
				//widgetDefaultSelected (e);
			}

			@Override
			public void widgetDefaultSelected (SelectionEvent e) {
				// TODO Auto-generated method stub
				
				int day, month, year;
				
				day = dateTime.getDay ();
				month = dateTime.getMonth () +1;
				year = dateTime.getYear ();
				
				text.setText (day/10 + "" + day%10 + "/" + month/10 + "" + month%10 + "/" + year);
				calendarShell.setVisible (false);
			}
			
			
		});
		
		
		
		
		
		((Text) text).addModifyListener(new ModifyListener () {

			@Override
			public void modifyText(ModifyEvent e) {
				// TODO Auto-generated method stub
				
				if (text.isDateSet()) {
					
					dateTime.setDay(text.getDay());
					dateTime.setMonth(text.getMonth());
					dateTime.setYear(text.getYear());
					
					
					
					/*
					System.out.println (text.getDay());
					System.out.println (text.getMonth());
					System.out.println (text.getYear());
					
					System.out.println("Date is set");
					*/
				}
				
				//System.out.println("Lol");
			}
			
			
		});
		
	}
	
	private void showCalendar (Composite parent) {
		
		Rectangle textBounds;
		Point calendarSize;
		Rectangle textSize;
		
		calendarSize = dateTime.computeSize (SWT.DEFAULT, SWT.DEFAULT);
		textSize = text.getBounds ();
		
		textBounds = parent.getDisplay ().map (parent, null, getBounds ());
		calendarShell.setBounds ((textBounds.x+textSize.width/2)-calendarSize.x/2, textBounds.y + textBounds.height, calendarSize.x, calendarSize.y);
		
		calendarShell.setVisible (true);
		calendarShell.setFocus ();
	}
	
	public DateTime getDateTime () {
		
		return dateTime;
	}
	
	public Text getText () {
		
		return text;
	}

}
