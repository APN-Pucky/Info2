public class Test
{
	public static void main(String[] args)
	{
		DEQueue<Long> zq = new ListDEQueue<Long>();
		//DEQueue<Long> zq = new ZirkDEQueue<Long>(100);
		zq.enqueuef(17L);
		zq.enqueueb(18L);
		zq.enqueuef(27L);
		System.out.println(zq.back());
		zq.dequeueb();
		System.out.println(zq.back());
		zq.dequeueb();
		System.out.println(zq.back());
		zq.dequeueb();
		System.out.println(zq.back());
		System.out.println(zq.front());
		zq.enqueueb(67L);
		zq.enqueueb(47L);
		zq.enqueuef(37L);
		System.out.println(zq.front());
		zq.dequeuef();
		System.out.println(zq.front());
		zq.dequeuef();
		System.out.println(zq.back());
		zq.dequeueb();

	}
}
