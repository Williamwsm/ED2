import java.util.ArrayList;

public class BPlusTree<T extends Comparable<T>> {
    private int t; // Grau mínimo
    private BPlusNode<T> root; // Raiz da árvore

    public BPlusTree(int t) {
        this.t = t;
        this.root = new BPlusNode<>(t, true);
    }

    private static class BPlusNode<T extends Comparable<T>> {
        int n; // Número de chaves atualmente no nó
        ArrayList<T> keys; // Chaves no nó
        ArrayList<BPlusNode<T>> children; // Filhos do nó
        boolean isLeaf; // Se é uma folha
        BPlusNode<T> next; // Referência para o próximo nó folha

        BPlusNode(int t, boolean isLeaf) {
            this.keys = new ArrayList<>(2 * t - 1);
            this.children = new ArrayList<>(2 * t);
            this.isLeaf = isLeaf;
            this.n = 0;
            this.next = null; // Inicialmente, não há próximo nó folha
        }
    }

    public boolean search(T data) {
        return searchTree(root, data);
    }

    private boolean searchTree(BPlusNode<T> node, T data) {
        if (node == null) return false;

        int i = 0;
        while (i < node.n && data.compareTo(node.keys.get(i)) > 0) {
            i++;
        }

        // Se o nó for uma folha, verifica se a chave está presente
        if (node.isLeaf) {
            return i < node.n && data.compareTo(node.keys.get(i)) == 0;
        }

        return searchTree(node.children.get(i), data); // Desce para o filho apropriado
    }

    public void insert(T data) {
        BPlusNode<T> r = root;

        if (r.n == 2 * t - 1) { // Se a raiz estiver cheia, divide
            BPlusNode<T> s = new BPlusNode<>(t, false);
            s.children.add(r); // Novo nó se torna a raiz
            root = s;
            splitChild(s, 0, r); // Divide a raiz antiga
            insertNonFull(s, data); // Insere no nó apropriado
        } else {
            insertNonFull(r, data); // Insere diretamente na raiz
        }
    }

    private void insertNonFull(BPlusNode<T> node, T data) {
        int i = node.n - 1;

        if (node.isLeaf) {
            node.keys.add(null); // Reserva espaço
            while (i >= 0 && data.compareTo(node.keys.get(i)) < 0) {
                node.keys.set(i + 1, node.keys.get(i));
                i--;
            }
            node.keys.set(i + 1, data);
            node.n++;

            // Encadeamento de folhas
            if (node.n > 1) {
                node.next = null; // Para o primeiro nó
            }
        } else {
            while (i >= 0 && data.compareTo(node.keys.get(i)) < 0) {
                i--;
            }
            i++;
            if (node.children.get(i).n == 2 * t - 1) {
                splitChild(node, i, node.children.get(i));
                if (data.compareTo(node.keys.get(i)) > 0) {
                    i++;
                }
            }
            insertNonFull(node.children.get(i), data);
        }
    }

    private void splitChild(BPlusNode<T> parent, int i, BPlusNode<T> fullChild) {
        BPlusNode<T> newNode = new BPlusNode<>(t, fullChild.isLeaf);
        newNode.n = t - 1;

        for (int j = 0; j < t - 1; j++) {
            newNode.keys.add(fullChild.keys.get(j + t));
        }

        if (!fullChild.isLeaf) {
            for (int j = 0; j < t; j++) {
                newNode.children.add(fullChild.children.get(j + t));
            }
        }

        fullChild.n = t - 1;

        // Insere newNode como filho de parent
        parent.children.add(i + 1, newNode);
        parent.keys.add(i, fullChild.keys.get(t - 1));
        parent.n++;
    }

    public void delete(T data) {
        deleteNode(root, data);
        // Se a raiz não tem chaves, atualiza a raiz
        if (root.n == 0) {
            if (root.isLeaf) {
                root = null; // Se a árvore ficou vazia
            } else {
                root = root.children.get(0); // Atualiza a raiz
            }
        }
    }

    private void deleteNode(BPlusNode<T> node, T data) {
        int idx = 0;
        while (idx < node.n && data.compareTo(node.keys.get(idx)) > 0) {
            idx++;
        }

        if (idx < node.n && data.compareTo(node.keys.get(idx)) == 0) {
            // Se for um nó folha
            if (node.isLeaf) {
                removeFromLeaf(node, idx);
            } else {
                removeFromNonLeaf(node, idx);
            }
        } else {
            if (node.isLeaf) {
                return; // Se a chave não for encontrada em uma folha, não faz nada
            }

            boolean shouldMerge = (idx == node.n); // Se deve ir para o filho mais à direita
            BPlusNode<T> child = node.children.get(shouldMerge ? idx - 1 : idx);

            if (child.n < t) {
                fill(node, child, idx); // Agora passando o nó pai
            }

            if (shouldMerge) {
                deleteNode(node.children.get(idx - 1), data);
            } else {
                deleteNode(child, data);
            }
        }
    }


