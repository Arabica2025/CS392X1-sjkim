//
// HX: 20 points
//
/*
import Library00.FnA1sz.*;
*/
import MyLibrary.FnA1sz.*;
public class Quiz01_01 {
    public static
	<T extends Comparable<T>>
	int FnA1szBinarySearch(FnA1sz<T> A, T key) {
	// HX-2026-03-03:
	// Please implement binary search on a sorted functional array (FnA1sz)
	// that returns the largest index i such that key >= A[i] if such i exists,
	// or the method returns -1. The comparison function should be the compareTo
	// method implemented by the class T.

	// lib chanage: commented all methods from classes not configured such that I can create the class

		if (key == null || A == null || A.length() == 0){
			return -1;
		}
		return helperBinary(A, key, 0, A.length() - 1);
	}

	private static <T extends Comparable<T>> int helperBinary(FnA1sz<T> A, T key, int lower, int upper){
		if (lower > upper){
			return -1;
		}
		int mid = lower + (upper - lower) / 2;
		int sign = key.compareTo(A.getAt(mid));

		if (key == A.getAt(mid)){
			return mid;
		} else if (sign > 0){
			return helperBinary(A, key, mid + 1, upper);
		} else{
			return helperBinary(A, key, lower, mid - 1);
		}
	}
	    public static void main (String[] args) {
	// HX-2026-03-04:
	// Please write minimal testing code for FnA1szBinarySearch
	// Should test for cases T = Integer and T = String

	FnA1sz<Integer> intTestArr = new FnA1sz<Integer>(new Integer[]{1, 2, 3, 4, 5});
	FnA1sz<String> strTestArr = new FnA1sz<String>(new String[]{"apple", "banana", "cherry"});

	int intBSTest = FnA1szBinarySearch(intTestArr, 3); // should return 2
	int intBSNotFound = FnA1szBinarySearch(intTestArr, 6); // should return -1

	int strBSTest = FnA1szBinarySearch(strTestArr, "banana"); // should return 1
	int strBSNotFound = FnA1szBinarySearch(strTestArr, "date"); // should return -1

	System.out.println("intBSTest: " + intBSTest);
	System.out.println("intBSNotFound: " + intBSNotFound);
	System.out.println("strBSTest: " + strBSTest);
	System.out.println("strBSNotFound: " + strBSNotFound);

	return /*void*/;
}
}


