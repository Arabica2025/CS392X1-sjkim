/*
 *  Array-based Quicksort
 */
import java.util.Random;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntBiFunction;



public class Assign06_01 {
    public static <T> void arrayQuickSort(T[] A, ToIntBiFunction<T,T> cmp) {
	// Please implement standard array-based quickSort and make sure
	// that equal elements are properly handled. In particular, your
	// testing code should test your implementation on an array of 1M zeros!

		//edge case 1: empty array
		if (A == null || A.length == 0){
			System.out.println("A is an empty array");
			return;
		}
		// edge case 3: array that has all equal element
		// if (checkAllEqual(A)){
		// 	return;
		// }
		qSort(A, 0, A.length-1, cmp);
		// T pivot = A[findPvt(A, A.length)];
		// int pvtidx = findPvt(A, A.length);
		// // edge case 2: pivot not at the middle
		// pvtidx=findMidpvt(A, pvtidx);
    }

	private static<T> int[] partition(T[] A, int first, int last, ToIntBiFunction<T,T> cmp){
		// find pivot first
		T pivot = A[findPvtidx(A, first, last)];

		// three-pointer approach for partition
		// 1. left: subarray that contains elements less than pivot
		// 2. equal: subarray that contains elements equal to pivot (clustering)
		// 3. right: subarray that contains elements greater than pivot
		int left = first;
		int equal = first;
		int right = last;

		// each partition iteration loops until equal reaches to the end
		while (equal <= right){
			// if the input element is less than pivot
			if (cmp.applyAsInt(A[equal], pivot) < 0){
				// we swap with left
				swap(A, left, equal);
				// we increment left AND equal together
				left++;
				equal++;
				// if the input element is greater than pivot
			} else if (cmp.applyAsInt(A[equal], pivot)>0){
				// swap with right
				swap(A, equal, right);
				// decrement right
				right--;

				// equal case: increment equal only
			} else{
				equal++;
			}
		}

		// after the iteration(equal reached to the end), we have accumulated all equal elements in the middle
		// pass the left bound and right bound to qSort
		return new int[]{left, right};
		// int pivotidx = findPvtidx(A, first,last);
		// T pivot = A[pivotidx];
		// swap(A, last, pivotidx);

		// int i = first - 1;
		// int j = last + 1;

		// do {
		// 	do{
		// 		i++;
		// 	}while (cmp.applyAsInt(A[i], pivot) < 0);

		// 	do {
		// 		j--;
		// 	}while(cmp.applyAsInt(A[j], pivot) > 0);

		// 	while(cmp.applyAsInt(A[pivotidx], pivot) == 0){
		// 		pivotidx++;
		// 	}

		// 	if (i < j){
		// 		swap(A, i, j);
		// 	}
		// 	if (A[i] == pivot){
		// 		swap(A, i, pivotidx);
		// 	}
		// 	if (A[j] == pivot){
		// 		swap(A, j, pivotidx);
		// 	}
		// } while(i < j);

		// return j;
	}

	private static<T> void qSort(T[] A, int first, int last, ToIntBiFunction<T,T> cmp){
		if (first >= last) return; // no need to qSort if the array is sorted
		int[] split = partition(A, first, last, cmp); // clustering equal elements in the middle

		// left bound
		int firstToEqual = split[0]-1;
		// right bound
		int equalToLast = split[1]+1;

		// recursive call 
		// 1. first to the left bound
		qSort(A, first, firstToEqual, cmp);
		// 2. right bound to last
		qSort(A, equalToLast, last, cmp);

		// if (first < split){
		// 	qSort(a, first, split,cmp);
		// }
		// if (last > split + 1){
		// 	qSort(a, split + 1, last,cmp);
		// }
	} 

	// private static<T> boolean checkAllEqual(T[] A){
	// 	int i = 0;
	// 	boolean isEqual = true;
	// 	while(i < A.length-1){
	// 		if (A[i] == A[i+1]){
	// 			i++;
	// 			continue;
	// 		} else{
	// 			isEqual = false;
	// 			break;
	// 		}
	// 	}
	// 	return isEqual;
	// }

	// private static<T> int findMidpvt(T[] A,int pvtidx){
	// 	// edge case 2: pivot not at the middle
	// 	if (pvtidx == (A.length-1 - pvtidx)){
	// 		return pvtidx; 
	// 	} 
	// 	if (pvtidx < (A.length-1-pvtidx)) {
	// 		pvtidx = findPvt(A, A.length-1-pvtidx);
	// 	}
	// 	else {
	// 		pvtidx = findPvt(A, pvtidx);
	// 	}
	// 	return pvtidx;
	// }

	private static<T> void swap(T[] A, int inputidx, int swapidx){
		T temp = A[inputidx];
		A[inputidx] = A[swapidx];
		A[swapidx] = temp;
	}

	private static <T>int findPvtidx(T[] A, int first, int last){
		Random rand = new Random();

		int pivotidx = first + rand.nextInt(last - first + 1);
		return pivotidx;
	}

    private static <T> void printArray(T[] arr) {
		System.out.println("[");
        for (T element : arr) {
            System.out.print(element + " ");
        }
        System.out.print("]"); // Print a new line at the end
    }
	public static void main(String[] args){
		ToIntBiFunction<Integer, Integer> cmp = (a,b) -> a-b;

		Integer[] allZeroTest = new Integer[1000000];
		for (int i = 0; i < allZeroTest.length; i++){
			allZeroTest[i] = 0;
		}

		arrayQuickSort(allZeroTest, cmp);
		printArray(allZeroTest);
		
	}
	// private static <T> boolean isEmpty(T[] A){
	// 	Predicate<T[]> nullCase = arr -> arr.equals(null);
	// 	Predicate<T[]> zeroln = arr -> arr.length == 0;

	// 	Predicate<T[]> isempty = nullCase.or(zeroln);
	// 	return isempty(A);
	// }


} // end of [public class Assign06_01{...}]
