package ua.kpi.fict.oop2.tests.variant12;

import org.junit.Assert;
import org.junit.Test;
import ua.kpi.fict.oop2.classes.variant12.Lab6_var12;

import java.util.Random;

import static ua.kpi.fict.oop2.classes.variant12.Lab6_var12.createTrackList;
import static ua.kpi.fict.oop2.classes.variant12.Lab6_var12.getPrettyDuration;
import static ua.kpi.fict.oop2.classes.variant12.Lab6_var12.parseDuration;

/**
 * Created by Rock(https://github.com/Filin-Rock) on 12.05.2016.
 */
public class Lab6_var12Test {
    @Test
    public void createTrackListTest() {
        Lab6_var12.Music[] compositions = createTrackList();
        /*
        compositions[5] = new Rock("When the musick's over", "The Doors", 658);
        compositions[9] = new Classic("Requiem","Mozart",3314);
        */

        Assert.assertEquals("The Doors", compositions[5].getAuthor());
        Assert.assertEquals(3314, compositions[9].getDuration());
    }

    @Test(expected=IllegalArgumentException.class)
    public void getPrettyDurationTestWithException() {
        getPrettyDuration(-50);
    }

    @Test
    public void getPrettyDurationTestWithoutException() {
        Assert.assertEquals("00:00:00", getPrettyDuration(0));
        Assert.assertEquals("00:00:01", getPrettyDuration(1));
        Assert.assertEquals("00:00:59", getPrettyDuration(59));
        Assert.assertEquals("00:01:00", getPrettyDuration(60));
        Assert.assertEquals("02:01:33", getPrettyDuration(7293));
        Assert.assertEquals("23:00:13", getPrettyDuration(82813));
    }

    @Test
    public void testParseDuration() {
        Random random = new Random();
        int randint = random.nextInt(86400);
        Assert.assertEquals(randint, parseDuration(getPrettyDuration(randint)));
    }
}