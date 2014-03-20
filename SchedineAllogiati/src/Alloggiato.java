/**
 * 
 * @author Enrico Testori
 *
 */
public class Alloggiato {
	/*
	 * Dati personali dell'alloggiato
	 */
	
	public static final String CAPO_FAMIGLIA="CAPOFAMIGLIA";
	public static final String CAPO_GRUPPO="CAPOGRUPPO";
	public static final String OSPITE_SINGOLO="OSPITE SINGOLO";
	public static final String MEMBRO_GRUPPO="MEMBRO GRUPPO";
	public static final String MEMBRO_FAMIGLIA="FAMILIARE";
	public String nome, cognome, dataNascita, sesso, cittadinanza, statoNascita, comuneNascita;
	
	/**
	 * Costruttore della classe Alloggiato che contiene dati personali dell'alloggiato stesso.
	 * @param nome
	 * @param cognome
	 * @param dataNascita
	 * @param sesso
	 * @param cittadinanza
	 * @param statoNascita
	 * @param comuneNascita
	 */
	public Alloggiato(String nome, String cognome, String dataNascita, String sesso, String cittadinanza, String statoNascita, String comuneNascita){
		this.nome=nome;
		this.cognome=cognome;
		this.dataNascita=dataNascita;
		this.sesso=sesso;
		this.cittadinanza=cittadinanza;
		this.statoNascita=statoNascita;
		this.comuneNascita=comuneNascita;
	}

}
