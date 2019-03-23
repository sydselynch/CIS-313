import java.util.Scanner;


public class HW2 {
    public static void main(String[] args) {

        // Loop over the Scanner
        // Split each line into the task and the corresponding number (if one exists)
        // Depending on what the input task was, preform the corresponding function
        //      (ie) insert, delete, find, inoder, preorder, or postorder
        // Hint: Use a switch-case statement

        // Don't forget to close your Scanner!
    	BST bst = new BST();
    	Scanner scanner = new Scanner(System.in);
    	int iterations = scanner.nextInt();
    	scanner.nextLine();
    	for (int i=0; i < iterations; i++) {
	    	String operation = scanner.next();
	    	switch(operation) {
	    		case "delete" :
	    			bst.delete(scanner.nextInt());
	    			break;
	    		case "insert" :
	    			bst.insert(scanner.nextInt());
	    			break;
	    		case "inorder" :
	    			bst.traverse("inorder", bst.getRoot());
	    			break;
	    		case "preorder" :
	    			bst.traverse("preorder", bst.getRoot());
	    			break;
	    		case "postorder" :
	    			bst.traverse("postorder", bst.getRoot());
	    			break;
	    		default :
	    			System.out.println("Invalid input");
	    			break;
	    		}
		    }
    	scanner.close();
    }
}