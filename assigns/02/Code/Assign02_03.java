public class Assign02_03 {
    public static boolean solve_3sum(Integer[] A) {
	// Please give a soft qudratic time implementation
	// that solves the 3-sum problem. The function call
	// solve_3sum(A) returns true if and only if there exist
	// distinct indices i, j, and k satisfying A[i]+A[j] = A[k].
	// Why is your implementation soft O(n^2)?

		// sort the array first O(n log n)
		//mergeSort(A, 0, A.length-1);


		/*  e.g. {1,2,3,4,5}
		i < j < k

		i: start from the begining
		k: start from the end
		j: start from k-1
		{i, elem, elem, j, k}
		
		*/
			//System.out.println("Current i: " + i);
			for (int k = A.length-1; k >= 2; k--){ // lower bound restricted to index 2 because we don't want to touch either i or j
				//System.out.println("Current k: " + k);
				int i = 0;
				int j = k-1;
				//System.out.println("Current j: " + j);
				//System.out.println();
				

				while (i < j){
					int sum = A[i] + A[j];
					if (sum == A[k] && i != k && j != k){
					return true;
					}
					if (sum > A[k]){ // i + j > k: move j 1 down
						j--;
						//sum = A[i] + A[j]; //update sum accordingly
					} else {//if (sum < A[k]) i + j < k: move i 1 up
						i++;
					}
					}
				}
		return false;
		}

		// ex. {1,2,3,4,5}
/* 
		for (int i = 0; i < A.length-1; i++){
			//System.out.println("Current i: " + i);
			int j = i + 1;
			int k = A.length - 1;

			int sum = A[i] + A[j];

			if (sum > A[k]){ // if 1 + 2 > 5
				k--;
			} else if (sum < A[k]){
				j++;
			}
			else if (sum == A[k] && i != k && j != k){
				return true;
			}
			
			if (sum == A[k] && i != k && j != k){
				return true;
			} else {
				k--;
			}
			
		}
			return false;
		}		
*/	
			


/* 
	private static int findKval(Integer[] A, int i){
		for (int j = i+1; j < A.length-1; j++){
			int k = i;
			int lower = 0;
			int upper = A.length -1;
			int mid = lower + (upper - lower) /2;
			//int k = A[mid];

			int sum = A[i] + A[j];
			if (sum == A[k]){
				return k;
			}
			if (sum < A[k]){

			}
		}
	}
*/
	private static void mergeSort(Integer[] A, int lower, int upper){
		if (lower < upper){
			int mid = lower + (upper - lower) /2;
			mergeSort(A, lower, mid);
			mergeSort(A, mid+1, upper);
			merge(A, lower, mid, upper);
		}
	}
	private static void merge(Integer[] A, int lower, int mid, int upper){
		int n1 = mid - lower + 1;
		int n2 = upper - mid;
		Integer[] L = new Integer[n1];
		Integer[] R = new Integer[n2];
		for (int i = 0; i < n1; i++){
			L[i] = A[lower + i];
		}
		for (int j = 0; j < n2; j++){
			R[j] = A[mid + 1 + j];
		}
		int i = 0;
		int j = 0;
		int k = lower;
		while (i < n1 && j < n2){
			if (L[i] <= R[j]){
				A[k] = L[i];
				i++;
			} else {
				A[k] = R[j];
				j++;
			}
			k++;
		}
		while (i < n1){
			A[k] = L[i];
			i++;
			k++;
		}
		while (j < n2){
			A[k] = R[j];
			j++;
			k++;
		}
	}

	public static void main(String[] args) {
	// Please write some code here for testing solve_3sum
		Integer[] testArr1 = {1, 2, 3, 4, 5};
		Integer[] testArr2 = {-3, -1, 0, 1, 2};
		Integer[] testArr3 = {1, 2, 3, 4, 5, 6};
		Integer[] testArr4 = {1, 3, 10, 15};
		Integer[] testArr5 = {1, 2, 3, 4, 5, 7, 8};

		System.out.println("Test Array 1: " + solve_3sum(testArr1));
		System.out.println("Test Array 2: " + solve_3sum(testArr2));
		System.out.println("Test Array 3: " + solve_3sum(testArr3));
		System.out.println("Test Array 4: " + solve_3sum(testArr4));
		System.out.println("Test Array 5: " + solve_3sum(testArr5));
	}
}
