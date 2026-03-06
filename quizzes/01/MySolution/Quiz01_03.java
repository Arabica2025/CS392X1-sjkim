//
// HX-2026-03-04: 30 points
// This one may seem easy but can be time-consuming
// if you use a brute-force approach.
// Hint: Try to think about implementing bubble-sort
// without recursion
//

// import MyLibrary.FnList.*;
// import MyLibrary.Sorting.SrtArr;
public class Quiz01_03 {
    public static
	<T extends Comparable<T>>
	T[] sort20WithNoRecursion
	(T x00, T x01, T x02, T x03, T x04, T x05, T x06, T x07, T x08, T x09,
	 T x10, T x11, T x12, T x13, T x14, T x15, T x16, T x17, T x18, T x19) {
	// HX-2026-03-03:
	// Given 30 arguments,
	// please return an array of size 20 containing the
	// 20 arguments sorted according to the order implemented by
	// compareTo on T.
	// HX: No recursion is allowed for this one
	// HX: No loops (either while-loop or for-loop) is allowed.
	// HX: Yes, you can use functions (but not recursive functions)
	// HX: Please do not try to write a HUGE if-then-else mumble jumble!
	
	// cannot use loop or recursion, so decided to hardcode instead...
	// I feel uncomfortable doing this though... i know it is the way it goes but who does this misery in reality?
		T[] out = (T[]) new Comparable[20]; 
		// saw we can typecast T[] from FnA1sz constructor..? 
		// i guess we have to specify data type to put elements in array but in this way,
		// we can put elements in array without checking it..? not sure how but it works anyways...
		out[0] = x00;
		out[1] = x01;
		out[2] = x02;
		out[3] = x03;
		out[4] = x04;
		out[5] = x05;
		out[6] = x06;
		out[7] = x07;
		out[8] = x08;
		out[9] = x09;
		out[10] = x10;
		out[11] = x11;
		out[12] = x12;
		out[13] = x13;
		out[14] = x14;
		out[15] = x15;
		out[16] = x16;
		out[17] = x17;
		out[18] = x18;
		out[19] = x19;

		// T[] arr = (T[]) new Comparable[]{x00, x01, x02, x03, x04,x05,x06,x07, x08, x09, x10, x11,x12,x13,x14,x15,x16, x17,x18,x19};



		// because the elements are given individually as arguments,
		// it was a terrible idea to do the below:
		// FnList<T> left = new FnList<T>(x00, 
		// 	new FnList<T>(x01, 
		// 		new FnList<T>(x02, 
		// 			new FnList<T>(x03, 
		// 				new FnList<T>(x04, 
		// 					new FnList<T>(x05, 
		// 						new FnList<T>(x06, 
		// 							new FnList<T>(x07, 
		// 								new FnList<T>(x08, 
		// 									new FnList<T>(x09, 
		// 										new FnList<T>(x10, 
		// 											new FnList<T>(x11, 
		// 												new FnList<T>(x12, 
		// 													new FnList<T>(x13, 
		// 														new FnList<T>(x14, 
		// 															new FnList<T>(x15, null)))))))))))))))); <- I stopped counting after 5 paranthesis im not doing this ; probably the same with cons() method

		T[] answer = I20(out); // bubblish-sort
		// copy over... wait i think i can just return answer array. 
		// im so stupid. i realized this 30 min before submission.
		// out[0] = answer[0];
		// out[1] = answer[1];
		// out[2]=answer[2];
		// out[3]=answer[3];
		// out[4]=answer[4];
		// out[5]=answer[5];
		// out[6]=answer[6];
		// out[7]=answer[7];
		// out[8]=answer[8];
		// out[9]=answer[9];
		// out[10]=answer[10];
		// out[11]=answer[11];
		// out[12]=answer[12];
		// out[13]=answer[13];
		// out[14]=answer[14];
		// out[15]=answer[15];
		// out[16]=answer[16];
		// out[17]=answer[17];
		// out[18]=answer[18];
		// out[19]=answer[19];

		return answer;
    }
	// wait i can just create a temporary arr for swapping. this was bad.
	// private static<T extends Comparable<T>> void cmpNswap(T[] left, T[] right, int iteration){
	// 	if (left[iteration].compareTo(right[iteration])>0){
	// 		T temp = left[iteration];
	// 		left[iteration] = right[iteration];
	// 		right[0] = temp;
	// 	}
	// }

