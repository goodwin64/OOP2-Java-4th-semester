package ua.kpi.fict.oop2.classes.variant03;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by Max Donchenko (https://github.com/goodwin64) on 08.04.2016.
 */
public class Lab7_var03 {
    public static void main(String[] args) {
        DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();
        System.out.println("DLL is empty: " + dll.isEmpty());
        dll.addFirst(10);
        dll.addFirst(34);
        dll.addLast(56);
        dll.addLast(364);
        int numberToFind = 34;
        System.out.printf("Is %d in list: %s\n", numberToFind, dll.contains(34));
        System.out.println("DLL size: " + dll.getSize());
        System.out.printf("Is %d in list: %s\n", numberToFind, dll.contains(34));
    }

    public abstract class MySet implements Set {
        private int size = 0;
        private DoublyLinkedList value;
    }
}

class DoublyLinkedList<E> implements Iterable<E> {
    private Node head;
    private Node tail;
    private int size;

    public DoublyLinkedList() {
        size = 0;
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

        public boolean equals(Node other) {
            return this.element.equals(other.element);
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

    public boolean remove(E e) {
        if (head.element.equals(e)) {
            head.next.prev = null;
            head = head.next;
            size--;
            return true;
        } else if (tail.element.equals(e)) {
            tail.prev.next = null;
            tail = tail.prev;
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

