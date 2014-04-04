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

	/**
	 * Metodo per la connessione al DB
	 * @throws SQLException
	 */
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

	/**
	 * Metodo per la disconnessione dal DB
	 * @throws SQLException
	 */
	public void disconnect() throws SQLException{
		if(connection != null)
			connection.close();
	}

	/**
	 * Ritorna il codice del comune passato come parametro
	 * @param comune
	 * @return
	 * @throws SQLException
	 */
	public String getComuneByName(String comune) throws SQLException{
		String query, result = null;
		query = "select * FROM codici_luoghi WHERE nome = \"" + comune +"\"";

		Debug.print(query);

		ResultSet rs = statement.executeQuery(query);
		while(rs.next())
		{
			result = rs.getString("codice");
		}
		return result;
	}

	
	/**
	 * Ritorna il codice dello stato passato come parametro
	 * @param stato
	 * @return
	 * @throws SQLException
	 */
	public String getStatoByName(String stato) throws SQLException{
		String query, result = null;
		query = "select * FROM codici_luoghi WHERE nome = \"" + stato +"\"";

		Debug.print(query);

		ResultSet rs = statement.executeQuery(query);
		while(rs.next())
		{
			result = rs.getString("codice");
		}
		return result;

	}

	/**
	 * Ritorna il codice dello stato passato come parametro
	 * @param cittadinanza
	 * @return
	 * @throws SQLException
	 */
	public String getCittadinanzaByName(String cittadinanza) throws SQLException{
		return getStatoByName(cittadinanza);
	}

	public String getDocumentoByName(String documento) throws SQLException{
		String query, result = null;
		query = "select * FROM codici_documenti WHERE tipo = \"" + documento +"\"";

		Debug.print(query);

		ResultSet rs = statement.executeQuery(query);
		while(rs.next())
		{
			result = rs.getString("codice");
		}
		return result;
	}

	
	/**
	 * Ritorna il codice del luogo passato come parametro
	 * @param rilascio
	 * @return
	 * @throws SQLException
	 */
	public String getLuogoRilascioByName(String rilascio) throws SQLException{
		String query, result = null;
		query = "select * FROM codici_luoghi WHERE nome = \"" + rilascio +"\"";

		Debug.print(query);

		ResultSet rs = statement.executeQuery(query);
		while(rs.next())
		{
			result = rs.getString("codice");
		}
		return result;
	}

	
	/**
	 * Ritorna il codice alloggiato del tipo passato come parametro
	 * @param alloggiato
	 * @return
	 * @throws SQLException
	 */
	public String getAlloggiatoByName(String alloggiato) throws SQLException{
		String query, result = null;
		query = "select * from codici_alloggiati WHERE tipo = \"" + alloggiato +"\"";

		Debug.print(query);

		ResultSet rs = statement.executeQuery(query);
		while(rs.next())
		{
			result = rs.getString("codice");
		}
		return result;
	}

	/*
	 * Metodi per le query con codice in input
	 * 
	 */

	
	/**
	 * Ritorna il nome del comune in abse al codice passato come parametro
	 * @param comune
	 * @return
	 * @throws SQLException
	 */
	public String getComuneByCode(String comune) throws SQLException{
		String query, result = null;
		query = "select * FROM codici_luoghi WHERE codice = \"" + comune +"\"";

		Debug.print(query);

		ResultSet rs = statement.executeQuery(query);
		while(rs.next())
		{
			result = rs.getString("nome");
		}
		return result;
	}


	/**
	 * Ritorna il nome dello stato in base al codice passato come parametro
	 * @param comune
	 * @return
	 * @throws SQLException
	 */
	public String getStatoByCode(String comune) throws SQLException{
		String query, result = null;
		query = "select * FROM codici_luoghi WHERE codice = \"" + comune +"\"";

		Debug.print(query);

		ResultSet rs = statement.executeQuery(query);
		while(rs.next())
		{
			result = rs.getString("nome");
		}
		return result;
	}

	
	/**
	 * Ritorna il nome dello stato in base al codice passato come parametro
	 * @param cittadinanza
	 * @return
	 * @throws SQLException
	 */
	public String getCittadinanzaByCode(String cittadinanza) throws SQLException{
		return getStatoByCode(cittadinanza);
	}

	public String getDocumentoByCode(String documento) throws SQLException{
		String query, result = null;
		query = "select * FROM codici_documenti WHERE codice = \"" + documento +"\"";

		Debug.print(query);

		ResultSet rs = statement.executeQuery(query);
		while(rs.next())
		{
			result = rs.getString("tipo");
		}
		return result;
	}

	/**
	 * Ritorna il nome del luogo (comune o stato) in base al codice passato come parametro
	 * @param rilascio
	 * @return
	 * @throws SQLException
	 */
	public String getLuogoRilascioByCode(String rilascio) throws SQLException{
		String query, result = null;
		query = "select * FROM codici_luoghi WHERE codice = \"" + rilascio +"\"";

		Debug.print(query);

		ResultSet rs = statement.executeQuery(query);
		while(rs.next())
		{
			result = rs.getString("nome");
		}
		return result;
	}

	/**
	 * Ritorna il tipo alloggiato in base al codice passato come parametro
	 * @param alloggiato
	 * @return
	 * @throws SQLException
	 */
	public String getAlloggiatoByCode(String alloggiato) throws SQLException{
		String query, result = null;
		query = "select * from codici_alloggiati WHERE codice = \"" + alloggiato +"\"";

		Debug.print(query);

		ResultSet rs = statement.executeQuery(query);
		while(rs.next())
		{
			result = rs.getString("tipo");
		}
		return result;
	}
}
