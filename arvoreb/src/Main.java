public class Main {
    public static void main(String[] args) {
        BTree<Integer> bTree = new BTree<>(3); // Grau mínimo 3

        // Testes de inserção
        System.out.println("Inserindo elementos...");
        bTree.insert(10);
        bTree.insert(20);
        bTree.insert(5);
        bTree.insert(6);
        bTree.insert(12);
        bTree.insert(30);
        bTree.insert(7);
        bTree.insert(17);




        // Testes de remoção
        System.out.println("Removendo elemento 6...");
        bTree.delete(6);
        System.out.println("Removendo elemento 13 (não existe)...");
        bTree.delete(13);

        System.out.println("Busca por 12: " + bTree.search(12)); // Deve retornar true
        System.out.println("Busca por 15: " + bTree.search(15)); // Deve retornar false


    }
}