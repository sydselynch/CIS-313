import java.lang.Math;

public class AVL<E extends Comparable<E>> {
    private Node<E> root;

    public AVL(){
        root = null;
    }

    private void updateHeight(Node<E> node) {
        if (node == null) {
            return;
        }

        // No children, must be external node so its height is 1
        else if (node.getRightChild() == null && node.getLeftChild() == null) {
            node.setHeight(1);

        }

        // Only right child, set height equal to the height of right child + 1
        else if (node.getRightChild() != null && node.getLeftChild() == null) {
            node.setHeight(node.getRightChild().getHeight() + 1);
        }

        // Only left, set height = height of left + 1
        else if (node.getRightChild() == null && node.getLeftChild() != null) {
            node.setHeight(node.getLeftChild().getHeight() + 1);
        }

        // Set height to the maximum height of its children + 1
        else {
            node.setHeight(Math.max(node.getLeftChild().getHeight(), node.getRightChild().getHeight()) + 1);
        }
        
        // Recursively update height of parents
        updateHeight(node.getParent());
    }

    public Node<E> getRoot(){
        return root;
    }

    public void insert(E data){
        boolean done = false;
        Node<E> temp = root;

        while(!done){
            if (root == null) {
                root = new Node<E>(data);
                temp = root;
                done = true;
            } else if (temp.getData().compareTo(data) > 0){
                if (temp.getLeftChild() == null){
                    temp.setLeftChild(new Node<E>(data));
                    temp.getLeftChild().setParent(temp);
                    done = true;
                }
                temp = temp.getLeftChild();
            } else if (temp.getData().compareTo(data) <= 0){
                if (temp.getRightChild() == null){
                    temp.setRightChild(new Node<E>(data));
                    temp.getRightChild().setParent(temp);
                    done = true;
                }
                temp = temp.getRightChild();
            }
        }

        // Update the height of inserted node and subsequently its ancestors
        updateHeight(temp);
        // Set temp = first unbalanced node
        while (temp.getParent() != null && temp.isUnbalanced() == false) {
            temp = temp.getParent();
        }

        if (temp.isUnbalanced()) {
            // Right right case, perform left rotate
            if (balance(temp) < -1 && data.compareTo(temp.getRightChild().getData()) > 0) {
                leftRotate(temp);
            }
            // Right left, perform right left rotate
            else if (balance(temp) < -1 && data.compareTo(temp.getRightChild().getData()) < 0) {
                rightRotate(temp.getRightChild());
                leftRotate(temp);
            }
            // Left left, perform right rotate
            else if (balance(temp) > 1 && data.compareTo(temp.getLeftChild().getData()) < 0) {
                rightRotate(temp);
            }
            // Left right, perform left right rotate
            else if (balance(temp) > 1 && data.compareTo(temp.getLeftChild().getData()) > 0) {
                leftRotate(temp.getLeftChild());
                rightRotate(temp);
            }
        }
    }

    public Node<E> search(E data){
        // Return the node that corresponds with the given data
        // Note: No need to worry about duplicate values added to the tree
        Node<E> currentNode = root;

        while (currentNode != null){
            if (data == currentNode.getData()) {
                return currentNode;
            }
            else if (data.compareTo(currentNode.getData()) < 0) {
                currentNode = currentNode.getLeftChild();
            }
            else {
                currentNode = currentNode.getRightChild();
            }
        }
        return null;
    }

    private Node<E> findMin(Node<E> node) {
        Node<E> currentNode = node;
        while (currentNode.getLeftChild() != null) {
            currentNode = currentNode.getLeftChild();
        }
        return currentNode;
    }


