public interface IHashTable<T> {
    void insert(T data);    // Inserir elemento
    boolean search(T data); // Buscar elemento
    void delete(T data);    // Remover elemento
}
