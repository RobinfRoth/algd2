package ch.fhnw.algd2.collections.list.linkedlist;

import ch.fhnw.algd2.collections.list.DoublyLinkedListFactory;
import ch.fhnw.algd2.collections.list.MyAbstractList;

public class DLL_C_Test_Remove extends Abstract_C_ListTest_Remove {
	@Override
	protected <T> MyAbstractList<T> getListInstance() {
		return DoublyLinkedListFactory.createInstance();
	}
}
