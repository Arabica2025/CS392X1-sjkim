import java.util.function.Consumer;
import java.util.function.BiConsumer;

interface MyPQueue<T> {
//
    int size();
//
    boolean isFull(); // checks for fullness
    boolean isEmpty(); // checks for emptiness
//
    T top$raw(); // defined if !isEmpty()
    T top$opt(); // defined if !isEmpty() // T is optional
    T top$exn() throws MyPQueueEmptyExn; // defined if !isEmpty() 
//
    T deque$raw(); // defined if !isEmpty(), Generic but only Integer input predefined
    T deque$raw(int root); // defined if !isEmpty(), uses Comparable interface for Generic type input
    T deque$opt(); // defined if !isEmpty() // T is optional
    T deque$opt(int root); // defined if !isEmpty() // T is optional, takes root idx parameter(0), uses Comparable interface for Generic type input.
    T deque$exn() throws MyPQueueEmptyExn; // defined if !isEmpty() 
    T deque$exn(int root) throws MyPQueueEmptyExn; // defined if !isEmpty() // takes root idx parameter(0), uses Comparable interface for Generic type input.   
//
    void enque$raw(T itm, int last); // defined if !isFull(), takes idx parameter for Generic type input, idx is always size().
    void enque$raw(T itm); // defined if !isFull()
    void enque$exn(T itm) throws MyPQueueFullExn; // defined if !isFull()
    void enque$exn(T itm, int last) throws MyPQueueFullExn; // defined if !isFull(), takes idx parameter for Generic type input, idx is always size().
    boolean enque$opt(T itm); // defined if !isFull() // true/false: succ/fail
    boolean enque$opt(T itm, int last); // defined if !isFull() // true/false: succ/fail, takes idx parameter for Generic type input, idx is always size().
//
} // end of [interface MyPQueue<T>{...}]
