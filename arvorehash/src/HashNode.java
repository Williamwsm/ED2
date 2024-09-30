public class HashNode<T> {
    T data;
    HashNode<T> next;

    public HashNode(T data) {
        this.data = data;
        this.next = null;
    }
}
