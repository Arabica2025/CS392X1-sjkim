//
// HX-2026-04-28: 50 points
//
/*
A description on Game-of-24 and an accompanying
demo can be found by visiting the following link:
https://github.com/githwxi/XATSHOME/tree/main/contrib/githwxi/pground/proj002%40250507/misc004
Please give a high-level description in English as to
how Game-of-24 can be solved using either DFS or BFS.
Your description should be given in a README file for
this assignment.
1. Please give a DFS-based implementation according to your
   description that should directly use the DFirstEnumerate method.
2. Please give a BFS-based implementation according to your
   description that should directly use the BFirstEnumerate method.
*/
//
import MyLibrary.LnStrm.*;
import MyLibrary.FnGtree.*;
import MyLibrary.FnList.*;

class UnsupportedOpr
    extends RuntimeException {
    String opr;
    public UnsupportedOpr(String opr) {
	this.opr = opr;
    }
}

abstract class Term {
    public String tag = "Term";
    public abstract double eval();
    // eval() returns the value of the term
}

class TermInt extends Term {
    public int val;
    public TermInt(int val) {
	this.tag = "TermInt"; this.val = val;
    }
    public double eval() { return val; }
}

class TermOpr extends Term {
    public String opr;
    public Term arg1, arg2;
    public TermOpr(String opr0, Term arg1, Term arg2) {
	this.tag = "TermOpr";
	this.opr = opr0; this.arg1 = arg1; this.arg2 = arg2;
    }
    public double eval() {
	switch (opr) {
	  case "+":
	      return arg1.eval() + arg2.eval();
	  case "-":
	      return arg1.eval() - arg2.eval();
	  case "*":
	      return arg1.eval() * arg2.eval();
	  case "/":
	      return arg1.eval() / arg2.eval();
	}
	throw new UnsupportedOpr(     opr     );
    }
}

public class Quiz02_03 {
//
    public LnStrm<Term> GameOf24_bfs_solve
	(int n1, int n2, int n3, int n4) {
	// Please find ALL the solutions of GameOf24
	// for the input n1, n2, n3, and n4
	// Each solution is represented as a Term
	// that evaluates to 24
	// Note that your solution should be based on
	// BFirstEnumerate implemented in Assign07_01
	// we need to return the solutions in the order they are found.


	// 1. Wrap the four ints as a starting pool.
    // 2. Build an FnGtree<Term> rooted at this pool.
    // 3. Hand the root to DFirstEnumerate to get LnStrm<Term> of all node values.
    // 4. Filter for "leaf nodes whose value is 24".
    // 5. Return.
		
		// root
		FnGtree<Term> root = roots(n1, n2, n3, n4);
		LnStrm<Term> bfs_enum = FnGtreeSUtil.BFirstEnumerate(root);
		return LnStrmSUtil.filter0(bfs_enum, (t) -> isNodeSol(t));
    }
//
    public LnStrm<Term> GameOf24_dfs_solve
	(int n1, int n2, int n3, int n4) {
	// Please find ALL the solutions of GameOf24
	// for the input n1, n2, n3, and n4
	// Note that your solution should be based on
	// DFirstEnumerate implemented in Assign07_01

		FnGtree<Term> root = roots(n1, n2, n3, n4);
		LnStrm<Term> dfs_enum = FnGtreeSUtil.DFirstEnumerate(root);
		return LnStrmSUtil.filter0(dfs_enum, (t) -> isNodeSol(t));
    }
//

	private boolean isNodeSol(Term t){
		return oprCount(t) == 3;
	}

	private int oprCount(Term t){
		if (t.tag.equals("TermOpr")){
			TermOpr count = (TermOpr) t;
			return 1 + oprCount(count.arg1) + oprCount(count.arg2);
		}
		return 0;
	}
	// check if the term is solved
	// meaning evaluated n1 n2 n3 n4 = 24
	private boolean isSolved(Term term) {
		return term.eval() == 24;
	}

