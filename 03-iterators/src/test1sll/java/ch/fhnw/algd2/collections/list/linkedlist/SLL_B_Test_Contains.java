package ch.fhnw.algd2.collections.list.linkedlist;

import ch.fhnw.algd2.collections.list.MyAbstractList;

public class SLL_B_Test_Contains extends Abstract_B_ListTest_Contains {
	@Override
	protected <T> MyAbstractList<T> getListInstance() {
		return new SinglyLinkedList<T>();
	}
}
