/*
HX-2026-02-05: 10 points
*/

import Library00.FnList.FnList;
import Library00.FnList.FnListSUtil;


public class Assign03_02 {
    static boolean balencedq(String text) {
	//
	// There are only '(', ')', '[', ']', '{', and '}'
	// appearing in [text]. This method should return
	// true if and only if the parentheses/brackets/braces
	// in [text] are balenced.
	// Your solution must make proper use of MyStack!
	//

		// // create a new default FnList to store text
		// FnList<Character> textInit = FnListSUtil.nil();

		// // stacking all elements in text into FnList
		// for (int i = text.length()-1; i >= 0; i--){
		// 	textInit = FnListSUtil.cons(text.charAt(i),textInit);
		// }

		// check if the first char is },],) doing this outside is dangerous
		// char first = textInit.hd();
		
		// if (first == '}' || first == ')' || first ==']'){
		// 	return false;
		// }

		FnList<Character> appendStack = FnListSUtil.nil(); // create a stack
		int iteration = 0;

		while (iteration < text.length()){
			// set pointer
			char match = text.charAt(iteration); // head
			if (match == '[' || match == '{' || match =='('){ // append opener
				appendStack = FnListSUtil.cons(match, appendStack);
				iteration++;
			} else { // check for closer; 
				// 1. empty stack at this point -> unbalanced
				if (appendStack.nilq()){
					return false;
				}
				// FnList: pushes to the left and hd is the closer 
				// that we are pushing into the stack at this point
				char opener = appendStack.hd();
				if (!((opener == '{' && match == '}') ||
					(opener == '[' && match == ']') ||
					(opener == '(' && match == ')'))){
					return false;
				}
				// if match, delete the pair
				appendStack = appendStack.tl();
				iteration++;
			}
		}
		return appendStack.nilq(); // see if we have empty stack 
		// if empty: true
		// if not empty: false
    }
// return stack.nilq();
		// if it is balanced, then the reversed version of the original
		// must be same -> not for this problem... :(
		// final FnList<Character>[] revTextStack = new FnList[]{FnListSUtil.reverse(textStack)}; 

		// boolean eq = FnListSUtil.forall(textStack, ch -> {
		// 	char original = ch;
		// 	char revcomp = revTextStack[0].hd();
		// 	revTextStack[0] = revTextStack[0].tl();
		// 	return original == revcomp;
		// });

		// while (revTextStack[0].consq()){
		// 	if (eq == false){
		// 		return false;
		// 	}
		// }


		


	// helper method to check original with reversed version
	// private static Predicate<? super T> reverseCheck(FnList<Character> original, FnList<Character> rev){
	// 	while(original.consq()){
	// 		if (original.hd() == rev.hd()){
	// 			original = original.tl();
	// 			rev = rev.tl();
	// 		} else {
	// 			break;
	// 		}
	// 	}
	// }

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
}
