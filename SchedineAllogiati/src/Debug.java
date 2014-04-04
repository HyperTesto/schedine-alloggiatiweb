/**
 * 
 * @author Enrico Testori
 *
 */
public class Debug {
	
	/**
	 * true = stampa messaggi di debug
	 * false = non stampa niente
	 */
	private static boolean debug = true;		
	
	/**
	 * Stampa il messaggio passato come parametro con la formattazione per il debug
	 * @param s
	 */
	public static void print (Object s){
		if(debug)	
			System.out.println("[DEBUG] " + s);
	}

}
