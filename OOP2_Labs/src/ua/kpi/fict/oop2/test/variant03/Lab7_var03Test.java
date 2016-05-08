package ua.kpi.fict.oop2.test.variant03;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.kpi.fict.oop2.classes.variant03.Lab7_var03;

import java.util.Collections;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Max Donchenko (https://github.com/goodwin64) on 09.05.2016.
 */
public class Lab7_var03Test {
    private Lab7_var03.MySet mySet;
    private Lab7_var03.MySet anotherSet;

    @Before
    public void setMySet() {
        mySet = new Lab7_var03.MySet();
        anotherSet = new Lab7_var03.MySet();
    }

    @Test
    public void sizeTest() {
        assertEquals(0, mySet.size());
        assertEquals(true, mySet.isEmpty());

        Collections.addAll(mySet, 1, 2, 3, 3, 2, 1, 1, 1, 2);
        assertEquals(3, mySet.size());
        assertEquals(false, mySet.isEmpty());
    }

    @Test
    public void toArrayTest() {
        Collections.addAll(mySet, 1, 2, 3);
        Integer[] sample = new Integer[] {1, 2, 3};

        assertArrayEquals(sample, mySet.toArray());
    }

    @Test
    public void addAndRemoveTest() {
        Collections.addAll(mySet, 1, 2, 3);
        Collections.addAll(anotherSet, 1, 3, 4);
        mySet.add(4);
        mySet.remove(2);

        assertTrue(anotherSet.equals(mySet));
        assertEquals(anotherSet, mySet.toArray()); // fail, NEED FIX
    }

    @Test
    public void retainAllTest() {
        Collections.addAll(mySet, 1, 4, 3);
        Collections.addAll(anotherSet, 9, 8, 7, 6, 5, 4, 3, 2);

        Lab7_var03.MySet differenceSet = new Lab7_var03.MySet();
        Collections.addAll(differenceSet, 4, 3);

        boolean changed = mySet.retainAll(anotherSet);
        assertTrue(changed);
        assertEquals(mySet, differenceSet);
    }

    @After
    public void tearDown() {
        mySet = null;
        anotherSet = null;
    }
}
