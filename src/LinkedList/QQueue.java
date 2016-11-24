package LinkedList;
public interface QQueue<T> {
    boolean isEmpty();
    T dequeue();
    QQueue<T> enqueue(T item);
}

