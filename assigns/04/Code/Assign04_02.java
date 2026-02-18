/*
HX-2026-02-13: 20 points
*/
import MyLibrary.FnList.*;
import MyLibrary.FnStrn.*;

public class Assign04_02 {
	private static int listLength = 0;
	private static int iterate = 0;

    static FnStrn
	FnList$FnStrn_concate(FnList<FnStrn> xs) {
	// Given a list of strings, this method return the
	// concatenation of these string. For instance, given
	// ("a", "bc", "def"), the returned string is "abcdef"
	// You implementation is NOT allowed to use loops or
	// reccursion. Try to use the 'foritm' method in FnList
	// and FnStrn to accomplish this task.
	
	// lambda function can only capture local variables (within the method) that are final (or not changing what's inside)
	// we can avoid this problem by declaring private fields outside the method
	// update the length of the list
		FnListSUtil.foritm(xs, str -> listLength += FnStrnSUtil.length(str));

		// create new output array with the length of the updated version
		// data type: char[] because that is what FnStrn takes as its argument
		char[] outputarr = new char[listLength];

		// first foritm(FnListSUtil): input is xs which is FnList object; spit out each FnStrn in the list
			// because xs is FnList<FnStrn>, str is a FnStrn object
		// second foritm(FnStrnSUtil): input is str which is FnStrn object; and the data type inside FnStrn is char[] so we can iteration through char[]
			// in str, for each character, we assign each character to outputarray at index iterate
			// iterate++: we assign the char from index 0 to the end
		FnListSUtil.foritm(xs, str -> 
			FnStrnSUtil.foritm(str, c -> outputarr[iterate++] = c));

			// return value is FnStrn
			// FnStrn takes char[] as argument; in this case, outputarr
		return new FnStrn(outputarr);
		
    } 
    public static void main(String[] argv) {
	// Please write some testing code for your 'FnList$FnStrn_concate"

	// nested FnList with FnStrn to create FnList<FnStrn> object
		FnList<FnStrn> testConcat1 =
			FnListSUtil.cons(new FnStrn("a"),
			FnListSUtil.cons(new FnStrn("bc"),
			FnListSUtil.cons(new FnStrn("def"), FnListSUtil.nil())));

		FnStrn testc1 = FnList$FnStrn_concate(testConcat1);
		FnStrnSUtil.foritm(testc1, ch -> System.out.print(ch));
    
		System.out.println();
		
		FnList<FnStrn> testConcat2 = 
			FnListSUtil.cons(new FnStrn("fed"),
			FnListSUtil.cons(new FnStrn("cb"),
			FnListSUtil.cons(new FnStrn("a"), FnListSUtil.nil())));


		FnStrn testc2 = FnList$FnStrn_concate(testConcat2);
		FnStrnSUtil.foritm(testc2, ch -> System.out.print(ch));
	}
} // end of [public class Assign04_02{...}]
