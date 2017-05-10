import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Einfacher Canvas (Zeichenflaeche) fuer simple Grafiken wie die Visualisierung
 * der Zufallszahlen.
 * 
 * @author Aaron Scherzinger
 * @author Alexander Neuwirth
 * @author Leonhard Segger
 * @author Jonathan Sigrist
 */
public class ZufallszahlenVisualisierung extends JPanel {

	private int[] a;
	// constants
	private final int MAX_NUMBER = 100;
	private final int X_OFFSET = 15;
	private final int Y_OFFSET = 15;
	private final int TRIANGLE_LENGTH = 10;
	private final int TRIANGLE_HEIGHT = 10;
	private final int PIXEL_SIZE = 2;// Damit man die Punkte auch gut sehen kann
	private final int X_AXIS_STEP_SIZE = 100;

	public ZufallszahlenVisualisierung(int[] a_param) {
		a = a_param;
	}

	/**
	 * Zeichnet ein Polygon in die Graphic, jedoch werden alle Werte um x_off
	 * und y_off verschoben.
	 * 
	 * @param g
	 *            Graphic
	 * @param xpoints
	 *            Array x-Positionen Polygon
	 * @param ypoints
	 *            Array y-Positionen Polygon
	 * @param nPoints
	 *            Anzahl der Ecken des Polygons
	 * @param x_off
	 *            Verschiebung in x Richtung
	 * @param y_off
	 *            Verschiebung in y Richtung
	 */
	private void fillRelativePolygon(Graphics g, int[] xpoints, int[] ypoints, int nPoints, int x_off, int y_off) {
		for (int i = 0; i < nPoints; i++)
			xpoints[i] = xpoints[i] + x_off;
		for (int i = 0; i < nPoints; i++)
			ypoints[i] = ypoints[i] + y_off;
		g.fillPolygon(xpoints, ypoints, nPoints);
	}

	/**
	 * Zeichnet ein kleines Rechteck(Punkt) in die Graphic, jedoch werden alle
	 * Werte um x_off und y_off verschoben. Auch ist die Ausrichtung des
	 * Koordinatensystems anders: Y ^ | | 0----->X
	 * 
	 * @param g
	 *            Graphic
	 * @param x
	 *            x-Positionen Punkt
	 * @param y
	 *            y-Positionen Punkt
	 * @param x_off
	 *            Verschiebung in x Richtung
	 * @param y_off
	 *            Verschiebung in y Richtung
	 */
	private void drawAbsolutePoint(Graphics g, int x, int y, int x_off, int y_off) {
		g.fillRect(x + x_off, -y + y_off, PIXEL_SIZE, PIXEL_SIZE);
	}

	/**
	 * Zeichnet ein kleines Rechteck(Punkt) in die Graphic, jedoch werden alle
	 * Werte um x_off und y_off verschoben. Auch ist die Ausrichtung des
	 * Koordinatensystems anders: Y ^ | | 0----->X Der y Wert wird mit y_scale
	 * multipliziert.
	 * 
	 * @param g
	 *            Graphic
	 * @param x
	 *            x-Positionen Punkt
	 * @param y
	 *            y-Positionen Punkt
	 * @param x_off
	 *            Verschiebung in x Richtung
	 * @param y_off
	 *            Verschiebung in y Richtung
	 * @param y_scale
	 *            Skalierung des Y-Werts
	 */
	private void drawSemiRelativePoint(Graphics g, int x, int y, int x_off, int y_off, double y_scale) {
		drawAbsolutePoint(g, x, (int) (y * y_scale), x_off, y_off);
	}

	/**
	 * Diese Funktion wird zur Laufzeit immer dann aufgerufen, wenn der Canvas
	 * neu gezeichnet werden muss.
	 * 
	 * @param g
	 *            Graphics-Objekt zum Zeichnen
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// init
		final int scale_height = (getHeight() - Y_OFFSET * 4);
		g.setColor(Color.RED);

		// axis lines
		g.drawLine(X_OFFSET, Y_OFFSET, X_OFFSET, getHeight() - Y_OFFSET);
		g.drawLine(X_OFFSET, getHeight() - Y_OFFSET, getWidth() - X_OFFSET, getHeight() - Y_OFFSET);
		// axis triangles
		fillRelativePolygon(g, new int[] { 0, 0, TRIANGLE_HEIGHT },
				new int[] { -TRIANGLE_LENGTH / 2, TRIANGLE_LENGTH / 2, 0 }, 3, getWidth() - X_OFFSET,
				getHeight() - Y_OFFSET);
		fillRelativePolygon(g, new int[] { -TRIANGLE_LENGTH / 2, TRIANGLE_LENGTH / 2, 0 },
				new int[] { 0, 0, -TRIANGLE_HEIGHT }, 3, X_OFFSET, Y_OFFSET);

		// y-axis
		g.drawString("Zufallszahl", X_OFFSET + TRIANGLE_LENGTH, Y_OFFSET);
		g.drawString("" + MAX_NUMBER, X_OFFSET, getHeight() - Y_OFFSET - scale_height);
		// x-axis
		g.drawString("Durchgang", getWidth() - X_OFFSET - g.getFontMetrics().stringWidth("Durchgang"),
				getHeight() - Y_OFFSET - TRIANGLE_LENGTH);
		for (int i = 0; i < getWidth() / X_AXIS_STEP_SIZE; i++) {
			g.drawString("" + i * X_AXIS_STEP_SIZE,
					X_OFFSET - g.getFontMetrics().stringWidth("" + i * X_AXIS_STEP_SIZE) / 2 + i * X_AXIS_STEP_SIZE,
					getHeight() - Y_OFFSET + TRIANGLE_LENGTH);
		}
		// points from a
		g.setColor(Color.BLACK);
		for (int i = 0; i < a.length; i++)
			drawSemiRelativePoint(g, i, a[i], X_OFFSET, getHeight() - Y_OFFSET, scale_height / (double) MAX_NUMBER);

	}

	/**
	 * Main-Methode, die ein Fenster erzeugt und diesem ein Objekt der Klasse
	 * ZufallszahlenVisualisierung hinzufuegt.
	 */
	public static void main(String[] args) {
		// -------
		// Ein Zufallsgenerator nach dem ersten Punkt
		int[] test_1 = new Zufallszahlengenerator(21, 23, 100, 1).randomArray(200);
		// nach dem zweiten Punkt
		int[] test_2 = new Zufallszahlengenerator(21, 7, 100, 1).randomArray(200);
		// und mit dem Argument fuer zufaellige Integer von JAVA
		int[] test_3 = new Zufallszahlengenerator(21, 23, 100, 1, true).randomArray(200);

		int[] test97 = new int[] {};
		int offset = 0;

		for (int i = 0; i < 100; i++) {
			test97 = append(test97, new Zufallszahlengenerator(offset + i, 0, 97, 1).randomArray(100));
		}
		// --------
		JFrame frame = new JFrame("Visualisierung von Zufallszahlengeneratoren");
		frame.getContentPane().add(new ZufallszahlenVisualisierung(test97), BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 300);
		frame.setVisible(true);
	}

	/**
	 * Gibt ein neues Array zurueck bestehend aus a und b.
	 * 
	 * @param a
	 *            1. Array
	 * @param b
	 *            2. Array
	 * 
	 * @return Zusammengefuegtes Array
	 */
	private static int[] append(int[] a, int[] b) {
		int[] ret = new int[a.length + b.length];
		System.arraycopy(a, 0, ret, 0, a.length);
		System.arraycopy(b, 0, ret, a.length, b.length);
		return ret;
	}
}
