import java.util.ArrayList;

public class Heap<T extends Comparable<T>> implements IHeap<T> {
    private ArrayList<T> heap;  // ArrayList para armazenar os elementos da heap

    public Heap() {
        heap = new ArrayList<>();
    }

    @Override
    public void insert(T data) {
        heap.add(data); // Adiciona o novo elemento ao final
        bubbleUp(heap.size() - 1); // Reorganiza a heap
    }

    @Override
    public T delete() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        T root = heap.get(0); // O maior elemento é a raiz
        T lastElement = heap.remove(heap.size() - 1); // Remove o último elemento

        if (!heap.isEmpty()) {
            heap.set(0, lastElement); // Coloca o último elemento na raiz
            bubbleDown(0); // Reorganiza a heap
        }
        return root; // Retorna o maior elemento
    }

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    private void bubbleUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (heap.get(index).compareTo(heap.get(parentIndex)) > 0) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    private void bubbleDown(int index) {
        int lastIndex = heap.size() - 1;
        while (index <= lastIndex) {
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;
            int largestIndex = index;

            if (leftChildIndex <= lastIndex && heap.get(leftChildIndex).compareTo(heap.get(largestIndex)) > 0) {
                largestIndex = leftChildIndex;
            }

            if (rightChildIndex <= lastIndex && heap.get(rightChildIndex).compareTo(heap.get(largestIndex)) > 0) {
                largestIndex = rightChildIndex;
            }

            if (largestIndex != index) {
                swap(index, largestIndex);
                index = largestIndex;
            } else {
                break;
            }
        }
    }

    private void swap(int index1, int index2) {
        T temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }

    @Override
    public void printHeap() {
        System.out.println(heap);
    }
}
