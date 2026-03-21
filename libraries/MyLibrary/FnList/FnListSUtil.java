package MyLibrary.FnList;

import MyLibrary.FnList.*;

import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.ToIntBiFunction;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
/*
import java.util.function.ToIntBiFunction;
*/

public class FnListSUtil {
//
    public static<T>
	FnList<T> nil() {
	return new FnList<T>();
    }
    public static<T>
	FnList<T>
	cons(T x0, FnList<T> xs) {
	return new FnList<T>(x0, xs);
    }
//
    // HX: [length] is O(n)
    public static<T>
	int length(FnList<T> xs) {
	int res = 0;
	while (true) {
	    if (xs.nilq()) break;
	    res += 1; xs = xs.tl();
	}
	return res;
    }
//
    public static<T>
	FnList<T> reverse(FnList<T> xs) {
	FnList<T> ys;
	ys = nil();
	while (!xs.nilq()) {
	    ys = cons(xs.hd(), ys); xs = xs.tl();
	}
	return ys;
    }    

    public static<T>
	FnList<T> append(FnList<T> xs, FnList<T> ys) {
	if (xs.nilq()) {
	    return ys;
	} else {
	    return cons(xs.hd(), append(xs.tl(), ys));
	}
    }

    public static<T>
	void foritm(FnList<T> xs, Consumer<? super T> work) {
	while (xs.consq()) {
	    work.accept(xs.hd()); xs = xs.tl();
	}
	return;
    }

    public static<T>
	boolean forall(FnList<T> xs, Predicate<? super T> pred) {
	while (true) {
	    if (xs.nilq()) {
		break;
	    } else {
		if (pred.test(xs.hd())) {
		    xs = xs.tl(); continue;
		} else {
		    return false; // HX: counterexample found!
		}
	    }
	}
	return true;
    }

    public static<T>
	void iforitm(FnList<T> xs, BiConsumer<Integer, ? super T> work) {
	int i = 0;
	while (xs.consq()) {
	    work.accept(i, xs.hd()); i += 1; xs = xs.tl();
	}
	return;
    }

    public static<T>
	boolean iforall(FnList<T> xs, BiPredicate<Integer, ? super T> pred) {
	int i = 0;
	while (true) {
	    if (xs.nilq()) {
		break;
	    } else {
		if (pred.test(i, xs.hd())) {
		    i += 1; xs = xs.tl(); continue;
		} else {
		    return false; // HX: counterexample found!
		}
	    }
	}
	return true;
    }

    public static<T>
	void System$out$print(FnList<T> xs) {
    	System.out.print("FnList(");
	iforitm(xs,
          (i, itm) ->
	  {
	      if (i > 0) {
		  System.out.print(",");
	      }
	      System.out.print(itm.toString());
	  }
	);
	System.out.print(")");
    }

	public static<T extends Comparable<T>> void quickSort_array(T[] xs){
		quickSort_array(xs, (x1,x2) -> x1.compareTo(x2));
		return;
	}

	public static<T> void quickSort_array(T[] xs, ToIntBiFunction<T,T> cmp){
		quickSort_arrayseg_rand(xs, 0, xs.length-1,cmp,new Random());
		return;
	}

	private static<T> void array_swap(T[] xs, int i, int j){
		T tmp = xs[i];
		xs[i] = xs[j];
		xs[j] = tmp;
	}

	private static<T> int arrayseg_pivot(T[] xs, int ia, int iz, ToIntBiFunction<T,T> cmp){
		// assume pivot at the end of arr
		// split arr in half
		// 1 after the second half is the pivot position

		// first half: pivot < element
		// secnod half: pivot >= element
		// if both case match, swap

		// edge case 1(extreme): all elements in the arr are equal => O(n^2)
		// how to solve:
		// two pointers squeezing in
		

		// K & R bug
		// two pointers a,b pointing to the same thing
		// finish pivoting strategy
		return 0;
	}

	private static<T> void quickSort_arrayseg_rand(T[] xs, int ia, int iz,ToIntBiFunction<T,T> cmp, Random rand){
		int ln = iz-ia+1;
		if (iz-ia <= 0){ // xs contains at most one element! (inclusive approach)
			// in practice, we don't go all the way down to 0
			// instead, we go down to some place like 10
			// and call insertion sort to finish off to maximize efficiency
			return;
		}
		int pivot = rand.nextInt() % ln;
		pivot = (pivot >= 0) ? pivot : (ln+pivot); // 0 <= p0 <= ln-1
		//always check for error with assert
		assert(0 <= pivot && pivot <= ln-1);

		// pivoting
		// System.out.println("quickSort_arrayseg_rand: p0 = "+ p0);
		array_swap(xs,pivot, iz);
		
		int mid= arrayseg_pivot(xs, ia, iz, cmp);

		quickSort_arrayseg_rand(xs, ia, mid-1, cmp, rand);
		quickSort_arrayseg_rand(xs, mid+1, iz, cmp, rand);

		
		// int len = xs.length;
		// if (len<=1){
		// 	return;
		// } else{
		// 	int ia = 0;
		// 	int iz = len-1; // both min and max inclusive
		// 	void quickSort_arrayseg_rand(xs, ia, iz, cmp, rand);
		// }
	}

} // end of [public class FnListSUtil{...}]