    public void delete(E data){
    	Node<E> temp = search(data);
        Node<E> parent = null;
        Node<E> grandparent = null;
        Node<E> leftChild = null;
        Node<E> rightChild = null;

        if (temp.getParent() != null) {
            parent = temp.getParent();
        }

        if (parent != null && parent.getParent() != null) {
            grandparent = parent.getParent();
        }

        if (temp == null){
            return;
        }
        Node<E> replacement = new Node(null);

        boolean isRight;
        boolean isLeft;
        boolean isRoot;

        boolean hasChildren;
        boolean hasLeft;
        boolean hasRight;
        boolean hasBoth;

        // Find out if the node to be deleted is the root or if it is a left/right child
        if (temp == root){
            isRoot = true;
            isLeft = false;
            isRight = false;
        } else if (temp == temp.getParent().getLeftChild()){
            isRoot = false;
            isLeft = true;
            isRight = false;
        } else {
            isRoot = false;
            isLeft = false;
            isRight = true;
        }

        // Find out if the node to be deleted has children
        // If it does, find out if it has a Right/Left Child or both
        if (temp.getLeftChild() == null && temp.getRightChild() == null){
            hasChildren = false;
            hasBoth = false;
            hasLeft = false;
            hasRight = false;
        } else if (temp.getLeftChild() != null && temp.getRightChild() != null){
            hasChildren = true;
            hasBoth = true;
            hasLeft = true;
            hasRight = true;
        } else if (temp.getLeftChild() == null){
            hasChildren = true;
            hasBoth = false;
            hasRight = true;
            hasLeft = false;
        } else {
            hasChildren = true;
            hasBoth = false;
            hasRight = false;
            hasLeft = true;
        }

        // Seperate cases if the node to be deleted is the root of the tree
        if (isRoot){
            // Seperate cases depending on the number of children the node to be deleted has
            if (!hasChildren){
                root = replacement;
            } else if (hasBoth){
                replacement = findMin(temp.getRightChild());
                if (replacement == temp.getRightChild()){
                    replacement.setLeftChild(temp.getLeftChild());
                    replacement.setParent(temp.getParent());
                    replacement.getLeftChild().setParent(replacement);
                    root = replacement;
                } else {
                    if (replacement.getParent().getLeftChild() == replacement){
                        replacement.getParent().setLeftChild(null);
                    } else if (replacement.getParent().getRightChild() == replacement){
                        replacement.getParent().setRightChild(null);
                    }
                    replacement.setParent(temp.getParent());
                    replacement.setLeftChild(temp.getLeftChild());
                    replacement.setRightChild(temp.getRightChild());
                    temp.getLeftChild().setParent(replacement);
                    temp.getRightChild().setParent(replacement);
                    root = replacement;
                }

            } else if (hasLeft && !hasRight){
                temp.getLeftChild().setParent(null);
                root = temp.getLeftChild();
            } else if (hasRight && !hasLeft) {
                temp.getRightChild().setParent(null);
                root = temp.getRightChild();
            }
        } else {
            // This is the case where it isn't the root node
            if (!hasChildren){
                if (isLeft){
                    temp.getParent().setLeftChild(null);
                } else if (isRight){
                    temp.getParent().setRightChild(null);
                }
            } else if (hasBoth){
                replacement = findMin(temp.getRightChild());
                if (replacement == temp.getRightChild()){
                    replacement.setLeftChild(temp.getLeftChild());
                    replacement.setParent(temp.getParent());
                    replacement.getLeftChild().setParent(replacement);
                } else {
                    if (replacement.getParent().getLeftChild() == replacement){
                        replacement.getParent().setLeftChild(null);
                    } else if (replacement.getParent().getRightChild() == replacement){
                        replacement.getParent().setRightChild(null);
                    }
                    replacement.setParent(temp.getParent());
                    replacement.setLeftChild(temp.getLeftChild());
                    replacement.setRightChild(temp.getRightChild());
                    temp.getLeftChild().setParent(replacement);
                    temp.getRightChild().setParent(replacement);
                }
                if(isLeft){
                    temp.getParent().setLeftChild(replacement);
                } else if (isRight){
                    temp.getParent().setRightChild(replacement);
                }

            } else if (hasLeft && !hasRight){
                temp.getLeftChild().setParent(temp.getParent());
                temp.getParent().setLeftChild(temp.getLeftChild());
            } else if (hasRight && !hasLeft) {
                temp.getRightChild().setParent(temp.getParent());
                temp.getParent().setRightChild(temp.getRightChild());
            }
        }
        
        // Update the heights of all ancestors of the deleted node    
        updateHeight(parent);
        // Set temp to be the unbalanced node
        while (temp.getParent() != null && temp.isUnbalanced() == false) {
            temp = temp.getParent();
        }

        // Perform appropriate rotation
        while (temp.isUnbalanced()) {
            // Right right
            if (balance(temp) > 1 && balance(temp.getLeftChild()) >= 0) {
                rightRotate(temp);
            }
            // Left right
            else if (balance(temp) > 1 && balance(temp.getLeftChild()) < 0) {
                leftRotate(temp.getLeftChild());
                rightRotate(temp);
            }
            // Left left
            else if (balance(temp) < -1 && balance(temp.getRightChild()) <= 0) {
                leftRotate(temp);
            }
            // Right left
            else if (balance(temp) < -1 && balance(temp.getRightChild()) > 0) {
                rightRotate(temp.getRightChild());
                leftRotate(temp);
            }

        }
    }

