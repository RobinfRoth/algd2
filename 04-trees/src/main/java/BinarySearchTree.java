import ch.fhnw.algd2.treeeditor.base.Tree;

/**
 * Implements an unbalanced binary search tree. Note that all "matching" is
 * based on the compareTo method.
 */

class BinarySearchTree<K extends Comparable<? super K>, E> implements Tree<K, E> {
	private Node<K, E> root = null;
	private int nodeCount = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see Tree#getRoot()
	 */
	@Override
	public Tree.Node<K, E> getRoot() {
		return root;
	}

	/**
	 * number of nodes in the tree
	 * 
	 * @return size of the tree.
	 */
	@Override
	public int size() {
		return nodeCount;
	}

	/**
	 * Test if the tree is logically empty.
	 * 
	 * @return true if empty, false otherwise.
	 */
	@Override
	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * Insert a value into the tree; if an element is already stored under the
	 * given key the element is replaced by the new one.
	 * 
	 * @param key
	 *          key with which the specified element is to be associated.
	 * @param element
	 *          element to be inserted into the tree.
	 */
	@Override
	public void insert(K key, E element) {
		if (isEmpty()) {
			root = new Node<K, E>(key, element);
			nodeCount++;
			return;
		}
		insert(root, key, element);
	}

	/**
	 * Helper method for recursion that takes in tee root of a (sub)tree.
	 *
	 * @param currentRoot Node where the subtree starts.
	 * @param key Key of the element to be inserted.
	 * @param element Element to be inserted.
	 */
	private void insert(Node<K, E> currentRoot, K key, E element) {
		if (key.equals(currentRoot.getKey())) {
			currentRoot.element = element;
			return;
		}

		if (key.compareTo(currentRoot.getKey()) > 0) {
			if (currentRoot.right == null) {
				currentRoot.right = new Node<>(key, element);
				nodeCount++;
				return;
			}
			insert(currentRoot.right, key, element);
		} else {
			if (currentRoot.left == null) {
				currentRoot.left = new Node<K, E>(key, element);
				nodeCount++;
				return;
			}
			insert(currentRoot.left, key, element);
		}
	}

	/**
	 * Searches an item in the tree.
	 * 
	 * @param key
	 *          the key to search for.
	 * @return the matching item or null if not found.
	 */
	@Override
	public E search(K key) {
		return search(root, key);
	}

	private E search(Node<K, E> currentRoot, K key) {
		if(currentRoot == null) return null;

		if (currentRoot.key.equals(key)) {
			return currentRoot.element;
		}

		if (key.compareTo(currentRoot.getKey()) > 0) {
			return search(currentRoot.right, key);
		} else {
			return search(currentRoot.left, key);
		}
	}

	/**
	 * Remove Node with key <code>key</code> from the tree. Nothing is done if x
	 * is not found.
	 * 
	 * @param key
	 *          the key of the item to remove.
	 */
	@Override
	public void remove(K key) {
		// TODO implement method remove here
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Tree#height()
	 */
	@Override
	public int height() {
		// TODO implement method height here
		return 0;
	}

	@Override
	public String toString() {
		// TODO implement method toString here
		return "TO BE IMPLEMENTED";
	}

	private static class Node<K extends Comparable<? super K>, E> implements Tree.Node<K, E> {
		final K key;
		E element;
		Node<K, E> left, right;

		@SuppressWarnings("unused")
		Node(K key) {
			this(key, null);
		}

		Node(K key, E element) {
			this.key = key;
			this.element = element;
		}

		@SuppressWarnings("unused")
		Node(K key, E element, Node<K, E> left, Node<K, E> right) {
			this(key, element);
			this.left = left;
			this.right = right;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see Tree.Node#getKey()
		 */
		@Override
		public K getKey() {
			return key;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see Tree.Node#getLeft()
		 */
		@Override
		public Node<K, E> getLeft() {
			return left;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see Tree.Node#getRight()
		 */
		@Override
		public Node<K, E> getRight() {
			return right;
		}
	}
}