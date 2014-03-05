/**
 * 
 * @author Enrico Testori
 *
 */
public class Permanenza {
	
	/*
	 * Campi prenotazione 
	 */
	public String tipoAlloggiato, dataArrivo;
	public int permanenza;
	
	/**
	 * Costruttore classe permanenza che contiene i dati di base della prenotazione.
	 * @param tipoAlloggiato
	 * @param dataArrivo
	 * @param permanenza
	 */
	public Permanenza(String tipoAlloggiato, String dataArrivo, int permanenza){
		this.tipoAlloggiato=tipoAlloggiato;
		this.dataArrivo=dataArrivo;
		this.permanenza=permanenza;
	}
	

}
