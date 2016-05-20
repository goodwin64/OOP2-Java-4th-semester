package ua.kpi.fict.oop2.tests.variant03;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.kpi.fict.oop2.classes.variant03.Airliner;
import ua.kpi.fict.oop2.classes.variant03.Airplane;
import ua.kpi.fict.oop2.classes.variant03.Lab6_var03;
import ua.kpi.fict.oop2.classes.variant03.Lab7_var03;

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
    public void sameAirplanesTest() {
        Airplane airplane1 = new Airplane("Model", 2000, 54.5, 500, 3000);
        Airplane airplane2 = new Airplane("Model", 2000, 54.5, 500, 3000);

        // difference is only in ID
        Assert.assertEquals(airplane1, airplane2);
    }

    @Test
    public void comparingAirplanesTest() {
        Airplane airplane1 = new Airplane("Model", 2000, 54.5, 500, 3000);
        Airplane airplane2 = new Airplane("Model", 2000, 54.5, 500, 3000);
        Airplane airplane3 = new Airplane("Another Model", 1000, 34.5, 200, 300);

        Assert.assertEquals(0, airplane1.compareTo(airplane2));
        Assert.assertEquals(1, airplane1.compareTo(airplane3)); // by fuel consumption
    }

    @Test
    public void createAirplanesBasedOnPrototypesTest() {
        Airplane[] airplanes1 = Lab6_var03.createAirplanesBasedOnPrototypes();
        Airplane[] airplanes2 = Lab6_var03.createAirplanesBasedOnPrototypes();
        mySet = new Lab7_var03.MySet(airplanes1);
        anotherSet = new Lab7_var03.MySet(airplanes2);

        // duplicate will be ignored:
        mySet.add(new Airliner("Cessna 172R", 767, 28.47, 226, 1289, 4));

        Assert.assertArrayEquals(airplanes1, airplanes2);
        Assert.assertEquals(mySet, anotherSet);
    }

    @After
    public void tearDown() {
        mySet = null;
        anotherSet = null;
    }
}
