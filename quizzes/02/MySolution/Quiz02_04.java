//
// HX-2026-04-28: 30 points
// (plus up to 20 bonus points)
// This is more of a theory problem
// than a programming one.
//
import MyLibrary.LnStrm.*;
//
public class Quiz02_04 {
    public class AVLnode {
	int key;
	AVLnode lchild;
	AVLnode rchild;
    int height;

    AVLnode(int key){
        this.key = key;
        this.lchild = null;
        this.rchild = null;
        this.height = 1;
    }
    }
    //
    // HX: 10 points for this one
    // HX: If your implementation only
    // visit each node in [avl] at most once,
    // then it will be rewarded with some bonus
    // points (up to 20 bonus points).
    // For instance, if you compute the size of
    // height of a tree, then you have already
    // visited each node once.
    //
    // private AVLnode search(AVLnode node,int key){
    //     return node;
    // }

    /*
    case 1. not AVL if the tree is not balanced
    case 2. not AVL if the absolute value of balance factor is greater than 1
    */
    public static boolean isAVL (AVLnode avl) {
	// HX: Please implement a function that
	// tests whether a given AVLnode is a valid
	// AVL tree. If it is unclear what an
	// AVL tree, you can readily find it on-line
	// Note that you are not asked to check if avl is
	// a binary search tree in this case.

    // isAVL if emtpy
    if (avl == null){
        return true;
    }
    //2. not AVL if its abs val of balance factor is greater than 1; should be either {-1,0,1}.
    int lchildH = height(avl.lchild);
    int rchildH = height(avl.rchild);

    int abs_diff = ((lchildH - rchildH) < 0) ? (lchildH - rchildH) * -1 : (lchildH - rchildH);
    
    if (abs_diff > 1){
        return false;
    }

    return isAVL(avl.lchild) && isAVL(avl.rchild);
    }

    private static int height(AVLnode node){
        if (node == null) return 0;

        int leftH = height(node.lchild);
        int rightH = height(node.rchild);
        return 1 + (leftH > rightH ? leftH : rightH);
    }
    //
    // HX: 20 points
    // This is largely about understanding AVL trees.
    // Please explain BRIEFLY as to why the generated AVL is
    // of maximal height (not minimal height). Note that this
    // is different from what is asked in Quiz02_05.
    //
    public static boolean genAVLBST() {
	// Please genenerate a binary search RBT that
	// contains exactly 1 million keys: 0, 1, 2, ..., 999999
	// such that the height of this tree is maximal (that is,
	// as large as possible). What is this height? Please give
	// a brief explanation on your implementation strategy.
    return true;
    }

    private int h_w_fib(int numKeys){
        int min_h_2 = 0;
        int min_h_1 = 1;
        int height = 1;

        while (min_h_1 <= numKeys){
            int min_h1 = 1 + min_h_1 + min_h_2;
            min_h_2 = min_h_1;
            min_h_1 = min_h1;
            height++;
        }

        return height - 1;
    }
    public static void main (String[] args) {
	// Please add minimal testing code for isRBT()
	// Please add minimal testing code for genAVLBST()
	return /*void*/;
    }
}
