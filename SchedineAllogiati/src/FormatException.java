/**
 * 
 * @author Enrico Testori
 *
 */

public class FormatException {
	
	private Exception exception;
	private int position;
	
	public FormatException(int pos, String tipo){
		position = pos;
		exception = new Exception("tipo");
	}
	
	public int getPosition(){
		return position;
	}
	
	public Exception getException(){
		return exception;
	}

}