	// Combinations of possible operations.
	// we have +,-,*,/
	// for - and /, the order matters
	private Term[] combOps(Term double1, Term double2){
		// terms are double
		// division by zero not allowed
		boolean valid_A_B = double2.eval() != 0; // double1 / double2
		boolean valid_B_A = double1.eval() != 0; // double2 / double1

		// if division by zero occurs, skip that part
		int n = 4 + (valid_A_B ? 1 : 0) + (valid_B_A ? 1 : 0);
		Term[] res = new Term[n];

		// loop to fill the operations
		int i = 0;
		for (String opr : new String[]{"+", "-", "*", "/"}) {
			res[i++] = new TermOpr(opr, double1, double2);
		}
		// account for division permutation
		if (valid_A_B) {
			res[i++] = new TermOpr("/", double1, double2);
		}
		if (valid_B_A) {
			res[i++] = new TermOpr("/", double2, double1);
		}
		return res;
	}
	
	// after first value, r1, produced, we place [n3, n4, r1]
	private Term[] appendR1(Term[] original, int i, int j, Term r1){
		Term[] result = new Term[original.length - 1]; // size 3
		int k = 0; // iteration in result array
		// skip n1 and n2 and store n3 and n4
		for (int q = 0; q < original.length; q++){
			if (q != i && q != j){
				result[k++] = original[q];
			}
		}
		// now r1 goes to the end
		result[k] = r1;
		return result;
	}

	// build the roots to start the BFS/DFS
	private FnGtree<Term> roots(int n1, int n2, int n3, int n4){
		Term[] numlist = new Term[] {
			new TermInt(n1),
			new TermInt(n2),
			new TermInt(n3),
			new TermInt(n4)
		};
		return makeNode(new TermInt(0), numlist);
	}


	// private FnList<FnGtree<Term>> children(Term[] num_list){
	// 	int n = num_list.length;
	// 	if (n <= 1){
	// 		return FnListSUtil.nil();
	// 	}

	// 	// the number of child nodes: C(n,2) * number of ops(6)
	// 	int numchild = (n * (n-1) / 2) * 6;

	// 	FnGtree<Term>[] root = new FnGtree[numchild]; // initialize

	// 	int idx = 0;
	// 	for (int i = 0; i < n; i++){
	// 		for (int j = i + 1; j < n; j++){
	// 			Term num1 = num_list[i];
	// 			Term num2 = num_list[j];
	// 			Term[] opslist = combOps(num1, num2);

	// 			for (int k = 0; k < opslist.length; k++){
	// 				Term r1 = opslist[k];
	// 				Term[] child = appendR1(num_list, i, j, r1);
	// 				numchild[idx++] = makeNode(r1, child);
	// 			}
	// 		}
	// 	}

	// }


	// need to create nodes to pass to the FnGtree
	// that has a value and its children
	private FnGtree<Term> makeNode(Term value, Term[] num_list){
		return new FnGtree<Term>() {
			@Override
			public Term value(){
				return value;
			}
			@Override
			// with combinations of [n3,n4, r1], we create child node for each 
			// list of tree nodes of Term 
			public FnList<FnGtree<Term>> children() {
				int n = num_list.length;
				if (n <= 1) return FnListSUtil.nil();

				FnList<FnGtree<Term>> result = FnListSUtil.nil();
				for (int i = n - 1; i >= 0; i--) {
					for (int j = n - 1; j > i; j--) {
						Term a = num_list[i];
						Term b = num_list[j];

						Term[] r1_list = combOps(a, b);
						for (int c = r1_list.length - 1; c >= 0; c--) {
							Term r1 = r1_list[c];
							Term[] child = appendR1(num_list, i, j, r1);
							result = FnListSUtil.cons(makeNode(r1, child), result);
						}
					}
				}
				return result;
			}
		};
	}

	public static void main(String[] args){
		Quiz02_03 quiz = new Quiz02_03();
		// Test BFS
		LnStrm<Term> bfsSolutions = quiz.GameOf24_bfs_solve(5, 1, 2, 3);
		System.out.println("BFS Solutions:");
  		bfsSolutions.foritm0((t) -> System.out.println(t.eval()));
		// Test DFS
		LnStrm<Term> dfsSolutions = quiz.GameOf24_dfs_solve(5, 1, 2, 3);
		System.out.println("DFS Solutions:");
		dfsSolutions.foritm0((t) -> System.out.println(t.eval()));
	}
    // Please add minimal testing code for GameOf24_bfs_solve
    // Please add minimal testing code for GameOf24_dfs_solve
//
} // end of [public class Quiz02_03{...}]
