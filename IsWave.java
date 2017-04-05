public class IsWave {
 
	//f(x)
	public static int f(int x){
		return (x % 2) + 2 ;
	}
	
	//g(x)
	public static int g(int x, int y){
		if (x == 0 || x == y) return 0;  
	    return y; 
	}


		
	public static void main(String[] args) {
		
		//set the value in array a
		//{7, 2, 9, 10, 5}, {4, 11, 12, 1, 6}, {1, 0, 5},{2}  are all wave arrays.
		// {2, 6, 3, 4} is not a wave array because the even numbers 2 and 6 are adjacent to each other.
		//{3, 4, 9, 11, 8} is not a wave array because the odd numbers 9 and 11 are adjacent to each other.
		int[] a = {3, 4, 9, 11, 8};
		int[] b = new int[a.length];
		int[] c = new int[a.length];
		
		//set the value in array b
		for (int i = 0; i < a.length; i++){
			b[i] = f(a[i]);
		}
		
		int x = 1;
		
		//set the value in array c
		for (int i = 0; i < a.length; i++){
			x = g(x, b[i]);
			c[i] = x;
		}
		
		
		// print array a b c
		for (int i = 0; i < a.length; i++)
			System.out.print("\t"+ a[i] + " ");
		System.out.println();
		for (int i = 0; i < a.length; i++)
			System.out.print("\t"+ b[i] + " ");
		System.out.println();
		for (int i = 0; i < a.length; i++)
			System.out.print("\t"+ c[i] + " ");
		System.out.println();		
	}
}

/*	13 	11 	12 	16 	0 	21 
	-1 	-1 	1 	1 	1 	-1 
	-1 	-2 	-1 	0 	1 	0 
 */
