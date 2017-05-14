package blatt3;

/**
 * 
 * @author Alexander Neuwirth
 * @author Leonhard Segger
 * @author Jonathan Sigrist
 * 
 */
public class Aufgabe11 {
	/**
	 * Teilt Integer auf
	 * @param internalFormat
	 * @return
	 */
	public static int[] decode(int internalFormat)
	{
		return decode(internalFormat,3);
	}
	/**
	 * Teilt Integer auf
	 * @param internalFormat
	 * @param numberOfInts
	 * @return
	 */
	public static int[] decode(int internalFormat, int numberOfInts)
	{
		int[] ret = new int[numberOfInts];
		for(int i =0; internalFormat>=powI(10,i*numberOfInts);i++)
		{
			for(int j =0; j < numberOfInts;j++)
			{
				ret[j] = setDecimalAt(ret[j],getDecimalAt(internalFormat,numberOfInts + i*numberOfInts +j),i);
			}
		}
		for(int j =0; j < numberOfInts;j++)
		{
			ret[j] *= getDecimalAt(internalFormat,j)-1;
		}
		return ret;
	}
	
	/**
	 * Fuegt Integer zusammen
	 * @param integersArray
	 * @return
	 */
	public static int encode(int[] integersArray)
	{
		int ret = 0;
		for(int i=0; i < integersArray.length;i++)
		{
			ret = setDecimalAt(ret, (int)Math.signum(integersArray[i])+1,i);
			int abs_value = Math.abs(integersArray[i]);
			for(int j =0; abs_value>=powI(10,j)  ;j++)
			{
				ret = setDecimalAt(ret, getDecimalAt(abs_value,j), integersArray.length *(j+1)+i);
			}
		}
		return ret;
	}
	/**
	 * Setzt Dezimalziffer
	 * @param number
	 * @param decimal
	 * @param index
	 * @return
	 */
	public static int setDecimalAt(int number, int decimal, int index)
	{
		if(decimal>9 || decimal < 0 || index <0)throw new IllegalArgumentException();
		return number + powI(10,index) * decimal;
	}
	/**
	 * Gibt Integer Dezimalziffer zurueck
	 * @param number
	 * @param index
	 * @return
	 */
	public static int getDecimalAt(int number,int index)
	{
		if(index < 0)throw new IllegalArgumentException();
		number = Math.abs(number);//should not happen :/
		return (number/(powI(10,index)))%10;
	}
	/**
	 * Integer Potenz
	 * @param base
	 * @param exp
	 * @return 
	 */
	public static int powI(int base, int exp)
	{
		return (int)Math.pow(base,exp);
	}
	/**
	 * Druckt ein Int Array aus.
	 * @param a
	 */
	public static void printIntArray(int[] a)
	{
		if(a.length==0)
		{
			System.out.println("{}");
			return;
		}
		System.out.print("{" + a[0]);
		for(int i =1; i<a.length;i++)
		{
			System.out.print("," + a[i] );
		}
		System.out.println("}");
	}
	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args)
	{
		int test_int = 222000123;
		int[] test_array = {123,124,125};
		//test 1
		System.out.print("Decode: " + test_int + " -> " );
		printIntArray(decode(test_int));
		System.out.print("Encode: " + encode(decode(test_int)) + " <- " );
		printIntArray(decode(test_int));
		//test 2
		System.out.print("Encode: " +encode(test_array) + " <- " );
		printIntArray(test_array);
		System.out.print("Decode: " +encode(test_array) + " -> " );
		printIntArray(decode(encode(test_array)));

		//own test
		int[] test_array_own = {44,-33,77};
		System.out.print("Encode+Decode: " +encode(test_array_own) +" <-> ");
		printIntArray(decode(encode(test_array_own)));
		
	}
}
