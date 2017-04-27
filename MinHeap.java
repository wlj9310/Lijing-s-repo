public class MinHeap {
	private int[] array;
	private int size;
	public MinHeap(int[] array) {
		if (array == null || array.length == 0) {
			throw new IllegalArgumentException("input array can not be null or empty");
		}
		this.array = array;
		size = array.length;
		heapify();
	}
	private void heapify() {
		for (int i = size / 2 - 1; i >= 0; i--) {
			percolateDown(i);
		}

	}
	public MinHeap (int cap) {
		if (cap <= 0) {
			throw new IllegalArgumentException("capacity need to be greater than zero");
		}
		array = new int[cap];
		size = 0; 
	}
	public int size () {
		return size;
	}
	public boolean isEmpty() {
		return size == 0;
	}
	public boolean isFull() {
		return size == array.length;
	}
	private void percolateUp(int index) {
		while (index <= size / 2 - 1 && index > 0) {
			int parent = (index - 1) / 2;
			if (array[index] < array[parent]) {
				int temp = array[index];
				array[index] = array[parent];
				array[parent] = temp;
			} else {
				break;
			}
			index = parent;
		}

	}
	private void percolateDown(int index) {
		while (index <= size / 2 - 1) {
			int lChild = 2 * index + 1;
			int rChild = 2 * index + 2;
			int candidate = lChild;
			if (rChild <= size - 1 && array[rChild] < array[lChild]) {
				candidate = rChild;
			}
			if (array[index] > array[candidate]) {
				int temp = array[index];
				array[index] = array[candidate];
				array[candidate] = temp;
			} else {
				break;
			}
			index = candidate;
		}

	}
	public int peek() {
		return size == 0 ? -1 : array[0];
	}
	public int poll() {
		if (size == 0) {
			throw new NoSuchElementException("heap is empty");
		}
		int element = array[0];
		array[0] = array[size - 1];
		size--;
		percolateDown(0);
		return element;
	}
	public void offer(int ele) {
		if (size == array.length) {
			array = Arrays.copyOf(array, array.length * 1.5 + 1);
		}
		array[size] = ele;
		size++;
		percolateUp(size - 1);
	}
	public void update(int index, int ele) {
		if (index < 0 || index > size - 1) {
			throw new ArrayIndexOutOfBoundException("invalid index range");
		}
		int old = array[index];
		array[index] = ele;
		if (old < ele) {
			percolateDown(index);
		} else {
			percolateUp(index);
		}
	}
}