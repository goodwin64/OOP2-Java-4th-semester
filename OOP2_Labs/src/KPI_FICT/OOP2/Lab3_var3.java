package KPI_FICT.OOP2;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Max Donchenko (https://github.com/goodwin64) on 27.01.2016.
 */

public class Lab3_var3 {

    public static void printVariantInfo(Scanner scan) {
        int recBook;
        System.out.print("Number of record-book: ");
        recBook = 4103; //scan.nextInt();
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

    public static String readText(Scanner scan) {
        System.out.println("Input the text below (input will terminated after word <EOF>):");
        String text = "";
        String line = scan.nextLine();
        while ( ! line.equalsIgnoreCase("EOF") ) {
            text += line + " ";
            line = scan.nextLine();
        }
        return text;
    }

    public static void printWords(String arr[]) {
        for (String word : arr) {
            System.out.println(word);
        }
    }


    /**
     * Returns 0 or 1 (not -1) because of comparing words for sorting.
     * Comparing s1 < s2 is not necessary.
     *
     * @param s1    first string
     * @param s2    second string
     *
     * @return      1 if {vowels in s1} > {vowels in s2}
     *              0 otherwise
     */
    public static int compareByVowels(String s1, String s2) {
        int     vowels1 = 0,
                vowels2 = 0;
        char vowels[] = { 'a', 'e', 'i', 'o', 'u', 'y' };
        for (int i = 0; i < s1.length(); i++) {
            for (char vowel : vowels) {
                if (s1.toLowerCase().charAt(i) == vowel) {
                    vowels1++;
                    break;
                }
            }
        }
        for (int i = 0; i < s2.length(); i++) {
            for (char vowel : vowels) {
                if (s2.toLowerCase().charAt(i) == vowel) {
                    vowels2++;
                    break;
                }
            }
        }
        if (vowels1 > vowels2)
            return 1;
        else
            return 0;
    }

    public static void sortByVowels(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if (compareByVowels(arr[i], arr[j]) == 1) {
                    String temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    /**
     * Sort words from the given text by increasing the number of vowel letters.
     * 1) Reading the text (now from console, soon from file).
     * 2) Splitting text (string) by tokens (words) and creating an array on this base.
     * 3) Sorting array of words.
     * 4) Output array of words.
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        printVariantInfo(scan);
        String text = readText(scan);
        String[] wordsArray = getTokens(text, " ,.!?", true);
        sortByVowels(wordsArray);
        printWords(wordsArray);
    }
}
