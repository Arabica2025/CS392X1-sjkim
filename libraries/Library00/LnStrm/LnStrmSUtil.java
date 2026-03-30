//
package Library00.LnStrm;
//
import Library00.FnList.*;
import Library00.FnTuple.FnTupl2;

//
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.ToIntBiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
//
// HX-2025-10-23:
// typedef LnStrm<T> = Supplier<LnStcn<T>>
//
public class LnStrmSUtil {

    public static<T>
	LnStcn<T> eval0(LnStrm<T> fxs) {
	return fxs.eval0();
    }
//
    public static<T>
	LnStrm<T> cons0(T x0, LnStrm<T> fxs) {
	return new LnStrm<T>(() -> new LnStcn<T>(x0, fxs));
    }
//
    public static<T>
	LnStrm<T> append0
	(LnStrm<T> fxs, LnStrm<T> fys) {
	return new LnStrm<T>(
	  () -> {
	      final LnStcn<T> cxs = fxs.eval0();
	      if (cxs.nilq()) {
		  return fys.eval0();
	      } else {
		  return new LnStcn<T>(cxs.hd(), append0(cxs.tl(), fys));
	      }
	  }
       );
    }
//
    public static<T>
	void foritm0
	(LnStrm<T> fxs, Consumer<? super T> work) {
	LnStcn<T> cxs = fxs.eval0();
	while (cxs.consq()) {
	    work.accept(cxs.hd()); cxs = cxs.tl().eval0();
	}
	return /*void*/;
    }
//
    public static<T>
	boolean forall0
	(LnStrm<T> fxs, Predicate<? super T> pred) {
	LnStcn<T> cxs = fxs.eval0();
	while (cxs.consq()) {
	    if (!pred.test(cxs.hd()))
		return false;
	    else {
		cxs = cxs.tl().eval0(); continue;
	    }
	}
	return true; // all satisfy
    }
//
    public static<T,R>
	LnStrm<R> map0
	(LnStrm<T> fxs, Function<? super T, R> fopr) {
	return new LnStrm<R>(
	  () -> {
	      LnStcn<T> cxs = fxs.eval0();
	      if (cxs.nilq()) {
		  return new LnStcn<R>();
	      } else {
		  final T hd = cxs.hd();
		  final LnStrm<T> tl = cxs.tl();
		  return new LnStcn<R>(fopr.apply(hd), map0(tl, fopr));
	      }
	  }
       );
    }
//
// stream transformer: takes one stream and turn into another stream
    public static<T>
	LnStrm<T> filter0
	(LnStrm<T> fxs, Predicate<? super T> pred) {
	return new LnStrm<T>(
	  () -> {
	      LnStcn<T> cxs = fxs.eval0();
	      while (cxs.consq()) {
		  final T hd = cxs.hd();
		  final LnStrm<T> tl = cxs.tl();
		  if (pred.test(hd)) {
		      return new LnStcn<T>(hd, filter0(tl, pred));
		  } else {
		      cxs = cxs.tl().eval0();
		  }
	      }
	      return new LnStcn<T>(); // no satisfying elements found
	  }
       );
    }

	public static<T>
		LnStrm<T> merge0(LnStrm<T> fxs, LnStrm<T> fys, ToIntBiFunction<T,T> cmp){
			return new LnStrm<T>(
				() -> {
					LnStcn<T> cxs = fxs.eval0();
					LnStcn<T> cys = fys.eval0();
					T hd1 = cxs.hd();
					T hd2 = cys.hd();

					int sgn = cmp.applyAsInt(hd1, hd2);

					if (sgn <= 0){
						return new LnStcn(hd1, merge0(cxs.tl(), fys), cmp);
					} else {
						return new LnStcn(hd2, merge0(fxs, cys.tl(), cmp));
					}
				}
			);
		}

	public static LnStrm<FnTupl2<Integer,Integer>> matrixEnum(LnStrm<LnStrm<FnTupl2<Integer,Integer>>> fxss){
		LnStcn<LnStrm<FnTupl2<Integer,Integer>>> cxss = fxss.eval0();
		LnStrm<FnTupl2<Integer,Integer>> frow1 = cxss.hd();
		LnStrm<FnTupl2<Integer,Integer>> frest= cxss.tl();
		return new LnStcn(crow1.hd(), LnStrmSUtil.merge0(crow1.tl(), matrixEnum(frest), (x,y) -> {
			return (cube_sum(x) <= cube_sum(y));
		}));
	}
//
} // end of [class LnStrmSUtil{...}]
