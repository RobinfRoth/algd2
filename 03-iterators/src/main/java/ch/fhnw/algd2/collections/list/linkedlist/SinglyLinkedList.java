package ch.fhnw.algd2.collections.list.linkedlist;

import java.util.*;

import ch.fhnw.algd2.collections.list.MyAbstractList;

public class SinglyLinkedList<E> extends MyAbstractList<E> {
	// variable int modCount already declared by AbstractList<E>
	private int size = 0;
	private Node<E> first;
	private Node<E> last;

	@Override
	public boolean add(E e) {

		Node<E> newNode = new Node<>(e);

		if (size == 0) {
			first = newNode;
		} else {
			last.next = newNode;
		}
		last = newNode;
		size++;
		modCount++;
		return true;
	}

	@Override
	public boolean contains(Object o) {
		// TODO: implement contains using while
		Node<E> currentNode = first;
		for (int i=0; i<size; i++) {
			if (Objects.equals(o, currentNode.elem)) {
				return true;
			}
			currentNode = currentNode.next;
		}
		return false;
	}

	@Override
	public boolean remove(Object o) {
		Node<E> current = first;
		Node<E> prev = first;
		while(current != null && !Objects.equals(o, current.elem)) {
			prev = current;
			current = current.next;
		}

		// o not contained
		if (current == null) return false;

		// o is first
		if (first == current) {
			first = current.next;

			// o is the only element of the list
			if (size == 1) {
				first = null;
				last = null;
				current.next = null;
			}
			size--;
			modCount++;
			return true;
		}

		// o is last
		if (last == current) {
			prev.next = null;
			last = prev;
			size--;
			modCount++;
			return true;
		}

		// o is an element in the middle
		prev.next = current.next;
		current.next = null;
		size--;
		modCount++;
		return true;
	}

	@Override
	public E get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for length " + size + ".");
		}

		Node<E> currentNode = first;
		int currentIndex = 0;
		while (currentIndex < index) {
			currentIndex++;
			currentNode = currentNode.next;
		}
		return currentNode.elem;
	}

	@Override
	public void add(int index, E element) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for length " + size + ".");
		}
		// first element
		Node<E> currentNode = first;
		if (index == 0) {
			first = new Node<>(element, currentNode);
			if (size == 0) last = first;
			size++;
			modCount++;
			return;
		}

		// last element
		if (index == size) {
			Node<E> newNode = new Node<>(element, null);
			last.next = newNode;
			last = newNode;
			size++;
			modCount++;
			return;
		}


		// middle element
		Node<E> previousNode = null;
		for (int i = 0; i < index; i++) {
			previousNode = currentNode;
			currentNode = currentNode.next;
		}
		previousNode.next = new Node<>(element, currentNode);
		size++;
		modCount++;
	}

	@Override
	public E remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for length " + size + ".");
		}
		// first element
		if (index == 0) {
			E removedElement = first.elem;
			first = first.next;
			size--;
			modCount++;
			return removedElement;
		}

		Node<E> currentNode = first;
		Node<E> previousNode = null;
		for (int i = 0; i < index; i++) {
			previousNode = currentNode;
			currentNode = currentNode.next;
		}
		previousNode.next = currentNode.next;
		if (index == size - 1) last = previousNode;
		size--;
		modCount++;
		return currentNode.elem;
	}

	@Override
	public Object[] toArray() {
		Object[] array = new Object[size];
		int index = 0;
		Node<E> current = first;
		while (current != null) {
			array[index++] = current.elem;
			current = current.next;
		}
		return array;
	}

	@Override
	public int size() {
		return size;
	}

	private static class Node<E> {
		private final E elem;
		private Node<E> next = null;

		private Node(E elem) {
			this.elem = elem;
		}

		private Node(E elem, Node<E> next) {
			this.elem = elem;
			this.next = next;
		}
	}

	@Override
	public Iterator<E> iterator() {
		return new MyIterator();
	}

	private class MyIterator implements Iterator<E> {
		Node<E> next = first;
		Node<E> prev = null;
		Node<E> oldPrev = null;
		int iterModCount = modCount;
		boolean mayRemove = false;

		@Override
		public boolean hasNext() {
			return next != null;
		}

		@Override
		public E next() {
			if (next == null) throw new NoSuchElementException();
			if(iterModCount != modCount) throw new ConcurrentModificationException();
			E element = next.elem;
			oldPrev = prev;
			prev = next;
			next = next.next;
			mayRemove = true;
			return element;
		}

		@Override
		public void remove() {
			if (iterModCount != modCount) throw new ConcurrentModificationException();
			if (!mayRemove) throw new IllegalStateException();
			prev.next = null;
			// only one element
			if (prev == first) first = next;
			// prev is last, next is null
			else if (prev == last) last = oldPrev;
			// more than two elements
			else if (oldPrev != null) oldPrev.next = next;
			prev = oldPrev;
			size--;
			modCount++;
			iterModCount++;
			mayRemove = false;
		}
	}

	public static void main(String[] args) {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		Iterator<Integer> it = list.iterator();
		it.next();
		it.next();
		it.remove();

		System.out.println(Arrays.toString(list.toArray()));
	}
}
