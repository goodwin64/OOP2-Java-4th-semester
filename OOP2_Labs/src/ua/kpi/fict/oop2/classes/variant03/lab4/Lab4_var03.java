package ua.kpi.fict.oop2.classes.variant03.lab4;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Max Donchenko (https://github.com/goodwin64) on 03.03.2016.
 */
public class Lab4_var03 {

    public static void main(String[] args) {

        Student[] students = fillStudentsArray(50);
        printStudentsArray(students);

        Arrays.sort(students);
        System.out.println("\n\t In ascending order by age:");
        printStudentsArray(students);

        Arrays.sort(students, new SortDescByAverMark());
        System.out.println("\n\t In descending order by average mark:");
        printStudentsArray(students);

        Arrays.sort(students, new SortAscBySurname());
        System.out.println("\n\t In ascending order by surname:");
        printStudentsArray(students);
    }

    public static Student[] fillStudentsArray(int count) {
        Student[] students = new Student[count];
        int middle = students.length / 2;

        /* Medium knowledge */
        for (int i = 0; i < middle; i++) {
            students[i] = new Student();
            Student st = students[i];
            st.addMarks(50);
            st.recalcScholarship();
        }
        /* Excellent pupils */
        for (int i = middle; i < students.length; i++) {
            students[i] = new Student();
            Student st = students[i];
            st.addMarks(10, 90, 100);
            st.recalcScholarship();
        }

        return students;
    }

    public static void printStudentsArray(Student[] students) {
        for (Student st : students) {
            System.out.println(st);
        }
    }
}

// Classes to implement a sorting the array of students by keys.
class SortDescByAverMark implements Comparator<Student> {
    @Override
    public int compare(Student st1, Student st2) {
        double am1 = st1.getAverageMark();
        double am2 = st2.getAverageMark();

        if (am2 == am1) {
            return 0;
        } else if (am2 < am1) {
            return -1;
        } else {
            return 1;
        }
    }
}
class SortAscBySurname implements Comparator<Student> {
    @Override
    public int compare(Student st1, Student st2) {
        if (st1.getSurname().compareTo(st2.getSurname()) == 0) {
            /* Surnames are equal, must be compared by names */
            return st1.getName().compareTo(st2.getName());
        } else {
            return st1.getSurname().compareTo(st2.getSurname());
        }
    }
}
