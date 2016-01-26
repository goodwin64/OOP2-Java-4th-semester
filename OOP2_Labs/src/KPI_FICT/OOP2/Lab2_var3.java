package KPI_FICT.OOP2;

import jdk.nashorn.internal.runtime.ECMAException;

import java.util.Scanner;

/**
 * Created by Max Donchenko (https://github.com/goodwin64) on 25.01.2016.
 */
public class Lab2_var3 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        byte a, b;
        int sum;

        System.out.print("Height (a): ");
        a = scan.nextByte();
        System.out.print("Width (b): ");
        b = scan.nextByte();

        try {
            if (a < 1) {
                throw new Exception("Matrix height is not natural number");
            }
            if (b < 1) {
                throw new Exception ("Matrix width is not natural number");
            }
        } catch (Exception e) {
            e.printStackTrace();
            while (a < 1) {
                System.out.print("Height (a): ");
                a = scan.nextByte();
            }
            while (b < 1) {
                System.out.print("Width (b): ");
                b = scan.nextByte();
            }
        }

        byte[][] myArray = new byte[a][b];

        for (byte i = 0; i < a; i++) {
            for (byte j = 0; j < b; j++) {
                myArray[i][j] = (byte) (i ^ j);
            }
        }

        for (byte[] row : myArray) {
            for (byte value : row) {
                System.out.format("%3d ", value);
            }
            System.out.println();
        }

        for (int i = 0; i < b * 4; i++) {
            System.out.printf("%s", "-"); // format and printf are equal
        }
        System.out.println();

        for (int j = 0; j < b; j++) {
            sum = 0;
            for (int i = 0; i < a; i++) {
                sum += myArray[i][j];
            }
            System.out.printf("%3d ", sum);
        }

    }
}
