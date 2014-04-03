import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class MunicipalityHints implements HintsManager {
	
	Connection  connection;
	
	public MunicipalityHints () throws ClassNotFoundException, SQLException {
		
		Class.forName ("org.sqlite.JDBC");
		
		connection = DriverManager
				.getConnection ("jdbc:sqlite:tabelle_questura.db");
	}
	
	@Override
	public ArrayList<String> getHints (String typed) throws SQLException {
		// TODO Auto-generated method stub
		
		ArrayList<String> res;
		ResultSet rs;
		Statement statement;
		
		res = new ArrayList<String> ();
		
		statement = connection.createStatement ();
		statement.setQueryTimeout (30);
		
		rs = statement
				.executeQuery ("select nome from codici_luoghi where nome like \""
						+ typed + "%\" order by nome");
		
		while (rs.next ()) {
			
			res.add (rs.getString ("nome"));
		}
		
		return res;
	}
	
	@Override
	public void finalize () {
		
		try {
			
			connection.close ();
			
		} catch (Exception e) {
			// The object is going to be destroyed, no need to report errors
			
			// e.printStackTrace();
		}
	}
	
	public static void main (String[] args) throws InterruptedException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		MunicipalityHints h;
		
		h = new MunicipalityHints ();
		
		for (String s : h.getHints ("bell")) {
			
			System.out.println (s);
		}
		
		Thread.sleep (1000);
		
		for (String s : h.getHints ("z")) {
			
			System.out.println (s);
		}
	}
	
}
