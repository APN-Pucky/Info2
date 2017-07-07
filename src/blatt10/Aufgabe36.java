package blatt10;

import java.util.Arrays;

public class Aufgabe36 {
    
    /** 
     * Methode binSort wie in der Aufgabenstellung beschrieben
     * 
     * @param node der Wurzelknoten des betrachteten (Teil-)Baums
     * @param a int-array, in dem die Werte der Knoten abgelegt werden sollen (Achtung: Array muss so viele Eintraege haben wie Knoten im Baum sind)
     * @param pos aktuelle Array-Position (0 fuer Aufruf mit der Wurzel des gesamten Baums)
     * @return Anzahl Knoten des Baums
     */

	/*
	 * Laufzeit O(n), da jedes Element 1x aufgerufen wird
	 */
    public int binSort(BinTreeNode node,int[] a,int pos) {
        // TODO: implement
	if(node == null) return 0;
	int sum = binSort(node.getLeftChild(),a,pos);
	a[pos +sum] = node.getValue();
	sum++;
	sum += binSort(node.getRightChild(),a,sum+pos);
	return sum;

    }
    
    /**
     * Main-Methode zum Testen der Methode binSort
     */
    public static void main(String[] args) {
        // Teste Implementierung
        // 1. Erzeuge einfachen Baum
        BinTreeNode root = new BinTreeNode(4);
        
        root.setLeftChild(new BinTreeNode(2));
        root.getLeftChild().setRightChild(new BinTreeNode(3));
        root.getLeftChild().setLeftChild(new BinTreeNode(1));
        
        root.setRightChild(new BinTreeNode(6));
        root.getRightChild().setRightChild(new BinTreeNode(7));
        root.getRightChild().setLeftChild(new BinTreeNode(5));
        
        // 2. Erstelle Array
        int result[] = new int[7];
        
        // 3. Erstelle Aufgabe36-Objekt und rufe binSort(...) auf
        Aufgabe36 myAufgabe = new Aufgabe36();
        int numNodes = myAufgabe.binSort(root, result, 0);
        System.out.println("Anzahl Knoten: " + numNodes);
        System.out.println(Arrays.toString(result));
    }
    
}

