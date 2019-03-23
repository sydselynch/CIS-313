
public class BST<E extends Comparable<E>> {
    private Node<E> root;

    public BST(){
        root = null;
    }

    public Node<E> getRoot(){
        return root;

    }

    public void insert(E data){

        // Find the right spot in the tree for the new node
        // Make sure to check if anything is in the tree
        // Hint: if a node n is null, calling n.getData() will cause an error
        root = insertRecursively(data, root);

    }

    private Node<E> insertRecursively(E data, Node<E> node) {
        if (node == null) {
            node = new Node(data);
        }
        else {
            if (data.compareTo(node.getData()) < 0) {
                Node<E> insert = insertRecursively(data, node.getLeftChild());
                node.setLeftChild(insert);
                insert.setParent(node);
            }
            else if (data.compareTo(node.getData()) > 0) {
                Node<E> insert = insertRecursively(data, node.getRightChild());
                node.setRightChild(insert);
                insert.setParent(node);
            }
        }
        return node;
    }

    public Node<E> find(E data){

        // Search the tree for a node whose data field is equal to data
        //return search(root, data);
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
      
    public void delete(E data){
        // Find the right node to be deleted
        Node<E> currentNode = find(data);       // Call our find method on the data
        if (currentNode == null) {
            return;                             // Do nothing if data not in tree
        }
        // If said node has no children, simply remove it by setting its parent to point to null instead of it.
        if (currentNode.getLeftChild() == null && currentNode.getRightChild() == null) {
            if (currentNode == root) {         // Case where only node in tree is the root
                root = null;
            }
            else if (currentNode.getParent().getLeftChild() == currentNode) {
                currentNode.getParent().setLeftChild(null);
            }
            else {
                currentNode.getParent().setRightChild(null);
            }
        }
        // If said node has one child, delete it by making its parent point to its child.
        else if (currentNode.getLeftChild() != null && currentNode.getRightChild() == null) {
            if (currentNode == root) {                                              // Case where deleting root with one child, root will not have a parent node
                root = currentNode.getLeftChild();
            }
            else if (currentNode == currentNode.getParent().getLeftChild()) {       // Case where current is the left child of parent, otherwise set right
                currentNode.getParent().setLeftChild(currentNode.getLeftChild());
                currentNode.getLeftChild().setParent(currentNode.getParent());
            }
            else {
                currentNode.getParent().setRightChild(currentNode.getLeftChild());
                currentNode.getLeftChild().setParent(currentNode.getParent());
            }
        }
        else if (currentNode.getLeftChild() == null && currentNode.getRightChild() != null) {
            if (currentNode == root) {                                              // Case where deleting root with one child, root will not have a parent node
                root = currentNode.getRightChild();
            }
            else if (currentNode == currentNode.getParent().getLeftChild()) {       // Case where current is the left child of parent, otherwise set right
                currentNode.getParent().setLeftChild(currentNode.getRightChild());
                currentNode.getRightChild().setParent(currentNode.getParent());
            }
            else {
                currentNode.getParent().setRightChild(currentNode.getRightChild());
                currentNode.getRightChild().setParent(currentNode.getParent());
            }
        }
        // If said node has two children, then replace it with its successor,
        //          and remove the successor from its previous location in the tree.
        // The successor of a node is the left-most node in the node's right subtree.
        // If the value specified by delete does not exist in the tree, then the structure is left unchanged.

        // Hint: You may want to implement a findMin() method to find the successor when there are two children
        else if (currentNode.getLeftChild() != null && currentNode.getRightChild() != null) {
            Node<E> min = findMin(currentNode.getRightChild());
            Node<E> minParent = min.getParent();
            if (min.getRightChild() != null) {
                minParent.setLeftChild(min.getRightChild());
                min.getRightChild().setParent(minParent);
                currentNode.setData(min.getData());
            }
            else if (minParent == currentNode) {
                currentNode.setData(min.getData());
                minParent.setRightChild(null);
            }

            else {
                currentNode.setData(min.getData());
                minParent.setLeftChild(null);
            }
        }
    }

    private Node<E> findMin(Node<E> node) {
        Node<E> currentNode = node;
        while (currentNode.getLeftChild() != null) {
            currentNode = currentNode.getLeftChild();
        }
        return currentNode;
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
}
