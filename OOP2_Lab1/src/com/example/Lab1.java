package com.example;

import java.util.Scanner;

/**
 * Created by Max Donchenko on 24.01.2016.
 */
public class Lab1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        short a = 1, n = 4, b = 10, m = 12 , C = 2;
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
                throw new Exception("Sum limits are inverted");
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
