package blatt6;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Rahmen zur Aufgabe 19.
 * Verwendet ein (privates) Visualizer-Objekt, um die
 * Animation der Sortieralgorithmen darzustellen.
 * 
 * @author    Daniel Ehlke, edited by Aaron Scherzinger
 * 
 * @see       Visualizer
 */
public class Aufgabe19 {
    
    // statisches Attribut fuer Visualizer-Objekt (fuer die Animation noetig)
    private static Visualizer v = null;
    //getestete Werte 5,10,15,20,25,30
    private static final int QUICKINSERTIONSORT_BORDER = 20;
    
    /**
     * Fuellt ein Eingabearray mit Zufallszahlen.
     * @param a das Eingabearray
     */
    private static void fillArray(int[] a) {
        java.util.Random r = new java.util.Random();
        for (int i=0; i<a.length; i++) {
            a[i] = r.nextInt(a.length) + 1; 
            
            // dafuer sorgen, dass jeder Wert von 1 bis a.length
            // nur genau einmal im Array vorkommt
            for (int k=0; k<i; k++) {
                if (a[i] == a[k]) {
                    i--;
                    break;
                }
            }
        }
    }
    
    /**
     * Zaehlt einen Countdown herunter und zeigt diesen auch an.
     * Erwartet, dass das Visualizer-Objekt v gesetzt ist.
     * @param seconds Laenge des Countdowns.
     */
    private static void countdown(int seconds) {
        if (v == null)
            throw new IllegalStateException("Visualizer-Objekt v "
                                            +"existiert nicht!");
        for (int i=seconds; i>0; i--) {
            v.setLegend("Start in "+i+" Sekunden ...");
            v.repaint();
            v.sleepRealtime(1000);
        }
    }
    
    /**
     * Das Hauptprogramm fuer die Aufgabe 19
     * @throws FileNotFoundException 
     */
    public static void main(String[] args) throws Exception {
    	int[] a_save = new int[100];// Erstelle Array a 
    	int[] a = new int[100];// Erstelle Array a 
    	if(args.length != 0)
    	{
    		File f = new File(args[0]);
    		BufferedReader br = new BufferedReader( new FileReader(f));
    		String line;
    		for (int i = 0;(line = br.readLine()) != null && i <a.length;i++) {
    			a_save[i] = Integer.parseInt(line);
    		}
    		a=a_save.clone();
    	}
    	else
    	{
    		fillArray(a);          // Fuelle das Array mit Zufallszahlen
    	}
        
        v = new Visualizer(a); // Visualizer-Objekt erzeugen und v zuweisen
                               // die Animation wird damit moeglich
        
        countdown(3);          // es spannend machen ;-)
        visualQuickInsertSort(a);   // visuell sortieren
        v.sleepRealtime(5000); // 5 Sekunden warten
        
        // Zuruecksetzen fuer den naechsten Sortier-Algorithmus
        v.reset();    // grafischen Visualizer zuruecksetzen
        if(args.length!=0)a=a_save.clone();else fillArray(a); // Array mit neuen Zahlen fuellen
        v.setData(a); // Array-Daten des Visualizers setzen
        v.repaint();  // Neuzeichnen des Fensters
        
        countdown(3);          // es spannend machen ;-)
        visualQuickSort(a);   // visuell sortieren
        v.sleepRealtime(5000); // 5 Sekunden warten
        
        // Zuruecksetzen fuer den naechsten Sortier-Algorithmus
        v.reset();    // grafischen Visualizer zuruecksetzen
        if(args.length!=0)a=a_save.clone();else fillArray(a); // Array mit neuen Zahlen fuellen
        v.setData(a); // Array-Daten des Visualizers setzen
        v.repaint();  // Neuzeichnen des Fensters
        
        countdown(3);          // es spannend machen ;-)
        visualMergeSort(a);   // visuell sortieren
        v.sleepRealtime(5000); // 5 Sekunden warten
        
        // Zuruecksetzen fuer den naechsten Sortier-Algorithmus
        v.reset();    // grafischen Visualizer zuruecksetzen
        if(args.length!=0)a=a_save.clone();else fillArray(a); // Array mit neuen Zahlen fuellen
        v.setData(a); // Array-Daten des Visualizers setzen
        v.repaint();  // Neuzeichnen des Fensters
        
        countdown(3);
        visualSelectionSort(a); // bzw. eigentlich visualSelectionSort(a);
        
        // Zuruecksetzen fuer den naechsten Sortier-Algorithmus
        v.reset();    // grafischen Visualizer zuruecksetzen
        if(args.length!=0)a=a_save.clone();else fillArray(a); // Array mit neuen Zahlen fuellen
        v.setData(a); // Array-Daten des Visualizers setzen
        v.repaint();  // Neuzeichnen des Fensters
        
        countdown(3);
        visualInsertionSort(a); // bzw. eigentlich visualSelectionSort(a);
    }
    
