package ua.kpi.fict.oop2.classes.variant12;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by Rock(https://github.com/Filin-Rock) on 18.03.2016.
 */
public class Lab3_var12 {

    public static void main(String[] args) {

        /*
         * 1) Reading the text.
         * 2) Splitting text (string) by tokens (words)
         *      and replacing each word with some entered length on entered word.
         * 3) Write edited text in the file.
         */

        String prefix = "src\\KPI_FICT\\OOP2\\Classes\\Variant12\\Resources\\";
        String fileNameIn = prefix + "Lab3_var12_in.txt";
        String fileNameOut = prefix + "Lab3_var12_out.txt";
        String text = readFile(fileNameIn);
        System.out.println(text);

        Scanner sc = new Scanner(System.in);

        System.out.print("Слово для замены: ");
        String replaceWord = sc.next();
        System.out.print("Длина заменяемых слов = ");
        int count = 0;
        try {
            count = sc.nextInt();
            if (count <= 0) {
                throw new IllegalArgumentException("Oops");
            }
        } catch (InputMismatchException e) {
            e.printStackTrace();
        }
        System.out.println();


        String delimiters = "[ ,.]|\\b";
        String wordToReplacePattern = String.format("(%s)[A-Za-zА-Яа-я]{%d}(%s)", delimiters, count, delimiters);

        text = text.replaceAll(wordToReplacePattern, "$1" + replaceWord + "$2");


        Charset charset = StandardCharsets.UTF_8;
        Path path = Paths.get(fileNameOut);
        byte[] textBytes = text.getBytes(charset);
        try {
            Files.write(path, textBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(text);
    }

    /**
     * Reads the text from the file.
     *
     * @param path      path to file.
     */
    private static String readFile(String path) {
        String result = "";
        try {
            FileInputStream fis = new FileInputStream(path);
            int a;
            do {
                a = fis.read();
                if (a != -1) {
                    result += ((char) a);
                }
            }
            while (a != -1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
