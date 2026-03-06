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

		// left and right like FnList insertionsort
		LnList<T> sorted = xs; 
		LnList<T> sortedTail = sorted;
		LnList<T> unsorted = xs.unlink(); // unlink()-> automatically uncouple the rest of the list from the first element

		// now we handle unsorted list
		while(unsorted.consq1()){


			LnList<T> temp = unsorted;
			unsorted = unsorted.unlink();

			if (sortedTail.hd1().compareTo(temp.hd1())<=0){ // if the first element is less than second, 
				// concat : [first, second ...]
				sortedTail.link(temp);
				// advance
				sortedTail = temp;
				continue;
			}

			// if the first element is greater or equal to second, need to find the right place
			LnList<T> before = temp.unlink();
			LnList<T> beforeTail = before;
			LnList<T> after = sorted;

			// 
			while(after.consq1() && after.hd1().compareTo(temp.hd1())<=0){
				LnList<T> h = after;
				after = after.unlink();
				if(before.nilq1()){
					before = h;
					beforeTail = h;
				} else {
					beforeTail.link(h);
					beforeTail = h;
				}
			}
			if (before.nilq1()){
				temp.link(after);
				sorted = before;
			} else {
				beforeTail.link(temp);
				temp.link(after);
				sorted = before;
			}
		}
		return sorted;

    }
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
		while (t.consq1() && i < k) {
			if (i > 0) System.out.print(", ");
			System.out.print(t.hd1());
			t = t.tl1();
			i++;
		}
		if (t.consq1()) System.out.print(", ...");
		System.out.println("]");
	}
    public static void main (String[] args) {
	// HX-2026-03-04:
	// Here you can use constructors in LnList.
	// Please write minimal testing code for LnListInsertSort
	// 1. Please sort a nearly sorted list of 1M elements
	// 2. Please do parity-sorting to test that LnListInsertSort is stable

		LnList<Integer> test = new LnList<Integer>();
		for (int i = 1000000 -1; i >= 0; i-=2){
			test = new LnList<Integer>(i-1, new LnList<Integer>(i, test));
		}

		// test = LnListInsertSort(test);
		LnList<Integer> sorted = LnListInsertSort(test);
		printFirstK(sorted, 100);

	}
}
