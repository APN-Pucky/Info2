
/**
 * 
 * @author Alexander Neuwirth
 * @author Leonhard Segger
 * @author Jonathan Sigrist
 *
 */
public class MyMatrix {
	int n;
	double[] a;

	/**
	 * Konstruktor. Erzeugt eine nxn-Matrix mit M_ij = -M_ji
	 * 
	 * @param n
	 *            groesse der nxn-Matrix
	 */
	public MyMatrix(int n) {
		this.n = n;
		a = new double[(int) (Math.pow(n, 2) - n) / 2];
	}

	/**
	 * Gibt den Wert der Matrix an der Stelle M_i,j zurück
	 * 
	 * @param i
	 *            Zeile
	 * @param j
	 *            Spalte
	 * @return Wert bei M_ij
	 */
	public double get(int i, int j) {
		if (i >= n || j >= n || i < 0 || j < 0) // Ausserhalb des Bereichs
			throw new ArrayIndexOutOfBoundsException();
		if (i == j) // Hauptdiagonale
			return 0;
		if (i > j) // Tausche i und j, falls auf der negativen Seite
			return -get(j, i);
		return a[n * i + j - i * (i + 1) / 2 - i - 1];
	}

	/**
	 * Setzt die Werte M_ij = -M_ji fest, falls i != j
	 * 
	 * @param i
	 *            Zeile
	 * @param j
	 *            Spalte
	 * @param value
	 *            Zusetzender Wert bei M_ij
	 */
	public void set(int i, int j, double value) {
		if (i >= n || j >= n || i < 0 || j < 0) // Ausserhalb des Bereichs
			throw new ArrayIndexOutOfBoundsException();
		if (i == j) // Hauptdiagonale
			return;
		if (i > j) // Tausche i und j, falls auf der negativen Seite
			set(j, i, -value);
		else
			a[n * i + j - i * (i + 1) / 2 - i - 1] = value;
	}

	/**
	 * Hauptmethode fuer ein Beispiel einer Matrix
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		MyMatrix mm = new MyMatrix(5);
		mm.a = new double[] { 1, 2, 3, 4, 5, 6, 0, 0, 0, 0 };
		mm.set(0, 3, -7);
		mm.set(2, 1, 2.5);
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.print(mm.get(i, j) + "  ");
			}
			System.out.println();
		}
	}
}
