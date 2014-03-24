import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class MunicipalityHints implements HintsManager {
	
	Connection  connection;
	
	public MunicipalityHints () throws ClassNotFoundException {
		
		Class.forName ("org.sqlite.JDBC");
		
	}
	
	@Override
	public ArrayList<String> getHints (String typed) throws SQLException {
		// TODO Auto-generated method stub
		
		ArrayList<String> res;
		Connection connection;
		ResultSet rs;
		Statement statement;
		
		res = new ArrayList<String> ();
		connection = null;
		
		connection = DriverManager
				.getConnection ("jdbc:sqlite:src/res/files/tabelle_questura.db");
		
		statement = connection.createStatement ();
		statement.setQueryTimeout (30);
		
		rs = statement
				.executeQuery ("select nome from codici_luoghi where nome like \""
						+ typed + "%\" order by nome");
		
		while (rs.next ()) {
			
			res.add (rs.getString ("nome"));
		}
		
		if (connection != null)
			connection.close ();
		
		return res;
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
