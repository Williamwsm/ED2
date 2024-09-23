package arvore;

    public interface IBinarySearchTree<T extends Comparable<T>> {
        void insert(T data); // Insere um dado na árvore
        void delete(T data); // Remove um dado da árvore
        boolean search(T data); // Verifica se um dado está presente na árvore
    }


