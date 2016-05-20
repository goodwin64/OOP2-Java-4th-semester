package ua.kpi.fict.oop2.classes.variant03.lab5;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Created by Max Donchenko (https://github.com/goodwin64) on 09.03.2016.
 */
public class Lab5_var03 {
    public static void main(String[] args) {
        String pathPrefix = "src\\ua\\kpi\\fict\\oop2\\Resources\\Variant03\\";
        String fileName = "someText";
        String fileExtension = ".txt";

        String textFromFile = readTextFromFile(pathPrefix + fileName + fileExtension);
        Text text = parseStringToText(textFromFile);
        ArrayList<Word> words = getWords(text);
        Collections.sort(words);
        toFile(pathPrefix + fileName + " - output" + fileExtension, words);
    }

    /**
     * Split input string on words, punctuation marks and sentences
     * (respective classes instances)
     * Words and PMs are put in Sentence value,
     * Sentences are put in Text value.
     *
     * @param text                          text to parse
     * @throws IllegalArgumentException     if line is empty
     */
    public static Text parseStringToText(String text) throws IllegalArgumentException {
        if (text.equals("")) {
            throw new IllegalArgumentException("Empty text on input");
        }
        //int numberOfWords = countMatches(text, PunctuationMark.sentenceDelimiters);
        int numberOfSentences = countMatches(text, PunctuationMark.textDelimiters);
        ArrayList<Sentence> result = new ArrayList<>(numberOfSentences);
        char currentChar;
        Word lastWord = new Word();
        PunctuationMark lastPM = new PunctuationMark();
        Sentence lastSentence = new Sentence();

        for (int i = 0; i < text.length(); i++) {
            currentChar = text.charAt(i);
            if (Character.isLetter(currentChar)) {
                lastWord.add(currentChar);
            } else if (isDelimiter(currentChar, PunctuationMark.sentenceDelimiters)) {
                lastPM.setValue(String.valueOf(currentChar));
                // TODO: add special case - the hyphen (-)
                lastSentence.add(new Word(lastWord.getValue()));
                if (!lastSentence.toString().equals("")) {
                    lastSentence.add(new PunctuationMark(lastPM.getValue()));
                }
                lastWord.setValue("");
            } else if (isDelimiter(currentChar, PunctuationMark.textDelimiters)) {
                // TODO: add special case - Shortened word (Mr. Sherlock)
                lastSentence.add(new Word(lastWord.getValue()));
                lastWord.setValue("");
                lastSentence.add(new PunctuationMark(lastPM.getValue()));
                result.add(new Sentence(lastSentence.getValue()));
                lastSentence.value.clear();
            } else {
                // TODO special case: numbers within word (2nd, 5th)
            }
        }
        return new Text(result);
    }

    public static boolean isDelimiter(char c, char[] delimiters) {
        // TODO: 20.05.2016 how is better? loop or 1-line action
        /*
        for (char del : delimiters) {
            if (c == del)
                return true
        }
        return false;
        */
        return new String(delimiters).indexOf(c) != -1;
    }

    public static int countMatches(String text, char[] delimiterCharsSequence) {
        int result = 0;
        for (char c : text.toCharArray()) {
            for (char delimiter : delimiterCharsSequence) {
                if (c == delimiter) {
                    result++;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Scan text from file to a Text() instance.
     *
     * @param path      the path to the file with text
     *
     * @return text     the String contains the text
     */
    public static String readTextFromFile(String path) {
        File file = new File(path);
        String text = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            try {
                int ch;
                while ((ch = br.read()) != -1) {
                    text += (char) ch;
                }
                br.close();
            } catch (IOException e) {
                System.err.format("IOException: %s%n", e);
            }
        } catch (FileNotFoundException e) {
            System.err.format("Exception: %s%n", e);
        }
        return text;
    }

    /**
     * Gets words from the text and puts them into ArrayList.
     * Then returns this ArrayList.
     *
     * In ArrayList words will be repeated.
     */
    public static ArrayList<Word> getWords(Text text) {
        ArrayList<Word> result = new ArrayList<>(2000); // depends on text
        for (Sentence s : text.getValue()) {
            result.addAll(s.getValue().stream()
                    .filter(se -> se instanceof Word && !se.getValue().equals(""))
                    .map(se -> new Word(se.getValue()))
                    .collect(Collectors.toList()));
        }
        return result;
    }

    /**
     * Prints words from ArrayList to file.
     *
     * @param path      path to file
     * @param words     ArrayList with words
     */
    private static void toFile(String path, ArrayList<Word> words) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(path, "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (writer != null) {
            for (Word word : words) {
                writer.println(word);
            }
            writer.close();
        }
    }
}

