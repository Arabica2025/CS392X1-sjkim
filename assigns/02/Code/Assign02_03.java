public class Assign02_03 {
    public static boolean solve_3sum(Integer[] A) {
	// Please give a soft qudratic time implementation
	// that solves the 3-sum problem. The function call
	// solve_3sum(A) returns true if and only if there exist
	// distinct indices i, j, and k satisfying A[i]+A[j] = A[k].
	// Why is your implementation soft O(n^2)?

		for (int i = 0; i < A.length-1; i++){
			System.out.println("Current i: " + i);
			for (int k = 0; k < A.length-1; k++){
				System.out.println("Current k: " + k);
				int j = i+1;
				System.out.println("Current j: " + j);
				System.out.println();
				int sum = A[i] + A[j];
				if (sum == A[k] && i != k && j != k){
					return true;
				}
			}
		}
		return false;
    }
    public static void main(String[] argv) {
	// Please write some code here for testing solve_3sum
		Integer[] testArr1 = {1, 2, 3, 4, 5};
		Integer[] testArr2 = {0, -1, 2, -3, 1};
		Integer[] testArr3 = {1, 2, 3, 4, 5, 6};
		Integer[] testArr4 = {10, 15, 3, 1};
		Integer[] testArr5 = {5, 7, 1, 2, 8, 4, 3};

		System.out.println("Test Array 1: " + solve_3sum(testArr1));
		System.out.println("Test Array 2: " + solve_3sum(testArr2));
		System.out.println("Test Array 3: " + solve_3sum(testArr3));
		System.out.println("Test Array 4: " + solve_3sum(testArr4));
		System.out.println("Test Array 5: " + solve_3sum(testArr5));
	}
}
