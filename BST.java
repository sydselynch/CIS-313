
public class BST<E extends Comparable<E>> {
    private Node<E> root;

    public BST(){
        root = null;
    }

    public Node<E> getRoot(){
        //System.out.println(root.getData());
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
                //node.setLeftChild(insertRecursively(data, node.getLeftChild()));
                node.setLeftChild(insert);
                insert.setParent(node);
            }
            else if (data.compareTo(node.getData()) > 0) {
                Node<E> insert = insertRecursively(data, node.getRightChild());
                //node.setRightChild(insertRecursively(data, node.getRightChild()));
                node.setRightChild(insert);
                insert.setParent(node);
            }
        }
        return node;
    }

    public Node<E> find(E data){

        // Search the tree for a node whose data field is equal to data
        return treeSearch(data, root);
    }

    private Node<E> treeSearch(E data, Node<E> node) {
        if (node == null) {
            return null;
        }
        if (node.getLeftChild() == null && node.getRightChild() == null) {
            return null;
        }
        else {
            if (data == node.getData()) {
                return node;
            }
            else if (data.compareTo(node.getData()) < 0) {
                return treeSearch(data, node.getLeftChild());
            }
            else {
                return treeSearch(data, node.getRightChild());
            }
        }
    }

    public void delete(E data){
        // Find the right node to be deleted
        Node<E> currentNode = find(data);
        // If said node has no children, simply remove it by setting its parent to point to null instead of it.
        if (currentNode.getLeftChild() == null && currentNode.getRightChild() == null) {
            Node<E> parent = currentNode.getParent();
            if (parent.getLeftChild() == currentNode) {
                parent.setLeftChild(null);
            }
            else {
                parent.setRightChild(null);
            }
        }
        // If said node has one child, delete it by making its parent point to its child.
        if (currentNode.getLeftChild() != null && currentNode.getRightChild() == null) {
/*            currentNode.getParent().setRightChild(currentNode.getLeftChild());
            currentNode.getLeftChild().setParent(currentNode.getParent());*/
            if (currentNode == currentNode.getParent().getLeftChild()) {
                currentNode.getParent().setLeftChild(currentNode.getLeftChild());
                currentNode.getLeftChild().setParent(currentNode.getParent());
            }
            else {
                currentNode.getParent().setRightChild(currentNode.getLeftChild());
                currentNode.getLeftChild().setParent(currentNode.getParent());
            }
        }
        if (currentNode.getLeftChild() == null && currentNode.getRightChild() != null) {
/*            currentNode.getParent().setRightChild(currentNode.getRightChild());
            currentNode.getRightChild().setParent(currentNode.getParent());*/
            if (currentNode == currentNode.getParent().getLeftChild()) {
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
        if (currentNode.getLeftChild() != null && currentNode.getRightChild() != null) {
            Node<E> min = findMin(currentNode.getRightChild());
            Node<E> minParent = min.getParent();
/*            minParent.setLeftChild(min.getRightChild());
            min.getRightChild().setParent(minParent);
            currentNode.setData(min.getData());*/
            if (min.getRightChild() != null) {
                minParent.setLeftChild(min.getRightChild());
                min.getRightChild().setParent(minParent);
                currentNode.setData(min.getData());
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
            System.out.println(node.getData());
            inorder(node.getRightChild());
        }
    }

    private void preorder(Node<E> node) {
        if (node == null) {
            return;
        }
        else {
            System.out.println(node.getData());
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
            System.out.println(node.getData());
        }
    }

    public void traverse(String order, Node<E> top) {
        if (top != null){
            switch (order) {
                case "preorder":
                    // Your Code here
                    preorder(top);
                    break;
                case "inorder":
                    // Your Code here
                    inorder(top);
                    break;
                case "postorder":
                    // Your Code here
                    postorder(top);
                    break;
            }
        }
    }
}
