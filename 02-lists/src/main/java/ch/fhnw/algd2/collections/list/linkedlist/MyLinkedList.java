package ch.fhnw.algd2.collections.list.linkedlist;

import java.util.Arrays;

import ch.fhnw.algd2.collections.list.MyAbstractList;

public class MyLinkedList<E> extends MyAbstractList<E> {
    private int size = 0;
    private Node<E> first;
    private Node<E> last;

    @Override
    public boolean add(E e) {

        Node<E> newNode = new Node<>(e);

        if (size == 0) {
            first = newNode;
        } else {
            last.next = newNode;
        }
        last = newNode;
        size++;
        return true;
    }

    @Override
    public boolean contains(Object o) {
        // TODO: implement contains using while
        Node<E> currentNode = first;
        for (int i=0; i<size; i++) {
            if (currentNode.elem.equals(o)) {
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        Node<E> current = first;
        Node<E> prev = first;
        while(current != null && !current.elem.equals(o)) {
            prev = current;
            current = current.next;
        }

        // o not contained
        if (current == null) return false;

        // o is first
        if (first == current) {
            first = current.next;

            // o is the only element of the list
            if (size == 1) {
                first = null;
                last = null;
                current.next = null;
            }
            size--;
            return true;
        }

        // o is last
        if (last == current) {
            prev.next = null;
            last = prev;
            size--;
            return true;
        }

        // o is an element in the middle
        prev.next = current.next;
        current.next = null;
        size--;
        return true;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for length " + size + ".");
        }

        Node<E> currentNode = first;
        int currentIndex = 0;
        while (currentIndex < index) {
            currentIndex++;
            currentNode = currentNode.next;
        }
        return currentNode.elem;
    }

    @Override
    public void add(int index, E element) {
        // TODO implement this operation (part D)
        throw new UnsupportedOperationException();
    }

    @Override
    public E remove(int index) {
        // TODO implement this operation (part D)
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int index = 0;
        Node<E> current = first;
        while (current != null) {
            array[index++] = current.elem;
            current = current.next;
        }
        return array;
    }

    @Override
    public int size() {
        return size;
    }

    private static class Node<E> {
        private final E elem;
        private Node<E> next;

        private Node(E elem) {
            this.elem = elem;
        }

        private Node(E elem, Node<E> next) {
            this.elem = elem;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list.contains(2));
        System.out.println(list.remove(new Integer(2)));
        System.out.println(Arrays.toString(list.toArray()));
        System.out.println(list.get(2));
    }
}
