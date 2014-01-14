import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;


public class Test {
	
	public static int steps;
	
	private static void bubbleSort (ArrayList<String> array) {
		
		boolean scambio;
		
		scambio = true;
		
		while (scambio) {
			
			scambio = false;
			
			for (int i=0; i<array.size ()-1; i++) {
				
				if (array.get(i).compareTo(array.get(i+1)) > 0) {
					
					String aux;
					
					aux = array.get(i);
					array.set(i, array.get(i+1));
					array.set (i+1, aux);
					
					scambio = true;
				}
			}
		}
	}
	
	public static int categoryBeginningIndex (ArrayList<String> array, String string) throws InterruptedException {
		
		/*
		if (string.charAt(0) == 'a') {
			if (array.get(0).toUpperCase().charAt(0) == 'A')
				return 0;
		}
		*/
		
		int lenght, currentIndex, previousIndex, aux, increment;
		boolean found;
		final int STEP = 30;
		
		lenght = array.size();
		found = false;
		currentIndex = lenght/2;
		previousIndex = 0;
		steps = 0;
		
		System.out.println ("Indice iniziale: " + currentIndex);
		
		while (!found && currentIndex >= 0 && currentIndex < array.size()) {
			
			steps++;
			
			int comparison;
			
			System.out.println("Sono in posizione: " + currentIndex);
			System.out.println ("Analizzo " + array.get(currentIndex));
			
			comparison = string.toUpperCase().compareTo(array.get(currentIndex).toUpperCase());
			aux = currentIndex;
			increment = Math.abs(currentIndex-previousIndex)/2;
			//comparison = string.toUpperCase().charAt(0) - array.get(currentIndex).toUpperCase().charAt(0);
			
			
			
			//Thread.sleep(1000);
			
			/*
			if ((string.toUpperCase().charAt(0) != array.get(currentIndex).toUpperCase().charAt(0)) &&
					(array.get(currentIndex).toUpperCase().charAt(0) != array.get(currentIndex+1).toUpperCase().charAt(0)) &&
					(string.toUpperCase().charAt(0) != array.get(currentIndex+1).toUpperCase().charAt(0))) {
				
				return -1;
			}
			*/
			
			
			
			if (increment == 1) {
				// System.out.println("AAAAAA");
				if (!array.get(currentIndex).toUpperCase()
						.startsWith(string.toUpperCase())) {
					// System.out.println("bbbbb");
					if (!array.get(currentIndex + 1).toUpperCase()
							.startsWith(string.toUpperCase())) {
						// System.out.println("CCCCC");
						if ((string.toUpperCase().compareTo(
								array.get(currentIndex + 1).toUpperCase()) < 0)
								&& (string.toUpperCase().compareTo(
										array.get(currentIndex).toUpperCase()) > 0)) {

							return -1;
						}
					}
				}
			}
			
			
			
			if (array.get(currentIndex).toUpperCase().startsWith(string.toUpperCase())) {
				
				found = true;
			
			} else if (comparison < 0) {
				
				System.out.println ("Decremento");
				currentIndex -= increment == 0 ? 1 : increment;
				previousIndex = aux;
			
			} else if (comparison > 0) {
				
				System.out.println ("Incremento");
				currentIndex += increment == 0 ? 1 : increment;
				previousIndex = aux;
			
			} 
		}
		
		System.out.println("currentIndex: " + currentIndex + "; array.size (): " + array.size());
		
		if (currentIndex < 0 || currentIndex >= array.size())
			return -1;
		
		for (int i=currentIndex; array.get(currentIndex).toUpperCase().startsWith(string.toUpperCase()); i-= STEP) {
			
			steps++;
			
			currentIndex = i;
			System.out.println ("Torno indietro fino a " + array.get(currentIndex));
			
			if (i - STEP < 0) {
				
				currentIndex = 0;
				break;
			}
		}
		
		
		for (int i=currentIndex; string.toUpperCase().compareTo(array.get(currentIndex).toUpperCase()) > 0; i++) {
			
			steps++;
			
			//Thread.sleep(500);
			
			currentIndex = i;
			System.out.println ("Avanzo fino a " + array.get(currentIndex));
		}
		
		System.out.println("Steps = " + steps);
		
		return currentIndex;
	}
	
	public static void main(String[] args) throws InterruptedException {
		/* 
		Display display = new Display();
		    Shell shell = new Shell(display);
		    shell.setLayout(new GridLayout());
		    final FormDateChooser text = new FormDateChooser(shell, SWT.BORDER);
		    
		    
		   
		    
		    shell.pack();
		    shell.open();
		    while (!shell.isDisposed()) {
		      if (!display.readAndDispatch())
		        display.sleep();
		    }
		    display.dispose();
		    */
		
		/*
		ArrayList<String> vettore = new tabelleCodici().vettoreComune;
		
		bubbleSort (vettore);
		
		FileWriter fstream;
		BufferedWriter out = null;
		try {
			
			fstream = new FileWriter("/home/hypertesto/COMUNI-ORDINATI.txt");
			out = new BufferedWriter(fstream);
			for (int i=0; i<vettore.size();i++){
				out.write(vettore.get(i)+"\n");
			}
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		*/
		
		ArrayList<String> array;
		
		array = ResourceLoader.succhiaComuni();
		/*
		int beginChar, endChar, averange;
		
		beginChar = (int) 'a';
		endChar = (int) 'z';
		averange = 0;
		
		//System.out.println((int) 'a');
		
		for (int i=beginChar; i<=endChar; i++) {
			
			int r;
			
			r = categoryBeginningIndex (array, Character.toString((char) i));
			System.out.println (((char) i) + " --> " + steps + ", ritorno = " + r);
			averange += steps;
		}
		
		System.out.println("Averange: " + ((double) averange/26));
		
		*/
		
		System.out.println("Elemento a: " + categoryBeginningIndex (array, "zzzzzzzz"));
		
		
	}
	
	private void ammazzaThread (Thread t) {
		
		t.getName();
	}
	
	private void lol () {
		
		ammazzaThread (new Cacca ());
	}
	
	public class Cacca extends Thread {
		
		public Cacca () {
			
			super ();
			
		}
	}
}
