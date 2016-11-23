package LinkedList;

public interface Queue<T> {
    boolean isEmpty();
    T dequeue();
    Queue<T> enqueue(T item);
}

public interface Stack<T> {
    boolean isEmpty();
    T pop();
    Stack<T> push(T item);
}
