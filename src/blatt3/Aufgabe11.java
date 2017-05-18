package blatt3;

/**
 * 
 * @author Alexander Neuwirth
 * @author Leonhard Segger
 * @author Jonathan Sigrist
 * 
 */
public class Aufgabe11 {
	/**
	 * Teilt einen Integer in 3 Integer auf.
	 * 
	 * @param internalFormat
	 *            aufzuteilender Integer
	 * @return dekodierte Integer als Array
	 */
	public static int[] decode(int internalFormat) {
		return decode(internalFormat, 3);
	}

	/**
	 * Teilt einen Integer in numberOfInts Integer auf.
	 * 
	 * @param internalFormat
	 *            aufzuteilender Integer
	 * @param numberOfInts
	 *            Anzahl an Integer des Klartextes
	 * @return dekodierte Integer als Array
	 */
	public static int[] decode(int internalFormat, int numberOfInts) {
		int[] ret = new int[numberOfInts];
		for (int i = 0; internalFormat >= powI(10, i * numberOfInts); i++) {
			for (int j = 0; j < numberOfInts; j++) {
				ret[j] = setDecimalAt(ret[j], getDecimalAt(internalFormat, numberOfInts + i * numberOfInts + j), i);
			}
		}
		for (int j = 0; j < numberOfInts; j++) {
			ret[j] *= getDecimalAt(internalFormat, j) - 1;
		}
		return ret;
	}

	/**
	 * Fuegt Integer eines Arrays zusammen.
	 * 
	 * @param integersArray
	 *            Integer des Klartextes als Array
	 * @return kodierter Integer
	 */
	public static int encode(int[] integersArray) {
		int ret = 0;
		for (int i = 0; i < integersArray.length; i++) {
			ret = setDecimalAt(ret, (int) Math.signum(integersArray[i]) + 1, i);
			int abs_value = Math.abs(integersArray[i]);
			for (int j = 0; abs_value >= powI(10, j); j++) {
				ret = setDecimalAt(ret, getDecimalAt(abs_value, j), integersArray.length * (j + 1) + i);
			}
		}
		return ret;
	}

	/**
	 * Setzt Dezimalziffer an bestimmten Index ein.
	 * 
	 * @param number
	 *            urspruengliche Zahl
	 * @param decimal
	 *            Dezimalstelle
	 * @param index
	 *            Index an dem eingesetzt werden soll
	 * @return resultierende Zahl
	 */
	public static int setDecimalAt(int number, int decimal, int index) {
		if (decimal > 9 || decimal < 0 || index < 0)
			throw new IllegalArgumentException();
		return number + powI(10, index) * decimal;
	}

	/**
	 * Gibt Integer Dezimalziffer an bestimmten Index zurueck.
	 * 
	 * @param number
	 *            Zahl
	 * @param index
	 *            Index von dem gelesen werden soll
	 * @return Dezimalstelle beim Index der Zahl
	 */
	public static int getDecimalAt(int number, int index) {
		if (index < 0)
			throw new IllegalArgumentException();
		number = Math.abs(number);// should not happen :/
		return (number / (powI(10, index))) % 10;
	}

	/**
	 * Rechnet die Potenz von Integer aus.
	 * 
	 * @param base
	 *            Basis der Potenz
	 * @param exp
	 *            Exponent der Potenz
	 * @return Potenz
	 */
	public static int powI(int base, int exp) {
		return (int) Math.pow(base, exp);
	}

	/**
	 * Druckt ein int-Array in der Konsole aus.
	 * 
	 * @param a
	 *            auszugebendes Array
	 */
	public static void printIntArray(int[] a) {
		if (a.length == 0) {
			System.out.println("{}");
			return;
		}
		System.out.print("{" + a[0]);
		for (int i = 1; i < a.length; i++) {
			System.out.print("," + a[i]);
		}
		System.out.println("}");
	}

	/**
	 * Main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int test_int = 222000123;
		int[] test_array = { 123, 124, 125 };

		// test 1
		System.out.print("Decode: " + test_int + " -> ");
		printIntArray(decode(test_int));
		System.out.print("Encode: " + encode(decode(test_int)) + " <- ");
		printIntArray(decode(test_int));

		// test 2
		System.out.print("Encode: " + encode(test_array) + " <- ");
		printIntArray(test_array);
		System.out.print("Decode: " + encode(test_array) + " -> ");
		printIntArray(decode(encode(test_array)));

		// own test
		int[] test_array_own = { 44, -33, 77 };
		System.out.print("Encode+Decode: " + encode(test_array_own) + " <-> ");
		printIntArray(decode(encode(test_array_own)));

	}
}
