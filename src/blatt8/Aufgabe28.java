package blatt8;

public class Aufgabe28 {

	public static void main(String[] args)
	{
		int[][] band= new int[][]{{0,5,4},{3,2,1},{1,2,3},{4,5,6},{9,8,7},{6,5,0}};
		int[][] band2= new int[][]{{0,5,2},{3,2,1},{1,2,3},{4,5,6},{9,8,7},{6,5,0}};
		for(int i =0;i<6;i++)
		{
			for(int j = 0;j< 6;j++)
			{
				System.out.print(getVal(band,6,1,i,j) + " ");
			}
			System.out.println();
		}
		System.out.println("Quad:");
		band =  multipliziereBandmatrizen(band,band,6,1);
		for(int i =0;i<6;i++)
		{
			for(int j = 0;j< 6;j++)
			{
				System.out.print(getVal(band,6,1,i,j) + " ");
			}
			System.out.println();
		}
		//System.out.println(multipliziereBandmatrixElement(band,band,6,1,5,1));
	}
/**
 * Laufzeit: n*(2*b+1)*(2*b+1) = O(n*b^2)
 * @param a1
 * @param a2
 * @param n
 * @param b
 * @return
 */
	public static int[][] multipliziereBandmatrizen(int[][] a1, int[][] a2, int n, int b)
	{
		int[][] ret = new int[n][2*b+1];
		for(int i =0;i<n;i++)//n
		{
			for(int j =0;j<2*b+1;j++)//2*b+1
			{
				ret[i][j] = multipliziereBandmatrixElement(a1,a2,n,b,i,j);
			}
		}
		return ret;
	}
	
	public static int multipliziereBandmatrixElement(int[][] a1, int[][] a2, int n, int b,int i,int j)
	{
		int ret = 0;
		for(int l =0; l<2*b+1;l++)//2*b+1
		{
			if(!(l+i-b>=n || -(l-b)+j>=2*b+1 || l+i-b<0 ||-(l-b)+j<0))
			{
				ret += a1[i][l] * a2[l+i-b][-(l-b)+j];
				//System.out.println(a1[i][l] +" | " + a2[l+i-b][-(l-b)+j]);
			}
		}
		return ret;
	}
	
	public static int getVal(int[][] band,int n, int b, int i, int j)
	{
		if(i<0||j<0||i>=n||j>=n)return 0;//oder error
		if(j>i+b|| j<i-b)return 0;
		//System.out.println(i+" " +j);
		return band[i][j-i+b];
	}
}
