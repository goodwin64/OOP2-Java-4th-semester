package ua.kpi.fict.oop2.classes.variant12.lab7;

import ua.kpi.fict.oop2.classes.variant12.Lab6_var12.Music;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Max Donchenko (https://github.com/goodwin64) on 22.05.2016.
 */
public class SuperPuperMegaDuperGyperCyberListTurboPlus3000<T> implements List, Collection {
    private DLList<T> base;

    public SuperPuperMegaDuperGyperCyberListTurboPlus3000() {
        base = new DLList<>();
    }

    public SuperPuperMegaDuperGyperCyberListTurboPlus3000(DLList<T> album) {
        base = album;
    }

    public SuperPuperMegaDuperGyperCyberListTurboPlus3000(T[] a) {
        base = new DLList<>();
        for (T t : a) {
            base.addLast(t);
        }
    }

    @Override
    public int size() {
        return base.getSize();
    }

    @Override
    public boolean isEmpty() {
        return base.getSize() == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (o.getClass().equals(Music.class)) {
            return base.contains((T) o);
        }
        return false;
    }

    @Override
    public Iterator iterator() {
        return base.iterator();
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[base.getSize()];
        int i = 0;
        for (Object o : base) {
            result[i++] = o;
        }
        return result;
    }

    @Override
    public Object[] toArray(Object[] a) {
        int i = 0;
        if (a.length >= size()) {
            for (Object o : base) {
                a[i++] = o;
            }
        } else {
            a = new Object[size()];
            for (Object o : base) {
                a[i++] = o;
            }
        }
        return a;
    }

    @Override
    public boolean add(Object o) {
        if (o.getClass().equals(Music.class)) {
            base.addLast((T) o);
            return true;
        }
        return false;
    }

    @Override
    public void add(int index, Object element) {
        base.add(index, (T) element);
    }

    @Override
    public boolean addAll(Collection c) {
        int sizeBefore = size();
        for (Object o : c) {
            base.addLast((T) o);
        }
        int sizeAfter = size();
        return sizeAfter - sizeBefore != 0;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        int sizeBefore = size();
        for (Object o : c) {
            base.addLast((T) o);
        }
        int sizeAfter = size();
        return sizeAfter - sizeBefore != 0;
    }

    @Override
    public void clear() {
        base = new DLList<>();
    }

    @Override
    public Object get(int index) throws IndexOutOfBoundsException {
        /*
        * negative indices are supported
        */
        if (index >= size() || index < size() * -1) {
            throw new IndexOutOfBoundsException("Wrong index: " + index + ", size=" + size());
        }
        if (index < 0) {
            index = (-1 * index) - 1;
        }
        if (index > size() / 2) {
            return base.getFromTail(size() - index - 1);
        } else {
            return base.getFromHead(index);
        }
    }

    @Override
    public Object set(int index, Object element) throws IndexOutOfBoundsException {
        if (index >= size() || index < 0) {
            throw new IndexOutOfBoundsException("Wrong index: " + index + ", size=" + size());
        }
        if (index < 0) {
            index = (-1 * index) - 1;
        }
        if (index > size() / 2) {
            return base.setFromTail(size() - index - 1, (T) element);
        } else {
            return base.setFromHead(index, (T) element);
        }
    }

    @Override
    public Object remove(int index) {
        if (index >= size() || index < 0) {
            throw new IndexOutOfBoundsException("Wrong index: " + index + ", size=" + size());
        }
        if (index < 0) {
            index = (-1 * index) - 1;
        }
        if (index > size() / 2) {
            return base.removeFromTail(size() - index - 1);
        } else {
            return base.removeFromHead(index);
        }
    }

    @Override
    public boolean remove(Object o) {
        if (o.getClass().equals(Music.class)) {
            base.remove((T) o);
            return true;
        }
        return false;
    }

    @Override
    public int indexOf(Object o) {
        int i = 0;
        for (T t : base) {
            if (t.equals(o)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = base.getSize() - 1; i >= 0; i--) {
            if (get(i).equals(o)) {
                return i;
            }
        }
        return -1;
    }

    // TODO: 23.05.2016 Implementation
    @Override
    public ListIterator listIterator() {
        return new ListIterator() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public Object next() {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public Object previous() {
                return null;
            }

            @Override
            public int nextIndex() {
                return 0;
            }

            @Override
            public int previousIndex() {
                return 0;
            }

            @Override
            public void remove() {

            }

            @Override
            public void set(Object o) {

            }

            @Override
            public void add(Object o) {

            }
        };
    }

    // TODO: 23.05.2016 Implementation
    @Override
    public ListIterator listIterator(int index) {
        // TODO: 23.05.2016 check correctness
        return (ListIterator) base.iterator();
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        List result = new SuperPuperMegaDuperGyperCyberListTurboPlus3000<>();
        for (int i = fromIndex; i < toIndex; i++) {
            // TODO: 23.05.2016 improve algo, fix different types
            result.add(get(i));
        }
        return result;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        int sizeBefore = size();
        for (Object o : c) {
            remove(o);
        }
        int sizeAfter = size();
        return sizeAfter - sizeBefore != 0;
    }

    @Override
    public boolean containsAll(Collection c) {
        for (Object o : c) {
            if (indexOf(o) != -1) {
                return false;
            }
        }
        return true;
    }



    @Override
    public String toString() {
        String result = "List:\t[\n";
        for (T t : base) {
            result += "\t\t" + t + ",\n";
        }
        return result + "]";
    }
}
