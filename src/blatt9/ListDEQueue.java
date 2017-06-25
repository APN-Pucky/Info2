public class ListDEQueue<E> implements DEQueue<E>
{
	public class ListNode<E>
	{
		private E elem;
		private ListNode<E> next = null;
		private ListNode<E> prev = null;
		public ListNode(E element)
			{elem=element;}
		public void setNextNode(ListNode<E> next)
			{this.next = next;}
		public void setPrevNode(ListNode<E> prev)
			{this.prev = prev;}
		public ListNode<E> getNextNode()
			{return next;}
		public ListNode<E> getPrevNode()
			{return prev;}
		public void setData(E element)
			{elem = element;}
		public E getData()
			{return elem;}
	}	

	ListNode<E> front;
	ListNode<E> back;
	int size = 0;

	public ListDEQueue()
	{

	}

	public boolean empty()
	{
		return size ==0;
	}

	public void enqueuef(E elem)
	{
		ListNode<E> tmp = new ListNode<E>(elem);
		if(empty())
		{
			front = tmp;
			back = front;
		}	
		else
		{
			front.setNextNode(tmp);
			tmp.setPrevNode(front);
			front = tmp;	
		}
		size++;
	}

	public void enqueueb(E elem)
	{
		ListNode<E> tmp = new ListNode<E>(elem);
		if(empty())
		{
			front = tmp;
			back = front;
		}
		else
		{
			back.setPrevNode(tmp);
			tmp.setNextNode(back);
			back = tmp;
		}
		size++;
	}

	public E front()
	{
		if(empty())return null;
		else return front.getData();
	}

	public E back()
	{
		if(empty())return null;
		else return back.getData();
	}

	public void dequeuef()
	{
		if(!empty())
		{
			size--;
			front = front.getPrevNode();
		}
	}

	public void dequeueb()
	{
		if(!empty())
		{
			size--;
			back = back.getNextNode();
		}
	}
		
}
		
