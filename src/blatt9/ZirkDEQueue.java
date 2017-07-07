package blatt9;

/**
 * @author Alexander Neuwirth
 * @author Leonhard Segger
 * @author Jonathan SIgrist
 *
 * @param <E>
 *            Datentyp der DEQueue
 */
public class ZirkDEQueue<E> implements DEQueue<E> {
	private E[] b;
	private int front = 0;
	private int back = 0;

	/**
	 * Konstruktor: Erstellt eine zirkulaere Double-End-Queue mit maximaler
	 * Groesse
	 * 
	 * @param maxsize
	 *            maximale Groesse der DEQueue
	 */
	@SuppressWarnings("unchecked")
	public ZirkDEQueue(int maxsize) {
		b = (E[]) new Object[maxsize + 1];
	}

	/**
	 * Gibt zurueck, ob die DEQueue voll ist, also alle Speicherplaetze elegt
	 * sind.
	 * 
	 * @return true, falls kein freier Speicherplatz vorhanden; sonst false
	 */
	public boolean full() {
		return ((front + 1) % b.length == back);
	}

	public boolean empty() {
		return front == back;
	}

	public void enqueuefront(E elem) {
		if (full()) {
			throw new ZirkSpeicherOverflowException();
		} else {
			b[front] = elem;
			front = (front + 1) % b.length;
		}
	}

	public void enqueueback(E elem) {
		if (full()) {
			throw new ZirkSpeicherOverflowException();
		} else {
			back = (back - 1 + b.length) % b.length;
			b[back] = elem;
		}
	}

	public E front() {
		if (empty()) {
			return null;
		} else {
			return b[(front - 1 + b.length) % b.length];
		}
	}

	public E back() {
		if (empty()) {
			return null;
		} else {
			return b[back];
		}
	}

	public void dequeuefront() {
		if (!empty()) {
			b[front] = null;
			front = (front - 1 + b.length) % b.length;
		}
	}

	public void dequeueback() {
		if (!empty()) {
			b[back] = null;
			back = (back + 1) % b.length;
		}
	}

}
