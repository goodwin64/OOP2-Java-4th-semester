package ua.kpi.fict.oop2.classes.variant03;

import java.util.Set;

/**
 * Created by Max Donchenko (https://github.com/goodwin64) on 08.04.2016.
 */
public class Lab7_var03 {
    public static void main(String[] args) {
        DoublyLinkedList dll = new DoublyLinkedList<>();
        System.out.println(dll.isEmpty()); // true
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
    public int size() {
        return size;
    }

    /**
     * return whether the list is empty or not
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Adds element at the start of the linked list.
     * @param element
     */
    public void addFirst(E element) {

    }

    /**
     * Adds element at the end of the linked list.
     * @param element
     */
    public void addLast(E element) {

    }

    /**
     * this method walks forward through the linked list
     */
    public void iterateForward() {

    }

    /**
     * this method walks backward through the linked list
     */
    public void iterateBackward() {

    }

    /**
     * this method removes element from the start of the linked list
     */
    public E removeFirst() {
        return null;
    }

    /**
     * this method removes element from the end of the linked list
     */
    public E removeLast() {
        return null;
    }
}
