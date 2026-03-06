//
// HX: 10 points
//
/*
import MyLibrary.FnList.*;
// Add for imports as you see fit
*/

import MyLibrary.FnList.*;
import MyLibrary.FnStrn.*;
import MyLibrary.FnA1sz.*;
import MyLibrary.Search.*;
import MyLibrary.Sorting.*;
import MyLibrary.balance_barcket;
import MyLibrary.concatListOfStrn;
import MyLibrary.f91_tail_recursion;

public class Quiz01_00 {
    /*
     Please give a description of your MyLibrary
     What classes have you implemented? For each class
     you have implemented in MyLibrary, please create an
     object of that class as follows:
     */
    public static void main (String[] args) {
	// For instance, 
	// FnList<Integer> FnList_Integer_obj = new FnList<Integer>();

    // forgot to create constructors for the classes -> created constructors for BinarySearch, SrtArr, balance_barcket, concatListOfStrn, f91_tail_recursion

    FnList<String> FnList_String_obj = new FnList<String>();
    FnListSUtil FnListSUtil_obj = new FnListSUtil();
    FnStrn FnStrn_obj = new FnStrn();
    FnStrnSUtil FnStrnSUtil_obj = new FnStrnSUtil();
    
    String[] String_array_BS = new String[3]; // example String arr
    FnA1sz<String> FnA1sz_String_obj = new FnA1sz<String>(String_array_BS);

    BinarySearch<String> binarySearch = new BinarySearch<String>(String_array_BS, null);

    int[] int_arr_ex = new int[0];// example array for integer sorting
    SrtArr sorting_int_Array = new SrtArr(int_arr_ex);

    balance_barcket balance_barcket_obj = new balance_barcket("");
    FnList<FnStrn> xs = new FnList<FnStrn>();
    concatListOfStrn concatListOfStrn_obj = new concatListOfStrn(xs);

    f91_tail_recursion f91_tail_recursion_obj = new f91_tail_recursion(0);
	return /*void*/;
    }
}
