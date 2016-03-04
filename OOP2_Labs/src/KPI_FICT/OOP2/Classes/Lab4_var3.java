package KPI_FICT.OOP2.Classes;

/**
 * Created by Max Donchenko (https://github.com/goodwin64) on 03.03.2016.
 */
public class Lab4_var3 {

    public static void main(String[] args) {
        System.out.println(Name.getRandName(true));
        System.out.println(Name.getRandName(false));
        System.out.println(Name.getRandFullName(true));
        System.out.println(Name.getRandFullName(false));
    }
}

class Student {

    private String name;
    private String surname;
    private boolean isMale;
    private int age;
    private double scholarship;
    private int[] marks;

    protected double basicScholarship = 500;

    public Student() {}

    public double getAverageMark() {
        return 0;
    }
    public void recalcScholarship() {

    }
}

/**
 * Methods to generate a random name, surname.
 * Particularly, for the "Student" objects.
 *
 * @topMaleNames        the most popular male names in the US in 2014
 * @topFemaleNames      the most popular female names in the US in 2014
 * @topSurnames         the most popular surnames in the US in 2014
 */
class Name {
    /*
     * There is no need to create a polymorphic method getRandName()
     * without arguments because it has no practical importance.
     * This method will be called in context with "Student" object,
     * which always has a concrete attribute "isMale".
     */

    private static String[] topMaleNames = {
            "Noah",           "Liam",           "Mason",          "Jacob",          "William",
            "Ethan",          "Michael",        "Alexander",      "James",          "Daniel",
            "Elijah",         "Benjamin",       "Logan",          "Aiden",          "Jayden",
            "Matthew",        "Jackson",        "David",          "Lucas",          "Joseph"
    };
    private static String[] topFemaleNames = {
            "Emma",           "Olivia",         "Sophia",         "Isabella",		"Ava",
            "Mia",            "Emily",          "Abigail",        "Madison",		"Charlotte",
            "Harper",         "Sofia",          "Avery",          "Elizabeth",	    "Amelia",
            "Evelyn",         "Ella",           "Chloe",          "Victoria",		"Aubrey"
    };
    private static String[] topSurnames = {
            "SMITH",          "JOHNSON",        "WILLIAMS",       "JONES",          "BROWN",
            "DAVIS",          "MILLER",         "WILSON",         "MOORE",          "TAYLOR",
            "ANDERSON",       "THOMAS",         "JACKSON",        "WHITE",          "HARRIS",
            "MARTIN",         "THOMPSON",       "GARCIA",         "MARTINEZ",       "ROBINSON",
            "CLARK",          "RODRIGUEZ",      "LEWIS",          "LEE",            "WALKER",
            "HALL",           "ALLEN",          "YOUNG",          "HERNANDEZ",      "KING",
            "WRIGHT",         "LOPEZ",          "HILL",           "SCOTT",          "GREEN",
            "ADAMS",          "BAKER",          "GONZALEZ",       "NELSON",         "CARTER",
            "MITCHELL",       "PEREZ",          "ROBERTS",        "TURNER",         "PHILLIPS",
            "CAMPBELL",       "PARKER",         "EVANS",          "EDWARDS",        "COLLINS"
    };

    public static String getRandName(boolean isMale) {
        /*
         * Generates the random int index from 0 to names.length (not inclusive)
         * and get the element by this index.
         */

        String name;
        int randIndex = (int) (Math.random() * topMaleNames.length);

        if (isMale) {
            name = topMaleNames[randIndex];
        } else {
            name = topFemaleNames[randIndex];
        }
        return name;
    }
    public static String getRandSurname() {
        int randIndex = (int) (Math.random() * topSurnames.length);
        return topSurnames[randIndex];
    }
    public static String getRandFullName(boolean isMale) {
        return String.format("%s %s", getRandName(isMale), getRandSurname());
    }
}
