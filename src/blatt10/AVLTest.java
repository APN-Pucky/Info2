public class AVLTest
{
	public static void main(String[] args)
	{

		AVLBinTree tree = new AVLBinTree<Integer>();
		/*tree.insert(1);
		tree.insert(2);
		tree.insert(5);
		tree.insert(3);
		tree.insert(4);
		tree.insert(6);
		tree.insert(7);
		tree.delete(6);*/
		tree.insert(19);
		tree.insert(13);
		tree.insert(52);
		tree.insert(6);
		tree.insert(18);
		tree.insert(29);
		tree.insert(77);
		tree.insert(3);
		tree.insert(11);
		tree.insert(15);
		tree.insert(23);
		tree.insert(39);
		tree.insert(57);
		tree.insert(83);
		tree.insert(8);
		tree.insert(21);
		tree.insert(38);
		tree.insert(54);
		tree.insert(69);
		tree.insert(95);
		tree.insert(63);
		tree.delete(15);
		System.out.println(tree.toString());

	}
}
