
public class Debug {
	
	private static boolean debug;

	public static void print (Object s){
		if(debug)	
			System.out.println("[DEBUG] " + s);
	}

}
