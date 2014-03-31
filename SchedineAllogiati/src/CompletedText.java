import java.sql.SQLException;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public class CompletedText extends Text {

	private Table table;
	private List<String> hints;
	protected boolean forcedHints;
	private boolean allowTextChange;
	private boolean textSet;
	private HintsManager hintsManager;
	
	private static final boolean debug = false;
	
	public CompletedText (final Composite parent, int style, HintsManager hManager) {
		super (parent, style);

		final Shell popupShell, parentShell;
		final Text text = this;
		hintsManager = hManager;
		
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
		
		addVerifyListener (new VerifyListener () {

			@Override
			public void verifyText (VerifyEvent event) {
				// TODO Auto-generated method stub
				
				//System.out.println ("[debug] Verify");
				
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
					
							text = text.substring (0, text.length () - 1);
						}
						
						debug ("Text = " + text + "\n");
						
						
						debug ("Looking for hints...\n");
						try {
							
							hints = hintsManager.getHints (text);
						
						} catch (SQLException e) {
							// TODO Visualizzare all'utente l'eccezione
							e.printStackTrace();
						}
						
						if (hints.size () != 0) {
							
							debug ("There are hints for " + text + "\n");
							result = true;
						
						} else {
							
							debug ("No hints for " + text + "\n");
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
				
				//System.out.println ("[debug] Modify");
				
				TableItem temp;
				Rectangle textBounds;

				table.removeAll();
				
				if (getText ().length () == 0) {

					popupShell.setVisible (false);
				}	
				
				if (getText ().length () > 0 && (hints != null) && (hints.size () > 0)) {
					
					for (String hint : hints) {
						
						temp = new TableItem (table, SWT.NONE);
						temp.setText (hint);
					}
					
					textBounds = parent.getDisplay ().map (parent, null, getBounds ());
					popupShell.setBounds (textBounds.x, textBounds.y + textBounds.height, textBounds.width, 150);
					
					if (table.getItemCount () > 0) {
						
						popupShell.setVisible (true);
					
					} else {
						
						popupShell.setVisible (false);
					}
				}	
			}
		});
		
		table.addSelectionListener (new SelectionListener () {

			@Override
			public void widgetDefaultSelected (SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
				widgetSelected (arg0);
			}

			@Override
			public void widgetSelected (SelectionEvent arg0) {
				// TODO Auto-generated method stub
				allowTextChange = true;

				setText (table.getSelection ()[0].getText ());
				popupShell.setVisible (false);
				textSet = true;
			}
			
			
		});
		
		/*
		table.addListener (SWT.DefaultSelection, new Listener () {

			public void handleEvent (Event event) {
				
				allowTextChange = true;

				setText (table.getSelection ()[0].getText ());
				popupShell.setVisible (false);
				textSet = true;
			}
		});
		*/
		
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
					
				//System.out.println (table.getItemCount ());
				
				if (!textSet) {
					if (table.getItemCount () > 0) {
						
						allowTextChange = true;
						setText (table.getItem (0).getText ());
						
					} else {
						
						allowTextChange = true;
						setText ("");
						
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
	
	public boolean isForcedHints () {
		return forcedHints;
	}

	public void setForcedHints (boolean forcedHints) {
		this.forcedHints = forcedHints;
	}
	
	private static void debug (String m) {
		
		if (debug)
			System.out.print ("[debug] " + m);
	}
	
	public static void main (String args[]) throws ClassNotFoundException, SQLException {
		
		Display display;
		Shell shell;
		CompletedText text;
		
		display = new Display ();
		shell = new Shell (display);
		shell.setText ("CompletedText");
		shell.setSize (250, 64);
		
		shell.setLayout (new GridLayout (1, false));
		
		text = new CompletedText (shell, SWT.BORDER, new MunicipalityHints ());
		text.setForcedHints (true);
		text.setLayoutData (new GridData (SWT.FILL, SWT.CENTER, true, true, 1, 1));
		
		shell.open ();
		
		while (!shell.isDisposed ()) {

			if (!display.readAndDispatch ()) {
				display.sleep ();
			}
		}
	}
}
