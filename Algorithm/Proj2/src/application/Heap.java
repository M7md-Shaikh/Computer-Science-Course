package application;

public class Heap {

	private int currentSize;
	private int tableSize;
	private Node[] heapTable;

	// constructor by size of table
	public Heap(int tableSize) {
		this.tableSize = tableSize + 1;
		currentSize = 0;

		heapTable = new Node[this.tableSize];
	}

	public Node[] getHeapTable() {
		return heapTable;
	}

	public boolean add(Node newEntry) { // O(log n)

		if (tableSize - currentSize == 1) {
			System.out.println("list is full, can't add it");
			return false;
		}
		currentSize++;
		heapTable[currentSize] = new Node();
		heapTable[currentSize] = newEntry;
		swim(currentSize); // O(log n)
		return true;

	}

	public Node removeMin() { // O(log n)

		if (!isEmpty()) {
			Node min = heapTable[1];
			exchange(1, currentSize);
			currentSize--;
			sink(1); // O(log n)
			heapTable[currentSize + 1] = null;
			return min;
		}
		return null;
	}

	public Node getMin() { // o(c)
		if (!isEmpty())
			return heapTable[1];
		return null;
	}

	public boolean isEmpty() {
		return currentSize == 0;
	}

	public int getSize() {
		return currentSize;
	}

	public void clear() {

		// for (int i = 1; i <= currentSize; i++)
		// heapTable[i] = null;
		currentSize = 0;

	}

	public void swim(int k) { // promotion =(go up).... time: // O(log n)

		while (k > 1 && !firstArgIsLess(k / 2, k)) {

			exchange(k / 2, k);
			k = k / 2; // divide into 2s
		}
	}

	public void sink(int k) { // demotion = (go down),,,, time: // O(log n)

		while (2 * k <= currentSize) { // i still have a leaves nodes (entries)

			int j = 2 * k;

			if (j < currentSize && !firstArgIsLess(j, j + 1))
				j++; // take the right son of the parent 'k' "the parameter"
			if (firstArgIsLess(k, j))
				break;
			exchange(k, j);
			k = j;
		}

	}

	private boolean firstArgIsLess(int k1, int k2) {

		return heapTable[k1].compareTo(heapTable[k2]) < 0;
	}

	private void exchange(int k1, int k2) {
		Node temp = heapTable[k1];
		heapTable[k1] = heapTable[k2];
		heapTable[k2] = temp;

	}

	public String toString() {

		String result = "";
		for (int i = 1; i <= currentSize; i++)
			result += "{" + heapTable[i].toString() + "}; ";
		return result;
	}

}
