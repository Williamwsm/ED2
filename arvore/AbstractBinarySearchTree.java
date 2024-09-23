package arvore;

public abstract class AbstractBinarySearchTree<T extends Comparable<T>> implements IBalancedTree<T> {
    protected Node<T> root; // Raiz da árvore

    protected static class Node<T> {
        T data;
        Node<T> left, right, parent;
        boolean color; // true para vermelho, false para preto

        Node(T data) {
            this.data = data;
            this.left = this.right = this.parent = null;
            this.color = true; // Novos nós são vermelhos por padrão
        }
    }

    @Override
    public boolean search(T data) {
        return searchTree(root, data) != null;
    }

    public Node<T> searchTree(Node<T> node, T data) {
        if (node == null || data.compareTo(node.data) == 0)
            return node;
        if (data.compareTo(node.data) < 0)
            return searchTree(node.left, data);
        else
            return searchTree(node.right, data);
    }

    @Override
    public abstract void balanceAfterInsert(Node<T> node);

    @Override
    public abstract void balanceAfterDelete(Node<T> node);
}