    /**
     * Sortiert ein int-Array mit dem Bubble-Sort Algorithmus
     * (Code siehe Aufgabe 20) und visualisiert dies mit Hilfe
     * eines (statischen) Visualizer-Objekts v, welches existieren muss.
     * 
     * @param a   das zu sortierende Array
     * 
     * @throws IllegalStateException falls das Visualizer-Objekt nicht existiert
     */
    public static void visualBubbleSort(int[] a) {
        if (v == null)
            throw new IllegalStateException("Visualizer-Objekt v "
                                            +"existiert nicht!");
        v.setLegend("Bubble Sort");
        int hilf = 0;
        boolean vertauscht = false;
        int run = 0;
        do {
            run += 1;
            vertauscht = false;
            for (int i=0; i<a.length-run; i++) {
                v.setLabel(i, "i");
                v.setHighlight(i, v.VERTICAL_HIGHLIGHT);
                if (a[i] > a[i+1]) {
                    hilf = a[i];
                    a[i] = a[i+1];
                    a[i+1] = hilf;
                    vertauscht = true;
                }
                v.setColor(i, Color.RED);
                v.setColor(i+1,Color.ORANGE);
                v.setData(a);
                v.repaint(); // alles neuzeichnen
                v.sleep(2);  // und warten
                v.setLabel(i, "");
                v.setHighlight(i, v.NO_HIGHLIGHT);
                if (i == a.length-run-1)
                    v.setColor(i+1, Color.BLACK);
            }
        } while (vertauscht);
        v.setData(a);
        v.setLegend("Bubble Sort (terminiert)");
        v.repaint();
    }
    
    /**
     * Hilfstauschmethode
     * tauscht Werte von a[b]und a[c]
     * @param a Array
     * @param b Index 1
     * @param c Index 2
     */
    private static void swap(int[] a, int b, int c)
    {
    	int tmp = a[b];
        a[b] = a[c];
        a[c] = tmp;
    }
    /**
     * Sortiert ein int-Array mit dem Insertion-Sort Algorithmus
     * (Code siehe Aufgabe 20) und visualisiert dies mit Hilfe
     * eines (statischen) Visualizer-Objekts v, welches existieren muss.
     * 
     * @param a   das zu sortierende Array
     * 
     * @throws IllegalStateException falls das Visualizer-Objekt nicht existiert
     */
    public static void visualInsertionSort(int[] a) {
    	 if (v == null)
             throw new IllegalStateException("Visualizer-Objekt v "
                                             +"existiert nicht!");
         v.setLegend("Insertion Sort");
        for(int i=1; i < a.length;i++)
        {
        	v.setLabel(i, "i");
            v.setHighlight(i, v.VERTICAL_HIGHLIGHT);
            
        	int tmp= a[i];
        	int j = i-1;
        	for(;j>=0 && tmp < a[j];j--)
        	{
        		swap(a,j+1,j);
        	}
        	a[j+1] = tmp;
        	v.setColor(i, Color.RED);
            v.setColor(j+1,Color.ORANGE);
            v.setData(a);
            v.repaint(); // alles neuzeichnen
            v.sleep(2);  // und warten
            v.setLabel(i, "");
            v.setHighlight(i, v.NO_HIGHLIGHT);
        }
        v.setData(a);
        v.setLegend("Insertion Sort (terminiert)");
        v.repaint();
    }


    /**
     * Sortiert ein int-Array mit dem Selection-Sort Algorithmus
     * (Code siehe Aufgabe 20) und visualisiert dies mit Hilfe
     * eines (statischen) Visualizer-Objekts v, welches existieren muss.
     * 
     * @param a   das zu sortierende Array
     * 
     * @throws IllegalStateException falls das Visualizer-Objekt nicht existiert
     */
    public static void visualSelectionSort(int[] a) 
    {
    	if (v == null)
            throw new IllegalStateException("Visualizer-Objekt v "
                                            +"existiert nicht!");
        v.setLegend("Selection Sort");
        for(int i=0; i < a.length-1;i++)
        {
        	v.setLabel(i, "i");
        	v.setHighlight(i, v.VERTICAL_HIGHLIGHT);
        	int minind = i;
        	for(int j=i+1;j<a.length;j++)
       		{
        			if(a[j]<a[minind])minind=j;
       		}
        	swap(a,i,minind);
        	
        	v.setColor(i, Color.RED);
           v.setColor(minind,Color.ORANGE);
           v.setData(a);
           v.repaint(); // alles neuzeichnen
           v.sleep(2);  // und warten
           v.setLabel(i, "");
           v.setHighlight(i, v.NO_HIGHLIGHT);
       }
       v.setData(a);
       v.setLegend("Selection Sort (terminiert)");
       v.repaint();
    }

    /**
     * Sortiert ein int-Array mit dem Merge-Sort Algorithmus
     * (Code siehe Aufgabe 20) und visualisiert dies mit Hilfe
     * eines (statischen) Visualizer-Objekts v, welches existieren muss.
     * 
     * @param a   das zu sortierende Array
     * 
     * @throws IllegalStateException falls das Visualizer-Objekt nicht existiert
     */
    public static void visualMergeSort(int[] a) {
    	if (v == null)
            throw new IllegalStateException("Visualizer-Objekt v "
                                            +"existiert nicht!");
        v.setLegend("Merge Sort");
    	mergeSort(a,0,a.length-1);
    	v.setData(a);
        v.setLegend("Merge Sort (terminiert)");
        v.repaint();
    }
    
