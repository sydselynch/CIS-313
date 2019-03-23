import java.util.Scanner;

public class HW3 {
    public static void main(String[] args) {
    
    	// Loop over the Scanner
        // Split each line into the task and the corresponding number (if one exists)
        // Depending on what the input task was, preform the corresponding function
        //      (ie) insert, findMin, removeMin, isEmpty, or print
        // Hint: Use a switch-case statement

        // Don't forget to close your Scanner!
        pQueue<Integer> queue = new pQueue(100);
        Scanner scanner = new Scanner(System.in);
        int iterations = scanner.nextInt();
        scanner.nextLine();
        for (int i=0; i < iterations; i++) {
            String operation = scanner.next();
            switch(operation) {
                case "insert" :
                    queue.insert(scanner.nextInt());
                    break;
                case "findMin" :
                    System.out.println(queue.findMin());
                    break;
                case "removeMin" :
                    System.out.println(queue.removeMin());
                    break;
                case "isEmpty" :
                    if (queue.isEmpty() == true) {
                        System.out.println("The queue is empty");
                    }
                    else {
                        System.out.println("The queue is not empty");
                    }
                    break;
                case "print" :
                    queue.print();
                    break;
                default :
                    System.out.println("Invalid input");
                    break;
            }
        }
        
        scanner.close();

    }
}
