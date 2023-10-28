import ch.fhnw.algd2.treeeditor.base.Tree;
import ch.fhnw.algd2.treeeditor.binarysearchtree.BinarySearchTreeTest_Height;

/*
 * Created on Mar 13, 2016
 */
/**
 * @author Wolfgang Weck
 */
public class Test_Height extends BinarySearchTreeTest_Height {
    @Override
    protected <K extends Comparable<? super K>, V> Tree<K, V> newTree() {
        return new BinarySearchTree<>();
    }
}
