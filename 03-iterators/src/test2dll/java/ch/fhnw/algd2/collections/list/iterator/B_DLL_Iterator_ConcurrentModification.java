package ch.fhnw.algd2.collections.list.iterator;

import ch.fhnw.algd2.collections.list.DoublyLinkedListFactory;
import ch.fhnw.algd2.collections.list.MyAbstractList;

public class B_DLL_Iterator_ConcurrentModification extends Abstract_B_ListIterator_ConcurrentModification {
	@Override
	protected <T> MyAbstractList<T> createInstance() {
		return DoublyLinkedListFactory.createInstance();
	}
}
