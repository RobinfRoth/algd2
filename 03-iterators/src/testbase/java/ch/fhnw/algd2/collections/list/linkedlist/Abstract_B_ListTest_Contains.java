package ch.fhnw.algd2.collections.list.linkedlist;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public abstract class Abstract_B_ListTest_Contains extends AbstractMyLinkedListTest {
	@Test
	public void contains_OnEmptyList_False() {
		assertFalse(list.contains(1));
	}

	@Test
	public void contains3_Numbers1To5Added_True() {
		addNumbersFromOneToFiveToList();
		assertTrue(list.contains(3));
	}

	@Test
	public void contains1_Numbers1To5Added_True() {
		addNumbersFromOneToFiveToList();
		assertTrue(list.contains(1));
	}

	@Test
	public void contains5_Numbers1To5Added_True() {
		addNumbersFromOneToFiveToList();
		assertTrue(list.contains(5));
	}

	@Test
	public void contains10_Numbers1To5Added_False() {
		addNumbersFromOneToFiveToList();
		assertFalse(list.contains(10));
	}

	@Test
	public void contains_NullAsParameter_FalseOrNullpointer() {
		addNumbersFromOneToFiveToList();
		boolean contains = true;
		try {
			contains = list.contains(null);
		}
		catch (NullPointerException ex) {
			contains = false;
		}
		assertFalse(contains);
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void contains_StringAsParameterForEmptyIntegerList_FalseOrClassCastException() {
		boolean found = true;
		try {
			found = list.contains("Abc");
		}
		catch (ClassCastException ex) {
			found = false;
		}
		assertFalse(found);
	}

	@Test
	public void contains_SeparateButEqualObjects_WillBeFound() {
		List<Elem> list = getListInstance();
		assertTrue(list.add(new Elem(0)));
		assertTrue("Different but equal object not identified as equal by contains", list.contains(new Elem(0)));
		assertTrue(list.add(new Elem(1)));
		assertTrue(list.add(new Elem(2)));
		for (int i = 0; i < 3; i++)
			assertTrue("Different but equal object not identified as equal by contains", list.contains(new Elem(i)));
	}
}
