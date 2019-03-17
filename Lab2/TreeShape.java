public class TreeShape {
	public boolean sameShape(BST t1, BST t2) {
		return recursiveShapeCheck(t1.getRoot(), t2.getRoot());
	}

	public boolean recursiveShapeCheck(Node root1, Node root2) {
		if (root1 == null && root2 == null) {		// Base case
			return true;
		}

		else if (root1 == null && root2 != null) {	// Will return false if one node has a left/right child while the other doesn't
			return false;
		}
		else if (root1 != null && root2 == null) {
			return false;
		}
		else {
			return (recursiveShapeCheck(root1.getLeftChild(), root2.getLeftChild()) && recursiveShapeCheck(root1.getRightChild(), root2.getRightChild()));
		}

	}
}