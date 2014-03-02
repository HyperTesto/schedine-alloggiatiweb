import java.util.ArrayList;


public class DumbHints implements HintsManager {

	@Override
	public ArrayList<String> getHints (String typed) {
		// TODO Auto-generated method stub
		
		ArrayList<String> res;
		
		res = new ArrayList<String> ();
		
		res.add ("Black Sabbath");
		res.add ("Judas Priest");
		res.add ("Led Zeppelin");
		res.add ("Iron Maiden");
		
		return res;
	}

}
