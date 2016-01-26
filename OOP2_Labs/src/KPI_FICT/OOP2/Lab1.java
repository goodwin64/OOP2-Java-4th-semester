package KPI_FICT.OOP2;

import java.util.Scanner;

/**
 * Created by Max Donchenko on 24.01.2016.
 */

public class Lab1 {
    public static void swap(short[] a, short[] b) {
        /*
        In Java, all the arguments are passed by value.
        So there is no such thing as a "ref".
        However, I achieve variable swapping by wrapping values in arrays.
        */
        short t = a[0];
        a[0] = b[0];
        b[0] = t;
    }

    public static char determineOp1(int value) {
        try {
            switch (value) {
                case 0: return '+';
                case 1: return '-';
                default: throw new Exception("Incorrect value");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 'E';
        }
    }

    public static char determineOp2(int value) {
        try {
            switch (value) {
                case 0: return '*';
                case 1: return '/';
                case 2: return '%';
                case 3: return '+';
                case 4: return '-';
                default: throw new Exception("Incorrect value");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 'E';
        }
    }

    public static short makeOperation(short a, short b, char operation) {
        try {
            switch (operation) {
                case '+': return (short) (a + b);
                case '-': return (short) (a - b);
                case '*': return (short) (a * b);
                case '/': return (short) (a / b);
                case '%': return (short) (a % b);
                default: throw new Exception("Incorrect operation");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // if this method return 0, it doesn't entail changes in further calculations
            return 0;
        }

    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        short a, n, b, m;
        double partSum, Sum = 0.0;
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

        /*int c7 = recordBook % 7;
        switch (c7) {
            case 0: byte i, j;
            case 1: short i, j;
            case 2: int i, j;
            case 3: long i, j;
            case 4: char i, j;
            case 5: float i, j;
            case 6: double i, j;
        }*/

        try {
            System.out.print("Sum 1 from ");
            a = scan.nextShort();
            System.out.print("to ");
            n = scan.nextShort();

            System.out.print("Sum 2 from ");
            b = scan.nextShort();
            System.out.print("to ");
            m = scan.nextShort();

            if (a > n) {
                short[] A = {a};
                short[] N = {n};
                swap(A, N);
                a = A[0];
                n = N[0];
                // it's possible to delete arrays A and N here
                System.out.println("Inverted limits were swapped: [n,a]->[a,n]");
            }
            if (b > m) {
                short[] B = {b};
                short[] M = {m};
                swap(B, M);
                b = B[0];
                m = M[0];
                System.out.println("Inverted limits were swapped: [m,b]->[b,m]");
            }


            for (short i = a; i <= n; i++) {
                for (short j = b; j <= m; j++) {
                    try {
                        if (i == c) {
                            throw new Exception("Division by 0.0 -> Inf");
                        }
                        partSum = ( (double) makeOperation(i, j, op2) / (double) makeOperation(i, (short) c, op1) );
                        Sum += partSum;
                        System.out.println(Sum);
                    }
                    catch (ArithmeticException e) {
                        System.err.println("Division by zero, action was ignored");
                    }
                    catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }

            System.out.print("Sum is ");
            System.out.println(Sum);
            
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
