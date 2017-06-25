//not Thread-safe
public class ZirkDEQueue<E> implements DEQueue<E>
{
	private E[] b;
	private int front = 0;
	private int back = 0;

	public ZirkDEQueue(int maxsize)
	{
		b = (E[])new Object[maxsize+1];
	}

	public boolean full()
	{
		return ((front+1)%b.length == back);
	}

	public boolean empty()
	{
		return front == back;
	}

	public void enqueuef(E elem)
	{
		if(full())
		{
		}
		else 
		{
			b[front]=elem;
			front= (front+1)%b.length;
		}
	}

	public void enqueueb(E elem)
	{
		if(full())
		{
		}
		else 
		{
			back = (back-1+b.length)%b.length;
			b[back]=elem;
		}
	}

	public E front()
	{
		if(empty())
		{
			return null;
		}
		else
		{
			return b[(front-1+b.length)%b.length];
		}
	}

	public E back()
	{
		if(empty())
		{
			return null;
		}
		else
		{
			return b[back];
		}
	}

	public void dequeuef()
	{
		if(!empty())
		{
			front = (front-1+b.length)%b.length;
			b[front] = null;
		}
	}

	public void dequeueb()
	{
		if(!empty())
		{
			b[back] = null;
			back = (back+1)%b.length;
		}
	}




}
