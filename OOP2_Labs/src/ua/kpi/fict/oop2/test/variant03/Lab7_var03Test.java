package ua.kpi.fict.oop2.test.variant03;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.kpi.fict.oop2.classes.variant03.Lab7_var03;

import java.util.Collections;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by Max Donchenko (https://github.com/goodwin64) on 09.05.2016.
 */
public class Lab7_var03Test {
    Set mySet;

    @Before
    public void setMySet() {
        mySet = new Lab7_var03.MySet();
    }

    @Test
    public void checkSetSize() {
        assertEquals(0, mySet.size());
        assertEquals(true, mySet.isEmpty());

        Collections.addAll(mySet, 1, 2, 3);
        assertEquals(3, mySet.size());
        assertEquals(false, mySet.isEmpty());
    }

    @After
    public void tearDown() {
        mySet = null;
    }
}
