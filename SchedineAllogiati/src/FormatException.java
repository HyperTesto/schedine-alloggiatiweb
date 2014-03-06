/**
 * 
 * @author Enrico Testori
 *
 */

public class FormatException extends Exception{
	
	/*
	 * Variabili statiche per i messaggi di errore.
	 */
	public static String MISSING_CAPO_FAMIGLIA = "Capo famiglia mancante";
	public static String MISSING_CAPO_GRUPPO = "Capo gruppo mancante";
	public static String MISSING_MEMBRO_GRUPPO = "Membri gruppo mancanti";
	public static String MISSING_MEMBRO_FAMIGLIA = "Membri famiglia mancanti";
	public static String INVALID_RECORD = "Questo record sembra non essere formattato correttamente";
	public static String PERMANENZA_TOO_LONG = "Il campo permanenza deve essere minore di 30 giorni";
	public static String REMOTE_DATA_ARRIVO = "La data di arrivo deve essere compresa nelle ultime 48 ore";
	
	private Exception exception;
	private int position;
	
	public FormatException(int pos, String tipo){
		
		super(tipo);
		position = pos;
		
	}
	
	public int getPosition(){
		return position;
	}
	
	public Exception getException(){
		return this.getException();
	}

}
