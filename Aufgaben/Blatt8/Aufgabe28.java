
/**
 * 
 * @author Alexander Neuwirth
 * @author Leonhard Segger
 * @author Jonathan Sigrist
 *
 */
public class Aufgabe28 {

	/**
	 * Hauptmethode: Multiplikation zweier Bandmatrizen anhand zweier
	 * Beispielmatrizen.
	 * 
	 * @param args
	 *            Konsoleneingaben
	 */
	public static void main(String[] args) {
		int[][] band1 = new int[][] { { 0, 5, 4 }, { 3, 2, 1 }, { 1, 2, 3 }, { 4, 5, 6 }, { 9, 8, 7 }, { 6, 5, 0 } };
		int[][] band2 = new int[][] { { 0, 5, 2 }, { 3, 2, 1 }, { 1, 2, 3 }, { 4, 5, 6 }, { 9, 8, 7 }, { 6, 5, 0 } };

		System.out.println("band1:");
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				System.out.print(getVal(band1, 6, 1, i, j) + " ");
			}
			System.out.println();
		}
		System.out.println("band2:");
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				System.out.print(getVal(band2, 6, 1, i, j) + " ");
			}
			System.out.println();
		}
		System.out.println("Quad band1:");
		int[][] solve = multipliziereBandmatrizen(band1, band1, 6, 1);
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				System.out.print(getVal(solve, 6, 1, i, j) + " ");
			}
			System.out.println();
		}
		System.out.println("band1 * band2:");
		solve = multipliziereBandmatrizen(band1, band2, 6, 1);
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				System.out.print(getVal(solve, 6, 1, i, j) + " ");
			}
			System.out.println();
		}
		// System.out.println(multipliziereBandmatrixElement(band,band,6,1,5,1));
	}

	/**
	 * Multipliziert zwei Bandmatrizen miteinander
	 * 
	 * @param a1
	 *            Matrix 1
	 * @param a2
	 *            Matrix 2
	 * @param n
	 *            groesse der nxn-Matrizen
	 * @param b
	 *            breite des Bandes
	 * @return Ergebnis der Berechnung
	 */
	public static int[][] multipliziereBandmatrizen(int[][] a1, int[][] a2, int n, int b) {
		int[][] ret = new int[n][2 * b + 1];
		for (int i = 0; i < n; i++)// n
		{
			for (int j = 0; j < 2 * b + 1; j++)// 2*b+1
			{
				ret[i][j] = multipliziereBandmatrixElement(a1, a2, n, b, i, j);
			}
		}
		return ret;
	}

	/**
	 * Errechnet ein Element der neuen Matrix
	 * 
	 * @param a1
	 *            Matrix 1
	 * @param a2
	 *            Matrix 2
	 * @param n
	 *            groesse der nxn-Matrizen
	 * @param b
	 *            breite des Bandes
	 * @param i
	 *            Zeile
	 * @param j
	 *            Spalte
	 * @return Wert der neuen Matrix bei (i, j)
	 */
	private static int multipliziereBandmatrixElement(int[][] a1, int[][] a2, int n, int b, int i, int j) {
		int ret = 0;
		for (int l = 0; l < 2 * b + 1; l++)// 2*b+1
		{
			if (!(l + i - b >= n || -(l - b) + j >= 2 * b + 1 || l + i - b < 0 || -(l - b) + j < 0)) {
				ret += a1[i][l] * a2[l + i - b][-(l - b) + j];
				// System.out.println(a1[i][l] +" | " + a2[l+i-b][-(l-b)+j]);
			}
		}
		return ret;
	}

	/**
	 * Gibt den Wert bei (i, j) einer Bandmatrix zurueck
	 * 
	 * @param band
	 *            Bandmatrix
	 * @param n
	 *            groesse der nxn-Matrix
	 * @param b
	 *            breite des Bandes
	 * @param i
	 *            Zeile
	 * @param j
	 *            Spalte
	 * @return
	 */
	private static int getVal(int[][] band, int n, int b, int i, int j) {
		if (i < 0 || j < 0 || i >= n || j >= n)
			return 0;// oder error
		if (j > i + b || j < i - b)
			return 0;
		// System.out.println(i+" " +j);
		return band[i][j - i + b];
	}
}
