import java.io.FileInputStream;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.*;

/**
 * @author hypertesto
 *
 */
public class tabelleCodici {
	public  ArrayList<String> vettoreAlloggiato;
	public  ArrayList<String> vettoreCodiceAlloggiato;
	public  ArrayList<String> vettoreStato;
	public  ArrayList<String> vettoreCodiceStato;
	public  ArrayList<String> vettoreComune;
	public  ArrayList<String> vettoreCodiceComune;
	public  ArrayList<String> vettoreComuneStato;
	public  ArrayList<String> vettoreCodiceComuneStato;
	public  ArrayList<String> vettoreDocumento;
	public  ArrayList<String> vettoreCodiceDocumento;

	
	public tabelleCodici(){
		//vettori codice alloggiato
	vettoreAlloggiato = new ArrayList<String>(Arrays.asList("OSPITE SINGOLO","CAPOFAMIGLIA","CAPOGRUPPO","FAMILIARE","MEMBRO GRUPPO"));
	vettoreCodiceAlloggiato = new ArrayList<String>(Arrays.asList("16","17","18","19","20"));

	//vettori stato
	vettoreStato = new ArrayList<String>();
	try{
		 
		
		  
		  InputStream in = ResourceLoader.loader("files/nazioni.txt");
		  BufferedReader br = new BufferedReader(new InputStreamReader(in));
		  String strLine;
		  //Read File Line By Line
		  while ((strLine = br.readLine()) != null)   {
			  vettoreStato.add(strLine);
		  }
		  //Close the input stream
		  in.close();
		    }catch (Exception e){//Catch exception if any
		  System.err.println("Error: " + e.getMessage());
		  }
	vettoreCodiceStato =new ArrayList<String>();
	try{
		  
		  InputStream in = ResourceLoader.loader("files/codNazioni.txt");
		  BufferedReader br = new BufferedReader(new InputStreamReader(in));
		  String strLine;
		  //Read File Line By Line
		  while ((strLine = br.readLine()) != null)   {
			  vettoreCodiceStato.add(strLine);
		  }
		  //Close the input stream
		  in.close();
		    }catch (Exception e){//Catch exception if any
		  System.err.println("Error: " + e.getMessage());
		  }
	
	//vettori comune
	vettoreComune = new ArrayList<String>();
	try{
		  InputStream in = ResourceLoader.loader("files/comuni.txt");
		  BufferedReader br = new BufferedReader(new InputStreamReader(in));
		  String strLine;
		  //Read File Line By Line
		  while ((strLine = br.readLine()) != null)   {
			  vettoreComune.add(strLine);
		  }
		  //Close the input stream
		  in.close();
		    }catch (Exception e){//Catch exception if any
		  System.err.println("Error: " + e.getMessage());
		  }
	
	vettoreCodiceComune = new ArrayList<String>();
	try{
		  
		  InputStream in = ResourceLoader.loader("files/codComuni.txt");
		  BufferedReader br = new BufferedReader(new InputStreamReader(in));
		  String strLine;
		  //Read File Line By Line
		  while ((strLine = br.readLine()) != null)   {
			  vettoreCodiceComune.add(strLine);
		  }
		  //Close the input stream
		  in.close();
		    }catch (Exception e){//Catch exception if any
		  System.err.println("Error: " + e.getMessage());
		  }
	
	
	//vettori misti
	vettoreComuneStato = new ArrayList<String>();
	try{
		  InputStream in = ResourceLoader.loader("files/comuniNazioni.txt");
		  BufferedReader br = new BufferedReader(new InputStreamReader(in));
		  String strLine;
		  //Read File Line By Line
		  while ((strLine = br.readLine()) != null)   {
			  vettoreComuneStato.add(strLine);
		  }
		  //Close the input stream
		  in.close();
		    }catch (Exception e){//Catch exception if any
		  System.err.println("Error: " + e.getMessage());
		  }
	
	vettoreCodiceComuneStato=new ArrayList<String>();
	try{
		  InputStream in = ResourceLoader.loader("files/codComuniNazioni.txt");
		  BufferedReader br = new BufferedReader(new InputStreamReader(in));
		  String strLine;
		  //Read File Line By Line
		  while ((strLine = br.readLine()) != null)   {
			  vettoreCodiceComuneStato.add(strLine);
		  }
		  //Close the input stream
		  in.close();
		    }catch (Exception e){//Catch exception if any
		  System.err.println("Error: " + e.getMessage());
		  }
	
	vettoreDocumento=new ArrayList<String>();
	try{
		  InputStream in = ResourceLoader.loader("files/documenti.txt");
		  BufferedReader br = new BufferedReader(new InputStreamReader(in));
		  String strLine;
		  //Read File Line By Line
		  while ((strLine = br.readLine()) != null)   {
			  vettoreDocumento.add(strLine);
		  }
		  //Close the input stream
		  in.close();
		    }catch (Exception e){//Catch exception if any
		  System.err.println("Error: " + e.getMessage());
		  }
	
	vettoreCodiceDocumento=new ArrayList<String>();
	try{
		  InputStream in = ResourceLoader.loader("files/codDocumenti.txt");
		  BufferedReader br = new BufferedReader(new InputStreamReader(in));
		  String strLine;
		  //Read File Line By Line
		  while ((strLine = br.readLine()) != null)   {
			  vettoreCodiceDocumento.add(strLine);
		  }
		  //Close the input stream
		  in.close();
		    }catch (Exception e){//Catch exception if any
		  System.err.println("Error: " + e.getMessage());
		  }
	
	
	}
	
	

	public String cercaCodAlloggiato(String ricerca){
		
		return vettoreCodiceAlloggiato.get(vettoreAlloggiato.indexOf(ricerca));
	}
	
	public String cercaCodComune(String ricerca){
		return vettoreCodiceComune.get(vettoreComune.indexOf(ricerca));
	}
	
	public String cercaCodStato(String ricerca){
		return vettoreCodiceStato.get(vettoreStato.indexOf(ricerca));
	}
	
	public String cercaCodMisti(String ricerca){
		return vettoreCodiceComuneStato.get(vettoreComuneStato.indexOf(ricerca));
	}
	
	public String cercaPorovincia(String ricerca){
		return ricerca.substring(ricerca.length()-3, ricerca.length()-1);
	}
	public String cercaCodDocumento(String ricerca){
		return vettoreCodiceDocumento.get(vettoreDocumento.indexOf(ricerca));
	}
	
	
}
