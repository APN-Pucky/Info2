package blatt10;

/**
 * Klasse BinTreeNode realisiert einen einfachen Knoten in einem binaeren Baum.
 * Hierbei wird der Integer-Wert, den der Knoten enthaelt, im Konstruktor
 * angegeben.
 *
 * @author Aaron Scherzinger
 */
public class BinTreeNode {

	public BinTreeNode(int value) {
		// setze den Wert des Knotens
		this.value = value;
	}

	/* getter und setter */

	public BinTreeNode getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(BinTreeNode left) {
		leftChild = left;
	}

	public BinTreeNode getRightChild() {
		return rightChild;
	}

	public void setRightChild(BinTreeNode right) {
		rightChild = right;
	}

	public int getValue() {
		return value;
	}

	/* member attribute */
	private int value;
	private BinTreeNode leftChild;
	private BinTreeNode rightChild;

}
