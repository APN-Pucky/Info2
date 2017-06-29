package blatt9;

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
public class Aufgabe19v2 {
    
    private static String[] args;
    private static int[] a_save;
    private static int swap_count = 0;
    private static int compare_count = 0;
    private static int read_count = 0;
    private static int write_count = 0;
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
	Aufgabe19v2.args = args;
    	a_save = new int[100];// Erstelle Array a 
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
        
        visualHeapSort(a);
	reset(a);	
	visualQuickInsertSort(a);   // visuell sortieren
        reset(a);
        visualQuickSort(a);   // visuell sortieren
        reset(a);
        visualMergeSort(a);   // visuell sortieren
        reset(a);
        visualSelectionSort(a); 
        reset(a);
        visualInsertionSort(a); 
	reset(a);

    }

    private static void reset(int[] a)
    {

	v.sleepRealtime(1500);
	String s  = "Read-OPs: " + read_count + " | Write-OPs: " + write_count + " | Swaps: " + write_count/2 + "(" + swap_count + ")" + " | Comps.: " + (read_count-write_count)/2 + "(" + compare_count + ")" +  " | Guetekriterium: " + (write_count+(read_count-write_count)/2); 	
	v.setLegend(s);
	System.out.println(s);
     	v.repaint();
	v.sleepRealtime(3000); 
        
        // Zuruecksetzen fuer den naechsten Sortier-Algorithmus
        v.reset();    // grafischen Visualizer zuruecksetzen
        if(args.length!=0)a=a_save.clone();else fillArray(a); // Array mit neuen Zahlen fuellen
        v.setData(a); // Array-Daten des Visualizers setzen
        v.repaint();  // Neuzeichnen des Fensters

        read_count = 0;
	write_count = 0;
	swap_count = 0;
	compare_count = 0;

        countdown(2);          // es spannend machen ;-)
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
	swap_count++;
    	int tmp = get(a,b);
	set(a,b,get(a,c));
        set(a,c,tmp);
    }

    private static void set(int[] a, int b, int value)
    {
	write_count++;
    	a[b] = value;
    }

    private static int get(int[] a, int b)
    {
	read_count++;
	return a[b];
    }

    private static boolean smallerThan(int b, int c)
    {
	compare_count++;
	return b < c;
    }
    private static boolean biggerThan(int b, int c)
    {return smallerThan(c,b);}
    
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
	System.out.println("Bubble Sort");
        int hilf = 0;
        boolean vertauscht = false;
        int run = 0;
        do {
            run += 1;
            vertauscht = false;
            for (int i=0; i<a.length-run; i++) {
                v.setLabel(i, "i");
                v.setHighlight(i, v.VERTICAL_HIGHLIGHT);
                if (biggerThan(get(a,i),get(a,i+1))) {
		    swap(a,i,i+1);
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
	System.out.println("Insertion Sort");
        for(int i=1; i < a.length;i++)
        {
        	v.setLabel(i, "i");
            v.setHighlight(i, v.VERTICAL_HIGHLIGHT);
            
        	int tmp= get(a,i);
        	int j = i-1;
        	for(;j>=0 && smallerThan(tmp,get(a,j));j--)
        	{
        		swap(a,j+1,j);
        	}
		set(a,j+1,tmp);
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
	System.out.println("Selection Sort");
        for(int i=0; i < a.length-1;i++)
        {
        	v.setLabel(i, "i");
        	v.setHighlight(i, v.VERTICAL_HIGHLIGHT);
        	int minind = i;
        	for(int j=i+1;j<a.length;j++)
       		{
				if(smallerThan(get(a,j),get(a,minind)))minind=j;
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
	System.out.println("Merge Sort");
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
    		if((i>m)|| ((j<=r) && (smallerThan(get(a,j),get(a,i)))))
    		{
    			b[k] = get(a,j);
    			j++;
    		}
    		else
    		{
    			b[k] = get(a,i);
    			i++;
    		}
    	}
    	for(k=l;k <= r;k++)
    	{
		set(a,k,b[k-l]);
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
        System.out.println("Quick Sort");
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
    		for(;smallerThan(get(a,i),m);i++);
    		for(;smallerThan(m,get(a,j));j--);
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
	System.out.println("Quick Insertion Sort");
        
        quickInsertionSort(a,0,a.length-1);
        
       v.setData(a);
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
                
            	int tmp= get(a,i);
        	int j = i-1;
        	for(;j>=0 && smallerThan(tmp,get(a,j));j--)
        	{
        		swap(a,j+1,j);
        	}
		set(a,j+1,tmp);
            	
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
    		for(;smallerThan(get(a,i),m);i++);
    		for(;smallerThan(m,get(a,j));j--);
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

    private static void visualHeapSort(int[] a)
    { 
	if (v == null)
            throw new IllegalStateException("Visualizer-Objekt v "
                                            +"existiert nicht!");
        v.setLegend("Heap Sort");
	System.out.println("Heap Sort");
     	for ( int i = (a.length)/2;i>=1;i--)
		restore(a,i,a.length);
	for( int i = a.length;i>1;i--) {
		swap(a,i-1,1-1);

    	        v.repaint();
    	        v.sleep(2);
    	        v.setData(a);

		restore(a,1,i-1);
	}
        v.repaint();
    	v.sleep(2);
    	v.setData(a);
    }

    private static void restore(int[] a , int l , int r)
    {
 	int j;
	int i = l;
	while(i <=r/2){
	   	if ((2*i<r)&&(biggerThan(get(a,2*i+1-1),get(a,2*i-1))))
			j =2*i+1;
		else
			j= 2*i;
		if(biggerThan(get(a,j-1),get(a,i-1))){
			swap(a,i-1,j-1);

	    	        v.repaint();
    		        v.sleep(2);
    		        v.setData(a);

			i=j;
		}
		else
			break;
	}
     }
    
} // class Aufgabe19
