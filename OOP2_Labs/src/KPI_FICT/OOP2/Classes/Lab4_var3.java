package KPI_FICT.OOP2.Classes;

/**
 * Created by Max Donchenko (https://github.com/goodwin64) on 03.03.2016.
 */
public class Lab4_var3 {

    public static void main(String[] args) {
        Student st1 = new Student(true, "Max");
        Student st2 = new Student(true, "Max Donchenko");
        Student st3 = new Student(true, "Rauf-San Ibn-Al-Hattab-Al-Dzhazir");
        Student st4 = new Student();

        Student rst1 = new Student(true);
        Student rst2 = new Student(false);

        System.out.printf("\t%-16.25s\t %s\t %s%n", "Name", "Age", "Scholarship");
        System.out.printf("%-20.25s\t %d\t %11.2f%n", st1.getFullName(), st1.getAge(), st1.getScholarship());

        st1.addMarks(20);
        st1.recalcScholarship();
        st1.printMarks();
        System.out.printf("%-20.25s\t %d\t %11.2f%n", st1.getFullName(), st1.getAge(), st1.getScholarship());

        st1.addMarks(20, 94, 96);
        st1.recalcScholarship();
        st1.printMarks();
        System.out.printf("%-20.25s\t %d\t %11.2f%n", st1.getFullName(), st1.getAge(), st1.getScholarship());


        System.out.printf("%-20.25s\t %d\t %11.2f%n", st2.getFullName(), st2.getAge(), st2.getScholarship());
        System.out.printf("%-20.20s\t %d\t %11.2f%n", st3.getFullName(), st3.getAge(), st3.getScholarship());
        System.out.printf("%-20.25s\t %d\t %11.2f%n", st4.getFullName(), st4.getAge(), st4.getScholarship());

        System.out.printf("%-20.25s\t %d\t %11.2f%n", rst1.getFullName(), rst1.getAge(), rst1.getScholarship());
        System.out.printf("%-20.25s\t %d\t %11.2f%n", rst2.getFullName(), rst2.getAge(), rst2.getScholarship());
    }
}

class Student {

    private String name;
    private String surname;
    private boolean isMale;
    private int age;
    private double scholarship;
    private int[] marks = new int[50];

    protected double basicScholarship = 500;
    public int minAge = 16;
    public int maxAge = 60;


    public Student() {
        this.isMale = Math.random() < 0.5;
        this.name = Name.getRandName(isMale);
        this.surname = Name.getRandSurname();
        this.age = Lab2_var3.getRandInt(minAge, maxAge);
        this.scholarship = basicScholarship;
    }
    public Student(boolean isMale) {
        this.isMale = isMale;
        String fullName = Name.getRandFullName(isMale);
        String[] parts = fullName.split(" ");
        this.name = parts[0];
        this.surname = parts.length == 2 ? parts[1] : "";
        this.age = Lab2_var3.getRandInt(minAge, maxAge);
        this.scholarship = basicScholarship;
    }
    public Student(boolean isMale, String fullName) {
        this.isMale = isMale;
        String[] parts = fullName.split(" ");
        this.name = parts[0];
        this.surname = parts.length == 2 ? parts[1] : "";
        this.age = Lab2_var3.getRandInt(minAge, maxAge);
        this.scholarship = basicScholarship;
    }

    public char getGender() {
        return (isMale == true ? 'M' : 'F');
    }
    public void setGender(boolean isMale) {
        this.isMale = isMale;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }


    public String getFullName() {
        return String.format("%s %s", this.getName(), this.getSurname());
    }

    public double getAverageMark() {
        double sum = 0;
        int count = 0;

        for (int mark : marks) {
            if (mark > 0) {
                sum += mark;
                count++;
            }
        }
        return sum / count;
    }

    public double getScholarship() {
        return scholarship;
    }

    /**
     * Sets the scholarship attr based on the average mark value.
     * It depends will be Student receiving the scholarship for the next semester.
     * Also there are different bonuses for good study results.
     */
    public void recalcScholarship() {
        double averMark = getAverageMark();
        double factor = 1;
        boolean bonusLast10Marks = true;

        /*
         * Abolition the scholarship if an estimate is insufficient.
         * Or bonus 100% for an excellent average mark (95+).
         */
        if (averMark < 75) {
            factor = 0;
        } else if (averMark >= 95) {
            factor *= 2;
        }

        /*
         * Bonus 10% for a series of last 5 excellent estimates.
         * Minimum mark is 1 so there is a comparing of the mark: g.t. 0.
         */
        int lastIndex = marks.length - 1;
        for (int i = lastIndex; i > lastIndex - 5; i--) {
            if (marks[i] > 0 && marks[i] < 95) {
                bonusLast10Marks = false;
                break;
            }
        }

        scholarship = basicScholarship * factor * (bonusLast10Marks ? 1.1 : 1);
    }

    /**
     * Adds a certain amount of random marks.
     *
     * @param count     amount of marks to add
     */
    public void addMarks(int count) {
        for (int i = 0; i < count; i++) {
            if (i < marks.length) {
                marks[i] = Lab2_var3.getRandInt(50, 100);
            }
        }
    }

    /**
     * Adds a certain amount of random marks from a certain range.
     *
     * @param count     amount of marks to add
     * @param minMark   minimum value of random mark (gr.t. 0)
     * @param maxMark   maximum value of random mark (ls.t. 101)
     */
    public void addMarks(int count, int minMark, int maxMark) {
        try {
            if (minMark <= 0 || maxMark > 100) {
                throw new Exception("Wrong limit value");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        if (minMark > maxMark) {
            Lab1.swap(new int[] {minMark},
                      new int[] {maxMark});
        }
        for (int i = 0; i < count; i++) {
            if (i < marks.length) {
                marks[i] = Lab2_var3.getRandInt(minMark, maxMark);
            }
        }
    }

    /**
     * Outputs the array of Student's marks.
     */
    public void printMarks() {
        System.out.printf("%s's marks: ", this.getName());
        for (int mark : marks) {
            if (mark != 0) {
                System.out.printf("%3d ", mark);
            }
        }
        System.out.printf(", average: %.1f %n", this.getAverageMark());
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
