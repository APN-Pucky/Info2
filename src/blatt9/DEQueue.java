package blatt9;

/**
 * @author Alexander Neuwirth
 * @author Leonhard Segger
 * @author Jonathan Sigrist
 *
 * @param <E>
 *            Datentyp der Double-End.Queue
 */
public interface DEQueue<E> {
	/**
	 * Fuegt ein Element vor dem ersten Element ein.
	 * 
	 * @param elem
	 *            einzufuegendes Element
	 */
	public void enqueuefront(E elem);

	/**
	 * Fuegt ein Element hinter dem letzten Element ein.
	 * 
	 * @param elem
	 *            einzufuegendes Element
	 */
	public void enqueueback(E elem);

	/**
	 * Gibt zurueck, ob die DEQueue leer ist, also keine Elemente besitzt.
	 * 
	 * @return true falls DEQueue leer, false falls mindestens ein Element
	 */
	public boolean empty();

	/**
	 * Gibt das erste Element der DEQueue zurueck.
	 * 
	 * @return erstes Element
	 */
	public E front();

	/**
	 * Gibt das letzte Element der DEQueue zurueck.
	 * 
	 * @return letztes Element
	 */
	public E back();

	/**
	 * Entfernt das erste ELement.
	 */
	public void dequeuefront();

	/**
	 * Entfernt das letzte Element.
	 */
	public void dequeueback();
}
