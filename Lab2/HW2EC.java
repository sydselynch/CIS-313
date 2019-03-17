public class HW2EC {
	public static boolean sameShape(BST t1, BST t2) {
		return recursiveShapeCheck(t1.getRoot(), t2.getRoot());
	}

	public static boolean recursiveShapeCheck(Node root1, Node root2) {
		if (root1 == null && root2 == null) {		// Base case
			return true;
		}

		else if (root1 == null && root2 != null) {	// Will return false if one node has a left/right child while the other doesn't
			return false;
		}
		else if (root1 != null && root2 == null) {
			return false;
		}
		else {										// Traverse down each left and right child concurrently
			return (recursiveShapeCheck(root1.getLeftChild(), root2.getLeftChild()) && recursiveShapeCheck(root1.getRightChild(), root2.getRightChild()));
		}

	}

    public static void main(String[] args) {
    	BST bst2 = new BST();
    	bst2.insert(30);
    	bst2.insert(20);
    	bst2.insert(40);
    	bst2.insert(35);
    	BST bst3 = new BST();
    	bst3.insert(3);
    	bst3.insert(2);
    	bst3.insert(5);
    	bst3.insert(4);
    	System.out.println(sameShape(bst2, bst3));		// Should return true

    	BST bst4 = new BST();
    	bst4.insert(5);
    	bst4.insert(6);
    	bst4.insert(7);
    	bst4.insert(8);
    	System.out.println(sameShape(bst3, bst4));		// false
    	System.out.println(sameShape(bst2, bst4));		// false
    }
}