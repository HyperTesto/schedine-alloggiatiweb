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
		
		connection = DriverManager.getConnection("jdbc:sqlite:src/res/files/tabelle_questura.db");
		statement = connection.createStatement();
	    statement.setQueryTimeout(30);  // set timeout to 30 sec.
		
	}
	
	public void disconnect() throws SQLException{
		if(connection != null)
	          connection.close();
	}
	
	
	public String getComuneByName(String comune) throws SQLException{
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

	public String getStatoByName(String stato) throws SQLException{
		String query, result = null;
		query = "select * FROM codici_luoghi WHERE nome = \"" + stato +"\"";
		
		if (d) System.out.println(query);
		
		ResultSet rs = statement.executeQuery(query);
		 while(rs.next())
	      {
	        result = rs.getString("codice");
	      }
		return result;
		
	}
	
	/*
	 * Supplementare, ma va bene per la leggibilità
	 */
	public String getCittadinanzaByName(String cittadinanza) throws SQLException{
		return getStatoByName(cittadinanza);
	}
	
	public String getDocumentoByName(String documento) throws SQLException{
		String query, result = null;
		query = "select * FROM codici_documenti WHERE tipo = \"" + documento +"\"";
		
		if (d) System.out.println(query);
		
		ResultSet rs = statement.executeQuery(query);
		 while(rs.next())
	      {
	        result = rs.getString("codice");
	      }
		return result;
	}
	
	public String getLuogoRilascioByName(String rilascio) throws SQLException{
		String query, result = null;
		query = "select * FROM codici_luoghi WHERE nome = \"" + rilascio +"\"";
		
		if (d) System.out.println(query);
		
		ResultSet rs = statement.executeQuery(query);
		 while(rs.next())
	      {
	        result = rs.getString("codice");
	      }
		return result;
	}
	
	public String getAlloggiatoByName(String alloggiato) throws SQLException{
		String query, result = null;
		query = "select * from codici_alloggiati WHERE tipo = \"" + alloggiato +"\"";
		
		if (d) System.out.println(query);
		
		ResultSet rs = statement.executeQuery(query);
		 while(rs.next())
	      {
	        result = rs.getString("codice");
	      }
		return result;
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
			System.out.println(q.getComuneByName("FALCADE (BL)"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			//q.getCodiceComune("FALCADE (BL)");
			System.out.println(q.getStatoByName("ITALIA"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			//q.getCodiceComune("FALCADE (BL)");
			System.out.println(q.getCittadinanzaByName("ITALIA"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			//q.getCodiceComune("FALCADE (BL)");
			System.out.println(q.getDocumentoByName("CARTA DI IDENTITA\'"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		try {
			//q.getCodiceComune("FALCADE (BL)");
			System.out.println(q.getLuogoRilascioByName("BELLUNO (BL)"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			//q.getCodiceComune("FALCADE (BL)");
			System.out.println(q.getAlloggiatoByName(Alloggiato.OSPITE_SINGOLO));
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
