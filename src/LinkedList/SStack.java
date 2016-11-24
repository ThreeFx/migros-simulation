package LinkedList;

public interface SStack<T> {
    boolean isEmpty();
    T pop();
    SStack<T> push(T item);
}
