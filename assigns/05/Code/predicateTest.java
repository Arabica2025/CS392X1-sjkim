import MyLibrary.FnList.*;
import MyLibrary.FnList.FnListSUtil.*;
    
import java.util.Random;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntBiFunction;

public class predicateTest {

    public static
	<T extends Comparable<T>>
	FnList<T> insertSort(FnList<T> xs) {
	return insertSort(xs, (x1, x2) -> x1.compareTo(x2));
    }
	private static<T>
        FnList<T>
        insertSort_insert(FnList<T> xs, T x0, ToIntBiFunction<T,T> cmp) {
        FnList<T> ys = FnListSUtil.nil();
        while(xs.consq()) {
            if (cmp.applyAsInt(x0, xs.hd()) <= 0) {
                return FnListSUtil.rappend(ys, FnListSUtil.cons(x0, xs));
            } else {
                ys = FnListSUtil.cons(xs.hd(), ys);
                xs = xs.tl();
            }
        }
        return FnListSUtil.cons(x0, ys);
    }
//
    public static<T> FnList<T>
	insertSort(FnList<T> xs, ToIntBiFunction<T,T> cmp) {
	// HX-2026-02-26: Please implement this method
	// You can use while-loops but cannot make recursive
	// calls.
		// check if the input is empty
		if (xs.nilq()){
			return new FnList<T>();
		}
		// check if the input only has one element
		if (xs.tl().nilq()){
			return new FnList<T>(xs.hd(), new FnList<T>());
		}

		// create a copy to sort
		FnList<T> copy = new FnList<T>();

		// loop until we reach to the end of the list
		do{
			int indexf=0;
			int indexs=1;
			// store the first element
			// advance
			
			T first = xs.hd();
			xs = xs.tl();
			//T second = xs.hd();
			//xs = xs.tl();
			// if there is a second element
			while (xs.consq()){
				// store the second element
				T iterate = xs.hd();
				// advance
				xs = xs.tl();
                
                BiPredicate<T, T> swap = (i0, i1) -> cmp.applyAsInt(i0, i1) > 0;
				while(swap.test(first, iterate)){
					swapping(indexf, indexs);
					first = xs.hd();
					xs = xs.tl();
					iterate = xs.hd();
					xs = xs.tl();
				}
				// if the first element is greater than the second
				if (indexf == 1){
					// insert the second element before the first
					copy = new FnList<T>(iterate, new FnList<T>(first, copy));
				} else{
					// otherwise insert the first element before the second
					copy = new FnList<T>(first, new FnList<T>(iterate, copy));
				}
			}
			xs = xs.tl();
		}while(xs.consq());
		return copy;
    }
	// private static void swapping(int indexf, int indexs) {
	// 	int temp = 0;
	// 	// 1. assign a to temp
	// 	temp = indexf;
	// 	// 2. assign b to a
	// 	indexf = indexs;
	// 	// 3. assign temp to b
	// 	indexs = temp;
	// }



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
