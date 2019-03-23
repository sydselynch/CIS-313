public class TwoStackQueue<E> {
	private Stack<E> stackA;
	private Stack<E> stackB;

	public TwoStackQueue() {
		stackA = new Stack();
		stackB = new Stack();
	}

	public void enqueue(E newData){
		stackA.push(newData);
	}

	public Node<E> dequeue(){
		if (stackA.isEmpty() && stackB.isEmpty()){
			return null;
		}
		if (stackB.isEmpty()) {				// When B is empty, we pop everything from A into B to get correct order
			while (!stackA.isEmpty()) {
				stackB.push(stackA.pop().getData());
			}
		}
		return stackB.pop();
	}

	public boolean isEmpty(){
		return (stackA.isEmpty() && stackB.isEmpty());
	}

	public void printQueue(){
		if (stackB.isEmpty()) {
			while (!stackA.isEmpty()) {
				stackB.push(stackA.pop().getData());
			}
		}

		stackB.printStack();
		while (!stackB.isEmpty()) {						// Put everything back into stackA again for additonal inputs
				stackA.push(stackB.pop().getData());
		}
	}
}