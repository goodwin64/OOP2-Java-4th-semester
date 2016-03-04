package KPI_FICT.OOP2.Classes;

/**
 * Created by Max Donchenko (https://github.com/goodwin64) on 03.03.2016.
 */
public class Lab4_var3 {
    
    public static void main(String[] args) {

    }
}

class Student {

    private String name;
    private String surname;
    private int age;
    private double scolarship;
    private int[] marks;

    public Student() {}

    public double getAverageMark(int[] marks) {
        return 0;
    }

    public double calcScholarship(double averMark) {
        return 0;
    }
}

class Name {

    private static String[] topMaleNames = {};
    private static String[] topFemaleNames = {};

    public static String getRandName() {
        return "";
    }

    public static String getRandSurname() {
        return "";
    }

    public static String getRandFullname() {
        return getRandName() + getRandSurname();
    }
}
