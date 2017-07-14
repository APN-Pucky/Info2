package blatt8Lsg;
/*
 * Created on 02.07.2004
 *
 */

/**
 * @author Dominik Ullrich
 *
 */
public class Aufgabe41 {
	
	/** ruft den Konstruktor zum testen der computePairsForSum Methode auf.
	 * 
	 * @param args
	 */
	public static void main(String [] args){
		new Aufgabe41();
	}
	
	/** Der Konstruktor testet die computePairsForSum Methode.
	 *
	 */
	public Aufgabe41(){
		// Erzeuge Testarray
		int[] zahlen = new int[20];
		// fülle Testarray
		for(int i = 0; i<20;i++){
			zahlen[i] = 20-i*2;
		}
		//Teste computePairsForSum
		int[][]b = computePairsForSum(zahlen, 10);
		// Ausgabe des Startzustand und des Ergebnis
		System.out.print("Startarray:");
		for(int i = 0; i<19;i++){
			System.out.print(zahlen[i] + ",");
		}
		System.out.println(zahlen[19]);
		System.out.println();
		System.out.println("Ergebnis bei suche für 10:");
		for(int j = 0;j<b.length;j++){
			System.out.println("("+ b[j][0] + "," + b[j][1] + ")");
		}
	}

	/** führt auf dem Array values rekursiv eine Suche nach search durch (innerhalb 
	 *  der Grenzen left und right).
	 * 
	 * @param values
	 * @param search
	 * @param left
	 * @param right
	 * @return
	 */
	public boolean binsearch(int[] values ,int search, int left, int right){
		int middle = (left+right)/2;
		if (left == right){
			// Bereich besteht nur aus einem Element ==> Rückgabe ob das gesuchte.
			return (search == values[middle]);
		}
		else{
			if (search <= values[middle]){
				//suche im linken Bereich weiter
				return binsearch(values, search, left, middle);
			}else{
				//suche im rechten Bereich weiter
				return binsearch(values, search, middle+1, right);
			}
		}
	}
	
	/** gibt die Anzahl der (geordneten) Paare für die Summe zurück 
	 *  die im Array enthalten sind.
	 * 
	 * @param values
	 * @param sum
	 * @return
	 */ 
	public int countPairsForSum(int[] values, int sum){
		int[][] result = new int[values.length/2+1][2];
		int pairCounter = 0;
		for (int i = values.length-1 ; i>=0; i--){
			if (binsearch(values, sum-values[i], 0, values.length-1)){
				// wenn zu einem Element ein Partner gefunden wird erhöhe den Zähler
				pairCounter++;
			}
		}
		return pairCounter;	//gib Anzahl der Paare zurück
	}
	
	/** Gibt ein 2 Dimensionales Array mit den geordneten Paaren von Elementen aus 
	 *  values zurück die sum ergeben.
	 * 
	 * @param values
	 * @param sum
	 * @return
	 */
	public int[][] computePairsForSum(int[] values, int sum){
		mergesort(values,0,values.length-1);
		int size = countPairsForSum(values, sum); //Berechne anzahl der Paare
		int[][] result = new int[size][2];		//und erstelle 2-Dim-Array passender größe
		int resultindex = 0;
		for (int i = values.length-1 ; i>=0; i--){//suche Partner für jedes Element
			if (binsearch(values, sum-values[i], 0, values.length-1)){
				// trage gefundenes Paar ins zuruckzugebende Array ein
				result[resultindex][0] = sum - values[i];
				result[resultindex][1] = values[i];
				resultindex++;
			}
		}
		return result;	//gib das 2-Dim-Array zurück
	}

	/** Mergesort zum sortieren des arrays
	 * 
	 * @param a
	 * @param lo
	 * @param hi
	 */
	public void mergesort(int[] a,int lo, int hi){
		if (lo<hi){
			// initialisiere die Mitte
			int m=(lo+hi)/2;
			// rufe mergesort fuer die untere Haelfte auf
			mergesort(a,lo, m);
			// rufe mergesort fuer die obere Haelfte auf
			mergesort(a,m+1, hi);
			// fuege die beiden Teilarrays wieder zusammen
			merge(a,lo, hi);
		}
	}
	
	/** zum zusammenfügen der Teilarrays im Mergesort
	 * 
	 * @param a
	 * @param lo
	 * @param hi
	 */
	public void merge(int[] a,int lo, int hi){
		int i, j, k, m, n=hi-lo+1;
		int[] b = new int[a.length];
		k=0;
		// initialisiere die Mitte
		m=(lo+hi)/2;
		// untere Haelfte in Array b kopieren
		for (i=lo; i<=m; i++) {
			b[k++]=a[i];
		}
		// obere Haelfte in umgekehrter Reihenfolge in Array b kopieren
		for (j=hi; j>=m+1; j--) {
			b[k++]=a[j];
		}
		i=0; j=n-1; k=lo;
		// jeweils das naechstgroesste Element zurueckkopieren,
		// bis i und j sich ueberkreuzen
		while (i<=j) {
			if (b[i]<=b[j]) {
				a[k++]=b[i++]; //a[k] auf b[i]; k und i anschliessend incrementieren
			}
			else{
				a[k++]=b[j--];
			}
		}
	}	
}