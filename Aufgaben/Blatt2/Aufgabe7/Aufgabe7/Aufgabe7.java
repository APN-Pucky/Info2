/**
 * 
 * @author Alexander Neuwirth
 * @author Leonhard Segger
 * @author Jonathan Sigrist
 */

public class Aufgabe7 {
	// constants
	private static final int[] MODULAR_ARRAY = new int[] { 999, 1000, 1001 };

	private static final int MAX_MODULAR_VALUE;
	// calc MAX_MODULAR_VALUE
	static {
		int ret = 1;
		for (int m : MODULAR_ARRAY) {
			ret *= m;
		}
		MAX_MODULAR_VALUE = ret;
	}

	/**
	 * Testet die rekursive Fibonacci Methode und gibt das Ergebniss von fib(n)
	 * zurueck
	 * 
	 * @param n
	 *            fib-Index
	 */
	private static void testRecFib(int n) {
		System.out.println("recFib[" + n + "]=" + recursiveFib((short) n));
	}

	/**
	 * Testet die iterative Fibonacci Methode und gibt das Ergebniss von fib(n)
	 * zurueck
	 * 
	 * @param n
	 *            fib-Index
	 */
	private static void testIterFib(int n) {
		System.out.println("iterFib[" + n + "]=" + iterativeFib((short) n));
	}

	/**
	 * Testet die modulare Fibonacci Methode und gibt das Ergebniss von fib(n)
	 * zurueck
	 * 
	 * @param n
	 *            fib-Index
	 */
	private static void testModFib(int n) {
		short[] value = modularFib((short) n);
		int value_int = iterativeFib((short) n);
		short[] value_iter = intToModShort(value_int, MODULAR_ARRAY);

		System.out.println("modFib__[" + n + "]= {" + value[0] + ", " + value[1] + ", " + value[2] + "}");
		System.out.println("iterFib2[" + n + "]= {" + value_iter[0] + ", " + value_iter[1] + ", " + value_iter[2]
				+ "} = " + value_int);
	}

	/**
	 * Testet mehrere fib-Indizes rekursiv
	 * 
	 * @param tests
	 *            Array der Indizes
	 */
	private static void testRecFibArray(int[] tests) {
		for (int i : tests)
			testRecFib(i);
	}

	/**
	 * Testet mehrere fib-Indizes iterativ
	 * 
	 * @param tests
	 *            Array der Indizes
	 */
	private static void testIterFibArray(int[] tests) {
		for (int i : tests)
			testIterFib(i);
	}

	/**
	 * Testet mehrere fib-Indizes modular
	 * 
	 * @param tests
	 *            Array der Indizes
	 */
	private static void testModFibArray(int[] tests) {
		for (int i : tests)
			testModFib(i);
	}

	/**
	 * Gibt den Index der hoechsten Fibonacci-Zahl aus, die sich noch unter max
	 * befinded.
	 * 
	 * @param max
	 *            Obere Grenze fuer Fibonaccizahlen
	 * @return Index der hoechsten Fibonacci-Zahl unter max
	 */
	private static int getHighestFibIndexBelow(int max) {
		if (max <= 0)
			return 0;
		int value = 0;
		short i = 0;
		do {
			value = iterativeFib(i);
			i++;
		} while (value < max);
		return i - 2;
	}

	/**
	 * Gibt die n-te Fibonaccizahl zurueck. (rekursiv)
	 * 
	 * @param n
	 *            Index
	 * @return n-te Fibonaccizahl
	 */
	public static int recursiveFib(short n) {
		if (n < 1)
			return 0;
		else if (n == 1)
			return 1;
		return recursiveFib((short) (n - 1)) + recursiveFib((short) (n - 2));
	}

	/**
	 * Gibt die n-te Fibonaccizahl zurueck. (iterativ)
	 * 
	 * @param n
	 *            Index
	 * @return n-te Fibonaccizahl
	 */
	public static int iterativeFib(short n) {
		if (n < 1)
			return 0;
		int prev_val = 0;
		int val = 1;
		for (; n > 1; n--) {
			int tmp = val;
			val += prev_val;
			prev_val = tmp;
		}
		return val;
	}

	/**
	 * Gibt die n-te Fibonaccizahl in modularer Arithmetik zurueck. (rekursiv)
	 * 
	 * @param n
	 *            Index
	 * @return n-te Fibonaccizahl in modularer Arithmetik
	 */
	public static short[] modularFib(short n) {
		if (n < 1)
			return intToModShort(0, MODULAR_ARRAY);
		else if (n == 1)
			return intToModShort(1, MODULAR_ARRAY);
		return modularAddition(modularFib((short) (n - 1)), modularFib((short) (n - 2)), MODULAR_ARRAY);
	}

	/**
	 * Transformiert einen Integer in ein Modulares-Short-Array
	 * 
	 * @param value
	 *            Zahlenwert
	 * @param mods
	 *            Modulo-Array
	 * @return Zahlenwert in Modularer Arithmetik
	 */
	private static short[] intToModShort(int value, int[] mods) {
		short[] ret = new short[mods.length];
		for (int i = 0; i < mods.length; i++) {
			ret[i] = (short) ((value) % mods[i]);
		}
		return ret;
	}

	/**
	 * Addiert zwei modulare Integer
	 * 
	 * @param a
	 *            erstes Array
	 * @param b
	 *            zweites Array
	 * @param mods
	 *            Modulo-Array
	 * @return summiertes Array
	 */
	private static short[] modularAddition(short[] a, short[] b, int[] mods) {
		short[] ret = new short[mods.length];
		for (int i = 0; i < mods.length; i++) {
			ret[i] = (short) ((a[i] + b[i]) % mods[i]);
		}
		return ret;
	}

	/**
	 * Main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		testRecFibArray(new int[] { -1, 0, 1, 2, 5, 20, 40 });
		testIterFibArray(new int[] { -1, 0, 1, 2, 5, 20, 40 });
		testModFibArray(new int[] { -1, 0, 1, 2, 5, 20, 40 });
		System.out.println(
				"Hoechste Fibonacci-Zahl mit modularer Arithmetik[Index:" + getHighestFibIndexBelow(MAX_MODULAR_VALUE)
						+ "]: " + iterativeFib((short) getHighestFibIndexBelow(MAX_MODULAR_VALUE)));
	}
}
