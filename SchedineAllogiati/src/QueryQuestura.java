/**
 * 
 * @author Enrico Testori
 *
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class QueryQuestura {
	
	Connection connection;
	Statement statement;
	Boolean d = true;
	
	public QueryQuestura(){
		connection = null;
	}	
	
	
	public void connect() throws SQLException{
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		connection = DriverManager.getConnection("jdbc:sqlite:/home/hypertesto/git/SchedineAlloggiati/SchedineAllogiati/src/res/files/tabelle_questura.db");
		statement = connection.createStatement();
	    statement.setQueryTimeout(30);  // set timeout to 30 sec.
		
	}
	
	public void disconnect() throws SQLException{
		if(connection != null)
	          connection.close();
	}
	
	
	public String getCodiceComune(String comune) throws SQLException{
		String query, result = null;
		query = "select * FROM codici_luoghi WHERE nome = \"" + comune +"\"";
		
		if (d) System.out.println(query);
		
		ResultSet rs = statement.executeQuery(query);
		 while(rs.next())
	      {
	        result = rs.getString("codice");
	      }
		return result;
	}

	public String getCodiceStato(String stato){
		return "";
		
	}
	
	
	
	
	
	
	
	public static void main(String[] args) {
		QueryQuestura q = new QueryQuestura();
		try {
			q.connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			//q.getCodiceComune("FALCADE (BL)");
			System.out.println(q.getCodiceComune("FALCADE (BL)"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			q.disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
