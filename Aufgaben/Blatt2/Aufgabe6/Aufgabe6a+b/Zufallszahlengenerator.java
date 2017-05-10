import java.util.Random;

/**
 * 
 * @author Alexander Neuwirth
 * @author Leonhard Segger
 * @author Jonathan Sigrist
 */
public class Zufallszahlengenerator {
	private int current_val, mod, increment, mult;
	private boolean java;
	private Random rand;

	/**
	 * Konstruktor
	 * 
	 * @param mult
	 *            Multiplikator
	 * @param increment
	 *            Increment
	 * @param mod
	 *            Modulo
	 * @param seed
	 *            Startwert
	 */
	public Zufallszahlengenerator(int mult, int increment, int mod, int seed) {
		this(mult, increment, mod, seed, false);
	}

	/**
	 * Konstruktor
	 * 
	 * @param mult
	 *            Multiplikator
	 * @param increment
	 *            Increment
	 * @param mod
	 *            Modulo
	 * @param seed
	 *            Startwert
	 * @param use_java
	 *            Soll Random.class verwendet werden
	 */
	public Zufallszahlengenerator(int mult, int increment, int mod, int seed, boolean use_java) {
		current_val = seed;
		this.mult = mult;
		this.increment = increment;
		this.mod = mod;
		this.java = use_java;
		if (java)
			rand = new Random();
	}

	/**
	 * Generiert einen (pseudo-)zufaelligen Wert zwischen 0 und mod
	 * 
	 * @return Zufallszahl
	 */
	public int random() {
		if (java) {
			current_val = rand.nextInt(mod);
		} else
			current_val = (mult * current_val + increment) % mod;
		return current_val;
	}

	/**
	 * Generiert eine Liste von (pseudo-)zufaelligen Werten zwischen 0 und mod
	 * 
	 * @return Zufallszahl-Array
	 */
	public int[] randomArray(int size) {
		int[] ret = new int[size];
		for (int i = 0; i < size; i++)
			ret[i] = random();
		return ret;
	}
}
