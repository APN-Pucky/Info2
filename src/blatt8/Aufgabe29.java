package blatt8;

import java.util.ArrayList;

public class Aufgabe29 {
	/*
	 * Aufgabe 29 a)
	 * Sortierten des Arrays O(n*log(n)).
	 * Dann sucht man im sortierten Array binär für jedes Element nach einem geeigneten Paar-Element. O(n*log(n))
	 * Zu beachten ist dass keine Duplikate auftrette
	 * 
	 */
	public static void main(String[] args)
	{
		int [][]a =computePairsForSum(new int[]{1,2,-1,-2,-3,-5,-67,-82,-16,3,4,5,56,7,8,89,0,98,7,665,65,433,23,221},7);
		System.out.println();
		for(int i =0;i<a.length;i++)
		{
			for(int j =0;j< a[0].length;j++)
			{
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}
	}
	/**
	 * Laufzeit Quicksort O(n*log(n)) und N-fach binär Suchen auch O(n*log(n))
	 * macht zusammen O(n*log(n))
	 * @param values
	 * @param sum
	 * @return
	 */
	public static int[][] computePairsForSum(int[] values, int sum)
	{
		ArrayList<int[]> list = new ArrayList<int[]>();
		quickSort(values,0,values.length-1);
		for(int i=values.length-1;i>=0;i--)//n
		{
			if(contains(values,0,i,sum-values[i]))list.add(new int[]{sum-values[i],values[i]});
			
		}
		return list.toArray(new int[][]{});
	}
	
	//binäres Suchen
	private static boolean contains(int[] a, int begin, int end, int val)
	{
		if(a[(begin+end)/2] == val)
		{
			return true;
		}
		else
		{
			if(begin >= end)return false;
			
			if (a[(begin+end)/2] > val)
			{
				return contains(a,begin,(begin+end)/2-1,val);
			}
			else if (a[(begin+end)/2] < val)
			{
				return contains(a,(begin+end)/2+1,end,val);
			}
		}
		//dead code :/
		return false;
	}
	
	private static void quickSort(int[] a, int l, int r)
    {
    	//Pivot Mitte
    	int m = a[(l+r)/2];
    	int i = l;
    	int j = r;
    	while(i<=j)
    	{
    		for(;a[i]<m;i++);
    		for(;m<a[j];j--);
    		if(i<=j)
    		{
    			swap(a,i,j);
    	        
    			i++;
    			j--;
    		}
    	}
    	if(l<j)quickSort(a,l,j);
    	if(i<r)quickSort(a,i,r);
    }

    private static void swap(int[] a, int b, int c)
    {
    	int tmp = a[b];
        a[b] = a[c];
        a[c] = tmp;
    }
}
