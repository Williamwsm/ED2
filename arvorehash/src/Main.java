public class Main {
    public static void main(String[] args) {
        HashTable<Integer> hashTable = new HashTable<>(10); // Cria uma tabela hash com tamanho 10

        // Testa inserções
        System.out.println("Inserindo valores:");
        for (int i = 1; i <= 10; i++) {
            hashTable.insert(i);
            System.out.println("Inserido: " + i);
        }

        // Imprime a tabela hash após inserções
        System.out.println("\nTabela Hash após inserções:");
        hashTable.printTable();

        // Testa busca
        System.out.println("\nBuscando valores:");
        int searchValue = 5;
        boolean found = hashTable.search(searchValue);
        System.out.println("Valor " + searchValue + (found ? " encontrado." : " não encontrado."));

        // Testa remoções
        System.out.println("\nRemovendo valores:");
        for (int i = 1; i <= 5; i++) {
            hashTable.delete(i);
            System.out.println("Removido: " + i);
            hashTable.printTable();
        }

        // Imprime a tabela hash após remoções
        System.out.println("\nTabela Hash após remoções:");
        hashTable.printTable();
    }
}
