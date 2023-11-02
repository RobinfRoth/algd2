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
		root = insert(root, key, element);
	}

	/**
	 * Helper method for recursion that takes in tee root of a (sub)tree.
	 *
	 * @param currentRoot Node where the subtree starts.
	 * @param key Key of the element to be inserted.
	 * @param element Element to be inserted.
	 */
	private Node<K, E> insert(Node<K, E> currentRoot, K key, E element) {
		if (key.equals(currentRoot.getKey())) {
			currentRoot.element = element;
			return currentRoot;
		}

		if (key.compareTo(currentRoot.getKey()) > 0) {
			if (currentRoot.right == null) {
				currentRoot.right = new Node<>(key, element);
				nodeCount++;
				return currentRoot;
			}
			currentRoot.right = insert(currentRoot.right, key, element);
			currentRoot = balance(currentRoot);
		} else {
			if (currentRoot.left == null) {
				currentRoot.left = new Node<K, E>(key, element);
				nodeCount++;
				return currentRoot;
			}
			currentRoot.left = insert(currentRoot.left, key, element);
			currentRoot = balance(currentRoot);
		}
		return currentRoot;
	}

	private Node<K, E> balance(Node<K, E> currentRoot) {
		if (currentRoot == null) return null;

		int balParent = height(currentRoot.right) - height(currentRoot.left);
		if (balParent < -1 ) {
			int balChild = height(currentRoot.left.right) - height(currentRoot.left.left);
			if (balChild > 0) {
				return rotateLR(currentRoot);
			} else {
				return rotateR(currentRoot);
			}
		} else if (balParent > 1) {
			int balChild = height(currentRoot.right.right) - height(currentRoot.right.left);
			if (balChild < 0) {
				return rotateRL(currentRoot);
			} else {
				return rotateL(currentRoot);
			}
		}
		return currentRoot;
	}

	private Node<K, E> rotateR(Node<K, E> currentRoot) {
		var n1 = currentRoot.left;
		currentRoot.left = n1.right;
		n1.right = currentRoot;
		currentRoot = n1;
		return currentRoot;
	}

	private Node<K, E> rotateL(Node<K, E> currentRoot) {
		var n1 = currentRoot.right;
		currentRoot.right = n1.left;
		n1.left = currentRoot;
		currentRoot = n1;
		return currentRoot;
	}

	private Node<K, E> rotateLR(Node<K, E> currentRoot) {
		currentRoot.left = rotateL(currentRoot.left);
		currentRoot = rotateR(currentRoot);
		return currentRoot;
	}

	private Node<K, E> rotateRL(Node<K, E> currentRoot) {
		currentRoot.right = rotateR(currentRoot.right);
		return rotateL(currentRoot);
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
		root = remove(root, key);
	}

	private Node<K, E> remove(Node<K, E> currentRoot, K key) {
		if (currentRoot == null) return null;

		if (key.equals(currentRoot.key)) {
			if (currentRoot.left == null && currentRoot.right == null) {
				currentRoot = null;
				nodeCount--;
			} else if (currentRoot.left != null && currentRoot.right != null) {
				Node<K, E> symSuccessor = getSymSuccessor(currentRoot);
				remove(currentRoot, symSuccessor.key);
				symSuccessor.left = currentRoot.left;
				symSuccessor.right = currentRoot.right;
				currentRoot = symSuccessor;
			} else if (currentRoot.left != null) {
				currentRoot = currentRoot.left;
				nodeCount--;
			} else {
				currentRoot = currentRoot.right;
				nodeCount--;
			}
			return currentRoot;
		}

		if (key.compareTo(currentRoot.key) > 0) {
			currentRoot.right = remove(currentRoot.right, key);
		} else {
			currentRoot.left = remove(currentRoot.left, key);
		}
		return currentRoot;
	}

	private Node<K,E> getSymSuccessor(Node<K,E> currentRoot) {
		Node <K, E> successor = currentRoot.right;

		while (successor.left != null) {
			successor = successor.left;
		}
		return successor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Tree#height()
	 */
	@Override
	public int height() {
		return height(root);
	}

	private int height(Node<K, E> currentRoot) {
		int height = 0;
		if (currentRoot == null) return 0;
		if (currentRoot.left == null && currentRoot.right == null) return 1;
		height += Math.max(height(currentRoot.left), height(currentRoot.right)) + 1;

		return height;
	}

	@Override
	public String toString() {
		var sb = new StringBuilder();
		inorder(root, sb);
		return sb.toString();
	}

	private void inorder(Node<K, E> currentRoot, StringBuilder sb) {
		if (currentRoot == null) return;

		sb.append("[");
		inorder(currentRoot.left, sb);
		sb.append(" ").append(currentRoot.key.toString()).append(" ");
		inorder(currentRoot.right, sb);
		sb.append("]");
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