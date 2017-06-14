package blatt7;

public class Aufgabe24Test 
{

	public static void main(String[] args)
	{
		int[] a = new int[]{1,2,2,4,6};
		int[] b = new int[]{1,1,2,2,4,4,4,4,64};
		
		int c = 0;
		int j = 0;
		for(int i =0; i < a.length;i++)
		{
			while(a[i]>b[j] && j<b.length-1)j++;
			if(a[i]==b[j])
			{
				c++;
				while(a[i]==b[j]&& j<b.length-1)j++;
			}
		}
		
		System.out.println(c);
	}
}
