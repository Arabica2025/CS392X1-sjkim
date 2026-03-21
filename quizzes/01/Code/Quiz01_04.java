//
// HX: 50 points
//

public class Quiz01_04 {
    public static
	<T extends Comparable<T>>
	LnList<T> LnListInsertSort(LnList<T> xs) {
	// HX-2025-10-12:
	// Please implement (stable) insertion sort on a
	// linked list (LnList).
	// Note that you are not allowed to modify the definition
	// of the LnList class. You can only use the public methods
	// provided by the LnList class; you cannot use any constructors
	// in LnList
		if (xs.nilq1()){
			return xs;
		}

		LnList<T> sorted = xs; // assign xs temporarily; we will replace each element with sorted elements
		LnList<T> tail = sorted; // keep track of the element at the end of the sorted list
		LnList<T> beforeTail = null; // element right before tail -> this is for nearly sorted list
		LnList<T> unsorted = sorted.unlink();

		while(unsorted.consq1()){

			LnList<T> left = unsorted;
			unsorted = unsorted.unlink();
			// left: a single node between right and unsorted

			// if left is the smallest value, go straight to the first place
			if (left.hd1().compareTo(tail.hd1()) >= 0){ // if left < the smallest element in sorted list
				beforeTail = tail;
				tail.link(left); // tail -> sorted
				tail = left; // update sorted so that it starts from tail and goes -> sorted
			} else if (beforeTail != null && left.hd1().compareTo(beforeTail.hd1())>=0){
				beforeTail.unlink();
				beforeTail.link(left);
				left.link(tail);
				beforeTail = left;
			} else{
				// traverse right to find the "right value"
				LnList<T> right = sorted;
				boolean inserted = false;
				while(right.consq1() && !inserted){
					if (right.tl1().nilq1() || left.hd1().compareTo(right.tl1().hd1())<=0){
						LnList<T> rest = right.unlink();
						right.link(left);
						left.link(rest);
						inserted = true;
					} else {
						right = right.tl1();
					}
				}
				
			}
		}
		return sorted;
		}


		// // left and right like FnList insertionsort
		// LnList<T> sorted = xs; // list that we will use to store the sorted output
		// LnList<T> sortedTail = sorted; // separate the first element from the whole list
		// // unlink()-> automatically uncouple the rest of the list from the first element
		// LnList<T> unsorted = xs.unlink(); // the rest of the elements in the list


		// // now we handle unsorted list
		// // iterate until we reach to the end
		// while(unsorted.consq1()){
		// 	LnList<T> temp = unsorted; // store the unsorted list temporarily
		// 	unsorted = unsorted.unlink(); // separate the second element from the rest to compare


		// 	if (sortedTail.hd1().compareTo(temp.hd1())<=0){ // if the first element <= second element
		// 		// concat : [first, second ...]
		// 		sortedTail.link(temp);
		// 		// advance
		// 		sortedTail = temp;
		// 		continue;
		// 	}

		// 	// 
		// 	LnList<T> before = temp.unlink();
		// 	LnList<T> beforeTail = before;
		// 	LnList<T> after = sorted;

		// 	while(after.consq1() && after.hd1().compareTo(temp.hd1())<=0){
		// 		LnList<T> h = after;
		// 		after = after.unlink();
		// 		if(before.nilq1()){
		// 			before = h;
		// 			beforeTail = h;
		// 		} else {
		// 			beforeTail.link(h);
		// 			beforeTail = h;
		// 		}
		// 	}
		// 	if (before.nilq1()){
		// 		temp.link(after);
		// 		sorted = before;
		// 	} else {
		// 		beforeTail.link(temp);
		// 		temp.link(after);
		// 		sorted = before;
		// 	}
		// }
		// return sorted;

	// private static <T> void printLnList(LnList<T> xs) {
	// 	System.out.print("[");
	// 	LnList<T> sortedList = xs;
	// 	boolean first = true;
	// 	while(sortedList.consq1()){
	// 		if (!first){
	// 			System.out.print(sortedList.hd1());
	// 			first = false;
	// 			sortedList = sortedList.tl1();
	// 		}
	// 	}
	// 	System.out.print("]");
	// }

	private static <T> void printFirstK(LnList<T> xs, int k) {
		System.out.print("[");
		LnList<T> t = xs;
		int i = 0;
		while (i < k) {
			if (i > 0) System.out.print(", ");
			System.out.print(t.hd1());
			t = t.tl1();
			i++;
		}
		System.out.print(", ...]");
	}

	private static <T> void printLnList(LnList<T> xs){
		System.out.print("[");
		LnList<T> t = xs;
		int i = 0;
		while(t.consq1()){
			if (i > 0){
				System.out.print(", ");
			}
			System.out.print(t.hd1());
			t = t.tl1();
		}
		System.out.print("]");
	}
    public static void main (String[] args) {
	// HX-2026-03-04:
	// Here you can use constructors in LnList.
	// Please write minimal testing code for LnListInsertSort
	// 1. Please sort a nearly sorted list of 1M elements
	// 2. Please do parity-sorting to test that LnListInsertSort is stable

		LnList<Integer> test = new LnList<Integer>();
		for (int i = 1000000 -1; i >= 0; i-=2){
			test = new LnList<Integer>(i, new LnList<Integer>(i-1, test));
		}

		printFirstK(test,10);
		// test = LnListInsertSort(test);
		LnList<Integer> sorted = LnListInsertSort(test);
		
		printLnList(sorted);


        // LnList<Integer> test = new LnList<Integer>();
        //     for (int i = 1000000 -1; i >= 0; i-=2){
        //             test = new LnList<Integer>(i, new LnList<Integer>(i-1, test));
        //     }

        //     // test = LnListInsertSort(test);
        //     printFirstK(test, 100);
        //     LnList<Integer> sorted = LnListInsertSort(test);
        //     printFirstK(sorted, 100);

	}
}
