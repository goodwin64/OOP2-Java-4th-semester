package KPI_FICT.OOP2.Classes.Variant12;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.Scanner;


/**
 * Created by Rock(https://github.com/Filin-Rock) on 29.03.2016.
 */
public class Lab4_Var12 {

    static class Student {
        private String name, surname, secondName;
        private int age, numberOfRecordBook;

        public Student() {}
        public Student(String name, String surname, String secondName,
                       int age, int numberOfRecordBook) {
            setName(name);
            setSurname(surname);
            setSecondName(secondName);

            try {
                setAge(age);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                Scanner scanner = new Scanner(System.in);
                while (age <= 15 || age >= 100) {
                    System.out.printf("Incorrect age (%d)\n", age);
                    System.out.print("Enter correct age: ");
                    age = scanner.nextInt();
                }
                setAge(age);
            }

            setNumberOfRecordBook(numberOfRecordBook);
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

        public String getSecondName() {
            return secondName;
        }
        public void setSecondName(String secondName) {
            this.secondName = secondName;
        }

        public int getAge() {
            return age;
        }
        public void setAge(int age) throws IllegalArgumentException {
            if (age > 15 && age < 100) {
                this.age = age;
            } else {
                String errorMessage = String.format("Incorrect student age (%d)\n", age);
                throw new IllegalArgumentException(errorMessage);
            }
        }

        public int getNumberOfRecordBook() {
            return numberOfRecordBook;
        }
        public void setNumberOfRecordBook(int numberOfRecordBook) {
            this.numberOfRecordBook = numberOfRecordBook;
        }
    }

    /**
     * Sort by surname
     */
    static class SortedBySurname implements Comparator<Student> {

        public int compare(Student obj1, Student obj2) {

            String str1 = obj1.getSurname();
            String str2 = obj2.getSurname();

            return str1.compareTo(str2);
        }
    }

    /**
     * Sort by number Of Record Book
     */
    static class SortedByNumber implements Comparator<Student> {

        public int compare(Student obj1, Student obj2) {

            int var1 = obj1.getAge();
            int var2 = obj2.getAge();

            return var1 - var2;
        }
    }

    public static void printHeader(String criteria) {
        if (!Objects.equals(criteria, "null")) {
            System.out.printf("\n========== %s %s ===========\n", "Cортировано по", criteria);
        } else {
            System.out.printf("\n========== %s ===========\n", "Не сортировано");
        }
        System.out.printf("%12s %8s %15s %8s %5s\n", "Фамилия", "Имя", "Отчество", "ID", "Возраст");
    }

    public static void printStudents(Student[] students) {
        for (Student i : students) {
            if (i != null) {
                System.out.printf("%-12s %-8s %-15s %8d %5d\n",
                        i.getSurname(), i.getName(), i.getSecondName(), i.getNumberOfRecordBook(), i.getAge());
            }
        }
    }

    public static void main(String[] args) {

        Student[] students = new Student[6];

        students[4] = new Student("Максим", "Донченко", "Oлександрович", 20, 3);

        students[3] = new Student("Данил", "Клименко", "Eвгениевич", 19, 4);

        students[0] = new Student("Mаксим", "Кривицкий", "Богданович", 25, 5);

        students[2] = new Student("Руслан","Сopoчинcкий","Владимирович",44, 11);

        students[1] = new Student("Павел", "Шевченко", "Павлович", 28, 12);

        students[5] = new Student("Яна", "Шушакова", "Анатолиевна", 21, 13);

        // print without sort
        printHeader("null");
        printStudents(students);

        // sort by age and print
        printHeader("возрасту");
        Arrays.sort(students, new SortedByNumber());
        printStudents(students);

        // sort by surname and print
        printHeader("фамилии");
        Arrays.sort(students, new SortedBySurname());
        printStudents(students);
    }
}
