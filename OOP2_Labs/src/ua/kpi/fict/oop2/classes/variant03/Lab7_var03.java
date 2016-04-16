package ua.kpi.fict.oop2.classes.variant03;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Max Donchenko (https://github.com/goodwin64) on 08.04.2016.
 */
public class Lab7_var03 {
    public static void main(String[] args) {
        Set<Integer> ms1 = new MySet();
        Set<Integer> ms2 = new MySet();
        MySet ms3 = new MySet();
        MySet ms4 = new MySet();

        Integer[] even = {0, 2, 4, 6, 8};
        Integer[] odd = {1, 3, 5, 7, 9};
        Integer[] oneDigit = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Integer[] sixToTwelve = {6, 7, 8, 9, 10, 11, 12};

        ms1.addAll(Arrays.asList(even));
        ms2.addAll(Arrays.asList(odd));
        ms3.addAll(Arrays.asList(oneDigit));
        ms4.addAll(Arrays.asList(sixToTwelve));

        System.out.println(ms1);                // {0, 2, 4, 6, 8}
        System.out.println(ms2);                // {1, 3, 5, 7, 9}
        System.out.println(ms3);                // {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}
        System.out.println(ms4);                // {6, 7, 8, 9, 10, 11, 12}

        /* There is used the next construction below:
         * (Integer[]) mySet.toArray(new Integer[mySet.size()])
         * instead of
         * (Integer[]) mySet.toArray() // ClassCastException
         * because the second action returns the Object[] array and it cannot been casted.
         *
         * So:
         * 1) make an array sixToTwelve = {6, 7, 8, 9, 10, 11, 12};
         * 2) make a set based on this array;
         * 3) make an array based on this set
         * 4) check whether these arrays are equals (yes, they are equals)
         */
        Integer[] sixToTwelveFromSet = (Integer[]) ms4.toArray(new Integer[ms4.size()]);
        System.out.println(Arrays.equals(sixToTwelve, sixToTwelveFromSet)); // true

        System.out.println(ms3.removeAll(ms2)); // true
        System.out.println(ms3.removeAll(ms2)); // false
        System.out.println(ms3);                // {0, 2, 4, 6, 8}

        System.out.println(ms3.equals(ms1));    // true

        System.out.println(ms3.retainAll(ms4)); // true
        System.out.println(ms3);                // {6, 8}

        ms3.clear();
        System.out.println(ms3.isEmpty());      // true
        System.out.println(ms3.size());         // 0
    }

    public static class MySet implements Set, Collection {
        private DoublyLinkedList<Object> value;

        public MySet() {
            this.value = new DoublyLinkedList<>();
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
}
