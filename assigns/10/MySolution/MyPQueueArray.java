import java.util.function.Consumer;
import java.util.function.BiConsumer;

/*
    Array-based Priority Queue that supports the operations of:
        1. enque
        2. deque

    Underlying data structure: array-based heap.

    Priority Queue: First In First Out
        - Highest Priority comes first
        - Lowest Priority comes last

    Max Heap
 */

public class MyPQueueArray<T extends Comparable<T>> extends MyPQueueBase<T> {
    public static class Pair<T>{
        T a;
        T b;
        Pair(T a, T b){
            this.a = a;
            this.b = b;
        }
    }
    public static class Compare<T>{
        // we need some class to compare generic type
        private BiConsumer<Pair<T>, int[]> cmp;
        private int[] output = new int[1];
        
        public Compare(BiConsumer<Pair<T>, int[]> cmp) {
            this.cmp = cmp;
        }
        // Returns sign of compare(a, b): negative / zero / positive.
        public int sign(T a, T b) {
            cmp.accept(new Pair<>(a, b), output);
            return output[0];
        }
        public boolean less(T a, T b){
            return sign(a,b)<0;
        }
        public boolean less_eq(T a, T b){
            return sign(a,b)<= 0;
        }
        public boolean great(T a, T b){
            return sign(a,b)>0;
        }
        public boolean great_eq(T a, T b){
            return sign(a,b)>=0;
        }
        public boolean equal(T a, T b){
            return sign(a,b)==0;
        }
    }
    private int size;
    private T[] array;
    private Compare<T> cmp;
    MyPQueueArray(int size, Compare<T> cmp){
        this.size = 0;
        this.array = (T[]) new Object[size];
        this.cmp = cmp;
    }

    public int size(){
        return size;
    }

    public boolean isFull(){
        return size == array.length;
    }


    public T top$raw(){
        return array[0];
    }

    private void exchange(int itm1idx, int itm2idx){
        T temp = array[itm1idx];
        array[itm1idx] = array[itm2idx];
        array[itm2idx] = temp;
    }

    public T deque$raw(){
        int rootidx = 0;
        T rmd = array[rootidx];// save the top element to be rmved
        // case: we have only one element in the heap <- checking this is inefficient I guess on average
        // if (size() <= 1){
        //     array[rootidx] = null;
        //     size -= 1;
        //     return rmd;
        // }

        // case: we have multiple elmts in the heap
        // put the last elmt to the top
        array[rootidx] = array[size-1];
        // empty the last elmt
        array[size-1] = null;
        // decrement the size
        size -= 1;

        // if we have multiple elmts in the heap after rm,
        // need to check for sifting down
        if (size() > 1){
            siftDwn(rootidx);
        }
        return rmd;
    }

    public T deque$raw(int root){
        T rm = array[root]; // save the root element to return
        array[root] = array[size()-1]; // move the last to the top
        array[size()-1] = null; //empty the last 
        size-=1; //decrement size
        siftDown();
        return rm;
    }


    // no need to check for no child case
    // as per !isEmpty() for $deque$raw()
    private int cmpchild(int lchild, int rchild){

        /* return left child if
            1. right child goes out of bounds(size)
            2. left child is greater than the right child
        otherwise, return right child
         */
        return (rchild >= size() || cmp.great_eq(array[lchild], array[rchild])) ? lchild : rchild;
    }

    private <T extends Comparable<T>> int cmpchild(T lchild, T rchild){
        // compare two children
        // if we don't have right child OR left child >= right child
            // return 0
            // otherwise return 1
            // binary representation
        // compare generic inputs
        return (rchild == null || lchild.compareTo(rchild) >= 0) ? 0 : 1;
    }

