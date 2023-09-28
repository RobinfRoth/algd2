package ch.fhnw.algd2.arraycollections;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import ch.fhnw.algd2.arraycollections.general.AbstractBagTest;
import ch.fhnw.algd2.arraycollections.general.ElementExistenceChecker;

public class SortedBagTest extends AbstractBagTest {
	@Override
	protected <T extends Comparable<? super T>> AbstractArrayCollection<T> createCollection(int size) {
		return new SortedBag<T>(size);
	}

	@Override
	protected Integer[] getExpectedOrderFor(Integer[] values) {
		List<Integer> list = new LinkedList<Integer>(Arrays.asList(values));
		Collections.sort(list);
		Integer[] elements = new Integer[list.size()];
		int index = 0;
		for (Integer element : list) {
			elements[index++] = element;
		}
		return elements;
	}

	@Override
	protected void checkElementExistence(Comparable<Integer>[] numbers) {
		ElementExistenceChecker.inGivenOrder(Arrays.copyOf(numbers, numbers.length), bag);
	}
}
