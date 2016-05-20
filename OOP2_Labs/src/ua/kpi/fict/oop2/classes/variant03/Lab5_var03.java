package ua.kpi.fict.oop2.classes.variant03;

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

/**
 * Class implements the word - a sequence of alphabet characters:
 * A..Z, a..z
 */
class Word extends SentenceElement implements Comparable<Word> {
    private String value;
    public static final char[] vowels = {
            'a', 'e', 'i', 'o', 'u', 'y'
    };

    public Word() {
        this.value = "";
    }
    public Word(String word) {
        try {
            setValue(word);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public int countVowels() {
        int count = 0;
        for (char c : this.getValue().toCharArray()) {
            char cLower = Character.toLowerCase(c);
            for (char vowel : vowels) {
                if (cLower == vowel) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    @Override
    /**
     * Comparing by (in queue order):
     * 1) number of vowels (descending)
     * 2) length (descending)
     * 3) abc rules (ascending)
     */
    public int compareTo(Word other) {
        int vowelsDiff = other.countVowels() - this.countVowels();
        if (vowelsDiff != 0) {
            return vowelsDiff;
        }

        int lengthDiff = other.getValue().length() - this.getValue().length();
        if (lengthDiff != 0) {
            return lengthDiff;
        }

        return this.getValue().compareTo(other.getValue());
    }

    public void add(char c) {
        this.value += c;
    }
}

/**
 * Class implements the punctuation mark:
 * [ ,:;-()"'] in sentences between words,
 * [.!?\n] in text between sentences.
 */
class PunctuationMark extends SentenceElement {
    private char value;
    public static final char[] textDelimiters = {
            '.', '!', '?', '\n'
    };
    public static final char[] sentenceDelimiters = {
            ' ', ',', ':', ';', '-', '(', ')', '\'', '"', '«', '»'
    };

    public PunctuationMark() {
        super();
        setValue("\0");
    }
    public PunctuationMark(char c) {
        this.value = c;
    }
    public PunctuationMark(String punctuationMark) {
        super(punctuationMark);
        try {
            setValue(punctuationMark);
        } catch (IllegalArgumentException e) {
            /*
             * System.out and System.err are different console streams,
             * so here is using the first one
             * not to mix incorrect argument and error info.
             */
            System.out.printf("%s (%s)\n", e, punctuationMark);
        }
    }

    @Override
    public String getValue() {
        if (super.getValue().length() > 1) {
            return "";
        } else {
            return String.valueOf(this.value);
        }
    }

    @Override
    public void setValue(String value) throws IllegalArgumentException {
        switch (value.length()) {
            case 1:
                this.value = value.charAt(0);
                break;
            case 0:
                throw new IllegalArgumentException("Empty punctuation mark");
            default:
                // Punctuation mark's length is >1 so it'll be ignored
                throw new IllegalArgumentException("Incorrect punctuation mark");
        }
    }

    @Override
    public String toString() {
        return getValue();
    }
}

/**
 * Class implements the element of sentence: a word or a punctuation mark.
 *
 * Fundamental difference between Word and PunctuationMark
 * is that the Word contains String value, PM contains char value.
 */
class SentenceElement {
    private String value;

    public SentenceElement() {
        this.value = "";
    }
    public SentenceElement(String value) {
        this.value = value;
    }
    public SentenceElement(Word word) {
        setValue(word.getValue());
    }
    public SentenceElement(PunctuationMark punctuationMark) {
        setValue(punctuationMark.getValue());
    }

    public String getValue() {
        return this.value;
    }
    public void setValue(String value) {
        this.value = value;
    }
}

/**
 * Class implements the sentence - a sequence of sentence elements:
 * words and/or punctuation marks.
 */
class Sentence {
    protected ArrayList<SentenceElement> value;

    public Sentence() {
        this.value = new ArrayList<>(1);
    }
    public Sentence(PunctuationMark pm) {
        this.value = new ArrayList<>(1);
        value.add(pm);
    }
    public Sentence(ArrayList<SentenceElement> sentence) {
        this.value = new ArrayList<>(20);
        this.value.addAll(sentence.stream()
                .collect(Collectors.toList()));
    }

    public ArrayList<SentenceElement> getValue() {
        return value;
    }

    @Override
    public String toString() {
        String result = "";
        for (SentenceElement se : value) {
            result += se;
        }
        return result;
    }

    public void add(Word word) {
        this.value.add(word);
    }

    public void add(PunctuationMark pm) {
        this.value.add(pm);
    }
}

/**
 * Class implements the text - a sequence of sentences and/or punctuation marks.
 */
class Text {
    private ArrayList<Sentence> value;

    public Text() {
        this.value = new ArrayList<>(1);
    }
    public Text(ArrayList<Sentence> sentenceArrayList) {
        this.value = sentenceArrayList;
    }

    public ArrayList<Sentence> getValue() {
        return value;
    }

    @Override
    public String toString() {
        String result = "";
        for (Sentence sentence : value) {
            result += sentence;
        }
        return result;
    }
}
