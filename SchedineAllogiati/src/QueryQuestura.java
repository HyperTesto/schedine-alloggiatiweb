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
	
	public static void connect() throws SQLException{
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Connection connection = null;
		connection = DriverManager.getConnection("jdbc:sqlite:/home/hypertesto/git/SchedineAlloggiati/SchedineAllogiati/src/res/files/tabelle_questura.db");
	    Statement statement = connection.createStatement();
	    statement.setQueryTimeout(30);  // set timeout to 30 sec.
		
	}
	
	public void disconnect(){
		
	}

	public static void main(String[] args) {
		// load the sqlite-JDBC driver using the current class loader
	    try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	    Connection connection = null;
	    try
	    {
	      // create a database connection
	      connection = DriverManager.getConnection("jdbc:sqlite:/home/hypertesto/git/SchedineAlloggiati/SchedineAllogiati/src/res/files/tabelle_questura.db");
	      Statement statement = connection.createStatement();
	      statement.setQueryTimeout(30);  // set timeout to 30 sec.

	      ResultSet rs = statement.executeQuery("select * FROM codici_luoghi ORDER BY nome");
	      while(rs.next())
	      {
	        // read the result set
	        System.out.println("name = " + rs.getString("nome"));
	        System.out.println("id = " + rs.getInt("codice"));
	      }
	    }
	    catch(SQLException e)
	    {
	      // if the error message is "out of memory", 
	      // it probably means no database file is found
	      System.err.println(e.getMessage());
	    }
	    finally
	    {
	      try
	      {
	        if(connection != null)
	          connection.close();
	      }
	      catch(SQLException e)
	      {
	        // connection close failed.
	        System.err.println(e);
	      }
	    }
	}

}
