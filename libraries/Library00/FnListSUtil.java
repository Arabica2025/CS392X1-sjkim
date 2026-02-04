package libraries.Library00;

public class FnListSUtil {

    public static<T> FnList<T> nil(){
        return new FnList<T>();
    }

    public static<T> FnList<T> cons(T x, FnList<T> xs){
        return new FnList<T>(x, xs);
    }

    public static<T> int length(FnList<T> xs){
        int res = 0;
        while(true){
            if (xs.isEmpty()){
                break;
            }
            res += 1;
            xs = xs.tail();
        }
        return res;
    }
    
    // Reverses a list
    // O(n) operations
    public static<T> FnList<T> reverse(FnList<T> xs){
        FnList<T> ys;
        ys = nil();
        while (!xs.isEmpty()){
            ys = cons(xs.head(), ys);
            xs = xs.tail();
        }
        return ys;
    }

}
