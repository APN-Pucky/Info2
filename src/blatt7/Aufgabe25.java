package blatt7;

import java.util.Stack;

public class Aufgabe25 
{
	
	public static void main(String[] args)
	{
		//Test	
		class IntCmp implements Comparable<IntCmp>
		{
			public final int value;
			public IntCmp(int a)
			{
				this.value = a;
			}
			@Override
			public int compareTo(IntCmp o) {
				// TODO Auto-generated method stub
				return value-o.value;
			}
		}
		IntCmp[] values1 = new IntCmp[]{new IntCmp(1),new IntCmp(3),new IntCmp(2), new IntCmp(8), new IntCmp(2),new IntCmp(4),new IntCmp(1), new IntCmp(9), new IntCmp(2),new IntCmp(5),new IntCmp(1), new IntCmp(7) ,new IntCmp(1),new IntCmp(6),new IntCmp(2)};
		IntCmp[] values2 = new IntCmp[]{new IntCmp(1),new IntCmp(3), new IntCmp(8), new IntCmp(9)};
		IntCmp[] values3 = new IntCmp[]{new IntCmp(999999999),new IntCmp(8), new IntCmp(7), new IntCmp(4)};
		iterativQuickSort(values1);
		for(IntCmp v : values1)System.out.print(v.value + ", ");
		System.out.println();
		iterativQuickSort(values2);
		for(IntCmp v : values2)System.out.print(v.value + ", ");
		System.out.println();
		iterativQuickSort(values3);
		for(IntCmp v : values3)System.out.print(v.value + ", ");
	}
	
	private static <T extends Comparable<T>> void iterativQuickSort(T[] a)
	{
		Stack<Integer> stack = new Stack<Integer>();
		virtualFuncCall(stack,0,a.length-1);
		//
		while(!stack.empty())
		{
			int l = stack.pop();
			int r = stack.pop();
			//Pivot Mitte  	--------------------- Stack minimal, falls man immer genau in der Mitte aufteilt
			//				--------------------- In diesem Fall wird der Stack nicht größer als log_2(n)*2 (da zwei Werte pro Methodenaufruf auf den Stack kommen)
	    	//				--------------------- Bei der hier angewendeten Methode ergibt sch im Schlimmsten Fall eine Größe von n*2 also O(n)
			T m = a[(l+r)/2];
	    	int i = l;
	    	int j = r;
	    	while(i<=j)
	    	{
	    		for(;a[i].compareTo(m)<0;i++);
	    		for(;m.compareTo(a[j])<0;j--);
	    		if(i<=j)
	    		{
	    			swap(a,i,j);
	    	        
	    			i++;
	    			j--;
	    		}
	    	}
	    	if(l<j)virtualFuncCall(stack,l,j);
	    	if(i<r)virtualFuncCall(stack,i,r);
		}
		
		
	}
	
	private static void virtualFuncCall(Stack<Integer> stack,int l , int r)
	{
		//push on stack
		stack.push(r);
		stack.push(l);
	}
	
	/**
     * Hilfstauschmethode
     * tauscht Werte von a[b]und a[c]
     * @param a Array
     * @param b Index 1
     * @param c Index 2
     */
    private static <T extends Object> void swap(T[] a, int b, int c)
    {
    	T tmp = a[b];
        a[b] = a[c];
        a[c] = tmp;
    }
    private static  void swap(int[] a, int b, int c)
    {
    	int tmp = a[b];
        a[b] = a[c];
        a[c] = tmp;
    }
}
