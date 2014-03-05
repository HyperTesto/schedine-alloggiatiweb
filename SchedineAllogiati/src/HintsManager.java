import java.util.ArrayList;

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
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getHints(String typed);

}