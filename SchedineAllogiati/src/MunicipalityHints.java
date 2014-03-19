import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class MunicipalityHints implements HintsManager {
	
	public MunicipalityHints () throws ClassNotFoundException {
		
		Class.forName ("org.sqlite.JDBC");
	}
	
	@Override
	public ArrayList<String> getHints (String typed) {
		// TODO Auto-generated method stub
		
		ArrayList<String> res;
		Connection connection;
		ResultSet rs;
		Statement statement;
		
		res = new ArrayList<String> ();
		connection = null;
		
		try {
			
			connection = DriverManager.getConnection ("jdbc:sqlite:src/res/files/tabelle_questura.db");
			
			statement = connection.createStatement ();
			statement.setQueryTimeout (30);
			
			rs = statement
					.executeQuery ("select nome from codici_luoghi where nome like \""
							+ typed + "%\" order by nome");
			
			while (rs.next ()) {
				
				res.add (rs.getString ("nome"));
			}
		
		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			
			System.out.println ("Errore nel db: " + e.getLocalizedMessage ());
			
		} finally {
			
			try {
				
				if (connection != null)
					connection.close ();
			
			} catch (SQLException e) {
				// connection close failed.
				System.err.println (e);
			}
		}

		return res;
	}
	
	public static void main (String[] args) throws InterruptedException, ClassNotFoundException {
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
