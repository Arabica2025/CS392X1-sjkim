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

    public static
	<T extends Comparable<T>>
	FnList<T> insertSort(FnList<T> xs) {
	return insertSort(xs, (x1, x2) -> x1.compareTo(x2));
    }
//
    public static<T> FnList<T>
	insertSort(FnList<T> xs, ToIntBiFunction<T,T> cmp) {
	// HX-2026-02-26: Please implement this method
	// You can use while-loops but cannot make recursive
	// calls.
		// check if the input is empty or has only one element
		if (xs.nilq()){
			return new FnList<T>();
		}

		// create a copy to sort
		FnList<T> copy = new FnList<T>();

		// loop until we reach to the end of the list
		while(xs.consq()){
			// store the first element
			T first = xs.hd();
			// advance
			xs = xs.tl();

			// if there is a second element
			if (xs.consq()){
				// store the second element
				T second = xs.hd();
				// advance
				xs = xs.tl();

				// if the first element is greater than the seocond
				if (cmp.applyAsInt(first, second) > 0){
					// insert the second element before the first
					copy = new FnList<T>(second, new FnList<T>(first, copy));
				} else{
					// otherwise insert the first element before the second
					copy = new FnList<T>(first, new FnList<T>(second, copy));
				}
			} else{
				// insert the last element
				copy = new FnList<T>(first, copy);
			}
			// while(cmp.applyAsInt(xs.hd(), xs.tl().hd()) < 0){
				
			// 	// condition: if xs.hd() > xs.tl().hd()  = return -1
			// 	// condition: if xs.hd() < xs.tl().hd()  = return 1
			// 	FnList<T> temp = new FnList<T>(xs.tl().tl().hd(), new FnList<T>());
			// 	copy = new FnList<T>(xs.tl().hd(), new FnList<T>(xs.hd(), temp));
			// }
			// xs = xs.tl().tl();
		}
		return copy;
		
    }



    public static void main(String[] args) {
	// Please write some testing code that applies
	// insertSort to the following list of 1M numbers:
	// 1, 0, 3, 2, 5, 4, 7, 6, 9, 8, 11, 10, ..., 999999, 999998.

		FnList<Integer> xs = new FnList<Integer>();
		for (int i = 0; i < 1000000; i+=2) {
			xs = new FnList<Integer>(i+1, new FnList<Integer>(i, xs));
		}
		System.out.print("[" + xs.hd()+ ", " + xs.tl().hd());
		FnList<Integer> sorted = insertSort(xs, (i0, i1) -> i0.compareTo(i1));

		System.out.print("[");
		while(sorted.consq()){
			System.out.print(sorted.hd());
			sorted = sorted.tl();
			if(sorted.consq()){
				System.out.print("]");
			}
		}

		
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
