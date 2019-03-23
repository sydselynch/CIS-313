import java.util.Scanner;


public class HW1 {
	public static void main(String[] args){

		Scanner scanner = new Scanner(System.in);
		int loops = scanner.nextInt();
		for (int i=0; i < loops; ++i) {
			int number = scanner.nextInt();
			if (isPalindrome(Integer.toString(number))) {
				System.out.println("This is a palindrome.");
			}
			else {
				System.out.println("Not a palindrome.");
			}
		}
		scanner.close();
	}
	
	public static boolean isPalindrome(String s){
		
		Queue<Character> queue = new Queue<Character>();
		Stack<Character> stack = new Stack<Character>();
		//TwoStackQueue<Character> twostack = new TwoStackQueue<Character>();

		for (int i=0; i < s.length(); i++){
			char c = s.charAt(i);
			queue.enqueue(c);
			stack.push(c);
			//twostack.enqueue(c);
		}

		for (int i=0; i < s.length(); i++){
			if (queue.dequeue().getData() != stack.pop().getData()) {
				return false;
			}
			
			/*
			Testing extra credit

			if (twostack.dequeue().getData() != stack.pop().getData()) {
				return false;
			}
			*/
		}

		return true;
	}
}
