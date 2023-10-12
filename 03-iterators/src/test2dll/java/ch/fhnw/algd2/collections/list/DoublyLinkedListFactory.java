package ch.fhnw.algd2.collections.list;

import ch.fhnw.algd2.collections.list.linkedlist.DoublyLinkedList;

public final class DoublyLinkedListFactory {
	private DoublyLinkedListFactory() {}

	public static final <T> MyAbstractList<T> createInstance() {
		return createDoublyLinkedList();
	}

	private static <T> MyAbstractList<T> createDoublyLinkedList() {
		return new DoublyLinkedList<T>();
	}
}
