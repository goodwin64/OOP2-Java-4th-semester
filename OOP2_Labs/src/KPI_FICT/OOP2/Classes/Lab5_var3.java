package KPI_FICT.OOP2.Classes;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Max Donchenko (https://github.com/goodwin64) on 09.03.2016.
 */
public class Lab5_var3 {
    public static void main(String[] args) {
        Text text = readTextFromFile("someText.txt");

        System.out.println(text);
    }

    /**
     * Scan text from file to a Text() instance.
     *
     * @param path      the path to the file with text
     *
     * @return text     the String contains the text
     */
    public static Text readTextFromFile(String path) {
        File file = new File(path);
        Text text = new Text();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            try {
                String line = br.readLine();
                while (!line.equalsIgnoreCase("EOF")) {
                    try {
                        text.value.addAll(parseLine(line));
                    } catch (IllegalArgumentException e) {
                        // TODO: add PunctuationMark('\n') to the text
                        // empty line, ignore
                    }
                    line = br.readLine();
                }
                br.close();
            } catch (IOException e2) {
                System.err.format("IOException: %s%n", e2);
            }
        } catch (FileNotFoundException e1) {
            System.err.format("Exception: %s%n", e1);
        }
        return text;
    }

    /**
     * Split input string on words, punctuation marks and sentences
     * (respective classes instances)
     * Words and PMs are put in Sentence value,
     * Sentences are put in Text value.
     *
     * @param line                          line to parse
     * @throws IllegalArgumentException     if line is empty
     */
    public static ArrayList<Sentence> parseLine(String line) throws IllegalArgumentException {
        if (line.equals("")) {
            throw new IllegalArgumentException("Empty line");
        }
        ArrayList<Sentence> result = new ArrayList<>(20);
        char currentChar;
        Word lastWord = new Word();
        PunctuationMark lastPM = new PunctuationMark();
        Sentence lastSentence = new Sentence();

        for (int i = 0; i < line.length(); i++) {
            currentChar = line.charAt(i);
            if (Character.isLetter(currentChar)) {
                lastWord.add(currentChar);
            } else {
                lastPM.setValue(String.valueOf(currentChar));
                boolean isSentenceDelimiter = new String(PunctuationMark.sentenceDelimiters).indexOf(currentChar) != -1;
                boolean isTextDelimiter = new String(PunctuationMark.textDelimiters).indexOf(currentChar) != -1;
                if (isSentenceDelimiter) {
                    // TODO: add special case - the hyphen (-)
                    lastSentence.add(new Word(lastWord.getValue()));
                    if (!lastSentence.toString().equals("")) {
                        lastSentence.add(new PunctuationMark(lastPM.getValue()));
                    }
                    lastWord.setValue("");
                } else if (isTextDelimiter) {
                    // TODO: add special case - Shortened word (Mr. Sherlock)
                    lastSentence.add(new Word(lastWord.getValue()));
                    lastWord.setValue("");
                    lastSentence.add(new PunctuationMark(lastPM.getValue()));
                    result.add(new Sentence(lastSentence.getValue()));
                    lastSentence.value.clear();
                }
            }
        }
        return result;
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
            for (char vowel : vowels) {
                if (c == vowel) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    @Override
    /**
     * Comparing by number of vowels
     */
    public int compareTo(Word other) {
        return this.countVowels() - other.countVowels();
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
    public Sentence(ArrayList<SentenceElement> sentence) {
        this.value = new ArrayList<>(20);
        for (SentenceElement se : sentence) {
            this.value.add(se);
        }
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
        return String.format("%s", result);
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
    protected ArrayList<Sentence> value;

    public Text() {
        this.value = new ArrayList<>(2);
    }

    public ArrayList<Sentence> getValue() {
        return value;
    }

    @Override
    public String toString() {
        String result = "";
        for (Sentence sentence : value) {
            result += sentence + "\n";
        }
        return String.format("%s", result);
    }
}
