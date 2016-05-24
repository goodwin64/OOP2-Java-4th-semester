package ua.kpi.fict.oop2.tests.variant12;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.kpi.fict.oop2.classes.variant12.Lab6_var12;
import ua.kpi.fict.oop2.classes.variant12.lab7.SuperPuperMegaDuperGyperCyberListTurboPlus3000;

/**
 * Created by Max Donchenko (https://github.com/goodwin64) on 23.05.2016.
 */
public class Lab7_var12Test {
    Lab6_var12.Music[] album;
    SuperPuperMegaDuperGyperCyberListTurboPlus3000<Lab6_var12.Music> trackList;

    @Before
    public void setUp() {
        album = new Lab6_var12.Music[]{
                new Lab6_var12.Rock("Rock1", "Author1", 1),
                new Lab6_var12.Rock("Rock2", "Author2", 1),
                new Lab6_var12.Classic("Classic1", "Author3", 1),
                new Lab6_var12.Classic("Classic2", "Author4", 1),
                new Lab6_var12.Disco_80("Disco1", "Author5", 1),
                new Lab6_var12.Disco_80("Disco2", "Author6", 1),
                new Lab6_var12.Rock("Rock1", "Author1", 1)
        };
        trackList = new SuperPuperMegaDuperGyperCyberListTurboPlus3000<>(album);
    }

    @Test
    public void testListOperations() {

    }

    @Test
    public void testIndexActions() {
        Assert.assertEquals(album[0], trackList.get(0));
        Assert.assertEquals(album[0], trackList.get(-1));
        Assert.assertEquals(
                trackList.set(2, trackList.get(2)),
                trackList.remove(2)
        );
        Assert.assertEquals(0, trackList.indexOf(
                new Lab6_var12.Rock("Rock1", "Author1", 1)
        ));
        Assert.assertEquals(6, trackList.lastIndexOf(
                new Lab6_var12.Rock("Rock1", "Author1", 1)
        ));


        Assert.assertEquals(album.length, trackList.size());
        trackList.clear();
        Assert.assertEquals(0, trackList.size());
        Assert.assertTrue(trackList.isEmpty());

    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexActionsWithException() {
        trackList.get(trackList.size());
        trackList.get(-1 - trackList.size());
    }

    @Test
    public void testMusicActions() {
        Assert.assertNotEquals(
                new Lab6_var12.Music("Rock1", "Author1", 1),
                new Lab6_var12.Rock("Rock1", "Author1", 1)
        ); // different classes
        Assert.assertEquals(
                trackList.size(),
                Lab6_var12.getAlbumDuration(album)
        );
    }

    @Test(expected = MusicException.class)
    public void testOwnException() throws MusicException {
        album[2].setDuration(-5);
    }

    @After
    public void tearDown() throws Exception {
        album = null;
        trackList = null;
    }
}