public interface DEQueue<E>
{
	public void enqueuef(E elem);	
	public void enqueueb(E elem);	
	public boolean empty();
	public E front();
	public E back();
	public void dequeuef();	
	public void dequeueb();	
}

