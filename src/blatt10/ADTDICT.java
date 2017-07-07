package blatt10;
/**
 * Kommentier noch nötig =>Joey
 * 
 * @param <E>
 */
public class ADTDICT<E extends Comparable<E>> 
{
	private AVLBinTree<E> tree ;
	
	public ADTDICT()
	{
		tree = new AVLBinTree<E>();
	}
	
	public void insert(E elem){tree.insert(elem);}
	public void delete(E elem){tree.delete(elem);}
	public boolean member(E elem){return tree.contains(elem);}
}
