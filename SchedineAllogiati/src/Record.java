/**
 * @author Enrico Testori
 *
 */
public class Record {
	
	private Alloggiato allog;
	private Documento doc;
	private Permanenza perm;
	
	/**
	 * Record class constructor
	 * 
	 * @param tipoAlloggiato
	 * @param dataArrivo
	 * @param permanenza
	 * @param nome
	 * @param cognome
	 * @param dataNascita
	 * @param sesso
	 * @param cittadinanza
	 * @param statoNascita
	 * @param comuneNascita
	 * @param tipoDoc
	 * @param numeroDoc
	 * @param rilascioDoc
	 */
	public Record(String tipoAlloggiato, String dataArrivo, int permanenza, String nome, String cognome, String dataNascita, String sesso, String cittadinanza, String statoNascita, String comuneNascita, String tipoDoc, String numeroDoc, String rilascioDoc){
		
		perm = new Permanenza(tipoAlloggiato, dataArrivo, permanenza);
		allog = new Alloggiato(nome, cognome, dataNascita, sesso, cittadinanza, statoNascita, comuneNascita);
		doc = new Documento(tipoDoc, numeroDoc, rilascioDoc);
	
	}
	
	/*
	 * metodi ritorno campi alloggiato
	 */
	
	public String getNome(){
		return allog.nome;
	}
	
	public String getCognome(){
		return allog.cognome;
	}
	
	public String getSesso(){
		return allog.sesso;
	}
	
	public String getDataNascita(){
		return allog.dataNascita;
	}
	
	public String getCittadinanza(){
		return allog.cittadinanza;
	}
	
	public String getStatoNascita(){
		return allog.statoNascita;
	}
	
	public String getComuneNascita(){
		return allog.comuneNascita;
	}
	
	
	
	/*
	 * Metodi ritorno campi Permanenza
	 */
	
	public String getTipoAlloggiato(){
		return perm.tipoAlloggiato;
	}
	
	public String getDataArrivo(){
		return perm.dataArrivo;
	}
	
	public int getPermanenza(){
		return perm.permanenza;
	}
	
	
	
	/*
	 * Metodi ritorno campi Documento
	 */
	
	public String getTipoDocumento(){
		return doc.tipoDoc;
	}
	
	public String getNumeroDocumento(){
		return doc.numeroDoc;
	}
	
	public String getRilascioDocumento(){
		return doc.rilascioDoc;
	}
	

}
