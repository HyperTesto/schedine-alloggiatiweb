import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.StringTokenizer;

public class TestSqlite {

	public static void succhiaFile() {

		try {

			Class.forName ("org.sqlite.JDBC");
			Connection conn;
			Statement stat;
			BufferedReader br;
			StringTokenizer st;
			
			conn = DriverManager
					.getConnection ("jdbc:sqlite:/home/hypertesto/Scrivania/tabelle_questura.db");
			stat = conn.createStatement ();
			
			
			br = new BufferedReader (new FileReader (
					"/home/hypertesto/Scrivania/input2"));
			
			
			String line = br.readLine ();

			while (line != null) {
				
				st = new StringTokenizer (line, "\t");
				String a, b, query;
				
				a = st.nextToken ();
				b = st.nextToken ();
				
				query = "insert into codici_documenti (codice, tipo) VALUES (\"" + a + "\", \"" + b + "\")";
				
				//stat.executeUpdate ("create table people (name, occupation)");
				System.out.println (query);
				stat.executeUpdate (query);
				
				line = br.readLine ();
			}

			//rs.close ();
			br.close ();
			
		} catch (Exception e) {

			e.printStackTrace ();
		}

	}

	public static void main(String[] args) throws ClassNotFoundException,
			SQLException {
		/*
		Class.forName ("org.sqlite.JDBC");
		Connection conn = DriverManager
				.getConnection ("jdbc:sqlite:/home/hypertesto/Scrivania/tabelle_questura.db");

		Statement stat = conn.createStatement ();
		
		 stat.executeUpdate("create table people (name, occupation)");
		 stat.executeUpdate
		 ("insert into people (name, occupation) VALUES ('Gandhi', 'politics')"
		 ); stat.executeUpdate(
		 "insert into people (name, occupation) VALUES ('Turing', 'computers')"
		 ); stat.executeUpdate(
		 "insert into people (name, occupation) VALUES ('Wittgenstein', 'smartypants')"
		 );
		 
		ResultSet rs = stat
				.executeQuery ("select * from people where name like '%dhi'");
		while (rs.next ()) {
			System.out.println ("name = " + rs.getString ("name"));
			System.out.println ("occupation = " + rs.getString ("occupation"));
		}
		rs.close ();
		conn.close ();
		*/
		
		succhiaFile ();
	}
}