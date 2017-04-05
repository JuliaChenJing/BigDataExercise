public class IsNice {

	// f(x)
	public static int f(int x) {
		if (x == 4)
			return 4;
		if (x == 3)
			return 3;
		return 0;
	}

	// g(x)
	public static int g(int x, int y) {
		if (x == 1)
			return 1; // nice
		if (x == 4) {
			if (y == 3)
				return 1; // nice
			else
				return 4;
		}

		if (x == 3) {
			if (y == 4)
				return 1; // nice
			else
				return 3;
		}
		return y;
	}

	public static void main(String[] args) {
/*
 * [7, 6, 2, 3, 1]   //nice
[7, 6, 2, 4, 1]   // NOT nice. 4 is there, but 3 is not there
[3, 6, 2, 3, 4]   //nice
[3, 4, 2, 3, 4, 7, 4]   //nice
[1, 6, 2, 8, 2, 9]   //nice
 */
		// set the value in array a
		int[] a = {1, 6, 2, 8, 2, 9,3 };
		int[] b = new int[a.length];
		int[] c = new int[a.length];

		// set the value in array b
		for (int i = 0; i < a.length; i++) {
			b[i] = f(a[i]);
		}

		int x = 0;

		// set the value in array c
		for (int i = 0; i < a.length; i++) {
			x = g(x, b[i]);
			c[i] = x;
		}

		// print array a b c
		for (int i = 0; i < a.length; i++)
			System.out.print("\t" + a[i] + " ");
		System.out.println();
		for (int i = 0; i < a.length; i++)
			System.out.print("\t" + b[i] + " ");
		System.out.println();
		for (int i = 0; i < a.length; i++)
			System.out.print("\t" + c[i] + " ");
		System.out.println();
	}
}

