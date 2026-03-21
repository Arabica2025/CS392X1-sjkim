//
// HX: For testing Quiz01_05
//
import java.util.function.ToIntBiFunction;

import MyLibrary.FnList.*;;
abstract public class Quiz01_05_test {

    static class MySort extends Quiz01_05{
        public static<T> FnList<T> someSort(FnList<T> xs, ToIntBiFunction<T,T> cmp){
            return Assign05_01.insertSort(xs,cmp);
        }
    }
    public static void main (String args[]) {
	// Your testing code for Quiz01_05

        FnList<Integer> xs = new FnList<Integer>();

        // for (int i = 0; i <1000; i+=2){
        //     xs = new FnList<Integer>(i+1, new FnList<Integer>(i, xs));
        // }
        for (int i = 999; i >=0; i--){
            xs = new FnList<Integer>(i,xs);
        }

        // FnListSUtil.System$out$print(xs);
        // FnList<Integer> sorted = Quiz01_05.someSort(xs, (i0, i1) -> i0.compareTo(i1));
        // // FnListSUtil.System$out$print(sorted);

        FnList<Integer> revsorted = Quiz01_05.someRevStableSort(xs, (i0,i1)-> (i0%2)-(i1%2));
        FnListSUtil.System$out$print(revsorted);
        // FnList<Integer> rev = Quiz01_05.someRevStableSort(xs,(i0, i1) -> i0.compareTo(i1));
        // FnListSUtil.System$out$print(rev);
        // Quiz01_05.someRevStableSort(xs,(i0, i1) -> i0.compareTo(i1));
        // FnListSUtil.System$out$print(Quiz01_05.someRevStableSort(rev, (i0, i1) -> i0.compareTo(i1)));
    }
}
