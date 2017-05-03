package blatt1;

import java.util.Scanner;

/**
 * 
 * @author Alexander Neuwirth
 * @author Leonhard Segger
 * @author Jonathan Sigrist
 * 
 */
public class Aufgabe4 {

	/**
	 * Start-Methode zur Berechnung des kleinsten gemeinsamen Vielfachen.
	 * 
	 * Falls Werte als Konsolenargumente uebergeben werden, werden diese als
	 * Parameter fuer die Rechnung benutzt. Andernfalls werden die Standart
	 * Werte 798 und 3800 bennutzt.
	 * 
	 * Falls der erste Parameter "-h" oder "--help" seien sollte, so wird ein
	 * kurzer Hilfetext zur Parametereingabe ausgegeben.
	 * 
	 * Falls der erste Parameter "-i" oder "--interactive" seien sollte, so wird
	 * der Benutzer aktiv nach Werten zur Berechnung gefragt.
	 * 
	 * 
	 * @param args
	 *            Von der Konsole uebergebene Parameter
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			printCalc(798, 3800);
		} else if (args[0].equals("-h") || args[0].equals("--help")) {
			System.out.println("Test1");
			printHelp();
		} else if (args[0].equals("-i") || args[0].equals("--interactive")) {
			System.out.println("Test2");
			Scanner scan = new Scanner(System.in);
			System.out.println("Bitte geben sie Zahl 1 ein:");
			String a = scan.nextLine();
			System.out.println("Bitte geben sie Zahl 2 ein:");
			String b = scan.nextLine();
			scan.close();
			printCalc(a, b);
		} else if (args.length == 2) {
			printCalc(args[0], args[1]);
		} else {
			printHelp();
		}

	}

	/**
	 * Gibt das Ergebnis der Rechnung in einem Text eingebettet in der Konsole
	 * aus.
	 * 
	 * @param a
	 *            Erste Zahl
	 * @param b
	 *            Zweite Zahl
	 */
	static void printCalc(int a, int b) {
		System.out.println("Das kleinste gemeinsame Vielfache von " + a + " und " + b + " ist " + lcm(a, b) + ".");
	}

	/**
	 * Konvertiert die uebergebenen Strings in Integer und gibt das Ergebnis der
	 * Rechnung in einem Text eingebettet in der Konsole aus. Gibt eine
	 * Errornachricht aus, falls ein Fehrer bei der Konvertierung aufgetreten
	 * ist.
	 * 
	 * @param a
	 *            Erste Zahl als String
	 * @param b
	 *            Zweite Zahl als String
	 */
	static void printCalc(String a, String b) {
		try {
			int numA = Integer.parseInt(a);
			int numB = Integer.parseInt(b);
			printCalc(numA, numB);
		} catch (Exception e) {
			System.err.println("Eine oder mehrere Eingaben waren fehlerhaft.\n"
					+ " Bitte ueberpruefen sie ihre Eingaben und versuchen sie es erneut.");
		}
	}

	/**
	 * Gibt einen kurzen Hilfedialog in der Konsole aus.
	 */
	static void printHelp() {
		System.out.println("Das Java Programm gibt das kleinsten gemeinsame Vielfache aus.\n\n"
				+ "-h, --help\t\tEs wird dieser kurze Hilfetext zur Benutzung ausgegeben.\n"
				+ "-i, --interactive\tDer Benutzer wird nach Eingaben zur Berechnung gefragt.\n"
				+ "[zahl1] [zahl2]\t\tDie beiden Zahlen werden zur Berechnung genutzt\n\n"
				+ "Beispiel:\t\tjava aufgabe4 40 77");
	}

	/**
	 * Das Problem des kleinsten gemeinsamen Vielfachen wird auf das Problem des
	 * groessten gemeinsamen Teilers reduziert.
	 * 
	 * @param a
	 *            Erste Zahl
	 * @param b
	 *            Zweite Zahl
	 * @return kleinstes gemeinsames Vielfaches von a und b
	 */
	static int lcm(int a, int b) {
		return a * b / gcd(a, b);
	}

	/**
	 * Die Methode sucht iterativ nach den Euklidschen Algorithmus den groessten
	 * gemeinsamen Teiler der Zahlen a und b.
	 * 
	 * @param a
	 *            Erste Zahl
	 * @param b
	 *            Zweite Zahl
	 * @return groesster gemeinsamer Teiler von a und b
	 */
	static int gcd(int a, int b) {
		int r;
		while (b != 0) {
			r = a % b;
			a = b;
			b = r;
		}
		return a;
	}

}