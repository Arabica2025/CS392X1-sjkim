import java.util.Arrays;

public class Assign02_02 {
    /*
      HX-2025-02-13: 10 points
      Recursion is a fundamental concept in programming.
      However, the support for recursion in Java is very limited.
      Nontheless, we will be making extensive use of recursion in
      this class.
     */

    /*
    // This is a so-called iterative implementation:
    public static <T extends Comparable<T> > int indexOf(T[] a, T key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            final int mid = lo + (hi - lo) / 2;
	    final int sign = key.compareTo(a[mid]);
            if      (sign < 0) hi = mid - 1;
            else if (sign > 0) lo = mid + 1;
            else return mid;
        }
        return -1;
    }
    */
    public static <T extends Comparable<T> > int indexOf(T[] a, T key) {
	// Please give a recursive implementation of 'indexOf' that is
	// equivalent to the above one
        if (key == null || a == null || a.length == 0){
            return -1;
        }
        int lower = 0;
        int upper = a.length - 1;
        if (lower > upper){
            return -1;
        }
        /* 
        if (upper % 2 == 1){
            upper += 1;
        }
        */
        int mid = lower + (upper - lower) / 2;
        int sign = key.compareTo(a[mid]);
        // if we found the key
        if (sign == 0) {
            return mid;
        } 

        if (sign < 0){ // key < a[mid]

            T[] left = Arrays.copyOfRange(a, lower, mid);
            return indexOf(left, key);
        } else {//if (sign > 0){ // key > a[mid]

            T[] right = Arrays.copyOfRange(a,mid, upper);
            return mid + 1 + indexOf(right, key);
        } 
        /*else {
            return -1;
        }
            */
        
    }
    

    public static void main(String[] args) {
	// Please write some testing code for your implementation of 'indexOf'
        Integer[] testArr = {1,2,3,4,5};
        System.out.println(testArr.length); // expect 11
        System.out.println("test indexOf 3: " + indexOf(testArr, 3));
        System.out.println("test indexOf 2: " + indexOf(testArr, 2));
        System.out.println("test indexOf 5: " + indexOf(testArr, 5));
        System.out.println("test indexOf 10: " + indexOf(testArr, 10));
        System.out.println("test indexOf 11: " + indexOf(testArr, 11));
    }
}
