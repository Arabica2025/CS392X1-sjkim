package Library00.FnStrn;

import Library00.FnStrn.*;

import java.util.function.*;



public class FnStrnSUtil {
    // Functional String Static Util

    // this should be O(1)
    public static int length(FnStrn str){
        return str.length();
    }
    // this should be O(1)
    public static boolean consq(FnStrn str){
        return (str.length() >= 1);
    }
    // this should be O(1)
    public static boolean nilq(FnStrn str){
        return (str.length() == 0);
    }

    // for each item, we perform a certain given task.
    public static<T> void forItem(FnStrn str, Consumer<? super Character> work){
        for (int i = 0; i < str.length(); i += 1){
            work.accept(str.getAt(i));
        }
        return;
    }

//
    public static
	void foritm(FnStrn cs, Consumer<? super Character> work) {
	for (int i = 0; i < cs.length(); i += 1) {
	    work.accept(cs.getAt(i));
	}
	return;
    }
//
    public static
	boolean forall(FnStrn cs, Predicate<? super Character> pred) {
	for (int i = 0; i < cs.length(); i += 1) {
	    if (!pred.test(cs.getAt(i))) return false;
	}
	return true;
    }
//
    public static
	void iforitm(FnStrn cs, BiConsumer<Integer, ? super Character> work) {
	for (int i = 0; i < cs.length(); i += 1) {
	    work.accept(i, cs.getAt(i));
	}
	return;
    }
    public static
	boolean iforall(FnStrn cs, BiPredicate<Integer, ? super Character> pred) {
	for (int i = 0; i < cs.length(); i += 1) {
	    if (!pred.test(i, cs.getAt(i))) return false;
	}
	return true;
    }

    public static FnStrn reverse(FnStrn str){
        // 1. ordinary style
        char resolve[];
        int ln = str.length();
        resolve = new char[ln];

        // below looks like foritem that loops through the end
        // for(int i=0; i< ln; i+=1){
        //     resolve[i] = str.getAt(ln-1-i);
        // }
        iforitm(str, 
            (i,ch1) -> {resolve[ln-1-i] = ch1;}
        );
        return new FnStrn(resolve);
    }

}
