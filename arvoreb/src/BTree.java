import java.util.ArrayList;

public class BTree<T extends Comparable<T>> extends AbstractBinarySearchTree<T> {
    private int t; // Grau mínimo

    // Construtor da árvore B com grau mínimo
    public BTree(int t) {
        this.t = t;
        root = new BTreeNode<>(t, true);
    }

    // Classe interna que representa um nó da árvore B
    private static class BTreeNode<T extends Comparable<T>> extends Node<T> {
        int n; // Número de chaves atualmente no nó
        ArrayList<T> keys; // Chaves no nó
        ArrayList<BTreeNode<T>> children; // Filhos do nó
        boolean isLeaf; // Se é uma folha

        // Construtor para o nó da árvore B
        BTreeNode(int t, boolean isLeaf) {
            super(null);  // O data será gerido de forma diferente na árvore B
            this.keys = new ArrayList<>(2 * t - 1);
            this.children = new ArrayList<>(2 * t);
            this.isLeaf = isLeaf;
            this.n = 0;
        }
    }

    // Método principal de inserção
    @Override
    public void insert(T data) {
        BTreeNode<T> r = (BTreeNode<T>) root;

        if (r.n == 2 * t - 1) { // Se a raiz estiver cheia, divide
            BTreeNode<T> s = new BTreeNode<>(t, false);
            s.children.add(r); // Novo nó se torna a raiz
            root = s;
            splitChild(s, 0, r); // Divide a raiz antiga
            insertNonFull(s, data); // Insere no nó apropriado
        } else {
            insertNonFull(r, data); // Inserir diretamente na raiz
        }
    }

    // Insere no nó não cheio
    private void insertNonFull(BTreeNode<T> node, T data) {
        int i = node.n - 1;

        if (node.isLeaf) {
            // Insere diretamente na folha
            node.keys.add(null); // Reserva espaço
            while (i >= 0 && data.compareTo(node.keys.get(i)) < 0) {
                node.keys.set(i + 1, node.keys.get(i));
                i--;
            }
            node.keys.set(i + 1, data);
            node.n++;
        } else {
            // Desce para o filho apropriado
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

    // Divide um filho cheio
    private void splitChild(BTreeNode<T> parent, int i, BTreeNode<T> fullChild) {
        BTreeNode<T> newNode = new BTreeNode<>(t, fullChild.isLeaf);
        newNode.n = t - 1;

        // Move as últimas t-1 chaves de fullChild para newNode
        for (int j = 0; j < t - 1; j++) {
            newNode.keys.add(fullChild.keys.get(j + t));
        }

        // Se fullChild não for uma folha, move os filhos
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

    // Método principal de remoção (simplificado)
    @Override
    public void delete(T data) {
        deleteNode((BTreeNode<T>) root, data);
    }

    // Implementação da remoção (simples aqui, expandir conforme necessário)
    private void deleteNode(BTreeNode<T> node, T data) {
        // Remoção recursiva aqui (varia dependendo do caso)
        // Envolve fusões, redistribuições, etc.
        int idx = findKey(node, data);

        if (idx < node.n && node.keys.get(idx).compareTo(data) == 0) {
            // Caso 1: O nó é uma folha
            if (node.isLeaf) {
                node.keys.remove(idx);
                node.n--;
            } else {
                // Caso 2: O nó não é folha
                // Implementar a lógica para substituir a chave e remover
                // Pode usar o predecessor ou o sucessor
            }
        } else {
            // Se não for encontrado
            if (node.isLeaf) {
                System.out.println("Chave " + data + " não encontrada!");
                return;
            }

            boolean isLastChild = (idx == node.n); // Verifica se é o último filho
            BTreeNode<T> child = node.children.get(idx);

            if (child.n < t) {
                fillChild(node, idx); // Preenche o filho se necessário
            }

            if (isLastChild && idx > node.n) {
                deleteNode(node.children.get(idx - 1), data);
            } else {
                deleteNode(child, data);
            }
        }
    }

    // Método para encontrar a chave no nó
    private int findKey(BTreeNode<T> node, T key) {
        int idx = 0;
        while (idx < node.n && node.keys.get(idx).compareTo(key) < 0) {
            idx++;
        }
        return idx;
    }

    private void fillChild(BTreeNode<T> parent, int idx) {
        BTreeNode<T> child = parent.children.get(idx);
        // Implementar lógica para redistribuição ou fusão
    }

    @Override
    public void balanceAfterInsert(Node<T> node) {
        // Para árvores B, o balanceamento está implícito na inserção
    }

    @Override
    public void balanceAfterDelete(Node<T> node) {
        // Para árvores B, o balanceamento está implícito na remoção
    }


    @Override
    public boolean search(T data) {
        return searchTree((BTreeNode<T>) root, data);
    }

    private boolean searchTree(BTreeNode<T> node, T data) {
        if (node == null) return false;

        // Verifica todas as chaves do nó atual
        for (int i = 0; i < node.n; i++) {
            if (data.compareTo(node.keys.get(i)) == 0) {
                return true; // Elemento encontrado
            }
            if (data.compareTo(node.keys.get(i)) < 0) {
                // Verifica se é uma folha antes de acessar os filhos
                if (node.isLeaf) {
                    return false; // Se for uma folha e não encontrou, retorna false
                }
                return searchTree(node.children.get(i), data); // Desce para o filho apropriado
            }
        }

        // Se não encontrar no nó atual e não for uma folha, desce para o último filho
        if (!node.isLeaf) {
            return searchTree(node.children.get(node.n), data);
        }

        return false; // Se for uma folha e não encontrou, retorna false
    }




}
