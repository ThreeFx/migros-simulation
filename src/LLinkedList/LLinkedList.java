package LLinkedList;

import Tiles.Person;

import java.util.*;

public class LLinkedList<T> implements QQueue<T>, SStack<T>, Iterable<Node<T>> {
    Node<T> head;
    Node<T> tail;
    int size;

    public LLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public LLinkedList(T item) {
        this.head = new Node<T>(item);
        this.tail = head;
        this.size = 1;
    }

    public int getSize() {
        return this.size;
    }

    public LLinkedList<T> append(T item) {
        Node<T> newTail = new Node<T>(item);
        if(this.tail != null) {
            this.tail.append(newTail);
            this.tail = newTail;
        }else {
            this.head = newTail;
            this.tail = newTail;
        }
        size++;
        return this;
    }

    public LLinkedList<T> prepend(T item) {
        Node<T> newHead = new Node<T>(item);
        this.head.prepend(newHead);
        this.head = newHead;
        size++;
        return this;
    }

    public LLinkedList<T> delete(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " is invalid!");
        }

        int i = 0;
        Node<T> cur = head;
        while (i < index) {
            cur = cur.next;
            i++;
        }
        return delete(cur);
    }

    public LLinkedList<T> deleteFirst() {
        this.head = this.head.next;
        this.head.prev = null;
        size--;
        return this;
    }

    public LLinkedList<T> deleteLast() {
        this.tail = this.tail.prev;
        this.tail.next = null;
        size--;
        return this;
    }

    public LLinkedList<T> delete(Node<T> node) {
        if (this.size == 1) {
            this.head = null;
            this.tail = null;
            return this;
        } else if (node == this.head) {
            return deleteFirst();
        } else if (node == this.tail) {
            return deleteLast();
        } else {
            Node<T> toDelete = node;
            node.prev.next = toDelete.next;
            node.next.prev = toDelete.prev;
            size--;
            return this;
        }
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public QQueue<T> enqueue(T item) {
        return this.append(item);
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Cannot dequeue from empty Queue!");
        }

        T result = head.getItem();
        this.deleteFirst();
        return result;
    }
    
    public SStack<T> push(T item) {
        return this.prepend(item);
    }

    public T peek() {
        return (this.head != null)?this.head.getItem():null;
    }

    // Dequeue and pop work from the same end.
    public T pop() {
        return this.dequeue();
    }

    public Node<T> getFirst() { return head; }

    // add iterator so we can traverse the list more easily
    @Override
    public Iterator<Node<T>> iterator() {
        Iterator<Node<T>> iterator = new Iterator<Node<T>>() {
            private Node<T> currentNode = new Node<T>(null).append(head);

            @Override
            public boolean hasNext() {
                return currentNode.next != null;
            }

            @Override
            public Node<T> next() {
                currentNode = currentNode.next;
                return currentNode;
            }

            @Override
            public void remove() {
                System.out.println("Can't remove for now...");
            }

        };

        return iterator;
    }
}
