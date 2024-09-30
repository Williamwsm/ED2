public abstract class AbstractBinarySearchTree<T extends Comparable<T>> implements IBinarySearchTree<T> {
    protected Node<T> root;
    // Raiz da árvore

    // Classe interna que representa um nó
    protected static class Node<T> {
        T data;
        Node<T> left, right, parent;

        Node(T data) {
            this.data = data;
            this.left = this.right = this.parent = null;
        }
    }

    @Override
    public boolean search(T data) {
        return searchTree(root, data);
    }

    protected abstract boolean searchTree(Node<T> node, T data);
}
