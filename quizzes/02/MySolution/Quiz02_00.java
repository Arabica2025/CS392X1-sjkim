//
// HX: 20 points
//
import MyLibrary.FnList.*;
import MyLibrary.FnA1sz.*;
import MyLibrary.FnGtree.*;
import MyLibrary.FnStrn.*;
import MyLibrary.FnTuple.*;
import MyLibrary.LnList.*;
import MyLibrary.LnStrm.*;
import MyLibrary.MyMap00.*;
import MyLibrary.MyRefer.*;
import MyLibrary.MyStack.*;
import MyLibrary.MyQueue.*;
import MyLibrary.PQueue.*;
import MyLibrary.Search.*;
import MyLibrary.Sorting.*;
import MyLibrary.balance_barcket;
import MyLibrary.concatListOfStrn;
import MyLibrary.f91_tail_recursion;



public class Quiz02_00 {
    /*
     Please give a description of your MyLibrary
     What classes have you implemented? For each class
     you have implemented in MyLibrary, please create an
     object of that class as follows:
     */
    public static void main (String[] args) {
	// For instance, 
	// FnList<Integer> FnList_Integer_obj = new FnList<Integer>();

    /* List of Objects*/
    FnList<Integer> FnList_Integer_obj = new FnList<Integer>();
    FnListSUtil FnListSUtil_obj = new FnListSUtil();
    FnStrn FnStrnobj = new FnStrn();
    FnStrnSUtil FnStrnSUtil_obj = new FnStrnSUtil();

    FnA1sz<Integer>[] FnA1sz_int_arr = (FnA1sz<Integer>[]) new FnA1sz[10];
    FnA1szSUtil FnA1szSUtil_obj = new FnA1szSUtil();

    FnGtree<Integer> FnGtree_Integer_obj = new FnGtree<Integer>() {
        @Override
        public Integer value() {
            return 0;
        }

        @Override
        public FnList<FnGtree<Integer>> children() {
            return FnListSUtil.nil();
        }
    };

    FnGtreeSUtil FnGtreeSUtil_obj = new FnGtreeSUtil();


    FnTupl2<Integer, String> FnTupl2_int_str_obj = new FnTupl2<Integer, String>(0, "");
    FnTupl2SUtil FnTupl2SUtil_obj = new FnTupl2SUtil();
    FnTupl3<Integer, String, Double> FnTupl3_int_str_db_obj = new FnTupl3<Integer, String, Double>(0, "", 0.0);
    FnTupl3SUtil FnTupl3SUtil_obj = new FnTupl3SUtil();

    LnList<Integer> LnList_int_obj = new LnList<Integer>();
    LnListSUtil LnListSUtil_obj = new LnListSUtil();

    LnStrm<Integer> LnStrm_int_obj = new LnStrm<Integer>();
    LnStcn<Integer> LnStcn_int_obj = new LnStcn<Integer>();
    LnStrmSUtil LnStrmSUtil_obj = new LnStrmSUtil();

    MyMap00<String, Integer> SCTest = new MapOpenAddress<>(30);
    MyMap00<String, Integer> SCTest2 = new MapSeparateChain<>(5);

    MyQueueArray<String> myQueueArray = new MyQueueArray<>(10);
    MyQueueList<String> myQueueList = new MyQueueList<>();

    MyStackArray<String> myStackArray = new MyStackArray<>(10);
    MyStackList<String> myStackList = new MyStackList<>();

    MyRefer<String> myRefer = new MyRefer<>(); 
        MyPQueueArray.Compare<Integer> cmp = new MyPQueueArray.Compare<>(
                (pair, out) -> out[0] = pair.a - pair.b
        );
    MyPQueueArray<Integer> pq = new MyPQueueArray<>(16, cmp);

    SrtArr sort_int_arr = new SrtArr(new int[0]);
    
    BinarySearch bs = new BinarySearch();

    balance_barcket bb_obj = new balance_barcket("");
    FnList<FnStrn> xs = new FnList<FnStrn>();
    concatListOfStrn clofstr = new concatListOfStrn(xs);
    f91_tail_recursion f91_obj = new f91_tail_recursion(0);
    return /*void*/;
    }
} // end of [class Quiz01_00{...}]
