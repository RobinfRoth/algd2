package ch.fhnw.algd2.arraycollections;

import java.util.Arrays;

public class SortedBag<E extends Comparable<? super E>> extends AbstractArrayCollection<E> {
    public static final int DEFAULT_CAPACITY = 100;
    private E[] data;
    private int size;

    public SortedBag() {
        this(DEFAULT_CAPACITY);
    }

    private int indexOf(Object o) {
        checkNull(o);
        return Arrays.binarySearch(data, 0, size, o);
    }

    private void shiftRight(int startIndex) {
        int i = size;
        while (i > startIndex) {
            data[i] = data[i-1];
            i--;
        }
    }

    @SuppressWarnings("unchecked")
    public SortedBag(int capacity) {
        data = (E[])new Comparable[capacity];
    }

    @Override
    public boolean add(E e) {
        int index = indexOf(e);
        if (index < 0) index = (-1 * index) - 1; // when not found: index = (-(insertionPoint) - 1)
        if (index >= data.length) throw new IllegalStateException("Array is full");

        if (index < size) {
             shiftRight(index);
        } else {
             index = size;
        }

        data[index] = e;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        // TODO implement unless collection shall be immutable
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) > 0;
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
        SortedBag<Integer> bag = new SortedBag<Integer>();
        bag.add(2);
        bag.add(1);
        System.out.println(Arrays.toString(bag.toArray()));
    }
}
