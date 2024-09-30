public class AVLTree<T extends Comparable<T>> extends AbstractBinarySearchTree<T> {

    @Override
    public void insert(T data) {
        root = insertNode(root, data);
    }

    private Node<T> insertNode(Node<T> node, T data) {
        if (node == null)
            return new Node<>(data);

        if (data.compareTo(node.data) < 0)
            node.left = insertNode(node.left, data);
        else if (data.compareTo(node.data) > 0)
            node.right = insertNode(node.right, data);
        else
            return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));

        return balance(node);
    }

    @Override
    public void delete(T data) {
        root = deleteNode(root, data);
    }

    private Node<T> deleteNode(Node<T> node, T data) {
        if (node == null)
            return node;

        if (data.compareTo(node.data) < 0)
            node.left = deleteNode(node.left, data);
        else if (data.compareTo(node.data) > 0)
            node.right = deleteNode(node.right, data);
        else {
            if (node.left == null || node.right == null) {
                Node<T> temp = (node.left != null) ? node.left : node.right;
                node = (temp != null) ? temp : null;
            } else {
                Node<T> temp = getMinValueNode(node.right);
                node.data = temp.data;
                node.right = deleteNode(node.right, temp.data);
            }
        }

        if (node == null)
            return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));

        return balance(node);
    }

    private Node<T> getMinValueNode(Node<T> node) {
        Node<T> current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    private int height(Node<T> node) {
        return (node == null) ? 0 : node.height;
    }

    private int getBalanceFactor(Node<T> node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    private Node<T> balance(Node<T> node) {
        int balanceFactor = getBalanceFactor(node);

        if (balanceFactor > 1) {
            if (getBalanceFactor(node.left) < 0)
                node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balanceFactor < -1) {
            if (getBalanceFactor(node.right) > 0)
                node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private Node<T> rightRotate(Node<T> y) {
        Node<T> x = y.left;
        Node<T> T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    private Node<T> leftRotate(Node<T> x) {
        Node<T> y = x.right;
        Node<T> T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    @Override
    public void balanceAfterInsert(Node<T> node) {
        root = balance(node);
    }

    @Override
    public void balanceAfterDelete(Node<T> node) {
        root = balance(node);
    }
}
