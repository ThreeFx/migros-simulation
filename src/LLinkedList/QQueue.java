package LLinkedList;

import java.lang.*;

public interface QQueue<T> {
    boolean isEmpty();
    T dequeue();
    QQueue<T> enqueue(T item);
}

