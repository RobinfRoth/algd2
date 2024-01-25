package ch.fhnw.algd2.heaptest;

import com.sun.source.tree.BreakTree;

import java.util.Arrays;

/* Heap implementing a Priority Queue */
class Heap<K> implements PriorityQueue<K> {
	private HeapNode<K>[] heap; // Array to store the heap elements
	private int size; // Number of elements currently stored in heap

	/**
	 * Construct the binary heap.
	 * 
	 * @param capacity
	 *          how many items the heap can store
	 */
	@SuppressWarnings("unchecked")
	Heap(int capacity) {
		heap = new HeapNode[capacity];
		size = 0;
		// HeapNode without type parameter
	}

	/**
	 * Returns the number of elements in this priority queue.
	 * 
	 * @return the number of elements in this priority queue.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Check whether the heap is empty.
	 * 
	 * @return true if there are no items in the heap.
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Check whether the heap is full.
	 * 
	 * @return true if no further elements can be inserted into the heap.
	 */
	@Override
	public boolean isFull() {
		return size == heap.length;
	}

	/**
	 * Make the heap (logically) empty.
	 */
	@Override
	public void clear() {
		for (int i = 0; i < size; i++) {
			heap[i] = null;
		}
		size = 0;
	}

	/**
	 * Add to the priority queue, maintaining heap order. Duplicates and null
	 * values are allowed. Small values of the argument priority means high
	 * priority, Large values means low priority.
	 * 
	 * @param element
	 *          the item to insert
	 * @param priority
	 *          the priority to be assigned to the item element
	 * @exception QueueFullException
	 *              if the heap is full
	 */
	@Override
	public void add(K element, long priority) throws QueueFullException {
		if (isFull()) throw new QueueFullException();
		heap[size] = new HeapNode<>(element, priority);
		siftUp(size);
		size++;
	}

	/**
	 * Removes and returns the item with highest priority (smallest priority
	 * value) from the queue, maintaining heap order.
	 * 
	 * @return the item with highest priority (smallest priority value)
	 * @throws QueueEmptyException
	 *           if the queue is empty
	 */
	@Override
	public K removeMin() throws QueueEmptyException {
		if (isEmpty()) throw new QueueEmptyException();
		K result = heap[0].element;
		heap[0] = heap[size - 1];
		heap[size - 1] = null;
		size--;
		siftDown(0);
		return result;
	}

	/**
	 * Internal method to let an element sift up in the heap.
	 * 
	 * @param start
	 *          the index at which the percolate begins
	 */
	private void siftUp(int start) {
		int parentIndex = (start - 1) / 2;
		while (parentIndex >= 0 && heap[start].priority < heap[parentIndex].priority) {
			HeapNode<K> tmpNode = heap[start];
			heap[start] = heap[parentIndex];
			heap[parentIndex] = tmpNode;
			start = parentIndex;
			parentIndex = (parentIndex - 1) / 2;
		}
	}

	/**
	 * Internal method to let an element sift down in the heap.
	 * 
	 * @param start
	 *          the index at which the percolate begins
	 */
	private void siftDown(int start) {
		int leftChildIndex = 2 * start + 1;
		int rightChildIndex = 2 * start + 2;
		while ((heap[leftChildIndex] != null || heap[rightChildIndex] != null) &&
				(heap[start].priority > heap[leftChildIndex].priority ||
						(heap[rightChildIndex] != null && heap[start].priority > heap[rightChildIndex].priority))) {
			HeapNode<K> tmpNode = heap[start];

			int childIndex;
			if (heap[leftChildIndex] == null) {
				childIndex = rightChildIndex;
			} else if (heap[rightChildIndex] == null) {
				childIndex = leftChildIndex;
			} else if (heap[leftChildIndex].priority <= heap[rightChildIndex].priority) {
				childIndex = leftChildIndex;
			} else if (heap[leftChildIndex].priority > heap[rightChildIndex].priority) {
				childIndex = rightChildIndex;
			} else return;

			heap[start] = heap[childIndex];
			heap[childIndex] = tmpNode;
			start = childIndex;
			leftChildIndex = 2 * childIndex + 1;
			rightChildIndex = 2 * childIndex + 2;
		}
	}

	/**
	 * Decreases the priority of the node on a given index.
	 * @param index	index of the element to change.
	 * @param negChange Value the priority of the element should be decreased by.
	 */
	public void decreasePriority(int index, long negChange) {
		if (index < 0 || index > size) throw new IndexOutOfBoundsException();
		long newPrio = heap[index].priority - negChange;
		heap[index] = new HeapNode<>(heap[index].element, newPrio);
		if (negChange >= 0) {
			siftUp(index);
		} else {
			siftDown(index);
		}
	}

	/**
	 * Remove the element at a given index.
	 * @param index index of the element to delete.
	 */
	public void delete(int index) {
		if (index >= size || index < 0) throw new IndexOutOfBoundsException();
		heap[index] = heap[size-1];
		heap[size -1] = null;
		size--;
		siftDown(index);
		siftUp(index);
	}


	/**
	 * Allocates a long[] Array and copies the priority values from the heap
	 * array. The length of the returned array shall be equal to the number of
	 * elements in the heap (i.e. size()). The smallest element (root) shall be
	 * placed at index position 0.
	 * 
	 * @return Array with all priority values
	 */
	@Override
	public long[] toLongArray() {
		long[] arr = new long[size];
		for (int i=0; i<size; i++) {
			arr[i] = heap[i].priority;
		}

		return arr;
	}

	private static class HeapNode<K> {
		private final K element;
		private final long priority;

		HeapNode(K element, long priority) {
			this.element = element;
			this.priority = priority;
		}
	}
}
