/**
 * @author Enrico Testori
 *
 */
public class Record {
	
	private Alloggiato allog;
	private Documento doc;
	private Permanenza perm;
	
	/**
	 * Costruttore della classe Record (che aggrega Alloggiato, Permanenza e Documento)
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
	
	/**
	 * 
	 * @return nome alloggiato
	 */
	public String getNome(){
		return allog.nome;
	}
	
	/**
	 * 
	 * @return cognome alloggiato
	 */
	public String getCognome(){
		return allog.cognome;
	}
	
	/**
	 * 
	 * @return sesso alloggiato
	 */
	public String getSesso(){
		return allog.sesso;
	}
	
	/**
	 * 
	 * @return data di nascita
	 */
	public String getDataNascita(){
		return allog.dataNascita;
	}
	
	/**
	 * 
	 * @return cittadinanza
	 */
	public String getCittadinanza(){
		return allog.cittadinanza;
	}
	
	
	/**
	 * 
	 * @return stato di nascita
	 */
	public String getStatoNascita(){
		return allog.statoNascita;
	}
	
	/**
	 * 
	 * @return comune di nascia (vuoto se nato all'estero)
	 */
	public String getComuneNascita(){
		return allog.comuneNascita;
	}
	
	/**
	 * 
	 * @return 2 lettere della provincia (di supporto)
	 */
	public String getProvinciaNascita(){
		
		String comune_provincia = allog.comuneNascita;
		
		return comune_provincia.substring(comune_provincia.length()-3, comune_provincia.length()-1);
	}
	
	/*
	 * Metodi ritorno campi Permanenza
	 */
	
	
	/**
	 * 
	 * @return tipo di alloggiato
	 */
	public String getTipoAlloggiato(){
		return perm.tipoAlloggiato;
	}
	
	/**
	 * 
	 * @return data di arrivo
	 */
	public String getDataArrivo(){
		return perm.dataArrivo;
	}
	
	/**
	 * 
	 * @return permanenza
	 */
	public int getPermanenza(){
		return perm.permanenza;
	}
	
	
	
	/*
	 * Metodi ritorno campi Documento
	 */
	
	/**
	 * 
	 * @return tipo del documento
	 */
	public String getTipoDocumento(){
		return doc.tipoDoc;
	}
	
	/**
	 * 
	 * @return numero (codice) documento
	 */
	public String getNumeroDocumento(){
		return doc.numeroDoc;
	}
	
	/**
	 * 
	 * @return luogo di rilascio
	 */
	public String getRilascioDocumento(){
		return doc.rilascioDoc;
	}
	
	/**
	 *  @override
	 */
	public String toString(){
		String s = null;
		s+="TIPO ALLOGGIATO: " + perm.tipoAlloggiato + "\n";
		s+="DATA DI ARRIVO: " + perm.dataArrivo + "\n";
		s+="PERMANENZA: " + perm.permanenza + "\n";
		s+="NOME: " + allog.nome + "\n";
		s+="COGNOME: " + allog.cognome + "\n";
		s+="SESSO: " + allog.sesso + "\n";
		s+="DATA DI NASCITA: " + allog.dataNascita + "\n";
		s+="COMUNE DI NASCITA: " + allog.comuneNascita + "\n";
		s+="STATO DI NASCITA: " + allog.statoNascita + "\n";
		s+="CITTADINANZA: " + allog.cittadinanza + "\n";
		s+="TIPO DOCUMENTO: " + doc.tipoDoc + "\n";
		s+="NUMERO DOCUMENTO: " + doc.numeroDoc + "\n";
		s+="RILASCIO: " + doc.rilascioDoc + "\n";
		return s; 
		
	}
	

}
