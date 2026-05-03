//
// HX-2026-04-28: 50 points
// A partial implementation of
// randomized doubly linked binary search tree
// 30 points for reroot and 20 points for insert
//
import MyLibrary.FnList.*;
public class Quiz02_06 {
    Node root = null;
    public class Node {
	int key; // key stored in the node
	int size; // size of the tree rooted as the node
	Node parent; // parent of the node
	Node lchild; // left-child of the node
	Node rchild; // right-child of the node

		Node(int key){
			this.key = key;
			this.size = 1;
			this.parent = null;
			this.lchild = null;
			this.rchild = null;
		}
    }
	// need right and left rotation
	// 1. right rotation
	private void rrotate(Node xp){
		// we are rotating the target xp to right
		Node x_prime = xp.lchild;

		// if the left child x' does not exist, no rotate
		if (x_prime == null){
			return;
		}
		Node C = x_prime.rchild;
		Node cp = xp.parent;

		x_prime.parent = cp;
		if(cp == null){
			root = x_prime;
		} else if (cp.lchild == xp){
			cp.lchild = x_prime;
		} else {
			cp.rchild = x_prime;
		}

		x_prime.rchild = xp;
		xp.parent = x_prime;

		xp.lchild = C;
		if (C != null){
			C.parent = xp;
		}

		updateSize(xp);
		updateSize(x_prime);
	}

	private void lrotate(Node xp){
		Node x_prime = xp.rchild;
		if (x_prime == null){
			return;
		}

		Node C = x_prime.lchild;
		Node cp = xp.parent;

		x_prime.parent = cp;
		if(cp == null){
			root = x_prime;
		} else if (cp.lchild == xp){
			cp.lchild = x_prime;
		} else {
			cp.rchild = x_prime;
		}

		x_prime.lchild = xp;
		xp.parent = x_prime;

		xp.rchild = C;
		if (C != null){
			C.parent = xp;
		}

		updateSize(xp);
		updateSize(x_prime);
	}

	private FnList<Node> linearize(Node x, FnList<Node> accumulate){
		if (x == null){
			return accumulate;
		}
		accumulate = FnListSUtil.cons(x, accumulate);
		accumulate = linearize(x.lchild, accumulate);
		accumulate = linearize(x.rchild, accumulate);
		return accumulate;
	}

    public void reroot() {
	// HX-2025-11-20: 30 points
	// [reroot] picks a node RANDOMLY and
	// uses rotations to turn this picked node
	// into the root of a new binary search tree
	// (containing the same set of keys)
		if (root == null){
			return;
		}// no need to reroot if there is no tree

		FnList<Node> linearNodes = linearize(root, FnListSUtil.nil());

		int n = linearNodes.length();

		int idx = FnListSUtil.rand$int$make(n).hd() % n;
		if (idx < 0){
			idx *= -1;
		}

		FnList<Node> current = linearNodes;
		for (int i =0; i < idx; i++){
			current = current.tl();
		}

		Node target = current.hd();
		while(target.parent != null){
			Node parent = target.parent;
			if (parent.lchild == target){
				rrotate(parent);
			} else {
				lrotate(parent);
			}
		}
    }
	// helper to find the size of each node
	private int nodeSize(Node x){
		return (x != null) ? x.size : 0;
	}

	// helper to resize after update
	private void updateSize(Node x){
		if(x != null){
			x.size = nodeSize(x.lchild) + nodeSize(x.rchild) + 1;
		}
	}
    public boolean insert(int key) {
	// HX-2025-11-20: 20 points
	// If key is in the tree stored at [root],
	// [insert] does no nothing and just returns false
	// If key is not in the tree stored at [root],
	// the key is inserted as a leaf node and the new
	// tree is still a binary search tree and [insert]
	// returns true (to indicate insertion is done).
		if (root == null){
			root = new Node(key);
			return true;
		} // the inserted key is at root if no elements in the tree yet

		Node current = root; // trace down pointer
		Node parent = null; // parent pointer should be kept
		while (current != null){
			if (key == current.key){
				return false;
			}// if there is a Node that has the same key, return false

			parent = current; // trace down parent
			// if the input key is less than current key, 
				// go left
				// otherwise, go right
			current = (key < current.key) ? current.lchild : current.rchild; // update current Node
		}

		// if there is no overlapping key,
		// create new node that contains input key
		Node insert = new Node(key);
		// update parent
		insert.parent = parent;

		// determine where we would insert the input key
		// if input key is less than parent's, go lchild
		// otherwise go rchild.
		if (insert.key < parent.key){
			parent.lchild = insert;
		} else {
			parent.rchild = insert;
		}
		// update the size of all parents by incrementing 1
		for (Node siftup = parent; siftup != null; siftup = siftup.parent){
			updateSize(siftup);
		}
		return true; // always return true
		// insertion never fails.
    }
    public static void main (String[] args) {
	// Please add minimal testing code for reroot()
	// Please add minimal testing code for insert()

		Quiz02_06 test = new Quiz02_06();
		int[] keys = {41,2,3,8,10,1,78,94,32};
		for(int k : keys){
			boolean isValid = test.insert(k);
			System.out.println("insert " + k + ": " + isValid);
		}
		int randidx = FnListSUtil.rand$int$make(1).hd() % keys.length;
		int randKey = keys[randidx];
		System.out.println("insert random keys" + randKey + 
		" in the given list: " + test.insert(randKey)
		);

		System.out.println();
		test.reroot();
	return /*void*/;
    }
}
