package ch.fhnw.algd2.collections.list.stack;

import ch.fhnw.algd2.collections.list.linkedlist.MyLinkedList;
import java.util.EmptyStackException;

public class MyStack<E> implements IStack<E> {
	private MyLinkedList<E> list = new MyLinkedList<E>();

	@Override
	public E pop() {
		if (list.size() == 0) throw new EmptyStackException();
		return list.remove(0);
	}

	@Override
	public E push(E elem) {
		list.add(0, elem);
		return elem;
	}

	@Override
	public E peek() {
		// TODO implement this operation
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean empty() {
		return list.size() == 0;
	}

	public static void main(String[] args) {
		MyStack<Integer> stack = new MyStack<Integer>();
		System.out.println("Pushing numbers to stack (0 to 9)");
		for (int i = 0; i < 10; i++) {
			stack.push(i);
		}
		System.out.println("Pop numbers from stack");
		while (!stack.empty()) {
			System.out.println(stack.pop());
		}
	}
}
