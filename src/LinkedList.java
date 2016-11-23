class LinkedList<T> implements Queue<T> {
    Node<T> head;
    Node<T> tail;
    int size;

    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public LinkedList(T item) {
        this.head = new Node<T>(item);
        this.tail = head;
        this.size = 1;
    }

    public LinkedList<T> append(T item) {
        Node<T> newTail = new Node<T>(item);
        this.tail.append(newTail);
        this.tail = newTail;
        size++;
        return this;
    }

    public LinkedList<T> prepend(T item) {
        Node<T> newHead = new Node<T>(item);
        this.head.prepend(newHead);
        this.head = newHead;
        size++;
        return this;
    }

    public LinkedList<T> delete(int index) {
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

    public LinkedList<T> deleteFirst() {
        this.head = this.head.next;
        this.head.prev = null;
        size--;
        return this;
    }

    public LinkedList<T> deleteLast() {
        this.tail = this.tail.prev;
        this.tail.next = null;
        size--;
        return this;
    }

    public LinkedList<T> delete(Node<T> node) {
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

    public Queue<T> enqueue(T item) {
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
}
