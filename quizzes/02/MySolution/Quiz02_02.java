//
// HX: 50 points
// Here we revisit a question on quiz01 (Quiz01_03).
// Instead of sorting 10 elements without recursion,
// you are asked to insertion-sort up to 1 million
// elements without recursion.
// Attention:
// You are suppose to do insertion-sort. If you do
// bubble-sort, you can receive up to 60%, that is
// 30 points of 50.
//
import MyLibrary.LnStrm.*;
import MyLibrary.FnList.*;
import MyLibrary.LnStrm.LnStrmTest;

public class Quiz02_02 {
    public static
	<T extends Comparable<T>>
	void sort1000WithNoRecursion(T[] A) {
	// HX-2025-11-20:
	// A is an array of size at most 1000K.
	// Please implement a sorting algorithm
	// WITHOUT recursion that can effectively
	// sort A.

	// normal insertion sort compares elements next to each other
	// if we have a lot of elements unsorted in the array of size 1M,
	// and if it is reversed-sorted, especially, this would be disaster.

	// what if we compare elements far apart?
	// like set some intervals that the comparison should happen
	// like length 100 array, we compare A[0] with A[49] and then A[50] with A[99]
	// after we finish first comparison, shrink the interval to compare and repeat until the interval is 1 (which is comparing elmnts next to each other)

		final int n = A.length;

		final int startK = 19; // log(1M) = 20
		// 19 intervals to sort 1M elemnts
		// lazy operation
		LnStrmTest.intFrom(0).forall0((Integer k) -> {
			// calculating interval that the comparison should happen
			int interval = (1 << (startK - k)) - 1; // 2^{19 - k} -1 interval for 1M logrithmic sorting
			if (interval < 1) {
				return false;
			} // end if it's sorted

			// each intervall i from interval to n-1
			LnStrmTest.intFrom(interval).forall0((Integer i) -> {
				if (i >= n) {
					return false;
				} // end if interval [i, n] sorted

				T toInsert = A[i];
				final int[] slot = {i};

				// insertion sort part
				// Inner: gapped shift, takeWhile A[j-g] > toInsert
				LnStrmTest.intFrom(0).forall0((Integer step) -> {
					int j = i - step * interval; // j used in inner sorting to sort elements in each interval
					if (j < interval) {
						return false;
					} // escape if inner sorting finishes
					if (A[j - interval].compareTo(toInsert) <= 0) {
						return false; // compare
					}
					A[j] = A[j - interval];
					slot[0] = j - interval;
					return true;
				});
				A[slot[0]] = toInsert; // update list
				return true;
			});
			return true;
		});

		// int n = A.length;
		// int interval = 1;
		// while(2 * interval <= n){
		// 	interval *= 2;
		// }
		// interval = interval -1;

		// while(interval >= 1){
		// 	for(int i = interval; i < n; i++){
		// 		if(A[i].compareTo(A[i - interval]) < 0){
		// 			// swap(A,i,i-interval);
		// 			T toInsert = A[i];

		// 			int j = i;
		// 			do {
		// 				A[j] = A[j - interval];
		// 				j = j - interval;
		// 			} while(j > interval - 1 &&
		// 					toInsert.compareTo(A[j - interval]) < 0
		// 			);
		// 			A[j] = toInsert;
		// 		}
				
		// 	}
		// 	interval = interval / 2;
		// }
		// for(int nxt = 1; nxt < n; nxt++){
		// 	T next = A[nxt];
		// 	int current = nxt - 1;

		// 	while(current >= 0 && A[current].compareTo(next) > 0){
		// 		A[current + 1] = A[current];
		// 		current--;
		// 	}
		// 	A[current + 1] = next;
		// }
    }
	// // for bble srt
	// private static<T> void swap(T[] A, int i, int j){
	// 	T temp = A[i];
	// 	A[i] = A[j];
	// 	A[j] = temp;
	// }
	// private static<T extends Comparable<T>> boolean isSorted(T[] A){
	// 	int n = A.length;
	// 	for(int i = 0; i < n - 1; i++){
	// 		if(A[i].compareTo(A[i + 1])>0){
	// 			return false;
	// 		}
	// 	}
	// 	return true;
	// }
	
    public static void main (String[] args) {
	// HX-2025-11-19:
	// Please write minimal testing code for sort1000WithNoRecursion
    int N = 1000000;
    Integer[] test1mil = new Integer[N];
    int desc = 1;
    for (int i = N - 1; i >= 0; i--) {
        test1mil[i] = desc;
        desc++;
    }
    sort1000WithNoRecursion(test1mil);

    boolean sorted = true;
    for (int i = 1; i < N; i++) {
        if (test1mil[i - 1].compareTo(test1mil[i]) > 0) {
            sorted = false;
            break;
        }
    }
    System.out.println("1M element sorted? " + sorted);

	// random 1M elements test
	FnList<Integer> rand1mil = FnListSUtil.rand$int$make(N);

	Integer[] rand1Mtest = new Integer[N];
	final int[] idx = {0};
	rand1mil.foritm((Integer i)->{
		rand1Mtest[idx[0]] = i;
		idx[0]++;
	});
	sort1000WithNoRecursion(rand1Mtest);
	boolean randsorted = true;
    for (int i = 1; i < N; i++) {
        if (rand1Mtest[i - 1].compareTo(rand1Mtest[i]) > 0) {
            randsorted = false;
            break;
        }
    }
    System.out.println("1M random element sorted? " + randsorted);

	return /*void*/;
    }
} // end of [public class Quiz02_02{...}]
