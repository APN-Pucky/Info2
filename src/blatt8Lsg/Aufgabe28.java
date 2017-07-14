package blatt8Lsg;
import static java.lang.Math.*;

/**
 * Aufgabe 28 (Multiplikation von Bandmatrizen)
 *
 * @author Aaron Scherzinger
 */
public class Aufgabe28 {

    /**
     * Methode zum Ausgeben einer Bandmatrix (als komplette Matrix) auf der Konsole.
     *
     * @param matrix die auszugebende Bandmatrix
     * @param n die Anzahl Zeilen (= Anzahl Spalten) 
     * @param b die Groesse des Bandes
     */
    public static void printBandmatrixKomplett(int[][] matrix, int n, int b) {
        // Iteriere ueber Eintraege der n x n-Matrix
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
                // Berechne Index in der Bandmatrix
                int bandIndex = j - i + b;
                // Teste, ob der aktuelle Eintrag im Band ist, sonst gebe 0 aus
				if ((bandIndex >= 0) && (bandIndex < 2 * b + 1))
                    System.out.print(matrix[i][bandIndex]);
                else
                    System.out.print(0);
			}
			System.out.println();
		}
    }

    /**
     * Methode zum Ausgeben einer Bandmatrix in komprimierter Form auf der Konsole.
     *
     * @param matrix die auszugebende Bandmatrix
     * @param n die Anzahl Zeilen (= Anzahl Spalten) 
     * @param b die Groesse des Bandes
     */
    public static void printBandmatrix(int[][] matrix, int n, int b) {
        for (int i = 0; i < n; i++){
            for (int j = 0; j < 2 * b + 1; j++){
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Main-Methode zum Testen der Multiplikation zweier Bandmatrizen.
     */
	public static void main(String[] args) {
        // Parameter fuer den Testfall
		int n = 15;
		int b = 2; 
        
        // Initialisierung zweier Bandmatrizen a1 und a2 (alle Eintraege = 0)
		int[][] a1 = new int[n][2 * b + 1];
        int[][] a2 = new int[n][2 * b + 1];
        // setze das Band bei beiden Matrizen auf 1
        for (int i = 0; i < n; ++i) {
            for (int j = -b; j < b + 1; j++) {
                // Eintraege ausserhalb des Quadrats sollen 0 bleiben
                if ((j + i >= 0) && (j + i < n)) {
                    a1[i][j+b] = 1;
                    a2[i][j+b] = 1;
                }
            } 
        }

        // Gebe die Matrizen sowie ihre Bandmatrix-Repraesentation aus
        System.out.println("Matrix a1: ");
        printBandmatrixKomplett(a1, n, b);
        System.out.println();

        System.out.println("Repraesentation als Bandmatrix:");
        printBandmatrix(a1, n, b);
        System.out.println();

        System.out.println("Matrix a2: ");
        printBandmatrixKomplett(a2, n, b);
        System.out.println();

        System.out.println("Repraesentation als Bandmatrix:");
        printBandmatrix(a2, n, b);
        System.out.println();

        // Multipliziere die Matrizen
        int[][] result = multipliziereBandmatrizen(a1,a2,n,b);
        int resultB = (result[0].length - 1) / 2; // b ist verschieden in der Ergebnismatrix

        // Gebe das Ergebnis aus
        System.out.println("Ergebnis der Matrixmultiplikation von a1 und a2: ");
        printBandmatrixKomplett(result, n, resultB);
        System.out.println();
        System.out.println("Repraesentation als Bandmatrix:");
        printBandmatrix(result, n, resultB);
        System.out.println();

	}
	
    /**
     * Multipliziert zwei Bandmatrizen. Das Ergebnis ist wieder eine Bandmatrix (mit b' = 2*b).
     * 
     * @param a1 erste zu multiplizierende Bandmatrix
     * @param a2 zweite zu multiplizierende Bandmatrix 
     * @param n Parameter fuer Groesse der Matrizen (n x n)
     * @param b Parameter fuer die Groesse des Bandes der beiden Matrizen
     * @result Ergebnismatrix (ebenfalls als Bandmatrix)
     */
	public static int[][] multipliziereBandmatrizen(int[][] a1, int[][] a2, int n, int b) {	
        // Erzeuge Ergebnismatrix mit b' = 2*b
        int resultB = 2 * b;
		int[][] result = new int[n][2 * resultB + 1];

		// Iteriere ueber die Zeilen und Spalten der Resultat-Bandmatrix (d.h. die zu befuellenden Eintraege)
		for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2 * resultB + 1; j++) {
                // Eintrage ausserhalb des Quadrats muessen nicht berechnet werden (= 0)
                if ((j - resultB + i < 0) || (j - resultB + i >= n))
                    continue;

                // Matrixmultiplikation bildet fuer einen Eintrag (i, j) der Ergebnismatrix das
                // Skalarprodukt der i-ten Zeile von a1 und der j-ten Spalte von a2
                // -> berechne Spaltenindex in der korrespondierenden n x n-Matrix
                int column = j - resultB + i;
                // berechne benoetigte Spalten von a1 / Zeilen von a2 
                int minColumn = max(i - b, 0);          // erste Spalte, bei der in a1 keine 0 steht
                int maxColumn = min(i + b, n - 1);      // letzte Spalte, bei der in a1 keine 0 steht

                int minLine = max(column - b, 0);       // erste Zeile, bei der in a2 keine 0 steht
                int maxLine = min(column + b, n - 1);   // letzte Zeile, bei der in a2 keine 0 steht

                // Da Eintraege mit 0en bei der Multiplikation 0 werden, koennen wir diese in der Summe ignorieren
                // -> betrachte nur die benoetigten Indizes
                int minIndex = max(minColumn, minLine);
                int maxIndex = min(maxColumn, maxLine);

                for (int k = minIndex; k <= maxIndex; ++k) {
                    // Addiere Produkt der korrespondierenden Eintrage von a1 und a2 auf den Eintrag der Ergebnismatrix
                    result[i][j] += a1[i][k - i + b] * a2[k][i - k + b];  
                }           
            }
		}

		// Gib das Resultat zurueck
		return result;
	}
}
