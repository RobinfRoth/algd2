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
				first.next = new Node<>(first, element, first.next);
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
		throw new UnsupportedOperationException("Implement later");
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

	private class MyListIterator implements Iterator<E> {

		Node<E> next = first;
		int iterModCount = modCount;
		boolean mayRemove = false;

		@Override
		public boolean hasNext() {
			return next.next != first && next.next != null;
		}

		@Override
		public E next() {
			next = next.next;
			if (next == null || next == first) throw new NoSuchElementException();
			if(iterModCount != modCount) throw new ConcurrentModificationException();
			E element = next.elem;
			mayRemove = true;
			return element;
		}

		@Override
		public void remove() {
			if (iterModCount != modCount) throw new ConcurrentModificationException();
			if (!mayRemove) throw new IllegalStateException();
			next.prev.next = null;
			// remove first
			if (next.prev == first.next) {
				next.prev = first;
				first.next = next;
			}
			// remove last, next is first
			else if (next.prev == last) {
				last = next.prev.prev;
				last.next = first;
				first.prev = last;
			}
			// remove element in the middle
			else next.prev.prev = next;
			size--;
			modCount++;
			iterModCount++;
			mayRemove = false;
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
