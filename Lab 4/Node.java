public class Node<E extends Comparable<E>> {
    private E data;
    private Node<E> leftChild;
    private Node<E> rightChild;
    private Node<E> parent;

    private int height;

    public Node(E d) {
        data = d;
        leftChild = null;
        rightChild = null;
        parent = null;
        height = 1;
    }

    public void setData(E d) {
        data = d;
    }

    public void setLeftChild(Node<E> lc) {
        leftChild = lc;
    }

    public void setRightChild(Node<E> rc) {
        rightChild = rc;
    }

    public void setParent(Node<E> p) {
        parent = p;
    }

    public void setHeight(int h){
        height = h;
    }

    public E getData() {
        return data;
    }

    public Node<E> getLeftChild() {
        return leftChild;
    }

    public Node<E> getRightChild() {
        return rightChild;
    }

    public Node<E> getParent() {
        return parent;
    }

    public int getHeight(){
        return height;
    }

    public boolean isUnbalanced(){
    
    	// Return true when this node's children's heights differ by more than 1
        // Note: When a child is null, its height is 0
        if (leftChild == null && rightChild == null) {
            return false;
        }

        else if (leftChild != null && rightChild == null) {
            if (leftChild.getHeight() > 1) {
                return true;
            }
            return false;
        }

        else if (leftChild == null && rightChild != null) {
            if (rightChild.getHeight() > 1) {
                return true;
            }
            return false;
        }
        int balance = leftChild.getHeight() - rightChild.getHeight();
        if (balance < -1 || balance > 1) {
            return true;
        }
        return false;
    }

}
