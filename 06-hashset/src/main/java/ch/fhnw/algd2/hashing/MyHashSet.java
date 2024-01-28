package ch.fhnw.algd2.hashing;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * Hash Set with Open Addressing collision resolution
 */
public class MyHashSet<E> implements Set<E> {
	private Object[] table;
	private final Object sentinel = new	Object();
	private int size = 0;

	public MyHashSet(int minCapacity) {
		if (minCapacity < 4) throw new IllegalArgumentException("At least table size 4 required");
		int tableSize = 1;
		while (tableSize < minCapacity) {
			tableSize <<= 1;
		}
		table = new Object[tableSize];
	}

	@Override
	public boolean contains(Object o) {
		int i = (o.hashCode() & 0x7fffffff) % table.length;
		int step = 1 + (o.hashCode() & 0x7fffffff) % table.length;
		int cnt = 0;
		while (!o.equals(table[i]) && cnt != table.length) {
			i = (i + step) % table.length;
			cnt++;
		}
		return o.equals(table[i]);
	}

	@Override
	public Iterator<E> iterator() {
		throw new UnsupportedOperationException();
	}

	public Object[] copyOfArray() {
		return Arrays.copyOf(table, table.length);
	}

	@Override
	public Object[] toArray() {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean add(E e) {
		if (e == null) throw new NullPointerException("Null are not allowed in this collection.");

		int i = (e.hashCode() & 0x7fffffff) % table.length;
		int step = 1 + (e.hashCode() & 0x7fffffff) % table.length;
		int cnt = 0;
		while (table[i] != null && table[i] != sentinel && cnt != table.length && !e.equals(table[i])) {
			i = (i + step) % table.length;
			cnt++;
		}
		if (e.equals(table[i])) return false;
		if (size == table.length) throw new IllegalStateException("HashTable is full.");
		table[i] = e;
		size++;
		return true;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object o : c) {
			if (!contains(o)) return false;
		}
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		boolean hasChanged = false;
		for (E elem : c) {
			if (add(elem)) hasChanged = true;
		}
		return hasChanged;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		boolean changed = false;
		for (Object o : c) {
			if (remove(o)) changed = true;
		}
		return changed;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public boolean remove(Object o) {
		if (o == null) throw new NullPointerException("Null not allowed");
		// TODO: Aufgabe 4
		return false;
	}

	@Override
	public void clear() {
		table = new Object[table.length];
		size = 0;
	}
}
