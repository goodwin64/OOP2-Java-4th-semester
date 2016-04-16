package ua.kpi.fict.oop2.classes.variant03;

import java.util.*;

/**
 * Created by Max Donchenko (https://github.com/goodwin64) on 08.04.2016.
 */
public class Lab7_var03 {
    public static void main(String[] args) {
        /* Via empty constructor */
        Airplane wp1 = new Warplane("Warplane 1", 500, 20.4, 300, 1500, 100);
        Airplane wp2 = new Warplane("Warplane 2", 3000, 270.13, 800, 5000, 500);
        MySet airplanes1 = new MySet();
        airplanes1.add(wp1);
        airplanes1.add(wp2);

        /* Via constructor with Airplane instance */
        Airplane al1 = new Airliner("Airliner 1", 500, 20.4, 300, 1500, 2);
        Airplane al2 = new Airliner("Airliner 2", 3000, 270.13, 800, 5000, 500);
        MySet airplanes2 = new MySet(al1);
        airplanes2.add(al2);
        airplanes2.add(al2); // duplicate, ignore

        /* Via constructor with Airplane collection */
        Airplane af1 = new AirFreighter("AF 1", 500, 20.4, 300, 1500, 700);
        Airplane af2 = new AirFreighter("AF 2", 3000, 270.13, 800, 5000, 5800);
        List<Airplane> airFreightersList = new ArrayList<>(2);
        airFreightersList.add(af1);
        airFreightersList.add(af2);
        MySet airplanes3 = new MySet(airFreightersList);

        System.out.println(airplanes1);
        System.out.println(airplanes2);
        System.out.println(airplanes3);
    }

    /**
     * My own Set implementation based on Double Linked List
     * which is also implemented by me.
     *
     * @author      Max Donchenko (https://github.com/goodwin64)
     * @see         Set
     * @see         Collection
     * @see         DoublyLinkedList
     */
    public static class MySet implements Set, Collection {
        private DoublyLinkedList<Object> value;

        public MySet() {
            this.value = new DoublyLinkedList<>();
        }

        public MySet(Airplane airplane) {
            this.value = new DoublyLinkedList<>();
            add(airplane);
        }

        public MySet(List<Airplane> airplanes) {
            this.value = new DoublyLinkedList<>();
            addAll(airplanes);
        }


        @Override
        public int size() {
            return value.getSize();
        }

        @Override
        public boolean isEmpty() {
            return value.isEmpty();
        }

        @Override
        public boolean contains(Object o) {
            return value.contains(o);
        }

        @Override
        public Iterator iterator() {
            return value.iterator();
        }

        @Override
        public Object[] toArray() {
            Object[] result = new Object[value.getSize()];
            int i = 0;
            for (Object o : value) {
                result[i++] = o;
            }
            return result;
        }

        @Override
        public Object[] toArray(Object[] a) {
            int i = 0;
            if (a.length >= size()) {
                for (Object o : value) {
                    a[i++] = o;
                }
            } else {
                a = new Object[size()];
                for (Object o : value) {
                    a[i++] = o;
                }
            }
            return a;
        }

        @Override
        public boolean add(Object o) {
            if (value.contains(o)) {
                return false;
            } else {
                value.addLast(o);
                return true;
            }
        }

        @Override
        public boolean remove(Object o) {
            return value.remove(o);
        }

        @Override
        public boolean containsAll(Collection c) {
            for (Object o : c) {
                if (!value.contains(o)) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public boolean addAll(Collection c) {
            int sizeBefore, sizeAfter;
            sizeBefore = size();

            for (Object elem : c) {
                this.add(elem);
            }

            sizeAfter = size();
            return sizeAfter - sizeBefore != 0;
        }

        @Override
        public boolean retainAll(Collection c) {
            int sizeBefore, sizeAfter;
            sizeBefore = size();
            MySet tempSet = new MySet();

            for (Object o : c) {
                if (contains(o)) {
                    tempSet.add(o);
                }
            }

            clear();
            addAll(tempSet);
            sizeAfter = tempSet.size();
            return sizeAfter - sizeBefore != 0;
        }

        @Override
        public boolean removeAll(Collection c) {
            int sizeBefore, sizeAfter;
            sizeBefore = size();

            for (Object o : c) {
                remove(o);
            }

            sizeAfter = size();
            return sizeAfter - sizeBefore != 0;
        }

        @Override
        public void clear() {
            this.value = new DoublyLinkedList<>();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            MySet other = (MySet) o;
            for (Object elem : other) {
                if (!contains(elem)) {
                    return false;
                }
            }

            if (size() != other.size()) {
                return false;
            }

            return true;
        }

        @Override
        public String toString() {
            String result = "{";
            Iterator it = iterator();

            result += it.next();
            while (it.hasNext()) {
                result += ", " + it.next();
            }

            result += "}";
            return result;
        }
    }

    public static class DoublyLinkedList<E> implements Iterable<E> {
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

        /**
         * Removes an element from the list.
         * @param e     the element to remove
         */
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
}
