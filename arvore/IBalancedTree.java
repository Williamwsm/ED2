package arvore;

public interface IBalancedTree<T extends Comparable<T>> extends IBinarySearchTree<T> {
    void balanceAfterInsert(AbstractBinarySearchTree.Node<T> node); // Balanceia após inserção
    void balanceAfterDelete(AbstractBinarySearchTree.Node<T> node); // Balanceia após remoção
}

