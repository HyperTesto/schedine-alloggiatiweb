import java.util.AbstractList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public class CompletedText extends Text {

	private Table table;
	private AbstractList<String> hints;
	private int verifiedIndex = -1;
	protected boolean forcedHints;
	private boolean allowTextChange;
	private boolean textSet;
	
	public CompletedText (final Composite parent, int style) {
		super (parent, style);

		final Shell popupShell, parentShell;
		final Text text = this;

		textSet = false;
		forcedHints = false;
		allowTextChange = false;
		
		popupShell = new Shell (parent.getDisplay (), SWT.ON_TOP);
		popupShell.setLayout (new FillLayout ());

		parentShell = parent.getShell ();

		table = new Table (popupShell, SWT.SINGLE);

		addListener (SWT.KeyDown, new Listener () {

			public void handleEvent (Event event) {

				switch (event.keyCode) {

					case SWT.ARROW_DOWN:

						int index = (table.getSelectionIndex () + 1)
						% table.getItemCount ();

						table.setSelection (index);
						event.doit = false;

						break;

					case SWT.ARROW_UP:

						index = table.getSelectionIndex () - 1;

						if (index < 0)
							index = table.getItemCount () - 1;

						table.setSelection (index);
						event.doit = false;

						break;
					
					
					case SWT.CR: case SWT.KEYPAD_CR:

						if (popupShell.isVisible ()
								&& table.getSelectionIndex () != -1) {

							setText (table.getSelection ()[0].getText ());
							setSelection (getText ().length ());
							
							popupShell.setVisible (false);

						} else if (popupShell.isVisible ()
								&& table.getSelectionIndex () == -1
								&& table.getItemCount () != 0) {

							setText (table.getItem (0).getText ());
							setSelection (getText ().length ());
							
							popupShell.setVisible (false);
						}
						
						textSet = true;
						
						break;

					case SWT.ESC:

						popupShell.setVisible (false);

						break;
				}
			}
		});
		
		addListener (SWT.Verify, new Listener () {

			@Override
			public void handleEvent (Event event) {
				
				if (allowTextChange) {
					
					allowTextChange = false;
				
				} else if (forcedHints) {

					String text;
					boolean result;
					
					result = false;
					
					if (event.keyCode == SWT.CR) {
						
						result = true;
						
					} else {
						
						text = getText () + event.text;
						
						if (event.keyCode == SWT.BS) {
					
							text = text.substring(0, text.length()-1);
						}
						
						System.out.println(text);
						
						verifiedIndex = categoryBeginningIndex (hints, text);
						
						System.out.println("Index = " + verifiedIndex);
						
						if (verifiedIndex >= 0) {
							
							result = true;
						}
						
						if (event.keyCode == SWT.BS || event.keyCode == SWT.DEL) {
							
							result = true;
						}
					}
					
					event.doit = result;
				}
				
				
			}
			
			
		});
		
		addListener (SWT.Modify, new Listener () {

			public void handleEvent (Event event) {
				
				if (hints == null || hints.size () == 0)
					return;

				TableItem temp;
				Rectangle textBounds;
				int i;

				table.removeAll();
				
				if (getText ().length() == 0) {

					popupShell.setVisible(false);
				}
				
				if (getText ().length() > 0) {
					
					if (forcedHints)
						i = verifiedIndex;
					else
						i = categoryBeginningIndex (hints, getText ());
					
					if (i < 0) {
						
						return;
					}
					
					while (i < hints.size () && hints.get(i).toUpperCase ().startsWith (getText ().toUpperCase ())) {
						
						temp = new TableItem (table, SWT.NONE);
						temp.setText (hints.get (i));
						
						i++;
					}
					
					textBounds = parent.getDisplay ().map (parent, null, getBounds ());
					popupShell.setBounds (textBounds.x, textBounds.y + textBounds.height, textBounds.width, 150);
					
					if (table.getItemCount () > 0) {
						
						popupShell.setVisible (true);
						//table.setSelection (0);
					
					} else {
						
						popupShell.setVisible (false);
					}
				}	
			}
		});

		table.addListener (SWT.DefaultSelection, new Listener () {

			public void handleEvent (Event event) {
				
				allowTextChange = true;
				//System.out.println (table.getSelectionIndex ());

				setText (table.getSelection ()[0].getText ());
				popupShell.setVisible (false);
				textSet = true;
			}
		});

		table.addListener (SWT.KeyDown, new Listener () {

			public void handleEvent (Event event) {

				if (event.keyCode == SWT.ESC) {
					popupShell.setVisible (false);
				}
			}
		});

		Listener focusOutListener = new Listener () {
			public void handleEvent (Event event) {

				parent.getDisplay ().asyncExec (new Runnable () {

					public void run () {

						Control control;

						if (parent.getDisplay ().isDisposed ())
							return;

						control = parent.getDisplay ().getFocusControl ();

						if (control == null || (control != text && control != table)) {

							popupShell.setVisible (false);
						}
					}
				});
			}
		};
		
		addListener (SWT.FocusOut, new Listener () {

			@Override
			public void handleEvent(Event event) {
					
				System.out.println(table.getItemCount());
				
				if (!textSet) {
					if (table.getItemCount() > 0) {

						allowTextChange = true;
						setText(table.getItem(0).getText());

					} else {

						allowTextChange = true;
						setText("");

					}
				}
				
				textSet = false;
			}
			
		});
		
		table.addListener (SWT.FocusOut, focusOutListener);
		addListener (SWT.FocusOut, focusOutListener);

		parentShell.addListener (SWT.Move, new Listener () {

			public void handleEvent (Event event) {

				popupShell.setVisible (false);
			}
		});
	}

	@Override
	protected void checkSubclass () {

	}

	public Table getTable () {
		return table;
	}

	public void setTable (Table table) {
		this.table = table;
	}

	public AbstractList<String> getHints () {
		return hints;
	}

	public void setHints (AbstractList<String> hints) {
		this.hints = hints;

		if (hints == null)
			return;
	}
	
	public boolean isForcedHints () {
		return forcedHints;
	}

	public void setForcedHints (boolean forcedHints) {
		this.forcedHints = forcedHints;
	}

	public static int categoryBeginningIndex (AbstractList<String> array, String string) {
		
		int lenght, currentIndex, previousIndex, aux, increment;
		boolean found;
		final int STEP = 30;
		
		lenght = array.size();
		found = false;
		currentIndex = lenght/2;
		previousIndex = 0;
		
		while (!found && currentIndex >= 0 && currentIndex < array.size()) {
			
			int comparison;
			
			comparison = string.toUpperCase().compareTo(array.get(currentIndex).toUpperCase());
			aux = currentIndex;
			increment = Math.abs(currentIndex-previousIndex)/2;
			
			if (increment == 0)
				increment++;
			
			if (increment == 1) {
				if (!array.get(currentIndex).toUpperCase()
						.startsWith(string.toUpperCase())) {

					if (!array.get(currentIndex + 1).toUpperCase()
							.startsWith(string.toUpperCase())) {

						if ((string.toUpperCase().compareTo(
								array.get(currentIndex + 1).toUpperCase()) < 0)
								&& (string.toUpperCase().compareTo(
										array.get(currentIndex).toUpperCase()) > 0)) {

							return -1;
						}
					}
				}
			}
			
			if (array.get(currentIndex).toUpperCase().startsWith(string.toUpperCase())) {
				
				found = true;
			
			} else if (comparison < 0) {
				
				currentIndex -= increment;
				previousIndex = aux;
			
			} else if (comparison > 0) {
				
				currentIndex += increment;
				previousIndex = aux;
			
			} 
		}
		
		if (currentIndex < 0 || currentIndex >= array.size())
			return -1;
		
		for (int i=currentIndex; array.get(currentIndex).toUpperCase().startsWith(string.toUpperCase()); i-= STEP) {
			
			currentIndex = i;
			
			if (i - STEP <= 0) {
				
				currentIndex = 0;
				break;
			}
		}
		
		
		for (int i=currentIndex; string.toUpperCase().compareTo(array.get(currentIndex).toUpperCase()) > 0; i++) {
			
			currentIndex = i;
		}
		
		return currentIndex;
	}

}
