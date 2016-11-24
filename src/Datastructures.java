package LinkedList;


public interface Stack<T> {
    boolean isEmpty();
    T pop();
    LinkedList.SStack<T> push(T item);
}
