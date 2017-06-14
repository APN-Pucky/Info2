package blatt8;

public class MyMatrix 
{
		int n;
		double[] a;
		
		public MyMatrix(int n)
		{
			this.n =n;
			a = new double[(int)(Math.pow(n, 2)-n)/2];
		}
		
		public double get(int i , int j)
		{
			int mult = 1;
			if(i>=n||j>=n||i<0||j<0)throw new ArrayIndexOutOfBoundsException();
			if(i==j)return 0;
			if(i>j)
			{
				mult = j;
				j=i;
				i=mult;
				mult =-1;
			}
			return a[n*i+j    -   i*(i+1)/2-i-1]*mult;
		}
		
		public static void main(String[] args)
		{
			MyMatrix mm = new MyMatrix(5);
			mm.a= new double[]{1,2,3,4,5,6,0,0,0,0};
			for(int i =0;i < 5;i++)
			{
				for(int j =0;j < 5;j++)
				{
					System.out.print(mm.get(i, j) + "  ");
				}
				System.out.println();
			}
		}
}
