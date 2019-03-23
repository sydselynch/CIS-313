
public class Heapify {

	public static void main(String[] args) {
		Integer[] array = {null, 5, 4, 3, 2, 1};
		MinHeap<Integer> heap = new MinHeap(10);
		heap.setArray(array);
		Integer[] heapArray = heap.getArray();
		for (int i=1; i<heapArray.length; i++) {
			System.out.print(heapArray[i] + " ");
		}
		System.out.println();
		heap.heapify();
		for (int i=1; i<heapArray.length; i++) {
			System.out.print(heapArray[i] + " ");
		}
	}
}