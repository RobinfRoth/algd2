package ch.fhnw.algd2.collections.list.iterator;

import static org.junit.Assert.assertTrue;

import org.junit.Before;

import ch.fhnw.algd2.collections.list.MyAbstractList;

public abstract class AbstractListIteratorTest {
	protected MyAbstractList<Integer> list;

	@Before
	public void init() {
		list = createInstance();
	}

	protected abstract <T> MyAbstractList<T> createInstance();

	protected void addNumbersFromOneToFiveToList() {
		Integer[] ints = new Integer[] { 1, 2, 3, 4, 5 };
		for (Integer i : ints) {
			assertTrue(list.add(i));
		}
	}
}
