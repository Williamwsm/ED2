public class Main {
    public static void main(String[] args) {
        Heap<Integer> heap = new Heap<>(); // Cria uma heap

        // Testa inserções
        System.out.println("Inserindo valores:");
        for (int i = 1; i <= 10; i++) {
            heap.insert(i);
            System.out.println("Inserido: " + i);
        }

        // Imprime a heap após inserções
        System.out.println("\nHeap após inserções:");
        heap.printHeap();

        // Testa remoções
        System.out.println("\nRemovendo valores:");
        while (!heap.isEmpty()) {
            int max = heap.delete();
            System.out.println("Removido: " + max);
            heap.printHeap(); // Imprime a heap após cada remoção
        }
    }
}
