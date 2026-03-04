import MyLibrary.FnList.*;
    
import java.util.Random;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntBiFunction;

public class Assign05_01 {

    // public static
	// <T extends Comparable<T>>
	// FnList<T> insertSort(FnList<T> xs) {
	// return insertSort(xs, (x1, x2) -> x1.compareTo(x2));
    // }
//
	// public static<T> FnList<T> insertionSort(FnList<T> xs, ToIntBiFunction<T,T> cmp){
	// 	FnList<T> copy = new FnList<T>(); // create new list to store the sorted elements
	// 	// if the input is empty return empty list
	// 	if (xs.nilq()){
	// 		return copy;
	// 	}

	// 	copy = new FnList<T>(xs.hd(), copy); // insert the first element to the copy list
	// 	do{
	// 		T x0 = xs.hd(); // store the first element
	// 		BiPredicate<T, T> swapcheck = (i0, i1) -> cmp.applyAsInt(i0, i1) <= 0;
	// 		if (swapcheck.test(x0, xs.hd())){
	// 			copy = FnListSUtil.append(copy, FnListSUtil.cons(x0, xs));
	// 		} else{
	// 			copy = FnListSUtil.cons(xs.hd(), copy);
	// 			xs = xs.tl();
	// 		}
	// 	}while(xs.consq());
	// 	return FnListSUtil.reverse(copy);
	// }
    // public static<T> FnList<T>
	// insertSort(FnList<T> xs, ToIntBiFunction<T,T> cmp) {
	// // HX-2026-02-26: Please implement this method
	// // You can use while-loops but cannot make recursive
	// // calls.
	// 	// check if the input is empty or has only one element
	// 	if (xs.nilq()){
	// 		return new FnList<T>();
	// 	}

	// 	// create a copy to sort
	// 	FnList<T> copy = new FnList<T>();

	// 	// loop until we reach to the end of the list
	// 	// loop until we reach to the end of the list
	// 	do{
	// 		// store the first element
	// 		T first = xs.hd();
	// 		// advance
	// 		xs = xs.tl();
	// 		// if there is a second element
	// 		if (xs.consq()){
	// 			// store the second element
	// 			T second = xs.hd();
	// 			// advance
	// 			xs = xs.tl();
                
    //             BiPredicate<T, T> swap = (i0, i1) -> cmp.applyAsInt(i0, i1) > 0;

	// 			// if the first element is greater than the second
	// 			if (swap.test(first, second)){
	// 				// insert the second element before the first
	// 				copy = new FnList<T>(second, new FnList<T>(first, copy));
	// 			} else{
	// 				// otherwise insert the first element before the second
	// 				copy = new FnList<T>(first, new FnList<T>(second, copy));
	// 			}
	// 		} else{
	// 			// insert the last element
	// 			copy = new FnList<T>(first, copy);
	// 		}
	// 	}while(xs.consq());
	// 		// while(cmp.applyAsInt(xs.hd(), xs.tl().hd()) < 0){
				
	// 		// 	// condition: if xs.hd() > xs.tl().hd()  = return -1
	// 		// 	// condition: if xs.hd() < xs.tl().hd()  = return 1
	// 		// 	FnList<T> temp = new FnList<T>(xs.tl().tl().hd(), new FnList<T>());
	// 		// 	copy = new FnList<T>(xs.tl().hd(), new FnList<T>(xs.hd(), temp));
	// 		// }
	// 		// xs = xs.tl().tl();
	// 	return copy;
		
    // }
	public static <T> FnList<T> insertionSort(FnList<T> xs, ToIntBiFunction<T,T> cmp) {

		// assume we have already sorted
		FnList<T> sorted = new FnList<T>();
		// iterate until we reach to the end of the FnList
		while (xs.consq()) {
			// take the first element to compare
			T first = xs.hd();
			// exclude the taken element for comparison
			xs = xs.tl();

			// create left and right partitions
			FnList<T> left = new FnList<T>();
			FnList<T> right = sorted;

			// if the first element is greater than the second element, go ahead and be sorted
			// and store that element to the right (greater side)

			// if the first element is less than the second element, 
			// then insert the first element to the left (less side) and compare that with the next right element
			// until we have the first element greater than the second element in the right (greater side)

			// Move elements <= first to left to keep sort stable
			while (right.consq() && cmp.applyAsInt(right.hd(), first) <= 0) {
				left = new FnList<T>(right.hd(), left);
				right = right.tl();
			}

			// now we know where the first element belongs in between
			// between left -- first -- right
			// so merge first with right
			// merge = first, right...
			// now we need to merge left with the first -- right merged version
			// left -- first -- right
			FnList<T> merged = new FnList<T>(first, right);
			while (left.consq()) {
				merged = new FnList<T>(left.hd(), merged);
				left = left.tl();
			}
			// now merged has beedn sorted, put that into sorted
			sorted = merged;
		}
		return sorted;
	}


    public static void main(String[] args) {
	// Please write some testing code that applies
	// insertSort to the following list of 1M numbers:
	// 1, 0, 3, 2, 5, 4, 7, 6, 9, 8, 11, 10, ..., 999999, 999998.

		FnList<Integer> xs = new FnList<Integer>();
		for (int i = 0; i < 1000000; i+=2) {
			xs = new FnList<Integer>(i+1, new FnList<Integer>(i, xs));
		}
		//System.out.print("[" + xs.hd()+ ", " + xs.tl().hd());
		FnList<Integer> sorted = insertionSort(xs, (i0, i1) -> i0.compareTo(i1));

		FnListSUtil.System$out$print(sorted);
		// System.out.print("[");
		// while(sorted.consq()){
		// 	System.out.print(sorted.hd());
		// 	sorted = sorted.tl();
		// 	if(sorted.consq()){
		// 		System.out.print("]");
		// 	}
		// }

		
		// System.out.print("[");
		// for (int i = 0; i < 100; i++){
		// 	System.out.print(sorted.hd());
		// 	sorted = sorted.tl();
		// 	if(sorted.consq()) System.out.print(", ");
		// }
		// System.out.print("[");
		// while(sorted.consq()){
		// 	System.out.print(sorted.hd());
		// 	sorted = sorted.tl();
		// 	if(sorted.consq()) System.out.print(", ");
		// }
		// System.out.println("]");
    }

} // end of [public class Assign05_01{...}]
