package ua.kpi.fict.oop2.classes.variant03;

import java.util.Scanner;

/**
 * Created by Max Donchenko on 24.01.2016.
 */
public class Lab1_var03 {

    public static void swap(short[] a, short[] b) {
        /*
         * In Java, all the arguments are passed by value.
         * So there is no such thing as a "ref".
         * However, I achieve variable swapping by wrapping values in arrays.
         */
        short t = a[0];
        a[0] = b[0];
        b[0] = t;
    }

    public static void swap(int[] a, int[] b) {
        /*
         * In Java, all the arguments are passed by value.
         * So there is no such thing as a "ref".
         * However, I achieve variable swapping by wrapping values in arrays.
         */
        int t = a[0];
        a[0] = b[0];
        b[0] = t;
    }

    public static char determineOp1(int value) throws IllegalArgumentException {
        switch (value) {
            case 0:
                return '+';
            case 1:
                return '-';
            default:
                throw new IllegalArgumentException("Incorrect value: " + value);
        }
    }

    public static char determineOp2(int value) {
        switch (value) {
            case 0:
                return '*';
            case 1:
                return '/';
            case 2:
                return '%';
            case 3:
                return '+';
            case 4:
                return '-';
            default:
                throw new IllegalArgumentException("Incorrect value: " + value);
        }
    }

    /**
     * Simple math operations: "+", "-", "*", "/", "%"
     */
    public static double makeOperation(short a, short b, char operation)
            throws IllegalArgumentException {
        switch (operation) {
            case '+':
                return (double) (a + b);
            case '-':
                return (double) (a - b);
            case '*':
                return (double) (a * b);
            case '/':
                return (double) (a / b);
            case '%':
                return (double) (a % b);
            default:
                throw new IllegalArgumentException("Incorrect operation: " + operation);
        }
    }

    public static double calculateSum(short minOuter, short maxOuter,
                                      short minInner, short maxInner,
                                      char op1, char op2,
                                      short denominatorConstant) throws ArithmeticException {
        double sum = 0.0;

        for (short i = minOuter; i <= maxOuter; i++) {
            for (short j = minInner; j <= maxInner; j++) {
                if (i == denominatorConstant) {
                    throw new ArithmeticException("Division by 0.0 -> Inf");
                }
                double numerator = makeOperation(i, j, op2);
                double denominator = makeOperation(i, denominatorConstant, op1);
                sum += (numerator / denominator);
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        short a = 0, n = 0, b = 0, m = 0;
        int c, c2, c3, c5, recordBook;
        char op1, op2;

        System.out.print("Number of your record-book: ");
        recordBook = scan.nextInt();
        c2 = recordBook % 2;
        c3 = recordBook % 3;
        c5 = recordBook % 5;

        op1 = determineOp1(c2);
        c = c3;
        op2 = determineOp2(c5);

        try {
            System.out.print("Sum 1 from ");
            a = scan.nextShort();
            System.out.print("to ");
            n = scan.nextShort();

            if (a > n) {
                String message = String.format("Inverted loop limits: %d > %d", a, n);
                throw new ArithmeticException(message);
            }
        } catch (ArithmeticException e) {
            e.printStackTrace();
            short[] A = {a};
            short[] N = {n};
            swap(A, N);
            a = A[0];
            n = N[0];
            System.out.println("Inverted limits were swapped: [n,a]->[a,n]");
        }

        try {
            System.out.print("Sum 2 from ");
            b = scan.nextShort();
            System.out.print("to ");
            m = scan.nextShort();

            if (b > m) {
                String message = String.format("Inverted loop limits: %d > %d", b, m);
                throw new ArithmeticException(message);
            }
        } catch (ArithmeticException e) {
            e.printStackTrace();
            short[] B = {b};
            short[] M = {m};
            swap(B, M);
            b = B[0];
            m = M[0];
            System.out.println("Inverted limits were swapped: [m,b]->[b,m]");
        }

        try {
            System.out.print("Sum is " + calculateSum(a, n, b, m, op1, op2, (short) c));
        } catch (ArithmeticException e) {
            e.printStackTrace();
        }
    }
}
