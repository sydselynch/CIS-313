import java.util.Scanner;

public class TestContents{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        Queue<Character> queue = new Queue<Character>();
        Stack<Character> stack = new Stack<Character>();
        TwoStackQueue<Character> twostack = new TwoStackQueue<Character>();
        while (scan.hasNext()){
            String input = scan.nextLine();
            for(int i = 0; i < input.length(); i++){
                queue.enqueue(input.charAt(i));
                stack.push(input.charAt(i));
                twostack.enqueue(input.charAt(i));
            }
            System.out.println("Stack");
            stack.printStack();
            System.out.println();
            
            System.out.println("Queue");
            queue.printQueue();
            System.out.println();

            System.out.println("TwoStackQueue");
            twostack.printQueue();
            System.out.println();
        }
    }
}