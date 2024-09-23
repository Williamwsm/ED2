package arvore;

public class RedBlackTree<T extends Comparable<T>> extends AbstractBinarySearchTree<T> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    @Override
    public void insert(T data) {
        Node<T> newNode = new Node<>(data);
        root = insertRec(root, newNode);
        balanceAfterInsert(newNode);
    }

    private Node<T> insertRec(Node<T> root, Node<T> newNode) {
        if (root == null) {
            return newNode;
        }

        if (newNode.data.compareTo(root.data) < 0) {
            root.left = insertRec(root.left, newNode);
            root.left.parent = root;
        } else if (newNode.data.compareTo(root.data) > 0) {
            root.right = insertRec(root.right, newNode);
            root.right.parent = root;
        }

        return root;
    }

    @Override
    public void delete(T data) {
        Node<T> nodeToDelete = searchTree(root, data);
        if (nodeToDelete != null) {
            root = deleteRec(root, nodeToDelete);
            if (nodeToDelete.color == BLACK) {
                balanceAfterDelete(nodeToDelete);
            }
        }

    }

    private Node<T> deleteRec(Node<T> root, Node<T> nodeToDelete) {
        if (nodeToDelete.left == null) {
            // Caso 1: Nó a ser removido tem no máximo um filho à direita
            return substituir(nodeToDelete, nodeToDelete.right);
        } else if (nodeToDelete.right == null) {
            // Caso 2: Nó a ser removido tem no máximo um filho à esquerda
            return substituir(nodeToDelete, nodeToDelete.left);
        } else {
            // Caso 3: Nó a ser removido tem dois filhos
            Node<T> successor = menor(nodeToDelete.right); // Encontra o sucessor
            Node<T> tempSuccessor = successor;
            boolean originalColor = successor.color; // Salva a cor do sucessor
            Node<T> successorRight = successor.right;

            if (successor.parent != nodeToDelete) {
                substituir(successor, successor.right);
                successor.right = nodeToDelete.right;
                successor.right.parent = successor;
            }
            substituir(nodeToDelete, successor);
            successor.left = nodeToDelete.left;
            successor.left.parent = successor;
            successor.color = nodeToDelete.color;

            if (originalColor == BLACK && successorRight != null) {
                balanceAfterDelete(successorRight);
            }
        }
        return root;
    }
    private Node<T> substituir(Node<T> raiz , Node<T> novaRaiz) {
        if (raiz.parent == null) {
            root = novaRaiz;
        } else if (raiz  == raiz .parent.left) {
            raiz.parent.left = novaRaiz;
        } else {
            raiz.parent.right = novaRaiz;
        }
        if (novaRaiz != null) {
            novaRaiz.parent = raiz.parent;
        }
        return novaRaiz;
    }



    @Override
    public void balanceAfterInsert(Node<T> node) {
        Node<T> parent = null;
        Node<T> grandParent = null;

        while (node != root && node.color == RED && node.parent.color == RED) {
            parent = node.parent;
            grandParent = parent.parent;

            // Caso o pai de node seja filho à esquerda do avô
            if (parent == grandParent.left) {
                Node<T> uncle = grandParent.right;

                // Caso 1: Tio de node é vermelho
                if (uncle != null && uncle.color == RED) {
                    grandParent.color = RED;
                    parent.color = BLACK;
                    uncle.color = BLACK;
                    node = grandParent;
                } else {
                    // Caso 2: node é filho à direita
                    if (node == parent.right) {
                        rotateLeft(parent);
                        node = parent;
                        parent = node.parent;
                    }
                    // Caso 3: node é filho à esquerda
                    rotateRight(grandParent);
                    boolean tempColor = parent.color;
                    parent.color = grandParent.color;
                    grandParent.color = tempColor;
                    node = parent;
                }
            } else {
                Node<T> uncle = grandParent.left;

                if (uncle != null && uncle.color == RED) {
                    grandParent.color = RED;
                    parent.color = BLACK;
                    uncle.color = BLACK;
                    node = grandParent;
                } else {
                    if (node == parent.left) {
                        rotateRight(parent);
                        node = parent;
                        parent = node.parent;
                    }
                    rotateLeft(grandParent);
                    boolean tempColor = parent.color;
                    parent.color = grandParent.color;
                    grandParent.color = tempColor;
                    node = parent;
                }
            }
        }

        root.color = BLACK;
    }

    @Override
    public void balanceAfterDelete(Node<T> node) {
        // Implementação do balanceamento após a remoção
    }

    private void rotateLeft(Node<T> node) {
        Node<T> rightChild = node.right;
        node.right = rightChild.left;

        if (rightChild.left != null)
            rightChild.left.parent = node;

        rightChild.parent = node.parent;

        if (node.parent == null)
            root = rightChild;
        else if (node == node.parent.left)
            node.parent.left = rightChild;
        else
            node.parent.right = rightChild;

        rightChild.left = node;
        node.parent = rightChild;
    }

    private void rotateRight(Node<T> node) {
        Node<T> leftChild = node.left;
        node.left = leftChild.right;

        if (leftChild.right != null)
            leftChild.right.parent = node;

        leftChild.parent = node.parent;

        if (node.parent == null)
            root = leftChild;
        else if (node == node.parent.right)
            node.parent.right = leftChild;
        else
            node.parent.left = leftChild;

        leftChild.right = node;
        node.parent = leftChild;
    }
    private Node<T> menor(Node<T> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

}


