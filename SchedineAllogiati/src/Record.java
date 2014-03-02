/**
 * @author Enrico Testori
 *
 */
public class Record {
	
	public Alloggiato allog;
	public Documento doc;
	public Permanenza perm;
	
	public Record(String tipoAlloggiato, String dataArrivo, int permanenza, String nome, String cognome, String dataNascita, String sesso, String cittadinanza, String statoNascita, String comuneNascita, String tipoDoc, String numeroDoc, String rilascioDoc){
		
		perm = new Permanenza(tipoAlloggiato, dataArrivo, permanenza);
		allog = new Alloggiato(nome, cognome, dataNascita, sesso, cittadinanza, statoNascita, comuneNascita);
		doc = new Documento(tipoDoc, numeroDoc, rilascioDoc);
	
	}
	
	public String getNome(){
		return allog.nome;
	}
	
	public String getCognome(){
		return allog.cognome;
	}
	
	public String getSesso(){
		return allog.sesso;
	}

}
