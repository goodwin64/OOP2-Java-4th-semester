package KPI_FICT.OOP2;

import java.util.Scanner;

/**
 * Created by Max Donchenko on 24.01.2016.
 */
public class Lab1 {
    public static void swap(short[] a, short[] b) {
        /*
        In Java, all the arguments are passed by value. So there is no such thing as a "ref".
        However, I achieve variable swapping by wrapping values in arrays.
        */
        short t = a[0];
        a[0] = b[0];
        b[0] = t;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        short a, n, b, m, C = 2;
        double partSum, Sum = 0.0;

        try {
            System.out.print("Sum 1 from ");
            a = scan.nextShort();
            System.out.print("to ");
            n = scan.nextShort();

            System.out.print("Sum 2 from ");
            b = scan.nextShort();
            System.out.print("to ");
            m = scan.nextShort();

            if ((a > n) || (b > m)) {
                if (a > n) {
                    short[] A = {a};
                    short[] N = {n};
                    swap(A, N);
                    a = A[0];
                    n = N[0];
                    // it's possible to delete arrays A and N here
                }
                if (b > m) {
                    short[] B = {b};
                    short[] M = {m};
                    swap(B, M);
                    b = B[0];
                    m = M[0];
                }
                System.out.println("Inverted limits were swapped");
            }

            for (int i = a; i <= n; i++) {
                for (int j = b; j <= m; j++) {
                    try {
                        if (i == C) {
                            throw new Exception("Division by 0.0 -> Inf");
                        }
                        partSum = ( (double)(i + j) / (double)(i - C) );
                        Sum += partSum;
                        System.out.println(Sum);
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
