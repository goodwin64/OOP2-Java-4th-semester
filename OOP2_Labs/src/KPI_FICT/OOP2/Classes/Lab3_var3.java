package KPI_FICT.OOP2;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Max Donchenko (https://github.com/goodwin64) on 27.01.2016.
 */

public class Lab3_var3 {

    public static void printVariantInfo() {
        Scanner scan = new Scanner(System.in);
        int recBook;
        System.out.print("Number of record-book: ");
        recBook = scan.nextInt();
        int     c3 = recBook % 3,
                c17 = recBook % 17;
        String textVarsType = "";
        switch (c3) {
            case 0: textVarsType = "StringBuilder";
                break;
            case 1: textVarsType = "StringBuffer";
                break;
            case 2: textVarsType = "String";
                break;
        }
        System.out.printf("C3 = %d %% 3 = %d (%s), %n", recBook, c3, textVarsType);
        System.out.printf("C17 = %d %% 17 = %d %n", recBook, c17);
    }

    /**
     * Splits given string into tokens with delimiters specified.
     * It uses StringTokenizer for tokenizing.
     *
     * @param str           string to be tokenized
     * @param delimiter     delimiters used for tokenizing
     * @param trim          trim the tokens
     *
     * @return non-null     token array
     *
     * src: http://www.programcreek.com/java-api-examples/java.util.StringTokenizer
     */
    public static String[] getTokens(String str, String delimiter, boolean trim){
        StringTokenizer st = new StringTokenizer(str, delimiter);
        String tokens[] = new String[st.countTokens()];
        for(int i=0; i<tokens.length; i++){
            tokens[i] = st.nextToken();
            if(trim)
                tokens[i] = tokens[i].trim();
        }
        return tokens;
    }

    public static String readTextFromConsole() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Input the text below (input will terminated after word <EOF>):");
        String text = "";
        String line = scan.nextLine();
        while ( ! line.equalsIgnoreCase("EOF") ) {
            text += line + " ";
            line = scan.nextLine();
        }
        return text;
    }

    public static void createFile() {
        File file = new File("someText.txt");
        try {
            PrintWriter pw = new PrintWriter(file);
            pw.println("Line 1");
            pw.println("Line 2");
            pw.println("Line 3");
            pw.close();
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

    /**
     * Uncomment lines starts with triple-slash (///)
     * to output text from file to console.
     */
    public static String readTextFromFile() {
        // path: C:\Users\Admin\Documents\IdeaProjects\OOP2_Labs\
        File file = new File("someText.txt");
        String text = "";
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                try {
                    String line = br.readLine();
                    /// System.out.println(line);
                    while (!line.equalsIgnoreCase("EOF")) {
                        text += line + "\n";
                        line = br.readLine();
                        /// System.out.println(line);
                    }
                    br.close();
                    /// System.out.println("------------------------");
                } catch (IOException e2) {
                    System.err.format("IOException: %s%n", e2);
                }
            } catch (FileNotFoundException e1) {
                System.err.format("Exception: %s%n", e1);
            }
        return text;
    }

    public static void prettyPrint(String[] arr1, byte[] arr2) {
        System.out.printf("\tWords from text:%n");
        System.out.printf("%n%d: ", arr2[0]);
        System.out.printf("%s  ", arr1[0]);
        for (int i = 1; i < arr1.length; i++) {
            if ( arr2[i-1] < arr2[i] ) {
                System.out.printf("%n%d: ", arr2[i]);
            }
            System.out.printf("%s  ", arr1[i]);
        }
    }


    /**
     * Count the number of vowel letters in word.
     *
     * @param s     word
     *
     * @return      the number of vowel letters in word
     */
    public static byte vowelsCountInWord(String s) {
        byte result = 0;
        char vowels[] = { 'a', 'e', 'i', 'o', 'u', 'y' };
        for (int i = 0; i < s.length(); i++) {
            for (char vowel : vowels) {
                if (s.toLowerCase().charAt(i) == vowel) {
                    result++;
                    break;
                }
            }
        }
        return result;
    }

    public static byte[] vowelsCounts(String[] arr) {
        byte result[] = new byte[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = vowelsCountInWord(arr[i]);
        }
        return result;
    }

    /**
     * Soon here will be a stable sorting algorithm.
     */
    public static void sortWordsByVowels(String[] arr1, byte[] arr2) {
        for (int i = 0; i < arr1.length; i++) {
            for (int j = i + 1; j < arr1.length; j++) {
                if (arr2[i] > arr2[j]) {
                    String  temp1 = arr1[i];
                    byte    temp2 = arr2[i];

                    arr1[i] = arr1[j];
                    arr2[i] = arr2[j];

                    arr1[j] = temp1;
                    arr2[j] = temp2;
                }
            }
        }
    }

    /**
     * Sort words from the given text by increasing the number of vowel letters.
     * 1) Reading the text (now from console, soon from file).
     * 2) Splitting text (string) by tokens (words) and creating an array on this base.
     * 3) Creating the array of the numbers of vowels in each word.
     * 4) Sorting the array of words by comparing numbers in array of vowels.
     * 5) Outputting the array of words:
     *      {number of vowels} : {the array of words with this vowels number}.
     */
    public static void main(String[] args) {
        printVariantInfo();
        //createFile(); // uncomment this line if you want to rewrite .txt-file

        String text = readTextFromFile();
        String[] words = getTokens(text, " ,.!?\n", true);
        byte[] vowelNumber = vowelsCounts(words);
        sortWordsByVowels(words, vowelNumber);
        prettyPrint(words, vowelNumber);
    }
}
