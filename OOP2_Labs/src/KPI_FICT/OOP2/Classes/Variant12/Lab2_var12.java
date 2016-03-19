package KPI_FICT.OOP2.Classes.Variant12;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by Rock(https://github.com/Filin-Rock) on 17.03.2016.
 */
public class Lab2_var12 {
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.format("%7d", value);
            }
            System.out.println();
        }
        for (int i = 0; i < matrix[0].length * 7; i++) {
            System.out.printf("%s", "-");
        }
        System.out.println();
    }

    public static void printColumnsAverages(int[][] matrix) {
        int sum;
        double average;
        int n = matrix.length;

        for (int j = 0; j < matrix[0].length; j++) {
            sum = 0;
            for (int i = 0; i < n; i++) {
                sum += matrix[i][j];
            }
            average = (double) sum / n;
            System.out.printf("%7.1f", average);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int height, width;
        Random rand = new Random();

        System.out.print("Matrix height: ");
        height = scanner.nextInt();
        System.out.print("Matrix width: ");
        width = scanner.nextInt();

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
                height = scanner.nextInt();
            }
            while (width < 1) {
                System.out.print("Matrix width: ");
                width = scanner.nextInt();
            }
        }

        int[][] matrixA = new int[height][width];
        int[][] matrixB = new int[height][width];
        int[][] matrixC = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                matrixA[i][j] = rand.nextInt(50);
                matrixB[i][j] = rand.nextInt(50);
                matrixC[i][j] = matrixA[i][j] + matrixB[i][j];
            }
        }

        printMatrix(matrixA);
        printMatrix(matrixB);
        printMatrix(matrixC);

        printColumnsAverages(matrixC);

    }

}
