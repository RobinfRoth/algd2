package ch.fhnw.algd2.arraycollections.general;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ch.fhnw.algd2.arraycollections.AbstractArrayCollection;

public abstract class AbstractBagTest extends AbstractCollectionTest {
	@Override
	protected void addNumbersToBag(Integer[] numbers) {
		for (Integer number : numbers) {
			assertTrue(bag.add(number));
		}
	}

	@Test
	public void removesOnlyOneInstance() {
		AbstractArrayCollection<Elem> c = createCollection(20);
		for (int i = 0; i < 3; i++)
			c.add(new Elem(1));
		assertTrue(c.remove(new Elem(1)));
		assertEquals(c.size(), 2);
		assertTrue(c.contains(new Elem(1)));
	}
}