    private void siftDown(){
        int i = 0; // start from the root
        while(true){
            int lchildidx = (2 * i) + 1;
            int rchildidx = (2 * i) + 2;

            // get the children to sift down
            T lchild = (lchildidx < size()) ? array[lchildidx] : null;
            T rchild = (rchildidx < size()) ? array[rchildidx] : null;

            int childindicate = cmpchild(lchild, rchild);

            int childidx = (childindicate == 0) ? lchildidx : rchildidx;
            // if the current element is greater than or equal to the child element
            // we are done sifting down
            // this is for deque so we sift down until the current element is less than the child
            if (array[i].compareTo(array[childidx]) >= 0){
                break;
            }

            exchange(i, childidx);
            i = childidx;
        }
    }


    private void siftDwn(int current){

        // until we do not have a left child
        // if we don't have a left child,
        // then we are already at the bottom level so no need to sift down any longer
        while((2 * current) + 1 < size()){ 
            int lchild = (2 * current) + 1;
            int rchild = (2 * current) + 2;

            int childidx = cmpchild(lchild, rchild);
            // if the parent is less than the left child
            if (cmp.less(array[current], array[childidx])){
                exchange(current, childidx);
                current = childidx;

            } else {
                break;
            }
        }
    }

    // Generic Integer input only
    public T deque$exn() throws MyPQueueFullExn{
        if (!isEmpty()){
            throw new MyPQueueFullExn();
        }
        return deque$raw();
    }

    // Generic Object input using Comparable interface
    public T deque$opt(int root){
        return (isEmpty()? null:deque$raw(root));
    }

    // root is always index 0 
    public T deque$exn(int root) throws MyPQueueEmptyExn{
        if (!isEmpty()){
            throw new MyPQueueEmptyExn();
        }
        return deque$raw(root);
    }
    

    public void enque$raw(T itm){
        if (isEmpty()){
            array[0] = itm;
            size+=1;
            return;
        }
        // assign the itm at last idx of current size and sift up
        array[size] = itm; 
        size+=1; //increment size by 1 as we add an element

        int i = size() -1; // index of input
        siftUp(i);
    }

    // generic input; other than Integer can be parameterized
    public void enque$raw(T itm, int last){
        if (isEmpty()){
            array[0] = itm;
            size+=1;
            return;
        }
        // idx is always the last or size()
        array[last] = itm;
        size+=1;
        siftUp(last);
    }

    public void enque$exn(T itm, int last) throws MyPQueueEmptyExn{
        boolean res = enque$opt(itm, last);
        if (!res){
            throw new MyPQueueEmptyExn();
        } else {
            return;
        }
    }

    public boolean enque$opt(T itm, int last){
        if (isFull()){
            return false;
        } else {
            // last parameter is always the last index
            enque$raw(itm, last);
            return true;
        }
    }

    private void siftUp(int current){
        while(current > 0){
            int parent = (current - 1) / 2;

            if (cmp.great(array[current], array[parent])){
                exchange(current, parent);
                current = parent;
            } else {
                break;
            }
        }
    }

    // helper for generic type
    private void siftUp(){
        int currentidx = size() - 1; // index of input; start from the last element
        while(currentidx > 0){
            int parentidx = (currentidx - 1) / 2;
            // if current element is greater than parent element
            // sift up
            if (array[currentidx].compareTo(array[parentidx])>0){
                exchange(currentidx, parentidx);
                currentidx = parentidx; // update current idx to parent
            } else {
                break;
            }
        }
    }

    // public static void main(String args[]){
    //     MyPQueueArray.Compare<Integer> cmp = new MyPQueueArray.Compare<>(
    //             (pair, out) -> out[0] = pair.a - pair.b
    //     );

    //     MyPQueueArray<Integer> pqtest = new MyPQueueArray<>(16, cmp);

    //     int[] inputtest = {5,1,3,4,19,25,82,11,2,9};
    //     System.out.println("1. enqueue test");
    //     for (int x : inputtest){
    //         pqtest.enque$exn(x);
    //         System.out.println("Enqueue " + x);
    //     }
    //     // must dequeue from the highest value to lowest value(max heap Priority Queue)
    //     System.out.println("2. dequeue test");
    //     while (pqtest.size() > 0){
    //         int v = pqtest.deque$opt();
    //         System.out.println("Dequeue " + v);
    //     }
    // }
}
