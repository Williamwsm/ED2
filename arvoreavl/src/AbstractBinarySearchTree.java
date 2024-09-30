public abstract class AbstractBinarySearchTree<T extends Comparable<T>> implements IBalancedTree<T> {
    protected Node<T> root;

    protected static class Node<T> {
        T data;
        Node<T> left, right, parent;
        int height; // Usado para balanceamento AVL

        Node(T data) {
            this.data = data;
            this.left = this.right = this.parent = null;
            this.height = 1;
        }
    }

    @Override
    public boolean search(T data) {
        return searchTree(root, data) != null;
    }

    private Node<T> searchTree(Node<T> node, T data) {
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