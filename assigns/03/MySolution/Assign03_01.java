/*
HX-2026-02-05: 10 points
*/
public class Assign03_01 {
    //
    // HX-2025-09-15:
    // This implementation of f91
    // is not tail-recursive. Please
    // translate it into a version that
    // is tail-recursive
    //
    /*
    static int f91(int n) {
	if (n > 100)
	    return n-10;
	else
	    return f91(f91(n+11));
    }
    */

    private static int tailF91(int n, int iteration){
        // if we don't have any iterations to loop through, we just return what's left 
        if (iteration == 0){
            return n;
        }

        // if condition met, we have 1 less thing to concern
        if (n > 100){
            return tailF91(n-10, iteration-1);
        } else{
        // if condition not met, we need 1 more execution to solve the problem
            return tailF91(n+11, iteration + 1);
        }
    }   

    /* tail recursion works like a loop? 
    then it has a finite number of executions? */
    public static int f91(int n){
        // if (n > 100){
        //     return n-10;
        // } else {
        //     return f91(tailF91(n, 1));
        // }
        return tailF91(n, 1); 
        // even if n == 91, we must execute the method at least once
        // -> set iteration to 1 as default

    }

    public static void main(String[] args) {
	// Please write some testing code here
        int n1 = 91;
        int n2 = 100;
        int n3 = 98;
        int n4 = 75;
        int n5 = -1;

        System.out.printf("f91 tail-recursive test call for f91(n1): %d", f91(n1));
        System.out.println();

        System.out.printf("f91 tail-recursive test call for f91(n2): %d", f91(n2));
        System.out.println();
        
        System.out.printf("f91 tail-recursive test call for f91(n3): %d", f91(n3));
        System.out.println();

        System.out.printf("f91 tail-recursive test call for f91(n4): %d", f91(n4));
        System.out.println();

        System.out.printf("f91 tail-recursive test call for f91(n5): %d", f91(n5));



    }
}
