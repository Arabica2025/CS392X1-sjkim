package MyLibrary.Sorting;
import java.util.Random;
import MyLibrary.FnList.FnList;
import MyLibrary.FnList.FnListSUtil;
import MyLibrary.FnStrn.FnStrn;
import MyLibrary.FnStrn.FnStrnSUtil;
import java.util.function.ToIntBiFunction;

class SrtFnList implements SrtFnListInterface<Integer>{

    @Override
    public FnList<Integer> selectionSort(FnList<Integer> xs) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectionSort'");
    }

    @Override
    public FnList<Integer> insertionSort(FnList<Integer> xs) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertionSort'");
    }

    @Override
    public FnList<Integer> bubbleSort(FnList<Integer> xs) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'bubbleSort'");
    }

    @Override
    public static <T extends Comparable<T>>FnList<T> quickSort(FnList<T> xs) {
        return quickSort_t(xs, (x1,x2) -> x1.compareTo(x2));
    }

    public static<T> FnList<T> quickSort_t(FnList<T> xs, ToIntBiFunction<T,T> cmp){
        return quickSort_rand(xs, cmp, new Random());
    }

    private static<T> FnList<T> quickSort_rand(FnList<T> xs, ToIntBiFunction<T,T> cmp, Random rand){
        int n0 = xs.length();
        if (n0 <= 1) return xs;
        int p0 = rand.nextInt() % n0;
        p0 = (p0 >= 0) ? p0 : (n0+p0);

        FnList<T> ys = FnListSUtil.nil();
        for (int i = 0; i < p0; i+=1){
            ys = FnListSUtil.cons(xs.hd(), ys);
            xs = xs.tl();
        }
    }

    @Override
    public FnList<Integer> mergeSort(FnList<Integer> xs) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mergeSort'");
    }
}
