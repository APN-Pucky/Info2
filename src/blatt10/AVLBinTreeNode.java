package blatt10;

/**
 * Klasse BinTreeNode realisiert einen einfachen Knoten in einem binaeren Baum.
 * Hierbei wird der Integer-Wert, den der Knoten enthaelt, im Konstruktor
 * angegeben.
 *
 * @author Aaron Scherzinger
 * @author Jonathan Sigrist
 */
public class AVLBinTreeNode<E> {

	public AVLBinTreeNode(E value) {
		// setze den Wert des Knotens
		this.value = value;
	}

	/* getter und setter */

	public AVLBinTreeNode<E> getParent() {
		return parent;
	}

	public void setParent(AVLBinTreeNode<E> par) {
		parent = par;
	}

	public AVLBinTreeNode<E> getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(AVLBinTreeNode<E> left) {
		leftChild = left;
		if (left != null)
			left.setParent(this);
	}

	public AVLBinTreeNode<E> getRightChild() {
		return rightChild;
	}

	public void setRightChild(AVLBinTreeNode<E> right) {
		rightChild = right;
		if (right != null)
			right.setParent(this);
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int b) {
		balance = b;
	}

	public E getValue() {
		return value;
	}

	public boolean isRoot() {
		return parent == null;
	}

	public boolean isLeaf() {
		return leftChild == null && rightChild == null;
	}

	public AVLBinTreeNode<E> getRightmost() {
		AVLBinTreeNode<E> prev = null;
		AVLBinTreeNode<E> cur = this;
		do {
			prev = cur;
			cur = prev.getRightChild();
		} while (cur != null);
		return prev;
	}

	public AVLBinTreeNode<E> getLeftmost() {
		AVLBinTreeNode<E> prev = null;
		AVLBinTreeNode<E> cur = this;
		do {
			prev = cur;
			cur = prev.getLeftChild();
		} while (cur != null);
		return prev;
	}

	public void replace(AVLBinTreeNode<E> n) {
		if (parent == null)
			return;
		if (parent.childOrientation(this) > 0) {
			parent.setRightChild(n);
		} else {
			parent.setLeftChild(n);
		}
	}

	public String toString() {
		String ret = " (";
		if (leftChild != null)
			ret += leftChild.toString();
		ret += value.toString() + ((balance > 0) ? "+" : ((balance < 0) ? "-" : "*"));
		if (rightChild != null)
			ret += rightChild.toString();
		ret += ") ";
		return ret;
	}

	public int childOrientation(AVLBinTreeNode<E> node) {
		if (node == leftChild && node != rightChild)
			return -1;
		if (node == rightChild && node != leftChild)
			return 1;
		if (node == null && leftChild == null && rightChild == null)
			return getBalance();
		return 0;
	}

	/* member attribute */
	private E value;
	private int balance = 0;
	private AVLBinTreeNode<E> parent = null;
	private AVLBinTreeNode<E> leftChild;
	private AVLBinTreeNode<E> rightChild;

}
