public interface IHeap<T extends Comparable<T>> {
    void insert(T data);   // Inserir elemento
    T delete();            // Remover elemento (retornar o máximo)
    boolean isEmpty();     // Verifica se a heap está vazia
    void printHeap();      // Imprimir a heap
}
