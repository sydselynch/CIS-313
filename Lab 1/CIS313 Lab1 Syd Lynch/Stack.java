
public class Stack<E> {
	private Node<E> top;
	
	public Stack(){
	
		top = null;		
	}
	
	public void push(E newData){
		if (top == null) {
			top = new Node(newData, null);
		}
		else {
			Node<E> prevTop = top;
			top = new Node(newData, prevTop);
		}
	}
	
	public Node<E> pop(){
		if (top == null) {
			return null;
		}
		else {
			Node<E> currentTop = top;
			top = top.getNext();
			return currentTop;
		}
	}
	
	public boolean isEmpty(){
		return (top == null);
	}
	
	public void printStack(){
		Node<E> currentNode = top;
		while (currentNode.getNext() != null) {
			System.out.print(currentNode.getData());
			currentNode = currentNode.getNext();
		}

		System.out.print(currentNode.getData());
	}
}