package ua.kpi.fict.oop2.classes.variant03;

import java.util.NoSuchElementException;
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
        dll.iterateForward();
        dll.removeFirst();
        dll.removeLast();
        dll.iterateBackward();
        System.out.println("DLL size: " + dll.getSize());
        System.out.printf("Is %d in list: %s\n", numberToFind, dll.contains(34));
    }

    public abstract class MySet implements Set {
        private int size = 0;
        private DoublyLinkedList value;
    }
}

class DoublyLinkedList<E> {
    private Node head;
    private Node tail;
    private int size;

    public DoublyLinkedList() {
        size = 0;
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

    /**
     * Walks forward through the linked list and prints elements.
     */
    public void iterateForward() {
        System.out.println("Iterating forward:");
        Node temp = this.head;
        if (temp != null) {
            System.out.print(temp.element);
            temp = temp.next;
        }
        while (temp != null) {
            System.out.printf(" -> %s", temp.element);
            temp = temp.next;
        }
        System.out.println();
    }

    /**
     * Walks backward through the linked list and prints elements.
     */
    public void iterateBackward() {
        System.out.println("Iterating backward:");
        Node temp = this.tail;
        if (temp != null) {
            System.out.print(temp.element);
            temp = temp.prev;
        }
        while (temp != null) {
            System.out.printf(" <- %s", temp.element);
            temp = temp.prev;
        }
        System.out.println();
    }

    /**
     * Removes element from the start of the linked list.
     */
    public E removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Node temp = this.head;
        head = head.next;
        head.prev = null;
        size--;
        return temp.element;
    }

    /**
     * Removes element from the end of the linked list.
     */
    public E removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Node temp = this.tail;
        tail = tail.prev;
        tail.next = null;
        size--;
        return temp.element;
    }

    /**
     * Returns whether the list contains the element.
     * @param element      a sought-for element
     */
    public boolean contains(E element) {
        Node temp = this.head;
        while (temp != null) {
            if (temp.element == element) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }
}
