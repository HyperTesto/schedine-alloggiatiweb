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
	 * @param cognome
	 * @param nome
	 * @param sesso
	 * @param dataNascita
	 * @param comuneNascita
	 * @param statoNascita
	 * @param cittadinanza
	 * @param tipoDoc
	 * @param numeroDoc
	 * @param rilascioDoc
	 */
	public Record(String tipoAlloggiato, String dataArrivo, int permanenza, String cognome, String nome, String sesso, String dataNascita, String comuneNascita, String statoNascita, String cittadinanza, String tipoDoc, String numeroDoc, String rilascioDoc){
		
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
		return allog.comuneNascita.substring(allog.comuneNascita.length()-3, allog.comuneNascita.length()-1);
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
		String s;
		s="TIPO ALLOGGIATO: " + perm.tipoAlloggiato + "\n";
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
	
	/**
	 * 
	 * Ritorna un array di String contenente tutti gli attributi
	 * dell'oggetto in ordine come specificato dalla documentazione
	 * del sito della questura
	 * 
	 * 
	 * @return L'array di String
	 */
	
    public String[] toStringArray () {
		
		String[] res;
		
		res = new String[13];
		
		
		res[0]	= this.getTipoAlloggiato ();
		
		//System.out.println ("Tipo: " + res[0]);
		res[1]	= this.getDataArrivo ();
		
		//System.out.println ("Arrivo: " + res[1]);
		res[2]	= String.valueOf (this.getPermanenza ());
		
		//System.out.println ("Permanenza: " + res[2]);
		res[3]	= this.getCognome ();
		
		//System.out.println ("Cognome: " + res[3]);
		res[4]	= this.getNome ();
		
		//System.out.println ("Nome: " + res[4]);
		res[5]	= this.getSesso ();
		
		//System.out.println ("Sesso: " + res[5]);
		res[6]	= this.getDataNascita ();
		
		//System.out.println ("Data nascita: " + res[6]);
		res[7]	= this.getComuneNascita ();
		
		//System.out.println ("Comune nascita: " + res[7]);
		res[8]	= this.getStatoNascita ();
		
		//System.out.println ("Stato nascita: " + res[8]);
		res[9]	= this.getCittadinanza ();
		
		//System.out.println ("Citatdinanza: " + res[9]);
		res[10]	= this.getTipoDocumento ();
		
		//System.out.println ("Tipo documento: " + res[10]);
		res[11]	= this.getNumeroDocumento ();
		
		//System.out.println ("Numero documento: " + res[11]);
		res[12]	= this.getRilascioDocumento ();
		
		//System.out.println ("Rilascio documento: " + res[12]);
		return res;
	}
	

}
