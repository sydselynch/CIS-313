public class pQueue<E extends Comparable> {
    private MinHeap myHeap;

    public pQueue (int s) {
        // Build the Constructor
        myHeap = new MinHeap(s);
    }

    public void insert(E data){
        myHeap.insert(data);
    }

    public Comparable<E> findMin(){
        return myHeap.findMin();
    }

    public Comparable<E> removeMin(){
        return myHeap.extract();
    }

    public boolean isEmpty(){
        // Return true when the priority queue is empty
        // Hint: Do the actual printing in your HW3.java
        if (myHeap.getLength() == 0) {
            return true;
        }

        return false;
    }

    public void print(){ 
        // followed by each element separated by a comma. 
        // Do not add spaces between your elements.
        System.out.print("Current Queue: ");
        Comparable[] heapArray = myHeap.getArray();
        for (int i = 1; i < myHeap.getLength() + 1; i++) {
            System.out.print(heapArray[i]);
            if (heapArray[i+1] != null) {
                System.out.print(",");
            }
        }
    }
}
