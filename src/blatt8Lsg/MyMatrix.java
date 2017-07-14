package blatt8Lsg;
/**
 * Klasse zur Darstellung einer antisymmetrischen Matrix in einem Array
 * 
 * @author Louisa Henning
 * 
 */
public class MyMatrix {
	
	private int n;
	private double[] array;
	
	/**
	 * Konstruktor
	 * @param n Seitenlängen der Matrix
	 */
	public MyMatrix(int n){
		this.n=n;
		array=new double[((n-2)*(n-1))/2 + n-1];
		for(int i=0;i<array.length;i++)
			array[i]=0;
	}
	
	/**
	 * Methode zum Auslesen eines Elements
	 * 
	 * @param i Zeilenindex
	 * @param j Spaltenindex
	 * @return Wert, der in der Matrix an Position (i,j) steht
	 */
	public double getE(int i, int j){
		if(i<0 || j<0 || i>=n || j>= n)
			throw new IllegalArgumentException("ungültige Indizes");
		if(i==j)
			return 0;
		if(i>j)
			return array[((i-1)*i)/2+j];	
		else 
			return -array[((j-1)*j)/2+i];
	}
	
	/**
	 * Methode zum Einfügen eines Elements
	 * 
	 * @param i Zeilenindex
	 * @param j Spaltenindex
	 * @param e einzufügender Wert
	 */
	public void insertE(int i, int j, double e){
		if(i<0 || j<0|| i==j || i>=n || j>= n)
			throw new IllegalArgumentException("ungültige Indizes");
		if(i>j){
			array[((i-1)*i)/2+j]=e;
		}else{
			array[((j-1)*j)/2+i]=-e;
		}
	}
	
	/**
	 * Ausdrucken der Werte der Matrix
	 */
	public void print(){
		System.out.println("Werte der Matrix");
		for(int k=0;k<n;k++){
			for(int l=0;l<n;l++){
				System.out.print(this.getE(k, l)+"     ");
			}
			System.out.println();
		}
	}
	
	
	
	
	public static void main (String[] args){		
		
		MyMatrix matrix=new MyMatrix(4);
		
		matrix.insertE(1,0,-2.);
		matrix.insertE(2,0,-3.);
		matrix.insertE(3,0,1.);
		matrix.insertE(3,2,-4.);
		matrix.insertE(1,2,-1.);
		matrix.insertE(1,3,2.);
		matrix.insertE(1,0,-2.);
		
		matrix.print();
		System.out.println();
		
		System.out.println("Speicherung im Array:");
		for(int l=0;l<matrix.array.length;l++)
			System.out.print(matrix.array[l]+"  ");
		
		
	}
	

}