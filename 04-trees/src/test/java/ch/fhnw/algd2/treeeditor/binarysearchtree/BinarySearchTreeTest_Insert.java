package ch.fhnw.algd2.treeeditor.binarysearchtree;

import org.junit.Before;
import org.junit.Test;

import ch.fhnw.algd2.treeeditor.base.Tree;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertArrayEquals;


public abstract class BinarySearchTreeTest_Insert {

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

    @Test
    public void insertIntoEmptyTree() {
        tree = newTree();
        assertEquals(0, tree.size());
        tree.insert(TWELVE, TWELVE);
        assertEquals(1, tree.size());
        assertEquals(TWELVE, tree.getRoot().getKey());
    }

    @Test
    public void insertSmallerElementToSingleNode() {
        tree = newTree();
        tree.insert(TWELVE, TWELVE);
        assertEquals(1, tree.size());
        tree.insert(TEN,TEN);
        assertEquals(2, tree.size());
        assertEquals(TWELVE, tree.getRoot().getKey());
        assertEquals(TEN, tree.getRoot().getLeft().getKey());
    }

    @Test
    public void insertLargerElementToSingleNode() {
        tree = newTree();
        tree.insert(TWELVE, TWELVE);
        assertEquals(1, tree.size());
        tree.insert(FIFTEEN,FIFTEEN);
        assertEquals(2, tree.size());
        assertEquals(TWELVE, tree.getRoot().getKey());
        assertEquals(FIFTEEN, tree.getRoot().getRight().getKey());
    }

    @Test
    public void insertTwoElementsToSingleNode() {
        tree = newTree();
        tree.insert(TWELVE, TWELVE);
        tree.insert(TEN,TEN);
        tree.insert(FIFTEEN,FIFTEEN);
        assertEquals(3, tree.size());
        assertEquals(TWELVE, tree.getRoot().getKey());
        assertEquals(FIFTEEN, tree.getRoot().getRight().getKey());
        assertEquals(TEN, tree.getRoot().getLeft().getKey());
    }

    @Test
    public void insertAtThirdLevelMin() {
        tree = newTree();
        tree.insert(TWENTY, TWENTY);
        tree.insert(FIFTEEN,FIFTEEN);
        tree.insert(THIRTY,THIRTY);
        tree.insert(TEN,TEN);
        assertEquals(4, tree.size());
        assertEquals(TWENTY, tree.getRoot().getKey());
        assertEquals(TEN, tree.getRoot().getLeft().getLeft().getKey());
    }

    @Test
    public void insertAtThirdLevelMax() {
        tree = newTree();
        tree.insert(TWENTY, TWENTY);
        tree.insert(FIFTEEN,FIFTEEN);
        tree.insert(THIRTY,THIRTY);
        tree.insert(FORTY,FORTY);
        assertEquals(4, tree.size());
        assertEquals(TWENTY, tree.getRoot().getKey());
        assertEquals(FORTY, tree.getRoot().getRight().getRight().getKey());
    }

    @Test
    public void insertInnerIntoLargerTree() {
        tree = newTree();
        tree.insert(TWENTY, TWENTY);
        tree.insert(FIFTEEN,FIFTEEN);
        tree.insert(THIRTY,THIRTY);
        tree.insert(TEN,TEN);
        tree.insert(FORTY,FORTY);
        tree.insert(THIRTY_FIVE,THIRTY_FIVE);
        assertEquals(6, tree.size());
        assertEquals(TWENTY, tree.getRoot().getKey());
        assertEquals(THIRTY_FIVE, tree.getRoot().getRight().getRight().getLeft().getKey());
    }

}