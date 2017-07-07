package blatt9;

/**
 * @author Alexander Neuwirth
 * @author Leonhard Segger
 * @author Jonathan SIgrist
 *
 * @param <E>
 *            Datentyp der DEQueue
 */
public class ListDEQueue<E> implements DEQueue<E> {
	/**
	 * Innere Klasse: Stellt eine rekursive Datenstruktur zur Verfuegung. Jeder
	 * Knoten zeigt jeweils auf den Vorgaenger und den Nachfolger.
	 * 
	 * @author Alexander Neuwirth
	 * @author Leonhard Segger
	 * @author Jonathan SIgrist
	 *
	 * @param <E>
	 *            Datentyp der DEQueue
	 */
	@SuppressWarnings("hiding")
	class ListNode<E> {
		private E elem;
		private ListNode<E> next = null;
		private ListNode<E> prev = null;

		/**
		 * Konstruktor: Erzeugt einen neuen Knoten repraesentativ fuer ein
		 * Element der DEQueue.
		 * 
		 * @param element
		 *            Element der DEQueue
		 */
		ListNode(E element) {
			elem = element;
		}

		/**
		 * Setzt den Nachfolger.
		 * 
		 * @param next
		 *            Nachfolger
		 */
		void setNextNode(ListNode<E> next) {
			this.next = next;
		}

		/**
		 * Setzt den Vorgaenger.
		 * 
		 * @param prev
		 *            Vorgaenger
		 */
		void setPrevNode(ListNode<E> prev) {
			this.prev = prev;
		}

		/**
		 * Gibt den Nachfolger zurueck.
		 * 
		 * @return Nachfolger
		 */
		ListNode<E> getNextNode() {
			return next;
		}

		/**
		 * Gibt den Vorgaenger zurueck.
		 * 
		 * @return Vorgaenger
		 */
		ListNode<E> getPrevNode() {
			return prev;
		}

		/**
		 * Setzt den Wert des Knotens.
		 * 
		 * @param element
		 *            neues Element
		 */
		void setData(E element) {
			elem = element;
		}

		/**
		 * Gibt den Wert des Knotens zurueck.
		 * 
		 * @return Element
		 */
		E getData() {
			return elem;
		}
	}

	ListNode<E> front;
	ListNode<E> back;
	int size = 0;

	/**
	 * Konstruktor: Erzeugt eine neue ListDEQueue ohne Elemente.
	 */
	public ListDEQueue() {

	}

	public boolean empty() {
		return size == 0;
	}

	public void enqueuefront(E elem) {
		ListNode<E> tmp = new ListNode<E>(elem);
		if (empty()) {
			front = tmp;
			back = front;
		} else {
			front.setNextNode(tmp);
			tmp.setPrevNode(front);
			front = tmp;
		}
		size++;
	}

	public void enqueueback(E elem) {
		ListNode<E> tmp = new ListNode<E>(elem);
		if (empty()) {
			front = tmp;
			back = front;
		} else {
			back.setPrevNode(tmp);
			tmp.setNextNode(back);
			back = tmp;
		}
		size++;
	}

	public E front() {
		if (empty())
			return null;
		else
			return front.getData();
	}

	public E back() {
		if (empty())
			return null;
		else
			return back.getData();
	}

	public void dequeuefront() {
		if (!empty()) {
			size--;
			front = front.getPrevNode();
			if (front == null)
				back = null;
		}
	}

	public void dequeueback() {
		if (!empty()) {
			size--;
			back = back.getNextNode();
			if (back == null)
				front = null;
		}
	}

}
