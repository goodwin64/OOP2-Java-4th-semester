package ua.kpi.fict.oop2.classes.variant12;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Rock(https://github.com/Filin-Rock) on 04.04.2016.
 *
 */
public class Lab6_var12 {

    public static void main(String[] args) {
        Music[] compositions = createTrackList();
        shuffleArray(compositions);

        printHeader("null");
        printTrackList(compositions);

        printHeader("ID");
        Arrays.sort(compositions, new SortedByID());
        printTrackList(compositions);

        printHeader("Длительности");
        Arrays.sort(compositions, new SortedByDuration());
        printTrackList(compositions);

        printHeader("Названию");
        Arrays.sort(compositions, new SortedByTitle());
        printTrackList(compositions);

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nУкажите минимальную продолжительность для поиска: ");
        String minDuration = scanner.next();

        System.out.print("Укажите максимальную продолжительность для поиска: ");
        String maxDuration = scanner.next();

        Music[] filteredCompositions = searchByTrackLength(
                parseDuration(minDuration),
                parseDuration(maxDuration),
                compositions
        );
        System.out.printf("\nFilter: from %s to %s\n", minDuration, maxDuration);
        printTrackList(filteredCompositions);
    }

    /**
     * Parses the string which contains duration of a music composition to an integer number,
     * count of seconds:
     * 0:02:12 -> 132
     */
    public static int parseDuration(String compDuration) {
        String[] timeParameters = compDuration.split(":");
        int hours = Integer.parseInt(timeParameters[0]);
        int minutes = Integer.parseInt(timeParameters[1]);
        int seconds = Integer.parseInt(timeParameters[2]);
        return hours * 3600 + minutes * 60 + seconds;
    }

    //Print header
    public static void printHeader(String criteria) {
        if (!Objects.equals(criteria, "null")) {
            System.out.printf("\n==================== %s %s =====================\n", "Cортировано по", criteria);
        } else {
            System.out.printf("\n================== %s ===================\n", "Не сортировано");
        }
        System.out.printf("%-2s %-16s %-24s %-9s %-8s\n", "ID", "Автор", "Композиция", "Дли.-сть", "Жанр");
    }

    //Create Track list
    public static Music[] createTrackList() {
        Music[] musics = new Music[16];

        musics[0] = new Rock("Thunderstruck", "AC/DC", 293);
        musics[1] = new Rock("Knockin on heavens door","Guns & Roses", 334);
        musics[2] = new Rock("Stair way to heaven", "Led Zeppelin", 481);
        musics[3] = new Rock("Wish you were here", "Pink Floyd", 306);
        musics[4] = new Rock("Rock it", "Queen", 272);
        musics[5] = new Rock("When the musick's over", "The Doors", 658);

        musics[6] = new Classic("Moonlight Sonata", "Beethoven", 899);
        musics[7] = new Classic("Complete Nocturnes", "Chopin", 6798);
        musics[8] = new Classic("Consolation", "List", 185);
        musics[9] = new Classic("Requiem","Mozart",3314);
        musics[10] = new Classic("Four Seasons", "Vivaldi", 2519);

        musics[11] = new Disco_80("Sexual revolution","Army of lovers", 235);
        musics[12] = new Disco_80("Cause you are Young", "CC Catch", 194);
        musics[13] = new Disco_80("Daddy cool", "Boney M", 208);
        musics[14] = new Disco_80("Cheri lady", "Modern Talking", 197);
        musics[15] = new Disco_80("Simply the best", "Tina Turner", 280);

        return musics;
    }

    //TODO:Switch sorted track list

    //TODO:Some sort of polymorphism

    // Shuffle Track List
    public static void shuffleArray(Music[] arr) {

        Random rnd = ThreadLocalRandom.current();
        for (int i = arr.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);

            Music a = arr[index];
            arr[index] = arr[i];
            arr[i] = a;
        }
    }

    //convert seconds to hours and minutes
    public static String getPrettyDuration(int duration) throws IllegalArgumentException {
        int seconds, minutes, hours;

        if (duration < 0) {
            throw new IllegalArgumentException("Negative duration!");
        }
        seconds = duration % 60;
        duration -= seconds;

        minutes = duration % 3600; // in seconds (x60)
        duration -= minutes;
        minutes /= 60;

        hours = duration / 3600;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    //prints formed track list
    public static void printTrackList(Music[] compositions) {
        for (Music composition : compositions) {
            System.out.println(composition);
        }
    }

    // Sort by ID
    static class SortedByID implements Comparator<Music> {

        public int compare(Music obj1, Music obj2) {

            int var1 = obj1.getID();
            int var2 = obj2.getID();

            return var1 - var2;
        }
    }

    // Sort by duration
    static class SortedByDuration implements Comparator<Music> {

        public int compare(Music obj1, Music obj2) {

            int var1 = obj1.getDuration();
            int var2 = obj2.getDuration();

            return var1 - var2;
        }
    }

    // Sort by title
    static class SortedByTitle implements Comparator<Music> {

        public int compare(Music obj1, Music obj2) {

            String str1 = obj1.getTitle();
            String str2 = obj2.getTitle();

            return str1.compareTo(str2);
        }
    }

    // Search in track list
    public static Music[] searchByTrackLength(int minDuration, int maxDuration, Music[] src) {
        ArrayList<Music> result = new ArrayList<>(src.length);
        for (int i = 0; i < src.length; i++) {
            if (src[i].getDuration() > minDuration && src[i].getDuration() < maxDuration) {
                result.add(src[i]); // TODO: 20.05.2016 COPY?
            }
        }
        return result.toArray(new Music[]{});
    }

    //Parent class Music
    public static class Music {

        private int ID;
        private static int maxID = 1;
        private String author;
        private String title;
        private int duration;

        public Music() {
            this.ID = maxID;
            maxID++;
            setTitle("");
            setAuthor("");
            setDuration(new Random().nextInt(5000));
        }
        public Music(String title, String author, int duration) {
            this.ID = maxID;
            maxID++;
            setTitle(title);
            setAuthor(author);
            setDuration(duration);
        }


        public int getID() {
            return ID;
        }


        public String getAuthor() {
            return author;
        }
        public void setAuthor(String author) {
            this.author = author;
        }


        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }


        public int getDuration() {
            return duration;
        }
        public void setDuration(int duration) {
            this.duration = duration;
        }


        @Override
        public String toString() {
            String className = this.getClass().getName();
            className = className.substring(className.lastIndexOf('$') + 1);
            String content = String.format("%-2d %-16s %-23s %-10s %s",
                    getID(), getAuthor(), getTitle(), getPrettyDuration(getDuration()), className);
            return content;
        }
    }

    //extends music class rock
    public static class Rock extends Music {

        public Rock() {
            super();
        }
        public Rock(String title, String author, int duration) {
            super(title, author, duration);
        }
    }

    //extends music class classic
    public static class Classic extends Music {

        public Classic() {
            super();
        }
        public Classic(String title, String author, int duration) {super(title, author, duration); }
    }

    //extends music class disco 80
    public static class Disco_80 extends Music {

        public Disco_80() {
            super();
        }
        public Disco_80(String title, String author, int duration) {super(title, author, duration); }

    }

}
