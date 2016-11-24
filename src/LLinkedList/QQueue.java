package LLinkedList;
public interface QQueue<T> {
    boolean isEmpty();
    T dequeue();
    QQueue<T> enqueue(T item);
}

