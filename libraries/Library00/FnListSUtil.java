package libraries.Library00;

import libraries.Library00.*;
// javac: compiler
// -cp: classpath
// directories to find the libraries
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;


public class FnListSUtil {
    // information hiding; narrow the scope of visibility and make private functions as small as possible

    public static<T> FnList<T> nil(){
        return new FnList<T>();
    }

    public static<T> FnList<T> construct(T x, FnList<T> xs){
        return new FnList<T>(x, xs);
    }


    // length is O(n) operations
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
            ys = construct(xs.head(), ys);
            xs = xs.tail();
        }
        return ys;
    }

    //append
    // concatnation
    public static<T> FnList<T> append(FnList<T> xs, FnList<T> ys){
        if (xs.isEmpty()){ // if the list is empty
            return ys;
        } else{ // if not empty
            return construct(xs.head(), append(xs.tail(), ys));
        }
    }


    //visit each individual item and perform a task
    // e.g. print out every element in the list
    // forItem(xs): culminator
    public static<T> void forItem(FnList<T> xs, Consumer<? super T> work){
        // Consume class
        // Consume.accept: 
        while(!xs.consq()){ 
                work.accept(xs.head());
                xs = xs.tail();
        }
    }

    //visit each individual item and get index of the item
    // indexforItem(xs): culminator
    public static<T> void indexforItem(FnList<T> xs, BiConsumer<Integer, ? super T> work){ // "Bi"Consumer (takes two arguments)
        int index = 0;
        while(!xs.consq()){ 
                work.accept(index,xs.head());
                index += 1;
                xs = xs.tail();
        }
    }

    // forall: check for all elements satisfy the provided predicates
    public static<T> boolean forAll(FnList<T> xs, Predicate<? super T> predicate){
        while(true){
            if (xs.isEmpty()){
                break;
            } else {
                if(predicate.test(xs.head())){
                    xs = xs.tail();
                    continue;
                } else {
                    return false; // counterexample found
                }
            }
        }
        return true;
    }

    // forall: check for all elements satisfy the provided predicates
    public static<T> boolean indexforAll(FnList<T> xs, BiPredicate<Integer,? super T> predicate){
        int index=0;
        while(true){
            if (xs.isEmpty()){
                break;
            } else {
                if(predicate.test(index,xs.head())){
                    index += 1;
                    xs = xs.tail();
                    continue;
                } else {
                    return false; // counterexample found
                }
            }
        }
        return true;
    }

    public static<T> void System$out$print(FnList<T> xs){
        System.out.print("FnList(");
        // Biconsumer (i, item) -> {...}: lambda notation
        indexforItem(xs, (i, itm) -> {
            if (i > 0){
                System.out.print(",");
            }
            System.out.print(itm.toString());
        });
        System.out.println(")");
    }
}