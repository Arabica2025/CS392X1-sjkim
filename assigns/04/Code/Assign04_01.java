/*
HX-2026-02-13: 10 points
*/
import MyLibrary.FnList.*;
import MyLibrary.FnStrn.*;

public class Assign04_01 {

	// FnList for stack
	private	static FnList<Character> appendStack;
	
    static boolean balencedq(String text) {
	//
	// There are only '(', ')', '[', ']', '{', and '}'
	// appearing in [text]. This method should return
	// true if and only if the parentheses/brackets/braces
	// in [text] are balenced.
	// Your solution must make proper use of FnList (as a stack)!
	//
		if (text == null){
			return false;
		}
		appendStack = FnListSUtil.nil();
		// create new FnStrn object with input text
		FnStrn raw = new FnStrn(text);
		// FnList for stack
		
		if (FnStrnSUtil.nilq(raw)){
			return true;
		}

		boolean check = FnStrnSUtil.forall(raw, ch -> checker(ch));

		return check && appendStack.nilq();
    }

	private static boolean checker(char ch){
		if (ch == '[' || ch == '{' || ch == '('){
			appendStack = FnListSUtil.cons(ch, appendStack);
			return true;
		} else {
			if (appendStack.nilq()){
				return false;
			}
			char opener = appendStack.hd();

			boolean match = (ch == ')' && opener == '(')
						|| (ch == '}' && opener == '{')
						|| (ch == ']' && opener == '[');

			if (!match){
				return false;
			}
			appendStack = appendStack.tl();
			return true;
		}
	}

    public static void main(String[] args) {
	// Please write some testing code for your 'balencedq"


		System.out.println("------- testing for true state: testBalance1,3,4 must return true -------");
		// 1. testing true state
		String testBalance1 = "({()[({})]})";

		if(balencedq(testBalance1)){
			System.out.println("testBalence1 is balanced");
		} else{
			System.out.println("testBalence1 is not balanced");
		}

		String testBalance3 = "[({})({[]})]";

		if(balencedq(testBalance3)){
			System.out.println("testBalence3 is balanced");
		} else{
			System.out.println("testBalence3 is not balanced");
		}

		String testBalance4 = "{[()()([])]}";

		if(balencedq(testBalance4)){
			System.out.println("testBalence4 is balanced");
		} else{
			System.out.println("testBalence4 is not balanced");
		}
		// 2. testing false state

		System.out.println();
		System.out.println("------- testing for false state: testBalance2,5,6 must return false -------");
		String testBalance2 = "({()[({})])}";

		if(balencedq(testBalance2)){
			System.out.println("testBalence2 is balanced");
		} else{
			System.out.println("testBalence2 is not balanced");
		}

		String testBalance5 = "((())";

		if(balencedq(testBalance5)){
			System.out.println("testBalence5 is balanced");
		} else{
			System.out.println("testBalence5 is not balanced");
		}

		String testBalance6 = "{[()]}]";

		if(balencedq(testBalance6)){
			System.out.println("testBalence6 is balanced");
		} else{
			System.out.println("testBalence6 is not balanced");
		}	
    }
} // end of [public class Assign04_01{...}]
