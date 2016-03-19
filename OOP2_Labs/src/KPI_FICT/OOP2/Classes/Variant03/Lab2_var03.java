package KPI_FICT.OOP2.Classes.Variant03;

import java.util.Scanner;

/**
 * Created by Max Donchenko (https://github.com/goodwin64) on 25.01.2016.
 */
public class Lab2_var03 {

    public static byte getRandByte(byte min, byte max) {
        /*
         * Instead of rounding, flooring distribution is more uniform.
         */
        return (byte) (min + Math.random() * (max - min + 1));
    }

    public static int getRandInt(int min, int max) {
        return (int) (min + Math.random() * (max - min + 1));
    }

    public static byte getRandByte() {
        return (byte) (-128 + Math.random() * (127 + 128 + 1));
    }

    public static void prettyPrint(byte[][] matrix) {
        for (byte[] row : matrix) {
            for (byte value : row) {
                System.out.format("%5d", value);
            }
            System.out.println();
        }
        for (int i = 0; i < matrix[0].length * 5; i++) {
            System.out.printf("%s", "-"); /* format() and printf() are similar */
        }
        System.out.println();
    }

    /**
     * Calculates the sum of numbers in each column
     * and print additional row with these values.
     */
    public static void printColumnAmounts(byte[][] matrix) {
        int sum;
        for (int j = 0; j < matrix[0].length; j++) {
            sum = 0;
            for (int i = 0; i < matrix.length; i++) {
                sum += matrix[i][j];
            }
            System.out.printf("%5d", sum);
        }
        System.out.println(); /* analogue of '\n' */
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        byte height, width;

        System.out.print("Matrix height: ");
        height = scan.nextByte();
        System.out.print("Matrix width: ");
        width = scan.nextByte();

        try {
            if (height < 1) {
                throw new Exception("Matrix height is not natural number");
            }
            if (width < 1) {
                throw new Exception ("Matrix width is not natural number");
            }
        } catch (Exception e) {
            e.printStackTrace();
            while (height < 1) {
                System.out.print("Matrix height: ");
                height = scan.nextByte();
            }
            while (width < 1) {
                System.out.print("Matrix width: ");
                width = scan.nextByte();
            }
        }

        byte[][] A = new byte[height][width];
        byte[][] B = new byte[height][width];
        byte[][] C = new byte[height][width];

        for (byte i = 0; i < height; i++) {
            for (byte j = 0; j < width; j++) {
                A[i][j] = getRandByte();
                B[i][j] = getRandByte();
                C[i][j] = (byte) (A[i][j] ^ B[i][j]);
            }
        }

        //prettyPrint(A);
        //prettyPrint(B);
        prettyPrint(C);

        printColumnAmounts(C);

    }
}
