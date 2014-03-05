/**
 * 
 * @author Enrico Testori
 *
 */
public class Documento {
	
	/*
	 * Campi documento dell'alloggiato
	 */
	public String tipoDoc, numeroDoc, rilascioDoc;
	
	/**
	 * Costruttore della classe Documento che contiene i dati del documento
	 * dell'alloggiato.
	 * @param tipoDoc
	 * @param numeroDoc
	 * @param rilascioDoc
	 */
	public Documento(String tipoDoc, String numeroDoc, String rilascioDoc){
		this.tipoDoc=tipoDoc;
		this.numeroDoc=numeroDoc;
		this.rilascioDoc=rilascioDoc;
	}
}