	// swapping helper
	private static<T extends Comparable<T>> void cmpNswap(T[] arr, int iteration){
		if (arr[iteration].compareTo(arr[iteration+1])>0){
			T temp = arr[iteration];
			arr[iteration] = arr[iteration+1];
			arr[iteration+1] = temp;
		}
	}

	// iteration helper
	private static <T extends Comparable<T>> T[] I5(T[] arr){
		iteration(arr);
		iteration(arr);
		iteration(arr);
		iteration(arr);
		iteration(arr);
		return arr;
	}

	// final iteration helper
	private static <T extends Comparable<T>> T[] I20(T[] arr){
		I5(arr);
		I5(arr);
		I5(arr);
		I5(arr);
		return arr;
	}
	// defines each iteration for bubbl-ish sorting
	// hardcoded the amount of iteration for the first looping
	private static<T extends Comparable<T>> T[] iteration(T[] arr){
		cmpNswap(arr, 0);
		cmpNswap(arr, 1);
		cmpNswap(arr, 2);
		cmpNswap(arr, 3);
		cmpNswap(arr, 4);
		cmpNswap(arr, 5);
		cmpNswap(arr, 6);
		cmpNswap(arr, 7);
		cmpNswap(arr, 8);
		cmpNswap(arr, 9);
		cmpNswap(arr, 10);
		cmpNswap(arr, 11);
		cmpNswap(arr, 12);
		cmpNswap(arr, 13);
		cmpNswap(arr, 14);
		cmpNswap(arr, 15);
		cmpNswap(arr, 16);
		cmpNswap(arr, 17);
		cmpNswap(arr, 18);
		return arr;
	}

	// private static <T extends Comparable<T>> void iteration(FnList<T> left, FnList<T> right) {
	// 	// perform single iteration of bble sort.
	// 	cmpNswap(left, right);
	// 	cmpNswap(left, right);
	// 	cmpNswap(left, right);
	// 	cmpNswap(left, right);
	// 	cmpNswap(left, right);
	// 	cmpNswap(left, right);
	// 	cmpNswap(left, right);
	// 	cmpNswap(left, right);
	// 	cmpNswap(left, right);
	// 	cmpNswap(left, right);
	// 	cmpNswap(left, right);
	// 	cmpNswap(left, right);
	// 	cmpNswap(left, right);
	// 	cmpNswap(left, right);
	// }

	// private static <T extends Comparable<T>> void cmpNswap(FnList<T> left, FnList<T> right){
	// 	if (left.hd().compareTo(right.hd())>0){
	// 		left = new FnList<T>(right.hd(), left.tl()); 
	// 		right = new FnList<T>(left.hd(), right.tl());
	// 		// left and right are now swapped
	// 		left = left.tl();
	// 		right = right.tl();
	// 		// swap and advance			
	// 	} else {
	// 		left = left.tl();
	// 		right = right.tl();
	// 	}
	// }

	// hardcoded print helper
	private static <T> void printArr20(T[] a) {
		System.out.print("[");
		System.out.print(a[0]); System.out.print(", ");
		System.out.print(a[1]); System.out.print(", ");
		System.out.print(a[2]); System.out.print(", ");
		System.out.print(a[3]); System.out.print(", ");
		System.out.print(a[4]); System.out.print(", ");
		System.out.print(a[5]); System.out.print(", ");
		System.out.print(a[6]); System.out.print(", ");
		System.out.print(a[7]); System.out.print(", ");
		System.out.print(a[8]); System.out.print(", ");
		System.out.print(a[9]); System.out.print(", ");
		System.out.print(a[10]); System.out.print(", ");
		System.out.print(a[11]); System.out.print(", ");
		System.out.print(a[12]); System.out.print(", ");
		System.out.print(a[13]); System.out.print(", ");
		System.out.print(a[14]); System.out.print(", ");
		System.out.print(a[15]); System.out.print(", ");
		System.out.print(a[16]); System.out.print(", ");
		System.out.print(a[17]); System.out.print(", ");
		System.out.print(a[18]); System.out.print(", ");
		System.out.print(a[19]);
		System.out.println("]");
    }
    public static void main (String[] args) {
	// HX-2025-10-12:
	// Please write minimal testing code for sort20WithNoRecursion.
		Comparable[] testArr = sort20WithNoRecursion(30,29,28,27,26,25
													,24,23,22,21,20
													,19,18,17,16,15
													,14,13,12,11);


		printArr20(testArr);

	}
}
