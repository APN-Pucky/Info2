package blatt9;

/**
 * Testet ListDEQueue und ZirkDEQueue
 * 
 * @author Alexander Neuwirth
 * @author Leonhard Segger
 * @author Jonathan Sigrist
 *
 */
public class Test {
	public static void main(String[] args) {
		DEQueue<Long> zq = new ListDEQueue<Long>();
		// DEQueue<Long> zq = new ZirkDEQueue<Long>(100);
		zq.enqueuefront(17L);
		zq.enqueueback(18L);
		zq.enqueuefront(27L);
		System.out.println(zq.back());
		zq.dequeueback();
		System.out.println(zq.back());
		zq.dequeueback();
		System.out.println(zq.back());
		zq.dequeueback();
		System.out.println(zq.back());
		System.out.println(zq.front());
		zq.enqueueback(67L);
		zq.enqueueback(47L);
		zq.enqueuefront(37L);
		System.out.println(zq.front());
		zq.dequeuefront();
		System.out.println(zq.front());
		zq.dequeuefront();
		System.out.println(zq.back());
		zq.dequeueback();

	}
}
