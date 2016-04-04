package ua.kpi.fict.oop2.classes.variant12;

import java.util.Scanner;

/**
 * Created by Rock(https://github.com/Filin-Rock) on 17.03.2016.
 */
public class Lab1_var12 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        long a, n, b, m;
        double partSum, Sum = 0.0;

        System.out.print("Sum 1 from ");
        a = scan.nextLong();
        System.out.print("to ");
        n = scan.nextLong();

        System.out.print("Sum 2 from ");
        b = scan.nextLong();
        System.out.print("to ");
        m = scan.nextLong();

        for (long i = a; i <= n; i++) {
            for (long j = b; j <= m; j++) {
                try {
                    if (i == -2) {
                        throw new Exception("Division by 0.0 -> Inf");
                    }
                    partSum = ((double) (i % j) / (i + 2));
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

        System.out.printf("Sum is %.3f", Sum);
    }
}
