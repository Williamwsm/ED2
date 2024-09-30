// IBalancedTree.java
public interface IBalancedTree<T extends Comparable<T>> extends IBinarySearchTree<T> {
    void balanceAfterInsert(AbstractBinarySearchTree.Node<T> node); // Ajustar o tipo Node
    void balanceAfterDelete(AbstractBinarySearchTree.Node<T> node); // Ajustar o tipo Node
}
