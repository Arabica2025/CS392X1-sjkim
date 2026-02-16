package Library00.MyStack;

interface MyStack<T> {
    // generic interface
    /* a way to store methods or types 
     */

    int size();

    boolean isFull(); // check if it's full
    boolean isEmpty();// check if it's empty

    /*
    $raw
    $opt
    $exn
    */
    // raw: external approach; not caller's responsibility to check special cases
    // opt, exn: internal approach; it is caller's responsibility to check special cases

    // top: like a peak(); returns the first element (element at the top)
    T top$raw(); // defined if !isEmpty()
    T top$opt(); // same with top$raw; but T is optional here; it is for special cases; have to check if the return value is not null when using this method
    T top$exn(); throws MyStackEmptyExn; // defined if !isEmpty(); throw exception if special cases

    // pop: gives and removes first element from the stack
    T pop$raw();
    T pop$opt();
    T pop$exn();

    // push: guranteed that there is a space in the stack, push each element inside the stack
    void push$raw(T itm);
    void push$exn(T itm) throws MyStackEmptyExn;    
    boolean push$opt(T itm);



}
