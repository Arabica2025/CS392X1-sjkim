package Library00.MyStack;

import Library00.FnList.*;
import java.util.function.Consumer;
import java.util.function.BiConsumer;

// abstract class
public abstract class MyStackBase<T> implements MyStack<T> {
    // should put some code like
    public boolean isEmpty(){
        return (size() <= 0);
    }

    // sometimes $raw is used
    // for some methods, $opt or $exn is less efficient because the speical case is already staisfied 
    public T top$opt(){
        return (isEmpty() ? null : top$raw());
    }
    public T top$exn() throws MyStackEmptyExn{
        T top = top$opt();
        if (top != null) return top;
        else throw new MyStackEmptyExn();
    }

    public void pus$exn(T itm) throws MyStackEmptyExn{
        boolean res = push$opt(itm);
        if(!res) throw new MyStackEmptyExn();
    }

    // **assert: runtime checks

}
