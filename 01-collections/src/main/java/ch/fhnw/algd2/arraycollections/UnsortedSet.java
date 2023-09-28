package ch.fhnw.algd2.arraycollections;

import java.util.Arrays;
import java.util.Set;

public class UnsortedSet<E> extends AbstractArrayCollection<E> implements Set<E> {
	public static final int DEFAULT_CAPACITY = 100;
	private E[] data;
	private int size = 0;

	public UnsortedSet() {
		this(DEFAULT_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public UnsortedSet(int capacity) {
		data = (E[])new Object[capacity];
	}

	private int indexOf(Object o) {
		checkNull(o);
		int index = 0;
		while (index < data.length && data[index] != null && !data[index].equals(o)) {
			index++;
		}

		if (index == data.length || data[index] == null) {
			return -1;
		} else {
			return index;
		}

	}

	@Override
	public boolean add(E e) {
		if (contains(e)) return false;
		if (size >= data.length) throw new IllegalStateException("Array is full");
		data[size] = e;
		size++;
		return true;
	}

	@Override
	public boolean remove(Object o) {
		int index = indexOf(o);
		if (index < 0) return false;

		data[index] = data[size - 1];
		data[size - 1] = null;
		size--;
		return true;
	}

	@Override
	public boolean contains(Object o) {
		return indexOf(o) >= 0;
	}

	@Override
	public Object[] toArray() {
		return Arrays.copyOf(data, size());
	}

	@Override
	public int size() {
		return size;
	}

	public static void main(String[] args) {
		UnsortedSet<Integer> bag = new UnsortedSet<Integer>();
		bag.add(2);
		bag.add(2);
		bag.add(1);
		System.out.println(Arrays.toString(bag.toArray()));
	}
}
