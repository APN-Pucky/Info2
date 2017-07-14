package blatt10;

/**
 * @author Alexander Neuwirth
 * @author Leonhard Segger
 * @author Jonathan Sigrist
 * 
 * @param <E>
 *            Elemente der Datenstruktur
 */
public class ADTDICT<E extends Comparable<E>> {
	private AVLBinTree<E> tree;

	/**
	 * Konstruktor: Erstellt ein neues und leeres Dictionary.
	 */
	public ADTDICT() {
		tree = new AVLBinTree<E>();
	}

	/**
	 * Fuegt einen Eintrag hinzu.
	 * 
	 * @param elem
	 *            hinzuzufuegender Eintrag
	 */
	public void insert(E elem) {
		tree.insert(elem);
	}

	/**
	 * Loescht einen Eintrag heraus.
	 * 
	 * @param elem
	 *            tu loeschender Eintrag
	 */
	public void delete(E elem) {
		tree.delete(elem);
	}

	/**
	 * Gibt zurueck, ob ein Eintrag im Dictionary vorhanden ist.
	 * 
	 * @param elem
	 *            zu pruefender Eintrag
	 * @return true, falls vorhanden; false falls nicht vorhanden
	 */
	public boolean member(E elem) {
		return tree.contains(elem);
	}
}
