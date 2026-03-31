import MyLibrary.LnStrm.*;

import java.util.Random;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntBiFunction;

public class Assign07_01 {
//
    public static<T>
	LnStrm<T> mergeLnStrm(LnStrm<LnStrm<T>> fxss, ToIntBiFunction<T,T> cmpr) {
        return new LnStrm<T>(
            () -> {
                LnStcn<LnStrm<T>> cxss = fxss.eval0(); // Get the current stream ready to be evaluated
                // edge case: if the stream is empty, return an empty stream
                if (cxss.nilq()) {
                    return new LnStcn<T>();
                }

                // first input stream
                // row 1
                LnStrm<T> finstrm = cxss.hd();
                LnStcn<T> cfinstrm = finstrm.eval0();

                // remaining input streams
                // rest of the rows
                LnStrm<LnStrm<T>> rest = cxss.tl();

                // edge case 2: if the current stream is empty, merge the rest
                if (cfinstrm.nilq()) {
                    return mergeLnStrm(rest, cmpr).eval0();
                }

                // recursive call to merge using m2erge0
                // LnStrm<T> merged = LnStrmSUtil.m2erge0(finstrm, mergeLnStrm(rest, cmpr), cmpr);
                LnStrm<T> merged = LnStrmSUtil.cons0(
                    cfinstrm.hd(), 
                    LnStrmSUtil.m2erge0(cfinstrm.tl(), mergeLnStrm(rest, cmpr), 
                    cmpr)
                );

                // as we merge with recursive call, eval0() would not allow to access to the prior stream
                // so we need to create a new LnStrm<T> and store our merged stream into new one
                // return the evaluated LnStrm<T> in one.
                return merged.eval0();
            }
        );
    }
} // end of [public class Assign07_01{...}]

