package ch.fhnw.algd2.collections.list.iterator;

import ch.fhnw.algd2.collections.list.MyAbstractList;
import ch.fhnw.algd2.collections.list.linkedlist.SinglyLinkedList;

public class C_SLL_Iterator_RemoveElement extends Abstract_C_ListIterator_RemoveElement {
	@Override
	protected <T> MyAbstractList<T> createInstance() {
		return new SinglyLinkedList<T>();
	}
}
