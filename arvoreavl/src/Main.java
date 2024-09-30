public class Main {
    public static void main(String[] args) {
        // Cria uma árvore AVL de inteiros
        AVLTree<Integer> minhaArvore = new AVLTree<>();

        // Inserindo elementos na árvore AVL
        System.out.println("==== Teste de Inserção ====");
        minhaArvore.insert(10);
        minhaArvore.insert(20);
        minhaArvore.insert(30);
        minhaArvore.insert(40);
        minhaArvore.insert(50);
        minhaArvore.insert(25);

        // Imprimir a árvore após inserções
        System.out.println("\nÁrvore AVL após inserções:");
        imprimirArvore(minhaArvore.root);
        System.out.println("\n");

        // Teste de remoção
        System.out.println("==== Teste de Remoção ====");
        minhaArvore.delete(50);
        minhaArvore.delete(40);
        minhaArvore.delete(30);

        // Imprimir a árvore após remoções
        System.out.println("\nÁrvore AVL após remoções:");
        imprimirArvore(minhaArvore.root);
    }

    // Função auxiliar para imprimir a árvore em ordem (simples)
    public static <T extends Comparable<T>> void imprimirArvore(AbstractBinarySearchTree.Node<T> node) {
        if (node == null) return;

        imprimirArvore(node.left);
        System.out.print(node.data + " ");
        imprimirArvore(node.right);
    }
}