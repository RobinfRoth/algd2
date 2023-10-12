package ch.fhnw.algd2.collections.list.iterator;

import ch.fhnw.algd2.collections.list.DoublyLinkedListFactory;
import ch.fhnw.algd2.collections.list.MyAbstractList;

/**
 * Checks simple iteration over empty and integer list.
 * 
 * @author Michael
 */
public class A_DLL_Iterator_SimpleIteration extends Abstract_A_ListIterator_SimpleIteration {
	@Override
	protected <T> MyAbstractList<T> createInstance() {
		return DoublyLinkedListFactory.createInstance();
	}
}
