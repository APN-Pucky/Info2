public class AVLBinTree<E extends Comparable<E>>
{
	private AVLBinTreeNode<E> root;

	public AVLBinTree(){}

	public void insert(E elem)
	{
		if(root ==null)
		{
			root = new AVLBinTreeNode<E>(elem);
		}
		else
		{
			AVLBinTreeNode<E> cur = null;
			AVLBinTreeNode<E> next = root;
			do
			{
				cur = next;
				if(elem.compareTo(cur.getValue())<0)
				{
					next = cur.getLeftChild();
				}
				else
				{
					next = cur.getRightChild();
				}
			}while(next != null);
			
			next = new AVLBinTreeNode<E>(elem);
			if(elem.compareTo(cur.getValue())<0)
			{
				cur.setLeftChild(next);
			}
			else
			{
				cur.setRightChild(next);
			}
			//balance
			int last_orient = 0;
			while(cur !=null)
			{
				//i1
				if(cur.getBalance()==0)
				{
					last_orient = cur.childOrientation(next);
					//System.out.println("LO:" + last_orient  + " " + cur.getValue().toString());
					cur.setBalance(last_orient);
					next = cur;
					cur = cur.getParent();
				}
				else 
				{
					//i2
					if(cur.getBalance() == -cur.childOrientation(next))
					{
						cur.setBalance(0);
					}
					//i3
					else
					{
						 if(last_orient+cur.childOrientation(next) ==0)
						 {
							//doppelrot
							if(last_orient <0)
							{
								rotateRL(cur);
							}
							else
							{
								rotateLR(cur);
							}
						 }
						 else
						 {
							//rot
							if(cur.childOrientation(next) ==1)
							{
								rotateL(cur);
							}
							else
							{
								rotateR(cur);
							}
						 }
					}
					cur = null; //break
				}
					
			}

		}		
		System.out.println("I root: " +root.toString());
	}

	public void delete(E elem)
	{
		AVLBinTreeNode<E> cur = null;
		AVLBinTreeNode<E> next = root;
		do
		{
			cur = next;
			if(elem.compareTo(cur.getValue())<0)
			{
				next = cur.getLeftChild();
			}
			else if (elem.compareTo(cur.getValue())>0)
			{
				next = cur.getRightChild();
			}
			else if (elem.compareTo(cur.getValue())==0)
			{
				//del cur
				System.out.println(cur.toString());
				AVLBinTreeNode n, n_child,n_tmp;
				if(cur.isLeaf())
				{
					if(cur == root)
					{
						root =null;
						n=null;
					}
					n = cur.getParent();
					n_child = null;
					cur.replace(null);
				}
				else
				{
					if(cur.getLeftChild() !=null)
					{
						n_tmp = null;
						n = cur.getLeftChild().getRightmost();
						n_tmp = n.getParent();
						n_child = n.getLeftChild();
						n.replace(n.getLeftChild());
						n.setLeftChild(cur.getLeftChild());
						n.setRightChild(cur.getRightChild());
						cur.replace(n);
						n = n_tmp;
					}
					else
					{
						n_tmp = null;
						n= cur.getRightChild().getLeftmost();
						n_tmp = n.getParent();
						n_child = n.getRightChild();
						n.replace(n.getRightChild());
						n.setLeftChild(cur.getLeftChild());
						n.setRightChild(cur.getRightChild());
						cur.replace(n);
						n = n_tmp;
					}
				}
				//balance Node n -1
				int last_orient = 0;
				while(n!=null)
				{
					//d1
					if(n.getBalance() ==0)
					{
						last_orient = n.childOrientation(n_child);
						n.setBalance(-last_orient);	


						//n_child = n;
						//n = n.getParent();
						n=null; //break
					}
					else
					{
						//System.out.println(n.childOrientation(n_child));
						//d2
						if(n.getBalance() == n.childOrientation(n_child))
						{
							last_orient = n.childOrientation(n_child);
							n.setBalance(0);
							n_child = n;
							n = n.getParent();
						}	
						//d3
						else
						{
							if(n.childOrientation(n_child)>0)
							{
								int b = 0;
								if(n.getLeftChild() != null)b=n.getLeftChild().getBalance();
								n_child = n;
								n_tmp = n.getParent();
								if(b>0)rotateLR(n);
								else rotateR(n);
								n = n_tmp;
							}
							else
							{
								int b = 0;
							 	if(n.getRightChild() != null)b=n.getRightChild().getBalance();
								n_child = n;
								n_tmp = n.getParent();
								if(b<0)rotateRL(n);
								else rotateL(n);
								n = n_tmp;
							}

							System.out.println("root: "+ root.toString());
						}
					}

				}
				next =null;
			}
		}while(next != null);
		System.out.println("D root: " +root.toString());
	}

	public boolean contains(E elem)
	{
		cur = root;
		while(cur!=null)
		{
			if(elem.compareTo(cur) >0)
			{
				cur = cur.getRightChild();
			}
			else if(elem.compareTo(cur)<0)
			{
				cur = cur.getLeftChild();
			}
			else
			{
				return true;
			}

		}
		return false;
	}

	private void rotateRL(AVLBinTreeNode<E> a)
	{
		System.out.println("ROT RL:"+ a.toString());
		AVLBinTreeNode<E> par = a.getParent();
		int orient = 0;
		if(par != null) orient = par.childOrientation(a);
		AVLBinTreeNode<E> b = a.getRightChild();
		AVLBinTreeNode<E> c = b.getLeftChild();
		//Right rot
		b.setLeftChild(c.getRightChild());
		c.setRightChild(b);

		//Left rot
		a.setRightChild(c.getLeftChild());
		c.setLeftChild(a);
		
		if(orient >0)
		{
			par.setRightChild(c);
		}
		else if (orient <0)
		{
			par.setLeftChild(c);
		}
		else
		{
			root = c;
			c.setParent(null);
		}
		//balance
		if(c.getBalance()>0)
		{
			a.setBalance(-1);
			b.setBalance(0);
		}
		else if (c.getBalance() ==0)
		{
			a.setBalance(0);
			b.setBalance(0);
		}
		else
		{
			a.setBalance(0);
			b.setBalance(1);
		}
		c.setBalance(0);
				
	}

	private void rotateLR(AVLBinTreeNode<E> a)
	{
		System.out.println("ROT LR:"+ a.toString());
		AVLBinTreeNode<E> par = a.getParent();
		int orient = 0;
		if(par != null)orient = par.childOrientation(a);
		AVLBinTreeNode<E> b = a.getLeftChild();
		AVLBinTreeNode<E> c = b.getRightChild();
		//left rot
		b.setRightChild(c.getLeftChild());
		c.setLeftChild(b);

		//right rot 
		a.setLeftChild(c.getRightChild());
		c.setRightChild(a);
		
		if(orient >0)
		{
			par.setRightChild(c);
		}
		else if(orient <0)
		{
			par.setLeftChild(c);
		}
		else
		{
			root = c;
			c.setParent(null);
		}
		//balance..
		if(c.getBalance()>0)
		{
			b.setBalance(-1);
			a.setBalance(0);
		}
		else if (c.getBalance() ==0)
		{
			b.setBalance(0);
			a.setBalance(0);
		}
		else
		{
			b.setBalance(0);
			a.setBalance(1);
		}
		c.setBalance(0);
				
	}


	private void rotateL(AVLBinTreeNode<E> a)
	{ 
		System.out.println("ROT L:"+ a.toString());
		AVLBinTreeNode<E> par = a.getParent();
		int orient  = 0 ;
		if(par !=null)orient = par.childOrientation(a);
		AVLBinTreeNode<E> b = a.getRightChild();
		a.setRightChild(b.getLeftChild());
		b.setLeftChild(a);	
		if(orient >0)
		{
			par.setRightChild(b);
		}
		else if (orient <0)
		{
			par.setLeftChild(b);
		}
		else
		{
			root = b;
			b.setParent(null);
		}
		//balance
		if(b.getBalance()==0)
		{
			a.setBalance(1);
			b.setBalance(-1);
		}
		else
		{
			b.setBalance(0);
			a.setBalance(0);
		}

	}

	private void rotateR(AVLBinTreeNode<E> a)
	{
		System.out.println("ROT R: " + a.toString());
		AVLBinTreeNode<E> par = a.getParent();
		int orient =0;
		if(par!=null)orient = par.childOrientation(a);
		AVLBinTreeNode<E> b = a.getLeftChild();
		a.setLeftChild(b.getRightChild());
		b.setRightChild(a);	
		if(orient >0)
		{
			par.setRightChild(b);
		}
		else if (orient <0)
		{
			par.setLeftChild(b);
		}
		else
		{
			root = b;
			b.setParent(null);
		}
		//balance
		if(b.getBalance()==0)
		{
			b.setBalance(1);
			a.setBalance(-1);
		}
		else
		{
			b.setBalance(0);
			a.setBalance(0);
		}

	}

	public String toString()
	{
		return root.toString();
	}
}

