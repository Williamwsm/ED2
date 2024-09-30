public class Main {
        public static void main(String[] args) {
                // Cria uma nova árvore B+ com grau mínimo 3
                BPlusTree<Integer> bPlusTree = new BPlusTree<>(3);

                // Testa inserções
                System.out.println("Inserindo valores:");
                for (int i = 1; i <= 10; i++) {
                        bPlusTree.insert(i);
                        System.out.println("Inserido: " + i);
                }

                // Imprime a árvore após inserções
                System.out.println("\nÁrvore após inserções:");
                bPlusTree.printTree();

                // Testa busca
                System.out.println("\nBuscando valores:");
                int searchValue = 5;
                boolean found = bPlusTree.search(searchValue);
                System.out.println("Valor " + searchValue + (found ? " encontrado." : " não encontrado."));

                // Testa remoções
                System.out.println("\nRemovendo valores:");
                for (int i = 1; i <= 5; i++) {
                        bPlusTree.delete(i);
                        System.out.println("Removido: " + i);
                        bPlusTree.printTree();
                }

                // Imprime a árvore após remoções
                System.out.println("\nÁrvore após remoções:");
                bPlusTree.printTree();
        }
}
