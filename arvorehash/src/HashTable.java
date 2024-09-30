public class HashTable<T> implements IHashTable<T> {
    private HashNode<T>[] table;  // Array da tabela hash
    private int size;              // Tamanho da tabela

    @SuppressWarnings("unchecked")
    public HashTable(int size) {
        this.size = size;
        this.table = new HashNode[size];
    }

    private int hash(T data) {
        return Math.abs(data.hashCode()) % size; // Função de hash simples
    }

    @Override
    public void insert(T data) {
        int index = hash(data);
        HashNode<T> newNode = new HashNode<>(data);

        if (table[index] == null) {
            table[index] = newNode;
        } else {
            HashNode<T> current = table[index];
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode; // Adiciona ao final da lista encadeada
        }
    }

    @Override
    public boolean search(T data) {
        int index = hash(data);
        HashNode<T> current = table[index];

        while (current != null) {
            if (current.data.equals(data)) {
                return true; // Encontrou o elemento
            }
            current = current.next;
        }
        return false; // Não encontrou
    }

    @Override
    public void delete(T data) {
        int index = hash(data);
        HashNode<T> current = table[index];
        HashNode<T> previous = null;

        while (current != null) {
            if (current.data.equals(data)) {
                if (previous == null) {
                    table[index] = current.next; // Remove o primeiro nó
                } else {
                    previous.next = current.next; // Remove o nó do meio ou final
                }
                return;
            }
            previous = current;
            current = current.next;
        }
    }

    // Método para imprimir a tabela hash
    public void printTable() {
        for (int i = 0; i < size; i++) {
            System.out.print("Índice " + i + ": ");
            HashNode<T> current = table[i];
            while (current != null) {
                System.out.print(current.data + " -> ");
                current = current.next;
            }
            System.out.println("null");
        }
    }
}
