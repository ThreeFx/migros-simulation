interface Queue<T> {
    boolean isEmpty();
    T dequeue();
    Queue<T> enqueue(T item);
}
