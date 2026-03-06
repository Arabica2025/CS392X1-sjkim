import MyLibrary.Sorting.SrtArr;

public class Quiz01_02 {
    public static boolean solve_3prod(Integer[] A) {
	// Please give a soft quadratic time implementation
	// that solves the 3-prod problem. The function call
	// solve_3prod(A) returns true if and only if there exist
	// distinct indices i, j, and k satisfying A[i]*A[j] = A[k].
	// Why is your implementation soft O(n^2)? Please give a
	// BRIEF explanation

	// distinct indices i, j, and k satisfying A[i]*A[j] = A[k]
	// k: starts at the end of arr | j: starts at k-1 | i: starts at 0
	// i.........j   k
	// 0.........k-1 k
	// then we move k down until we reach to the end(which is 2)

	/* total time complexity: 
	O(n)(convert Integer[] to int[])
	 + O(nlogn) (mergesort)
	  + O(n^2) (two-pointer search)
	   = O(n^2log n)
		 = Soft O(n^2) because we ignore the logarithmic factor in Soft O notation.

	So, my implementation is soft O(n^2).
	*/

	// sort the array first: O(n) + O(nlogn)
	int[] sorted = wrapperBigToSmall(A);
	SrtArr merge = new SrtArr(sorted);
	merge.mergeSort(sorted);

	for (int k = sorted.length-1; k >= 2; k--){
		int i =0;
		int j = k-1;

		// for each k, we check pairs (i, j) with i < j
		while(i < j){
			int prod = sorted[i] * sorted[j];
			// loop until we reach to the case that satisfies all
			// 1. A[i] * A[j] == A[k]
			// 2. all i, j, k are distinct
			// true if satisfies all
			if (prod == sorted[k] && i != k && j != k){
				return true;
			}
			// if k is less than i * j, decrement j 
			// I think it is better to decrement j than increment i because j starts from higher end
			// and it would decrease average time to get closer to the equals if we decrease the higher number to multiply
			if (prod > sorted[k]){
				j--;
			} else { // if i * j < sorted, careful approach to reach to sorted because we decreased the higher number so far
				// and it is time to fine-tune.
				i++;
			}
			}
		}
	return false; // we did not find any so return false.
	}

	// private static Integer[] wrapperSmallToBig(int[] arr){
	// 	Integer[] wrapped = new Integer[arr.length];
	// 	for (int i = 0; i < arr.length; i++){
	// 		wrapped[i] = arr[i];
	// 	}
	// 	return wrapped;
	// }

	private static int[] wrapperBigToSmall(Integer[] arr){
		int[] wrapped = new int[arr.length];
		for (int i = 0; i < arr.length; i++){
			wrapped[i] = arr[i];
		}
		return wrapped;
	}
	
    public static void main(String[] args) {
	// Please write some code here for testing solve_3prod
		Integer[] testArr1 = {1, 2, 3, 4, 5};
		Integer[] testArr2 = {2, 4, 5, 6, 7, 8};

		System.out.println(solve_3prod(testArr1)); // Expected: false
		System.out.println(solve_3prod(testArr2)); // Expected: true

    }
}
