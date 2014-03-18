import java.util.List;

/**
 * 
 * @author Enrico Testori - Alberto Bonizzi
 *
 */
public interface HintsManager {
	
	/**
	 * Metodo che restituisce una lista di stringhe selezionate
	 * in base a quando specificato dal parametro typed
	 * @param typed
	 * @return List<String>
	 */
	public List<String> getHints(String typed);

}