    public void traverse(String order, Node<E> top) {
        if (top != null){
            switch (order) {
                case "preorder":
                    preorder(top);
                    System.out.println();
                    break;
                case "inorder":
                    inorder(top);
                    System.out.println();
                    break;
                case "postorder":
                    postorder(top);
                    System.out.println();
                    break;
            }
        }
    }

    private void inorder(Node<E> node) {
        if (node == null) {
            return;
        }
        else {
            inorder(node.getLeftChild());
            System.out.print(node.getData() + " ");
            inorder(node.getRightChild());
        }
    }

    private void preorder(Node<E> node) {
        if (node == null) {
            return;
        }
        else {
            System.out.print(node.getData() + " ");
            preorder(node.getLeftChild());
            preorder(node.getRightChild());
        }
    }

    private void postorder(Node<E> node) {
        if (node == null) {
            return;
        }
        else {
            postorder(node.getLeftChild());
            postorder(node.getRightChild());
            System.out.print(node.getData() + " ");
        }
    }

    public void rightRotate(Node<E> y){

        Node<E> x = y.getLeftChild();

        // If x has a right child, handle the case where x's right child becomes y's left child
        if (x.getRightChild() != null) {
            Node<E> t2 = x.getRightChild();
            y.setLeftChild(t2);
            t2.setParent(y);

        }
        // Case where y is the root, must perform rotation and update root
        if (y.getParent() == null) {
            y.setLeftChild(x.getRightChild());
            x.setRightChild(y);
            x.setParent(y.getParent());
            
            y.setParent(x);
            root = x;
        }
        // Unbalanced node is not the root, perform the rotation
        else if (y.getParent() != null) {
            y.setLeftChild(x.getRightChild());
            x.setRightChild(y);
            x.setParent(y.getParent());
            if (y == y.getParent().getRightChild()) {
                y.getParent().setRightChild(x);
            }
            else {
                y.getParent().setLeftChild(x);
            }
            y.setParent(x);
        }
        updateHeight(x);
        updateHeight(y); 
    }

    public void leftRotate(Node<E> x){
    
        Node<E> y = x.getRightChild();

        // If y has a left child, handle the case where y's left child becomes x's right child
        if (y.getLeftChild() != null) {
            Node<E> t2 = y.getLeftChild();
            x.setRightChild(t2);
            t2.setParent(x);

        }

        // Unbalanced node is the root
        if (x.getParent() == null) {
            x.setRightChild(y.getLeftChild());
            y.setLeftChild(x);
            y.setParent(x.getParent());
            
            x.setParent(y);
            root = y;
        }

        // Unbalanced node is not the root
        else if (x.getParent() != null) {
            x.setRightChild(y.getLeftChild());
            y.setLeftChild(x);
            y.setParent(x.getParent());
            if (x == x.getParent().getLeftChild()) {
                x.getParent().setLeftChild(y);
            }
            else {
                x.getParent().setRightChild(y);
            }
            x.setParent(y);
        }

        updateHeight(x);
        updateHeight(y);
        
    }
    
    private int balance(Node<E> node) {
        if (node == null) {
            return 0;
        }
        else if (node.getLeftChild() == null && node.getRightChild() == null) {
            return 0;
        }

        else if (node.getLeftChild() != null && node.getRightChild() == null) {
            return node.getLeftChild().getHeight() - 0;
        }
        else if (node.getLeftChild() == null && node.getRightChild() != null) {
            return 0 - node.getRightChild().getHeight();
        }
        else {
            return node.getLeftChild().getHeight() - node.getRightChild().getHeight();
        }
    }

    private int getHeight(Node<E> node) {
        if (node == null) {
            return 0;
        }

        return node.getHeight();
    }


}
