/*
 * Created on 11.02.2019
 */
package ch.fhnw.algd2.arraycollections.general;

/**
 * @author Wolfgang Weck
 */
final class Elem implements Comparable<Elem> {
	private final int value;

	public Elem(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		return value;
	}

	@Override
	public boolean equals(Object obj) {
		return this == obj || obj != null && getClass() == obj.getClass() && ((Elem)obj).value == value;
	}

	@Override
	public int compareTo(Elem o) {
		return value - o.value;
	}
}
