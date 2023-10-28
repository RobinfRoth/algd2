package ch.fhnw.algd2.treeeditor.binarysearchtree;

import org.junit.Before;
import org.junit.Test;

import ch.fhnw.algd2.treeeditor.base.Tree;

import static org.junit.Assert.*;

/**
 * @author B Geissmann
 */
public abstract class BinarySearchTreeTest_Search {
    private static final Integer FIFTEEN = Integer.valueOf(15);
    private static final Integer TEN = Integer.valueOf(10);
    private static final Integer FIFTY = Integer.valueOf(50);
    private static final Integer TWENTY = Integer.valueOf(20);
    private static final Integer FORTY = Integer.valueOf(40);
    private static final Integer SIXTY = Integer.valueOf(60);
    private static final Integer FIFTY_FIVE = Integer.valueOf(55);
    private static final Integer THIRTY = Integer.valueOf(30);
    private static final Integer TWELVE = Integer.valueOf(12);
    private static final Integer THIRTY_FIVE = Integer.valueOf(35);
    private static final Integer FIVE = Integer.valueOf(5);
    private static final Integer SEVENTY = Integer.valueOf(70);
    private static final Integer TWENTY_FIVE = Integer.valueOf(25);
    private Tree<Integer, Object> tree;
    private int initTreeSize;

    protected abstract <K extends Comparable<? super K>, V> Tree<K, V> newTree();

    @Before
    public void init() {
        tree = newTree();
        assertEquals(0, tree.size());
        tree.insert(TWENTY, TWENTY);
        tree.insert(TEN, TEN);
        tree.insert(FIFTEEN, FIFTEEN);
        tree.insert(TWELVE, TWELVE);
        tree.insert(FIFTY, FIFTY);
        tree.insert(SIXTY, SIXTY);
        tree.insert(FORTY, FORTY);
        tree.insert(THIRTY, THIRTY);
        tree.insert(THIRTY_FIVE, THIRTY_FIVE);
        tree.insert(FIFTY_FIVE, FIFTY_FIVE);
        assertEquals(10, tree.size());
        initTreeSize = tree.size();
    }

    @Test
    public void searchInEmptyTree() {
        Tree<Integer, Object> tree1 = newTree();
        assertNull(tree1.search(FORTY));
    }

    @Test
    public void searchInSingleNodeTreeRootElement() {
        Tree<Integer, Object> tree1 = newTree();
        tree1.insert(FORTY,FORTY);
        assertNotNull(tree1.search(FORTY));
    }

    @Test
    public void searchInSingleNodeTreeSmallerElement() {
        Tree<Integer, Object> tree1 = newTree();
        tree1.insert(FORTY,FORTY);
        assertNull(tree1.search(TEN));
    }

    @Test
    public void searchInSingleNodeTreeLargerElement() {
        Tree<Integer, Object> tree1 = newTree();
        tree1.insert(FORTY,FORTY);
        assertNull(tree1.search(FIFTY));
    }

    @Test
    public void searchRootElement() {
        assertEquals(TWENTY,tree.search(TWENTY));
    }

    @Test
    public void searchExistingInnerElement() {
        assertEquals(THIRTY,tree.search(THIRTY));
    }

    @Test
    public void searchMaxElement() {
        assertEquals(FIFTY_FIVE,tree.search(FIFTY_FIVE));
    }

    @Test
    public void searchMinElement() {
        assertEquals(TEN,tree.search(TEN));
    }

    @Test
    public void searchExistingLeafElement() {
        assertEquals(TWELVE,tree.search(TWELVE));
    }

    @Test
    public void searchNonexistingElement() {
        assertNull(tree.search(TWENTY_FIVE));
    }

    @Test
    public void searchTooSmallElement() {
        assertNull(tree.search(FIVE));
    }

    @Test
    public void searchTooLargeElement() {
        assertNull(tree.search(SEVENTY));
    }



}
