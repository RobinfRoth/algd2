package ch.fhnw.algd2.collections.list.linkedlist;

import java.util.*;

import ch.fhnw.algd2.collections.list.MyAbstractList;

public class DoublyLinkedList<E> extends MyAbstractList<E> {
	// variable int modCount already declared by AbstractList<E>
	private int size = 0;
	private Node<E> first = new Node<>(null), last;

	@Override
	public boolean add(E e) {
		Node<E> newNode = new Node<>(e);
		if (size == 0) {
			first.prev = newNode;
			first.next = newNode;
			newNode.prev = first;
			newNode.next = first;
		} else {
			first.prev = newNode;
			last.next = newNode;
			newNode.prev = last;
			newNode.next = first;
		}
		last = newNode;
		size++;
		modCount++;
		return true;
	}

	@Override
	public void add(int index, E element) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for length " + size + ".");
		}
		// first element
		if (index == 0) {
			if (size == 0) {
				first.next = new Node<>(first, element, first);
				first.prev = first.next;
				last = first.next;
			} else {
				Node<E> newNode = new Node<>(first, element, first.next);
				first.next.prev = newNode;
				first.next = newNode;
			}
			size++;
			modCount++;
			return;
		}

		// last element
		if (index == size) {
			Node<E> newNode = new Node<>(last, element, first);
			last.next = newNode;
			first.prev = last.next;
			last = newNode;
			size++;
			modCount++;
			return;
		}

		// middle element
		Node<E> currentNode = first.next;
		for (int i = 0; i < index; i++) {
			currentNode = currentNode.next;
		}
		Node<E> newNode = new Node<>(currentNode.prev, element, currentNode);
		currentNode.prev.next = newNode;
		currentNode.prev = newNode;
		size++;
		modCount++;
	}

	@Override
	public boolean remove(Object o) {
		Node<E> current = first.next;
		while(current != null && current != first && !Objects.equals(o, current.elem)) {
			current = current.next;
		}

		// o not contained
		if (current == null || current.elem == null) return false;

		// o is first
		if (first.next == current) {
			first.next = current.next;
			current.next.prev = first;
			current.prev = null;
			current.next = null;

			// o is the only element of the list
			if (size == 1) {
				first.prev = first;
				last = first;
			}
			size--;
			modCount++;
			return true;
		}

		// o is last
		if (last == current) {
			current.prev.next = first;
			first.prev = current.prev;
			last = current.prev;
			current.next = null;
			current.prev = null;
			size--;
			modCount++;
			return true;
		}

		// o is an element in the middle
		current.prev.next = current.next;
		current.next.prev = current.prev;
		current.next = null;
		current.prev = null;
		size--;
		modCount++;
		return true;
	}

	@Override
	public E remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for length " + size + ".");
		}
		// first element
		if (index == 0) {
			Node<E> removedNode = first.next;
			if (size == 1) {
				first.next = first;
				first.prev = first;
			}
			else {
				first.next = removedNode.next;
				removedNode.next.prev = first;
			}
			size--;
			modCount++;
			return removedNode.elem;
		}

		Node<E> currentNode = first.next;
		for (int i = 0; i < index; i++) {
			currentNode = currentNode.next;
		}
		currentNode.prev.next = currentNode.next;
		currentNode.next.prev = currentNode.prev;
		if (index == size - 1) last = currentNode.prev;
		size--;
		modCount++;
		return currentNode.elem;
	}

	@Override
	public boolean contains(Object o) {
		Node<E> currentNode = first.next;
		for (int i=0; i<size; i++) {
			if (Objects.equals(o, currentNode.elem)) {
				return true;
			}
			currentNode = currentNode.next;
		}
		return false;
	}

	@Override
	public E get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for length " + size + ".");
		}

		Node<E> currentNode = first.next;
		int currentIndex = 0;
		while (currentIndex < index) {
			currentIndex++;
			currentNode = currentNode.next;
		}
		return currentNode.elem;
	}

	@Override
	public Object[] toArray() {
		// return arrayForDoublyLinkedList();
		return arrayForCyclicDoublyLinkedList();
	}

	private Object[] arrayForDoublyLinkedList() {
		Object[] array = new Object[size];
		int index = 0;
		Node<E> current = first;
		while (current != null) {
			array[index++] = current.elem;
			current = current.next;
		}
		return array;
	}

	private Object[] arrayForCyclicDoublyLinkedList() {
		Object[] array = new Object[size];
		int index = 0;
		Node<E> current = first.next;
		while (current != first) {
			array[index++] = current.elem;
			current = current.next;
		}
		return array;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Iterator<E> iterator() {
		return new MyListIterator();
	}

	@Override
	public ListIterator<E> listIterator() {
		return new MyListIterator();
	}

	private static class Node<E> {
		private E elem;
		private Node<E> prev, next;

		private Node(E elem) {
			this.elem = elem;
		}

		private Node(Node<E> prev, E elem, Node<E> next) {
			this.prev = prev;
			this.elem = elem;
			this.next = next;
		}
	}

	private class MyListIterator implements ListIterator<E> {

		Node<E> next = first.next, returnedNode;
		int iterModCount = modCount;
		boolean mayChange = false, hasChanged = false;

		@Override
		public boolean hasNext() {
			return next != first && next != null;
		}

		@Override
		public E next() {
			if (next == null || next == first) throw new NoSuchElementException();
			if(iterModCount != modCount) throw new ConcurrentModificationException();
			returnedNode = next;
			next = next.next;
			mayChange = true;
			hasChanged = false;
			return returnedNode.elem;
		}

		@Override
		public boolean hasPrevious() {
			return next.prev != first && next.prev != null;
		}

		@Override
		public E previous() {
			if (next.prev == null || next.prev == first) throw new NoSuchElementException();
			if(iterModCount != modCount) throw new ConcurrentModificationException();
			returnedNode = next.prev;
			next = next.prev;
			mayChange = true;
			hasChanged = false;
			return returnedNode.elem;
		}

		@Override
		public int nextIndex() {
			Node<E> currentNode = first.next;
			int index = 0;
			if (currentNode == null) return 0;

			while (currentNode != next) {
				currentNode = currentNode.next;
				index++;
			}
			return index;
		}

		@Override
		public int previousIndex() {
			Node<E> currentNode = first.next;
			int index = 0;
			if (currentNode == null) return 0;

			while (currentNode != next.prev) {
				currentNode = currentNode.next;
				index++;
			}
			return index;
		}

		@Override
		public void remove() {
			if (iterModCount != modCount) throw new ConcurrentModificationException();
			if (!mayChange) throw new IllegalStateException();
			// remove first
			if (returnedNode == first.next) {
				returnedNode.next.prev = first;
				first.next = returnedNode.next;
			}
			// remove last, next is first
			else if (returnedNode.next == first) {
				last = returnedNode.prev;
				last.next = first;
				first.prev = last;
			}
			// remove element in the middle
			else {
				returnedNode.prev.next = returnedNode.next;
				returnedNode.next.prev = returnedNode.prev;
			}
			if (next == returnedNode) next = returnedNode.next;
			returnedNode = null;
			size--;
			modCount++;
			iterModCount++;
			mayChange = false;
			hasChanged = true;
		}

		@Override
		public void set(E e) {
			if (!mayChange || hasChanged) throw new IllegalStateException();
			returnedNode.elem = e;
		}

		@Override
		public void add(E e) {
			// add at the start
			if (next == first.next) {
				Node<E> newNode = new Node<>(first, e, next);
				first.next.prev = newNode;
				first.next = newNode;
			}

			// add at the end
			else if (next == first) {
				Node<E> newNode = new Node<>(last, e, first);
				last.next = newNode;
				first.prev = newNode;
				last = newNode;
			}
			// middle
			else {
				Node<E> newNode = new Node<>(next.prev, e, next);
				next.prev.next = newNode;
				next.prev = newNode;
			}

			size++;
			modCount++;
			iterModCount++;
			mayChange = false;
			hasChanged = true;
		}
	}

	public static void main(String[] args) {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		list.add(0, 1);
		list.add(1, 2);
		list.add(1,3);
		list.add(1,4);
		list.add(1,5);
		// System.out.println(list.remove(Integer.valueOf(3)));
		System.out.println(Arrays.toString(list.toArray()));
	}
}
