import MyLibrary.LnStrm.*;
import MyLibrary.FnTuple.*;

import java.util.Random;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntBiFunction;

public class Assign07_02 {
    public static
	LnStrm<Integer>
	ramanujanNumbers() {
		// a linear stream of all the ramanujanNumbers
		LnStrm<FnTupl2<Integer,Integer>> unprocessed = 	cubeSumOrderedIntegerPairs();
		return rjnhelper(unprocessed);
    }


	private static LnStrm<Integer> rjnhelper(LnStrm<FnTupl2<Integer, Integer>> unprocessed){
			// filter pairs to get only those where x^3 + y^3 = z^3 for some integer z
	return new LnStrm<Integer>(
		() -> {
			// comp1: current processing stream; unwrap the first element
			LnStcn<FnTupl2<Integer,Integer>> comp1 = unprocessed.eval0();

			if (comp1.nilq()){
				return new LnStcn<Integer>();
			}

			// comp1.hd(): first pair
			FnTupl2<Integer,Integer> pair1 = comp1.hd();
			LnStrm<FnTupl2<Integer,Integer>> rest = comp1.tl();

			// comp2: next processing stream; unwwrap the second element
			LnStcn<FnTupl2<Integer,Integer>> comp2 = rest.eval0();
			if (comp2.nilq()){
				return new LnStcn<Integer>();
			}
			// comp2.hd(): second pair
			FnTupl2<Integer,Integer> pair2 = comp2.hd();

			// calculate the cube sums
			int sum1 = cube_sum(pair1.sub0, pair1.sub1);
			int sum2 = cube_sum(pair2.sub0, pair2.sub1);

			// if the sums are equal
			if (sum1 == sum2){
				// found ramanujan pair
				// we are keep going with the rest of unprocessed stream
				// the rest of the unprocessed stream starts from the third element
				LnStrm<FnTupl2<Integer,Integer>> restUnprocessed = comp2.tl();

				// the next comparison happens between 
				// the second elmnt and the rest of them (third, fourth,...)

				LnStrm<FnTupl2<Integer,Integer>> nextcomp = 
					LnStrmSUtil.cons0(pair2, restUnprocessed);
					// store the current ramanujan number and
					// recursively calculate ramanujan number 
				return new LnStcn<Integer>(sum1, rjnhelper(nextcomp));
			} else{ // if the sums are not equal
				// we need to keep looking for pairs
				LnStrm<FnTupl2<Integer,Integer>> nextcomp = 
					LnStrmSUtil.cons0(pair2, comp2.tl());
				// we don't look back at previous element because
				// the previous element is not possible to be equal to the
				// any elements in the rest
				// return the result of the recursive call
				// and eval to the next element
				return rjnhelper(nextcomp).eval0();
			}

		}
	);
	}

    public static
	LnStrm<
	  FnTupl2<Integer,Integer>>
	cubeSumOrderedIntegerPairs() {
	// Return a stream of all the positive integer pairs
	// that are ordered according to the sum of the cubes
	// of the two integer components
	// it is linear

		// filter pairs to get only those where x^3 + y^3 = z^3 for some integer z
		ToIntBiFunction<FnTupl2<Integer, Integer>, FnTupl2<Integer, Integer>> cmpr = 
			(x,y) ->
				cube_sum(x.sub0, x.sub1).compareTo(cube_sum(y.sub0, y.sub1));
		return Assign07_01.mergeLnStrm(genMatrix(),	cmpr); // merge the streams in a linear stream


    }
	// generate all possible pairs (x,y)
	// $0 < x ≤ y < \infty$
	private static LnStrm<LnStrm<FnTupl2<Integer, Integer>>> genMatrix(){
		return LnStrmSUtil.map0(intFrom(1), (i) -> {
			return LnStrmSUtil.map0(intFrom(i), (y) ->
				new FnTupl2<Integer,Integer>(i, y));
		});
	}

	// LnStrmTest not in the same package; rewrite this
	private static LnStrm<Integer> intFrom(int start) {
		return new LnStrm<Integer>(
			() ->
			    new LnStcn<Integer>(start, intFrom(start+1))
		);
	}

	// same with intFrom
	private static Integer cube_sum(Integer x, Integer y) {
		return x*x*x + y*y*y;
	}

    public static void main(String[] args) {
		System.out.println("Ramanujan number unit test");
		LnStrm<Integer> ramanujanNumTest = ramanujanNumbers();
		int[] iteration = {1};
		// because ramanujan numbers are yet streams, we need to evaluate them
		// into LnStcn to store them and print.
		ramanujanNumTest.foritm0(
			(rjn) -> {
				
				System.out.println(iteration[0] +"th Ramanujan Number: " + rjn);
				iteration[0]++;
			}

		);
 // Please provide some minimal testing code
    }

} // end of [public class Assign07_02{...}]

