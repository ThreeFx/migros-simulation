package LLinkedList;

import java.util.*;

public class Node<T> {
    T item;
    Node<T> next;
    Node<T> prev;

    public Node(T item) {
        this.item = item;
    }

    public Node(T item, Node<T> next, Node<T> prev) {
        this.item = item;
        this.next = next;
        this.prev = prev;
    }

    public T getItem() {
        return this.item;
    }

    public Node<T> prepend(Node<T> node) {
        this.prev = prev;
        node.next = this;
        return this;
    }

    public Node<T> append(Node<T> node) {
        this.next = node;
        node.prev = this;
        return this;
    }

    public Node<T> prependFirst(Node<T> node) {
        Node<T> n = this;
        while (this.prev != null) n = n.prev;
        n.prev = node;
        node.next = n;
        return this;
    }

    public Node<T> appendLast(Node<T> node) {
        Node<T> n = this;
        while (this.next != null) n = n.next;
        n.next = node;
        node.prev = n;
        return this;
    }

    public boolean hasNext(){
        return next != null;
    }

    public Node<T> getNext(){
        return next;
    }
}
