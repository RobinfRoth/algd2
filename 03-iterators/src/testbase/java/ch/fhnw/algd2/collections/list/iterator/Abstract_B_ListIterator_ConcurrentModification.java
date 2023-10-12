package ch.fhnw.algd2.collections.list.iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.stream.IntStream;

import org.junit.Test;

import ch.fhnw.algd2.collections.list.MyAbstractList;
import ch.fhnw.algd2.collections.list.linkedlist.Elem;

public abstract class Abstract_B_ListIterator_ConcurrentModification extends AbstractListIteratorTest {
	@Test(expected = ConcurrentModificationException.class)
	public void next_ElementAddedAfterIteratorCreation_ConcurrentModificationException() {
		addNumbersFromOneToFiveToList();
		Iterator<Integer> it = list.iterator();
		list.add(5);
		assertTrue(it.hasNext());
		it.next();
		fail("Receiving element should throw an ConcurrentModificationException");
	}

	@Test(expected = ConcurrentModificationException.class)
	public void next_ElementReomvedAfterIteratorCreation_ConcurrentModificationException() {
		addNumbersFromOneToFiveToList();
		Iterator<Integer> it = list.iterator();
		list.remove(Integer.valueOf(2));
		assertTrue(it.hasNext());
		it.next();
		fail("Receiving element should throw an ConcurrentModificationException");
	}

	@Test(expected = ConcurrentModificationException.class)
	public void next_ElementRemovedByIndexAfterIteratorCreation_ConcurrentModificationException() {
		addNumbersFromOneToFiveToList();
		Iterator<Integer> it = list.iterator();
		list.remove(1);
		assertTrue(it.hasNext());
		it.next();
		fail("Receiving element should throw an ConcurrentModificationException");
	}

	private MyAbstractList<Elem> elemListFromOneToFive() {
		MyAbstractList<Elem> list = createInstance();
		IntStream.rangeClosed(1, 5).forEach(e -> list.add(new Elem(e)));
		return list;
	}

	@Test(expected = ConcurrentModificationException.class)
	public void next_ElementAddedAtHeadAfterIteratorCreation_ConcurrentModificationException() {
		MyAbstractList<Elem> list = elemListFromOneToFive();
		Iterator<Elem> it = list.iterator();
		list.add(0, new Elem(3));
		it.next();
	}

	@Test(expected = ConcurrentModificationException.class)
	public void next_ElementAddedAtTailAfterIteratorCreation_ConcurrentModificationException() {
		MyAbstractList<Elem> list = elemListFromOneToFive();
		Iterator<Elem> it = list.iterator();
		list.add(list.size(), new Elem(3));
		it.next();
	}

	@Test(expected = ConcurrentModificationException.class)
	public void next_ElementAddedInBetweenAfterIteratorCreation_ConcurrentModificationException() {
		MyAbstractList<Elem> list = elemListFromOneToFive();
		Iterator<Elem> it = list.iterator();
		list.add(2, new Elem(3));
		it.next();
	}

	@Test(expected = ConcurrentModificationException.class)
	public void next_ElementRemovedAfterIteratorCreation_ConcurrentModificationException() {
		addNumbersFromOneToFiveToList();
		Iterator<Integer> it = list.iterator();
		list.remove(Integer.valueOf(3));
		it.next();
	}

	@Test
	public void next_IterateThroughAllElements_NoException() {
		Integer[] expected = new Integer[] { 1, 2, 3, 4, 5 };
		addNumbersFromOneToFiveToList();
		Iterator<Integer> it = list.iterator();
		for (int i = 0; i < expected.length; i++) {
			assertTrue(it.hasNext());
			assertEquals(expected[i].intValue(), it.next().intValue());
		}
		assertFalse(it.hasNext());
	}
}