    private void removeFromLeaf(BPlusNode<T> leaf, int idx) {
        for (int i = idx + 1; i < leaf.n; i++) {
            leaf.keys.set(i - 1, leaf.keys.get(i));
        }
        leaf.n--;
    }

    private void removeFromNonLeaf(BPlusNode<T> node, int idx) {
        T key = node.keys.get(idx);

        BPlusNode<T> child = node.children.get(idx);
        if (child.n >= t) {
            T predecessor = getPredecessor(child);
            node.keys.set(idx, predecessor);
            deleteNode(child, predecessor);
        } else {
            BPlusNode<T> sibling = node.children.get(idx + 1);
            if (sibling.n >= t) {
                T successor = getSuccessor(sibling);
                node.keys.set(idx, successor);
                deleteNode(sibling, successor);
            } else {
                merge(node, idx);
                deleteNode(child, key);
            }
        }
    }

    private T getPredecessor(BPlusNode<T> node) {
        while (!node.isLeaf) {
            node = node.children.get(node.n);
        }
        return node.keys.get(node.n - 1);
    }

    private T getSuccessor(BPlusNode<T> node) {
        while (!node.isLeaf) {
            node = node.children.get(0);
        }
        return node.keys.get(0);
    }

    private void merge(BPlusNode<T> node, int idx) {
        BPlusNode<T> leftChild = node.children.get(idx);
        BPlusNode<T> rightChild = node.children.get(idx + 1);

        leftChild.keys.set(leftChild.n, node.keys.get(idx));
        for (int i = 0; i < rightChild.n; i++) {
            leftChild.keys.set(leftChild.n + 1 + i, rightChild.keys.get(i));
        }

        if (!leftChild.isLeaf) {
            for (int i = 0; i <= rightChild.n; i++) {
                leftChild.children.set(leftChild.n + 1 + i, rightChild.children.get(i));
            }
        }

        leftChild.n += rightChild.n + 1;

        node.keys.remove(idx);
        node.children.remove(idx + 1);
        node.n--;
    }

    private void fill(BPlusNode<T> parent, BPlusNode<T> child, int idx) {
        if (idx != 0 && parent.children.get(idx - 1).n >= t) {
            borrowFromPrev(child, idx, parent);
        } else if (idx != parent.n && parent.children.get(idx + 1).n >= t) {
            borrowFromNext(child, idx, parent);
        } else {
            if (idx != parent.n) {
                merge(parent, idx);
            } else {
                merge(parent, idx - 1);
            }
        }
    }

    private void borrowFromPrev(BPlusNode<T> child, int idx, BPlusNode<T> parent) {
        BPlusNode<T> sibling = parent.children.get(idx - 1); // Acessa o irmão à esquerda

        for (int i = child.n - 1; i >= 0; i--) {
            child.keys.set(i + 1, child.keys.get(i));
        }

        if (!child.isLeaf) {
            for (int i = child.n; i >= 0; i--) {
                child.children.set(i + 1, child.children.get(i));
            }
        }

        child.keys.set(0, parent.keys.get(idx - 1));
        if (!child.isLeaf) {
            child.children.set(0, sibling.children.get(sibling.n));
        }

        parent.keys.set(idx - 1, sibling.keys.get(sibling.n - 1));

        child.n++;
        sibling.n--;
    }

    private void borrowFromNext(BPlusNode<T> child, int idx, BPlusNode<T> parent) {
        BPlusNode<T> sibling = parent.children.get(idx + 1); // Acessa o irmão à direita

        child.keys.set(child.n, parent.keys.get(idx));
        if (!child.isLeaf) {
            child.children.set(child.n + 1, sibling.children.get(0));
        }

        parent.keys.set(idx, sibling.keys.get(0));

        for (int i = 1; i < sibling.n; i++) {
            sibling.keys.set(i - 1, sibling.keys.get(i));
        }

        if (!sibling.isLeaf) {
            for (int i = 1; i <= sibling.n; i++) {
                sibling.children.set(i - 1, sibling.children.get(i));
            }
        }

        child.n++;
        sibling.n--;
    }

    public void printTree() {
        printTree(root, 0);
    }

    private void printTree(BPlusNode<T> node, int level) {
        if (node == null) return;

        System.out.print("Level " + level + ": ");
        for (int i = 0; i < node.n; i++) {
            System.out.print(node.keys.get(i) + " ");
        }
        System.out.println();

        if (!node.isLeaf) {
            for (int i = 0; i <= node.n; i++) {
                printTree(node.children.get(i), level + 1);
            }
        }
    }
}
