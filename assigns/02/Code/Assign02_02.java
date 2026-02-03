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
    /*
        if (key == null || a == null || a.length == 0){
            return -1;
        }
        int lower = 0;
        int upper = a.length - 1;
        int mid = lower + (upper - lower) / 2;
        int sign = key.compareTo(a[mid]);
        // if we found the key
        if (sign == 0) {
            return mid;
        } 
        else if (sign < 0){ // key < a[mid]
            //T[] left = Arrays.copyOfRange(a, lower, mid+1);
            upper = mid+1;
            return indexOf(Arrays.copyOfRange(a, lower, upper), key);
        } else {//if (sign > 0){ // key > a[mid]
            lower = mid;
            //T[] right = Arrays.copyOfRange(a,mid, upper);
            return indexOf(Arrays.copyOfRange(a, lower, upper), key);
        }
    */
        if (key == null || a == null || a.length == 0){
            return -1;
        }
        return helperIndexOf(a, key, 0, a.length-1);
        
    }
    private static <T extends Comparable<T> > int helperIndexOf(T[] a, T key, int lower, int upper){
        //lower = 0;
        //upper = a.length -1;
        if (lower > upper) {
            return -1;
        }
        int mid = lower + (upper - lower) / 2;
        int sign = key.compareTo(a[mid]);

        if (key == a[mid]){
            return mid;
        } else if (sign > 0){
            return helperIndexOf(a, key, mid + 1, upper); // if key is on the right side of mid, it means we don't include mid in the parameter
        } else {
            return helperIndexOf(a, key, lower, mid -1); // if key is on the left side of mid, it meas we don't include mid in the parameter, just like when key is on the right side
        }
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
