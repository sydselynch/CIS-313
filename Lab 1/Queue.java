
public class Queue<E> {
	private Node<E> head;
	private Node<E> tail;
	
	public Queue(){
		head = null;
		tail = null;
	}
	
	public void enqueue(E newData){
		if (head == null) {
			head = new Node(newData, null);
			tail = head;
		}
		else {
			Node<E> prevTail = tail;
			tail = new Node(newData, null);
			prevTail.setNext(tail);
		}
	}
	
	public Node<E> dequeue(){
		if(head == null) {
			return null;
		}
		
		Node<E> prevHead = head;
		head = prevHead.getNext();
		return prevHead;
	}
	
	public boolean isEmpty(){
		return (head == null);
	}
	
	public void printQueue(){
		Node<E> currentNode = head;
		while (currentNode.getNext() != null) {
			System.out.print(currentNode.getData());
			currentNode = currentNode.getNext();
		}
		
		System.out.print(currentNode.getData());
	}
}
