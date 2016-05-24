package ua.kpi.fict.oop2.classes.variant12.lab7;

import java.util.Iterator;

/**
 * Created by Max Donchenko (https://github.com/goodwin64) on 22.05.2016.
 */
public class DLList<E> implements Iterable<E> {
    private Node head;
    private Node tail;
    private int size;

    public DLList() {
        size = 0;
    }

    public E getFromTail(int steps) {
        // TODO: 23.05.2016 special case: one-element list
        Node cur = tail;
        for (int i = 0; i < steps; i++) {
            cur = cur.prev;
        }
        return cur.element;
    }

    public E getFromHead(int steps) {
        Node cur = head;
        for (int i = 0; i < steps; i++) {
            cur = cur.next;
        }
        return cur.element;
    }

    public E setFromTail(int steps, E e) {
        Node cur = tail;
        for (int i = 0; i < steps; i++) {
            cur = cur.prev;
        }
        Node replacer = new Node(e, cur.next, cur.prev);
        if (cur.prev != null) {
            cur.prev.next = replacer;
        }
        cur.next.prev = replacer;
        return cur.element;
    }

    public E setFromHead(int steps, E e) {
        Node cur = head;
        for (int i = 0; i < steps; i++) {
            cur = cur.next;
        }
        Node replacer = new Node(e, cur.next, cur.prev);
        if (cur.next != null) {
            cur.next.prev = replacer;
        }
        cur.prev.next = replacer;
        return cur.element;
    }

    public E removeFromTail(int steps) {
        Node cur = tail;
        for (int i = 0; i < steps; i++) {
            cur = cur.prev;
        }
        if (cur.prev != null) {
            cur.prev.next = cur.next;
        }
        cur.next.prev = cur.prev;
        return cur.element;
    }

    public E removeFromHead(int steps) {
        Node cur = head;
        for (int i = 0; i < steps; i++) {
            cur = cur.next;
        }
        if (cur.next != null) {
            cur.next.prev = cur.prev;
        }
        cur.prev.next = cur.next;
        return cur.element;
    }

    /**
     * This class keeps track of each element information.
     */
    private class Node {
        E element;
        Node next;
        Node prev;

        public Node(E element, Node next, Node prev) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }

    /**
     * Returns the size of the linked list.
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns whether the list is empty or not.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Adds element at the start.
     * @param element       element to add
     */
    public void addFirst(E element) {
        Node tmp = new Node(element, head, null);
        if (head != null) {
            head.prev = tmp;
        }
        head = tmp;
        if (tail == null) {
            tail = tmp;
        }
        size++;
    }

    /**
     * Adds element at the end.
     * @param element       element to add
     */
    public void addLast(E element) {
        Node tmp = new Node(element, null, tail);
        if (tail != null) {
            tail.next = tmp;
        }
        tail = tmp;
        if (head == null) {
            head = tmp;
        }
        size++;
    }

    public void add(int index, E element) throws IndexOutOfBoundsException {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Wrong index: " + index);
        }
        Node tmp = new Node(element, head, null);
        Node cur = head;
        for (int i = 1; i < index; i++) {
            cur = cur.next;
        }
        tmp.next = cur.next;
        tmp.prev = cur;
        cur.next.prev = tmp;
        cur.next = tmp;
        size++;
    }

    /**
     * Removes an element from the list.
     * @param e     the element to remove
     */
    public boolean remove(E e) {
        if (tail.element.equals(e)) {
            tail.prev.next = null;
            tail = tail.prev;
            size--;
            return true;
        } else if (head.element.equals(e)) {
            head.next.prev = null;
            head = head.next;
            size--;
            return true;
        }
        Node temp = this.head.next;
        while (temp != null) {
            if (temp.element.equals(e)) {
                temp.prev.next = temp.next;
                temp.next.prev = temp.prev;
                size--;
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    /**
     * Returns whether the list contains the element.
     * @param element      a sought-for element
     */
    public boolean contains(E element) {
        Node temp = this.head;
        while (temp != null) {
            if (temp.element.equals(element)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node temp = new Node(null, head, null);

            @Override
            public boolean hasNext() {
                return temp.next != null;
            }

            @Override
            public E next() {
                temp = temp.next;
                return temp.element;
            }
        };
    }

    @Override
    public String toString() {
        String result = "";
        Node temp = this.head;
        if (temp != null) {
            result += temp.element;
            temp = temp.next;
        }
        while (temp != null) {
            result += " -> " + temp.element;
            temp = temp.next;
        }
        return result;
    }
}