    /**
     * Sortiert ein int-Array mit dem Merge-Sort Algorithmus
     * @param a Array
     * @param l	Linker Rand
     * @param r Rechter Rand
     */
    private static void mergeSort(int[] a, int l, int r)
    {
    	int m = (l+r)/2;
    	if(l<m)mergeSort(a,l,m);
    	if(m+1<r)mergeSort(a,m+1,r);
    	merge(a,l,m,r);

        v.repaint();
        v.sleep(2);
        v.setData(a);
    }
    
    /**
     * Fuegt ein Array partiell-sortiert zusammen
     * @param a Array
     * @param l Linker Rand
     * @param m Mitte
     * @param r Rechter Rand
     */
    private static void merge(int[] a, int l, int m , int r)
    {
    	int[] b = new int[r-l+1];
    	int i=l;
    	int j = m+1;
    	int k;
    	for(k=0;k<=r-l;k++)
    	{
    		if((i>m)|| ((j<=r) && (a[j] < a[i])))
    		{
    			b[k] = a[j];
    			j++;
    		}
    		else
    		{
    			b[k] = a[i];
    			i++;
    		}
    	}
    	for(k=l;k <= r;k++)
    	{
    		a[k] = b[k-l];
    	}
    }
    
    ///////////////////////////////////////////
    /////////////////////////////////////////////
    ////////Aufgabe 21////////////////////
    ///////////////////////////////////////////
    /////////////////////////////////////////////
    /**
     * Sortiert ein int-Array mit dem Quick-Sort Algorithmus
     * (Code siehe Aufgabe 20) und visualisiert dies mit Hilfe
     * eines (statischen) Visualizer-Objekts v, welches existieren muss.
     * 
     * @param a   das zu sortierende Array
     * 
     * @throws IllegalStateException falls das Visualizer-Objekt nicht existiert
     */
    public static void visualQuickSort(int[] a) 
    {
    	if (v == null)
            throw new IllegalStateException("Visualizer-Objekt v "
                                            +"existiert nicht!");
        v.setLegend("Quick Sort");
        
        quickSort(a,0,a.length-1);
        
       v.setData(a);
       v.setLegend("Quick Sort (terminiert)");
       v.repaint();
    }
    //Kommentar
    private static void quickSort(int[] a, int l, int r)
    {
    	//Pivot Mitte
    	int m = a[(l+r)/2];
    	int i = l;
    	int j = r;
    	while(i<=j)
    	{
    		for(;a[i]<m;i++);
    		for(;m<a[j];j--);
    		if(i<=j)
    		{
    			swap(a,i,j);

    	        v.repaint();
    	        v.sleep(2);
    	        v.setData(a);
    	        
    			i++;
    			j--;
    		}
    	}
    	if(l<j)quickSort(a,l,j);
    	if(i<r)quickSort(a,i,r);
    }
    
    /**
     * Sortiert ein int-Array mit dem Quick-Insert-Sort Algorithmus
     * (Code siehe Aufgabe 20) und visualisiert dies mit Hilfe
     * eines (statischen) Visualizer-Objekts v, welches existieren muss.
     * 
     * @param a   das zu sortierende Array
     * 
     * @throws IllegalStateException falls das Visualizer-Objekt nicht existiert
     */
    public static void visualQuickInsertSort(int[] a) 
    {
    	if (v == null)
            throw new IllegalStateException("Visualizer-Objekt v "
                                            +"existiert nicht!");
        v.setLegend("Quick Insertion Sort");
        
        quickInsertionSort(a,0,a.length-1);
        
       v.setData(a);
       v.setLegend("Quick Insertion Sort (terminiert)");
       v.repaint();
    }
    //Kommentar
    private static void quickInsertionSort(int[] a, int l, int r)
    {
    	if(r-l<QUICKINSERTIONSORT_BORDER)
    	{
    		//insertionsort
    		for(int i=l+1; i <= r;i++)
            {
                
            	int tmp= a[i];
            	int j = i-1;
            	for(;j>=0 && tmp < a[j];j--)
            	{
            		swap(a,j+1,j);
            	}
            	a[j+1] = tmp;
            	
                v.setData(a);
                v.repaint(); // alles neuzeichnen
                v.sleep(2);  // und warten
            }
    		return;
    	}
    	//Pivot Mitte
    	int m = a[(l+r)/2];
    	int i = l;
    	int j = r;
    	while(i<=j)
    	{
    		for(;a[i]<m;i++);
    		for(;m<a[j];j--);
    		if(i<=j)
    		{
    			swap(a,i,j);

    	        v.repaint();
    	        v.sleep(2);
    	        v.setData(a);
    	        
    			i++;
    			j--;
    		}
    	}
    	if(l<j)quickSort(a,l,j);
    	if(i<r)quickSort(a,i,r);
    }
    
} // class Aufgabe19
