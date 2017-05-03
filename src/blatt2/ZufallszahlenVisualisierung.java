package blatt2;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Einfacher Canvas (Zeichenflaeche) fuer simple Grafiken wie die Visualisierung der Zufallszahlen.
 * @author Aaron Scherzinger
 * @author Alexander Neuwirth
 * @author Leonhard Segger
 * @author Jonathan Sigrist
 */
public class ZufallszahlenVisualisierung extends JPanel 
{

	private int[] a;
	//constants
	private final int max_number = 100;
	private final int x_offset= 15;
	private final int y_offset= 15;
	private final int triangle_length = 10;
	private final int triangle_height = 10;
	private final int pixel_size = 1;
	private final int x_axis_step_size = 100;
	
	public ZufallszahlenVisualisierung(int[] a_param)
	{
		a=a_param;
	}
	
	/**
	 * Zeichnet ein Polygon in die Graphic, jedoch werden alle Werte um x_off und y_off verschoben.
	 * @param g			Graphic
	 * @param xpoints	Array x-Positionen Polygon
	 * @param ypoints	Array y-Positionen Polygon
	 * @param nPoints	Anzahl der Ecken des Polygons
	 * @param x_off		Verschiebung in x Richtung
	 * @param y_off		Verschiebung in y Richtung
	 */
	private void fillRelativePolygon(Graphics g, int[] xpoints, int[] ypoints, int nPoints, int x_off, int y_off)
	{
		for(int i =0; i < nPoints;i++)xpoints[i] = xpoints[i]+x_off;
		for(int i =0; i < nPoints;i++)ypoints[i] = ypoints[i]+y_off;
		g.fillPolygon(xpoints, ypoints, nPoints);
	}
	
	/**
	 * Zeichnet ein kleines Rechteck(Punkt) in die Graphic, jedoch werden alle Werte um x_off und y_off verschoben.
	 * Auch ist die Ausrichtung des Koordinatensystems anders:
	 * 		Y
	 * 		^
	 * 		|
	 * 		|
	 * 		0----->X
	 * @param g			Graphic
	 * @param x			x-Positionen Punkt
	 * @param y			y-Positionen Punkt
	 * @param x_off		Verschiebung in x Richtung
	 * @param y_off		Verschiebung in y Richtung
	 */
	private void drawAbsolutePoint(Graphics g, int x, int y, int x_off, int y_off)
	{
		g.drawRect(x+x_off, -y+y_off, pixel_size,pixel_size );
	}
	
	/**
	 * Zeichnet ein kleines Rechteck(Punkt) in die Graphic, jedoch werden alle Werte um x_off und y_off verschoben.
	 * Auch ist die Ausrichtung des Koordinatensystems anders:
	 * 		Y
	 * 		^
	 * 		|
	 * 		|
	 * 		0----->X
	 * Der y Wert wird mit y_scale multipliziert.
	 * @param g			Graphic
	 * @param x			x-Positionen Punkt
	 * @param y			y-Positionen Punkt
	 * @param x_off		Verschiebung in x Richtung
	 * @param y_off		Verschiebung in y Richtung
	 * @param y_scale	Skalierung des Y-Werts
	 */
    private void drawSemiRelativePoint(Graphics g, int x, int y, int x_off, int y_off, double y_scale)
    {
    	drawAbsolutePoint(g, x,(int) (y*y_scale),x_off,y_off);
    }
	
    /**
     * Diese Funktion wird zur Laufzeit immer dann aufgerufen, wenn der Canvas neu gezeichnet werden muss.
     * @param g Graphics-Objekt zum Zeichnen
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //init
        final int scale_height = (getHeight()-y_offset*4);
        g.setColor(Color.RED);
        
        //axis lines
        g.drawLine(x_offset, y_offset, x_offset,getHeight()-y_offset);
        g.drawLine(x_offset, getHeight()-y_offset, getWidth()-x_offset,getHeight()-y_offset);
        //axis triangles
        fillRelativePolygon(g,new int[]{0,0,triangle_height},new int[]{-triangle_length/2,triangle_length/2,0}, 3, getWidth()-x_offset,getHeight()-y_offset);      
        fillRelativePolygon(g,new int[]{-triangle_length/2,triangle_length/2,0},new int[]{0,0,-triangle_height}, 3, x_offset, y_offset);
        
        //y-axis
        g.drawString("Zufallszahl", x_offset+triangle_length, y_offset);
        g.drawString(""+max_number, x_offset,getHeight()-y_offset-scale_height);
        //x-axis
        g.drawString("Durchgang", getWidth()-x_offset-g.getFontMetrics().stringWidth("Durchgang"),getHeight()-y_offset-triangle_length);
        for(int i = 0 ; i < getWidth()/x_axis_step_size;i++)
        {
        	g.drawString(""+i*x_axis_step_size, x_offset-g.getFontMetrics().stringWidth(""+i*x_axis_step_size)/2+i*x_axis_step_size,getHeight()-y_offset+triangle_length);
        }
        //points from a
        g.setColor(Color.BLACK);
        for(int i=0; i<a.length;i++) drawSemiRelativePoint(g,i,a[i],x_offset, getHeight()-y_offset, scale_height/(double)max_number);
        
    }

    /**
     * Main-Methode, die ein Fenster erzeugt und diesem ein Objekt der Klasse ZufallszahlenVisualisierung hinzufuegt.
     */
    public static void main(String[] args) {
    	//-------
    	int[] test_1 = new Zufallszahlengenerator(21, 23, 100, 1).randomArray(200);
    	int[] test_2 = new Zufallszahlengenerator(21, 7, 100, 1).randomArray(200);
    	int[] test_3 = new Zufallszahlengenerator(21, 23, 100, 1,true).randomArray(200);
    	
    	int[] test97 = new int[]{};
    	int offset=0;		
    	
    	for(int i = 0; i <100;i++)
    	{
    		test97 = append(test97,new Zufallszahlengenerator(offset+i, 0, 97, 1).randomArray(100));
    	}
    	//--------
        JFrame frame = new JFrame("Visualisierung von Zufallszahlengeneratoren");
        frame.getContentPane().add(new ZufallszahlenVisualisierung(test97), BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setVisible(true);
    }
    
    /**
     * Gibt ein neues Array zurück bestehend aus a und b.
     * @param a 	1. Array
     * @param b		2. Array
     * @return		Zusammengefügtes Array
     */
    private static int[] append(int[] a, int[] b)
    {
    	int[] ret = new int[a.length+b.length];
    	System.arraycopy(a, 0, ret, 0, a.length);
    	System.arraycopy(b, 0, ret, a.length, b.length);
    	return ret;
    }
}
