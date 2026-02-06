package libraries.Library00;

public class FnList<T> {
    private Node root;
    private class Node {
        T head;
        FnList<T> tail;
        Node(T x0, FnList<T> xs){
            head = x0;
            tail = xs;
        }
    }
    // Constructor for an empty list
    public FnList(){
        root = null;
    }

    // Constructor for a non-empty list
    public FnList(T x0, FnList<T> xs){
        root = new Node(x0, xs);
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean consq() {
        return root != null;
    }
    // $exn and $opt: internal approach

    /* hd$exn(exn: exception): (public announcement) throw an Exception (cases that I don't know the answers for)
        -> then why don't we just return special values like -1?

        */

    /* hd$opt: we have two kinds of values (normal, special): telling the caller
        normal: what it returns as normal
        special: tell caller that something needs to be done to fix this
       */

    // $raw: external approach

    /* hd$raw: I know it cannot happen but my logic is wrong if it happens
     */

    //selectors (select the component of the list)
    public T head(){
        // = hd$raw
        return root.head;
    }

    public FnList<T> tail() {
        // = tl$raw
        return root.tail;
    }

    // [length] is O(n)
    // if we are checking if length() == 0 to check if the list is empty -> too expensive O(n); try alternatives to improve efficiency
/*
    public int length() {
        int res = 0;
        FnList<T> xs = this;
        while(true) {
            if (xs.isEmpty()) {
                break;
            }
            res += 1;
            xs = xs.tail();
        }
        return res;
    }
*/

}
