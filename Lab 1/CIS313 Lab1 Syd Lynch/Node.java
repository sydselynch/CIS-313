
public class Node<E> {
	private E data;
	private Node<E> next;
	
	public Node(E c, Node<E> n){
		data = c;
		next = n;
	}
	
	public void setData(E d){
		data = d;
	}
	
	public void setNext(Node<E> n){
		next = n;
	}
	public E getData(){
		return data;
	}
	
	public Node<E> getNext(){
		return next;
	}
	
}
