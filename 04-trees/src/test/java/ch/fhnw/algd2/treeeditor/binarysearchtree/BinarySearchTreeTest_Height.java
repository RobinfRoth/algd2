package ch.fhnw.algd2.treeeditor.binarysearchtree;

import ch.fhnw.algd2.treeeditor.base.Tree;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public abstract class BinarySearchTreeTest_Height {
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
    public void heightOfEmptyTree() {
        Tree<Integer, Object> tree1 = newTree();
        assertEquals(0, tree1.height());
    }

    @Test
    public void heightOfSingleRoot() {
        Tree<Integer, Object> tree1 = newTree();
        tree1.insert(TEN,TEN);
        assertEquals(1, tree1.height());
    }

    @Test
    public void heightOfSizeTwo1() {
        Tree<Integer, Object> tree1 = newTree();
        tree1.insert(TWELVE,TWELVE);
        tree1.insert(TEN,TEN);
        assertEquals(2, tree1.height());
    }

    @Test
    public void heightOfSizeTwo2() {
        Tree<Integer, Object> tree1 = newTree();
        tree1.insert(TWELVE,TWELVE);
        tree1.insert(TWENTY,TWENTY);
        assertEquals(2, tree1.height());
    }

    @Test
    public void heightOfBigTree() {
        assertEquals(5, tree.height());
    }

}
