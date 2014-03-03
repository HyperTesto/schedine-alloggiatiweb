import java.util.AbstractList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.layout.FillLayout;


public class SWTInterface extends Shell {
	private Table table;
	private Text text;
	private Text text_1;
	private Text text_3;
	private CompletedTextOld text_4;
	private Text text_5;
	private Text text_6;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			
			Display display = Display.getDefault();
			SWTInterface shell = new SWTInterface(display);
			shell.setMaximized(true);
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the shell.
	 * @param display
	 */
	public SWTInterface(Display display) {
		super(display, SWT.SHELL_TRIM);
		setLayout(new GridLayout(1, false));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Group grpGruppo = new Group(composite, SWT.NONE);
		grpGruppo.setLayout(new GridLayout(2, false));
		grpGruppo.setText("Informazioni alloggiato");
		grpGruppo.setBounds(0, 0, 68, 68);
		//grpGruppo.setSize(100, 100);
		
		Label lblCacca = new Label(grpGruppo, SWT.NONE);
		GridData gd_lblCacca = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblCacca.widthHint = 102;
		lblCacca.setLayoutData(gd_lblCacca);
		lblCacca.setText("Tipo alloggiato:");
		
		Combo combo = new Combo(grpGruppo, SWT.NONE);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		
		
		Label lblCacca_1 = new Label(grpGruppo, SWT.NONE);
		lblCacca_1.setText("Data di arrivo:");
		
		FormDateChooser formDateChooser_1 = new FormDateChooser(grpGruppo, SWT.NONE);
		GridData gd_formDateChooser_1 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_formDateChooser_1.widthHint = 50;
		formDateChooser_1.setLayoutData(gd_formDateChooser_1);
		//button.setText("5");
		
		
		
		Label lblCacca_2 = new Label(grpGruppo, SWT.NONE);
		lblCacca_2.setText("Permanenza:");
		
		Spinner spinner = new Spinner(grpGruppo, SWT.BORDER);
		spinner.setMinimum(1);
		spinner.setSelection(1);
		GridData gd_spinner = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_spinner.widthHint = 50;
		spinner.setLayoutData(gd_spinner);
		
		Group grpGruppo_1 = new Group(composite, SWT.NONE);
		grpGruppo_1.setLayout(new GridLayout(6, false));
		grpGruppo_1.setText("Dati personali");
		grpGruppo_1.setBounds(0, 0, 68, 68);
		
		Label lblCognome = new Label(grpGruppo_1, SWT.NONE);
		lblCognome.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblCognome.setText("Cognome:");
		
		text = new Text(grpGruppo_1, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblNome = new Label(grpGruppo_1, SWT.NONE);
		lblNome.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblNome.setText("Nome:");
		lblNome.setBounds(0, 0, 61, 15);
		
		text_1 = new Text(grpGruppo_1, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		text_1.setBounds(0, 0, 249, 25);
		
		Label lblSesso = new Label(grpGruppo_1, SWT.NONE);
		lblSesso.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblSesso.setText("Sesso:");
		
		Composite composite_3 = new Composite(grpGruppo_1, SWT.NONE);
		composite_3.setLayout(new GridLayout(1, false));
		composite_3.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 3));
		
		Label lblDataDiNascita = new Label(grpGruppo_1, SWT.NONE);
		lblDataDiNascita.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblDataDiNascita.setText("Data di nascita:");
		lblDataDiNascita.setBounds(0, 0, 61, 15);
		
		FormDateChooser formDateChooser = new FormDateChooser(grpGruppo_1, SWT.NONE);
		formDateChooser.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Label lblCittadinanza = new Label(grpGruppo_1, SWT.NONE);
		lblCittadinanza.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblCittadinanza.setText("Cittadinanza:");
		lblCittadinanza.setBounds(0, 0, 61, 15);
		
		text_3 = new Text(grpGruppo_1, SWT.BORDER);
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnRadioButton = new Button(grpGruppo_1, SWT.RADIO);
		btnRadioButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnRadioButton.setText("M");
		
		Label lblStatoDiNascita = new Label(grpGruppo_1, SWT.NONE);
		lblStatoDiNascita.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblStatoDiNascita.setText("Stato di nascita:");
		lblStatoDiNascita.setBounds(0, 0, 61, 15);
		
		text_4 = new CompletedTextOld(grpGruppo_1, SWT.BORDER);
		text_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		//text_4.setHints(ResourceLoader.succhiaComuni());
		text_4.setForcedHints (true);
		
		
		
		Label lblComuneDiNascita = new Label(grpGruppo_1, SWT.NONE);
		lblComuneDiNascita.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblComuneDiNascita.setText("Comune di nascita:");
		lblComuneDiNascita.setBounds(0, 0, 61, 15);
		
		text_5 = new Text(grpGruppo_1, SWT.BORDER);
		text_5.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnRadioButton_1 = new Button(grpGruppo_1, SWT.RADIO);
		btnRadioButton_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnRadioButton_1.setText("F");
		
		Composite composite_5 = new Composite(this, SWT.NONE);
		GridLayout gl_composite_5 = new GridLayout(2, false);
		gl_composite_5.marginHeight = 0;
		gl_composite_5.verticalSpacing = 0;
		gl_composite_5.marginWidth = 0;
		composite_5.setLayout(gl_composite_5);
		composite_5.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		
		Group grpGruppo_2 = new Group(composite_5, SWT.NONE);
		grpGruppo_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		grpGruppo_2.setLayout(new GridLayout(6, false));
		grpGruppo_2.setText("Documento");
		grpGruppo_2.setBounds(0, 0, 68, 68);
		
		Label lblTipoDiDocumento = new Label(grpGruppo_2, SWT.NONE);
		lblTipoDiDocumento.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTipoDiDocumento.setText("Tipo documento:");
		
		Combo combo_1 = new Combo(grpGruppo_2, SWT.NONE);
		GridData gd_combo_1 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_combo_1.widthHint = 104;
		combo_1.setLayoutData(gd_combo_1);
		
		Label lblNumeroDocumento = new Label(grpGruppo_2, SWT.NONE);
		lblNumeroDocumento.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNumeroDocumento.setText("Numero documento:");
		
		text_6 = new Text(grpGruppo_2, SWT.BORDER);
		text_6.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblLuogoRilascio = new Label(grpGruppo_2, SWT.NONE);
		lblLuogoRilascio.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblLuogoRilascio.setText("Luogo rilascio:");
		
		Combo combo_2 = new Combo(grpGruppo_2, SWT.NONE);
		GridData gd_combo_2 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_combo_2.widthHint = 142;
		combo_2.setLayoutData(gd_combo_2);
		
		Button btnSdf = new Button(composite_5, SWT.NONE);
		GridData gd_btnSdf = new GridData(SWT.FILL, SWT.BOTTOM, false, false, 1, 1);
		gd_btnSdf.heightHint = 45;
		btnSdf.setLayoutData(gd_btnSdf);
		btnSdf.setText("Aggiungi");
		
		Composite composite_1 = new Composite(this, SWT.NONE);
		GridLayout gl_composite_1 = new GridLayout(1, false);
		gl_composite_1.verticalSpacing = 0;
		gl_composite_1.marginWidth = 0;
		gl_composite_1.marginHeight = 0;
		gl_composite_1.horizontalSpacing = 0;
		composite_1.setLayout(gl_composite_1);
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		table = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(107);
		tblclmnNewColumn_1.setText("Tipo alloggiato");
		
		TableColumn tblclmnNewColumn_12 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_12.setWidth(100);
		tblclmnNewColumn_12.setText("Data di arrivo");
		
		TableColumn tblclmnNewColumn_11 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_11.setWidth(93);
		tblclmnNewColumn_11.setText("Permanenza");
		
		TableColumn tblclmnNewColumn_10 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_10.setWidth(100);
		tblclmnNewColumn_10.setText("Cognome");
		
		TableColumn tblclmnNewColumn_9 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_9.setWidth(100);
		tblclmnNewColumn_9.setText("Nome");
		
		TableColumn tblclmnNewColumn_8 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_8.setWidth(100);
		tblclmnNewColumn_8.setText("Sesso");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(100);
		tblclmnNewColumn_2.setText("Data di nascita");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_3.setWidth(100);
		tblclmnNewColumn_3.setText("Stato di nascita");
		
		TableColumn tblclmnNewColumn_4 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_4.setWidth(100);
		tblclmnNewColumn_4.setText("Comune di nascita");
		
		TableColumn tblclmnNewColumn_5 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_5.setWidth(100);
		tblclmnNewColumn_5.setText("Cittadinanza");
		
		TableColumn tblclmnNewColumn_6 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_6.setWidth(100);
		tblclmnNewColumn_6.setText("Tipo documento");
		
		TableColumn tblclmnNewColumn_7 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_7.setWidth(100);
		tblclmnNewColumn_7.setText("Numero documento");
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("Luogo di rilascio");
		
		Composite composite_2 = new Composite(this, SWT.NONE);
		GridLayout gl_composite_2 = new GridLayout(1, false);
		gl_composite_2.verticalSpacing = 0;
		gl_composite_2.marginWidth = 0;
		gl_composite_2.marginHeight = 0;
		gl_composite_2.horizontalSpacing = 0;
		composite_2.setLayout(gl_composite_2);
		composite_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblHaiInserito = new Label(composite_2, SWT.NONE);
		lblHaiInserito.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		lblHaiInserito.setBounds(0, 0, 62, 15);
		lblHaiInserito.setText("Hai inserito 2 record");
		
		Menu menu = new Menu(this, SWT.BAR);
		setMenuBar(menu);
		
		MenuItem mntmFile_1 = new MenuItem(menu, SWT.CASCADE);
		mntmFile_1.setText("File");
		
		Menu menu_1 = new Menu(mntmFile_1);
		mntmFile_1.setMenu(menu_1);
		
		MenuItem mntmImportaDaQuestura = new MenuItem(menu_1, SWT.NONE);
		mntmImportaDaQuestura.setText("Importa da questura");
		
		MenuItem mntmImportaDaCsv = new MenuItem(menu_1, SWT.NONE);
		mntmImportaDaCsv.setText("Importa da CSV");
		
		MenuItem menuItem = new MenuItem(menu_1, SWT.SEPARATOR);
		menuItem.setText("Esportazioni");
		
		MenuItem mntmEsportaPerQuestura = new MenuItem(menu_1, SWT.NONE);
		mntmEsportaPerQuestura.setText("Esporta per questura");
		
		MenuItem mntmEsportaComeCsv = new MenuItem(menu_1, SWT.NONE);
		mntmEsportaComeCsv.setText("Esporta come CSV");
		
		new MenuItem(menu_1, SWT.SEPARATOR);
		
		MenuItem mntmEsci = new MenuItem(menu_1, SWT.NONE);
		mntmEsci.setText("Esci");
		
		MenuItem mntmAiuto = new MenuItem(menu, SWT.CASCADE);
		mntmAiuto.setText("Aiuto");
		
		Menu menu_2 = new Menu(mntmAiuto);
		mntmAiuto.setMenu(menu_2);
		
		MenuItem mntmAbout = new MenuItem(menu_2, SWT.NONE);
		mntmAbout.setText("About");
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Generatore schede alloggiati");
		setSize(800, 600);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
