import java.lang.Math;

public class MinHeap<E extends Comparable> {
    private E[] myArray;
    private int maxSize;
    private int length;

    
    public MinHeap(int s){
    	// Build the constructor
        myArray = (E[]) new Comparable[s+1];
        maxSize = s;
        length = 0;
    }

    public E[] getArray(){
        return myArray;
    }

    public void setArray(E[] newArray){
        myArray = newArray;
    }

    public int getMaxSize(){
        return maxSize;
    }

    public void setMaxSize(int ms){
        maxSize = ms;
    }

    public int getLength(){
        return length;
    }

    public void setLength(int l){
        length = l;
    }

    // Other Methods
    public void insert(E data){
    
    	// Insert an element into your heap.
    	
    	// When adding a node to your heap, remember that for every node n, 
    	// the value in n is less than or equal to the values of its children, 
    	// but your heap must also maintain the correct shape.
		// (ie there is at most one node with one child, and that child is the left child.)
		// (Additionally, that child is farthest left possible.)
        int index;

        if (length == 0) {
            myArray[1] = data;
            length++;
        }
        else {
            myArray[length + 1] = data;
            index = length + 1;
            length++;
            bubbleup(index);
        }
    }

    private void bubbleup(int index) {
        if (index > 1) {
            int parentIndex = (int) Math.floor(index / 2);
            if (myArray[parentIndex].compareTo(myArray[index]) <= 0) {
                return;
            }
            else {
                E tempData = myArray[parentIndex];
                myArray[parentIndex] = myArray[index];
                myArray[index] = tempData;
                bubbleup(parentIndex);
            }
        }
    }

    public Comparable<E> findMin(){
        // return the minimum value in the heap
        return myArray[1];
    }

    public Comparable<E> extract(){
        // remove and return the minimum value in the heap
        Comparable<E> min = findMin();
        if (myArray[1] != null) {
            myArray[1] = myArray[length];
            myArray[length] = null;
            length--;
            bubbledown(1);
        }
        return min;


    }

    private void bubbledown(int index) {
        
        int leftChildIndex = index * 2;
        int rightChildIndex = index * 2 + 1;
        // Case 1: No children
        if (myArray[leftChildIndex] == null && myArray[rightChildIndex] == null) {
            return;             // No children so we're done
        }

        // Case 2: One child
        // 2.1 Left child exists, right is null
        if (myArray[leftChildIndex] != null && myArray[rightChildIndex] == null) {
            if(myArray[index].compareTo(myArray[leftChildIndex]) > 0) {
                E temp = myArray[index];
                myArray[index] = myArray[leftChildIndex];
                myArray[leftChildIndex] = temp;
                bubbledown(leftChildIndex);
            }
        }

        // 2.2 Right child exists, left is null
        if (myArray[rightChildIndex] != null && myArray[leftChildIndex] == null) {
            if(myArray[index].compareTo(myArray[rightChildIndex]) > 0) {
                E temp = myArray[index];
                myArray[index] = myArray[rightChildIndex];
                myArray[rightChildIndex] = temp;
                bubbledown(rightChildIndex);
            }
        }

        // Case 3: Two children, find the smallest then restore heap
        if (myArray[leftChildIndex] != null && myArray[rightChildIndex] != null) {
            if (myArray[index].compareTo(myArray[leftChildIndex]) <= 0 &&
                myArray[index].compareTo(myArray[rightChildIndex]) <= 0) {
                return;             // Two children but heap order property satisfied
            }
            else {
                if (myArray[leftChildIndex].compareTo(myArray[rightChildIndex]) <= 0) {
                    // left is smaller
                    E temp = myArray[index];
                    myArray[index] = myArray[leftChildIndex];
                    myArray[leftChildIndex] = temp;
                    bubbledown(leftChildIndex);
                }
                else {
                    // right is smaller
                    E temp = myArray[index];
                    myArray[index] = myArray[rightChildIndex];
                    myArray[rightChildIndex] = temp;
                    bubbledown(rightChildIndex);
                }
            }
        }
    }


    // Extra Credit Methods

    private void bottomUp(int index) {
            int leftChildIndex = index * 2;
            int rightChildIndex = index * 2 + 1;
            int smallerIndex;

        // Find the smallest node
        if (leftChildIndex < length && myArray[leftChildIndex].compareTo(myArray[index]) < 0) {
            smallerIndex = leftChildIndex;
        }
        else {
            smallerIndex = index;
        }

        if (rightChildIndex < length && myArray[rightChildIndex].compareTo(myArray[smallerIndex]) < 0) {
            smallerIndex = rightChildIndex;
        }
        
        // If the smallest node is not what we passed in, swap it with the smallest child and check
        // heap order property again
        if (smallerIndex != index) {
            E temp = myArray[index];
            myArray[index] = myArray[smallerIndex];
            myArray[smallerIndex] = temp;
            bottomUp(smallerIndex);
        }

    }

    public void heapify() {
        // Method for extra credit
        length = myArray.length - 1;
        for (int i = 1; i < myArray.length; i++) {
            bottomUp(i);
        }

    } 
}
