
/**
* @author Alexander Neuwirth
* @author Leonhard Segger
* @author Jonathan Sigrist
*/
public class Aufgabe18 {
	/**
	* gibt zurück, ob ein int in dem array ist.

	* @param array zu untersuchendes array
	* @param x zu untersuchender Integer
	* return true, falls x in array enthalten ist
	*/
	public static boolean arrayContainsElem(int[][] array, int x)
	{
		for(int i =0; i < array[0].length;i++)
		{
			if(array[array.length-1][i]>=x)
			{
				for(int j =0; j < array.length;j++)
				{
					if(x==array[j][i])return true;
				}
				return false;
			}
		}
		return false;
	}
	
	/**
	* Gibt ein 2-dimensionales Integer-Array in der Konsole aus.
	* @param a auszugebendes Array
	*/
	public static void printIntArray(int[] a) {
		if (a.length == 0) {
			System.out.println("{}");
			return;
		}
		System.out.print("{" + a[0]);
		for (int i = 1; i < a.length; i++) {
			System.out.print("," + a[i]);
		}
		System.out.println("}");
	}
	
	/**
	* main-Methode zum starten des Programmes
	*/
	public static void main(String[] args)
	{
		int[][] array = new int[][]{{1,6,11,16,21,26,31},
			{2,7,12,17,22,27,32},
			{3,8,13,18,23,28,33},
			{4,9,14,19,24,29,34},
			{5,10,15,20,25,30,35}};
		printIntArray(array[0]);
		System.out.println(arrayContainsElem(array, 35));
	}
}
