package KPI_FICT.OOP2;

import java.util.Scanner;

/**
 * Created by Max Donchenko (https://github.com/goodwin64) on 25.01.2016.
 */

public class Lab2_var3 {

    public static byte randByte(byte min, byte max) {
        // instead of rounding, distribution is more uniform
        return (byte) (min - 0.5 + Math.random() * (max - min + 1));
    }

    public static byte randByte() {
        // instead of rounding, distribution is more uniform
        return (byte) (-128 - 0.5 + Math.random() * (127 + 128 + 1));
    }

    public static void prettyPrint(byte[][] arr){
        for (byte[] row : arr) {
            for (byte value : row) {
                System.out.format("%5d", value);
            }
            System.out.println();
        }
        for (int i = 0; i < arr[0].length * 5; i++) {
            System.out.printf("%s", "-"); // format and printf are equal
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        byte height, width;
        int sum;

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
                A[i][j] = randByte();
                B[i][j] = randByte();
                C[i][j] = (byte) (A[i][j] ^ B[i][j]);
            }
        }

//        prettyPrint(A);
//        prettyPrint(B);
        prettyPrint(C);

        for (int j = 0; j < width; j++) {
            sum = 0;
            for (int i = 0; i < height; i++) {
                sum += C[i][j];
            }
            System.out.printf("%5d", sum);
        }

    }
}
