public class test {
    public static void main(String[] args) {
    	BST bst = new BST();
    	bst.insert(30);
    	bst.insert(40);
    	bst.insert(20);
    	bst.insert(10);
    	bst.insert(35);
    	bst.delete(30);
    	bst.traverse("inorder", bst.getRoot());
	}
}