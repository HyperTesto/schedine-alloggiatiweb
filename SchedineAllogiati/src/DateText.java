import java.util.Calendar;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

/**
 * @author hypertesto
 *
 */
public class DateText extends Text {

	/**
	 * @param parent
	 * @param style
	 * 
	 * 
	 * costruttore
	 * 
	 */
	
	String dd, mm, yyyy;
	
	public DateText(Composite parent, int style) {
		super(parent, style);
		
		
		setText("gg/mm/aaaa");
		
		final Calendar calendar = Calendar.getInstance();
	    addListener(SWT.Verify, new Listener() {
	      boolean ignore;

	      public void handleEvent(Event e) {
	        if (ignore)
	          return;
	        e.doit = false;
	        StringBuffer buffer = new StringBuffer(e.text);
	        char[] chars = new char[buffer.length()];
	        buffer.getChars(0, chars.length, chars, 0);
	        if (e.character == '\b') {
	          for (int i = e.start; i < e.end; i++) {
	            switch (i) {
	            case 0: /* [Y]YYY */
	            case 1: /* Y[Y]YY */
	            //case 2: /* YY[Y]Y */
	            //case 3: /* YYY[Y] */
	            {
	              buffer.append('g');
	              break;
	            }
	            case 3: /* [M]M */
	            case 4: /* M[M] */{
	              buffer.append('m');
	              break;
	            }
	            case 6: /* [D]D */
	            case 7: /* D[D] */
	            case 8:
	            case 9:{
	              buffer.append('a');
	              break;
	            }
	            case 2: /* YYYY[/]MM */
	            case 5: /* MM[/]DD */{
	              buffer.append('/');
	              break;
	            }
	            default:
	              return;
	            }
	          }
	          setSelection(e.start, e.start + buffer.length());
	          ignore = true;
	          insert(buffer.toString());
	          ignore = false;
	          setSelection(e.start, e.start);
	          return;
	        }

	        int start = e.start;
	        if (start > 9)
	          return;
	        int index = 0;
	        for (int i = 0; i < chars.length; i++) {
	          if (start + index == 2 || start + index == 5) {
	            if (chars[i] == '/') {
	              index++;
	              continue;
	            }
	            buffer.insert(index++, '/');
	          }
	          if (chars[i] < '0' || '9' < chars[i])
	            return;
	          if (start + index == 3 && '1' < chars[i])
	            return; /* [M]M */
	          if (start + index == 0 && '3' < chars[i])
	            return; /* [D]D */
	          index++;
	        }
	        String newText = buffer.toString();
	        int length = newText.length();
	        StringBuffer date = new StringBuffer(getText());
	        date.replace(e.start, e.start + length, newText);
	        calendar.set(Calendar.YEAR, 2004);
	        calendar.set(Calendar.MONTH, Calendar.JANUARY);
	        calendar.set(Calendar.DATE, 1);
	        
	        //String yyyy = date.substring(6, 10);
	        yyyy = date.substring(6, 10);
	        
	        if (yyyy.indexOf('a') == -1) {
	          int year = Integer.parseInt(yyyy);
	          calendar.set(Calendar.YEAR, year);
	        }
	        //String mm = date.substring(3, 5);
	        mm = date.substring(3, 5);
	        
	        if (mm.indexOf('m') == -1) {
	          int month = Integer.parseInt(mm) - 1;
	          int maxMonth = calendar.getActualMaximum(Calendar.MONTH);
	          if (0 > month || month > maxMonth)
	            return;
	          calendar.set(Calendar.MONTH, month);
	        }
	        //String dd = date.substring(0, 2);
	        
	        dd = date.substring(0, 2);
	        
	        if (dd.indexOf('g') == -1) {
	          int day = Integer.parseInt(dd);
	          int maxDay = calendar.getActualMaximum(Calendar.DATE);
	          if (1 > day || day > maxDay)
	            return;
	          calendar.set(Calendar.DATE, day);
	        } else {
	          if (calendar.get(Calendar.MONTH) == Calendar.FEBRUARY) {
	            char firstChar = date.charAt(8);
	            if (firstChar != 'D' && '2' < firstChar)
	              return;
	          }
	        }
	        setSelection(e.start, e.start + length);
	        ignore = true;
	        insert(newText);
	        ignore = false;
	      }
	    });
	    
	    addModifyListener(new ModifyListener () {

			@Override
			public void modifyText(ModifyEvent e) {
				// TODO Auto-generated method stub
				
			}
	    	
	    	
	    });
	}
	
	/**
	 * 
	 * This method executes a parse of the text inside the Text widget. If it is composed only of numbers
	 * and '/' returs true, false otherwise
	 *  
	 * 
	 * @return Returns true if the date is set on the widget
	 */
	
	public boolean isDateSet () {
		
		String text;
		
		text = getText ();
		
		for (int i=0; i<text.length(); i++) {
			
			char c;
			
			c = text.charAt(i);
			
			if (!(Character.isDigit(c) || c == '/'))
				return false;
		}
		
		return true;
	}
	
	public int getDay () {
		
		if (!isDateSet ())
			return -1;
		else
			return Integer.parseInt(dd);
	}
	
	public int getMonth () {
		
		if (!isDateSet ())
			return -1;
		else
			return Integer.parseInt(mm) -1;
	}

	public int getYear () {

		if (!isDateSet())
			return -1;
		else
			return Integer.parseInt(yyyy);
	}
	
	protected void checkSubclass () {};
}
