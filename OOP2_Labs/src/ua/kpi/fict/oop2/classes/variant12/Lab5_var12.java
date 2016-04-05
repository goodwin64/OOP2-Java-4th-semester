package ua.kpi.fict.oop2.classes.variant12;

import java.util.ArrayList;

/**
 * Created by Rock(https://github.com/Filin-Rock) on 25.03.2016.
 */
public class Lab5_var12 {
    public static void main(String[] args) {
        String pathPrefix = "src\\KPI_FICT\\OOP2\\Resources\\Variant12\\";
        String fileNameIn = "Lab5_var12_in.txt";
        String fileNameOut = "Lab5_var12_out.txt";
        String fileExtension = ".txt";

        Text text = readTextFromFile(pathPrefix + fileNameIn + fileExtension);
        replaceSomeWords(4, "exampleWord");
        writeToFile(pathPrefix + fileNameOut + fileExtension, text);

    }

    public static Text readTextFromFile(String path) {
        // TODO: char-by-char text parsing and creating a Text() instance.
        return new Text();
    }

    public static void replaceSomeWords(int wordsLength, String replacer) {
        // TODO: make some replaces in Text.
    }

    public static void writeToFile(String path, Text text) {
        // TODO: write text from Text() instance to file.
    }
}

/**
 * Class implements the element of sentence: a word or a punctuation mark.
 *
 * Fundamental difference between Word and PunctuationMark
 * is that the Word contains String value, PM contains char value.
 */
abstract class SentenceElement {
    private String value;

    public abstract String getValue();
    public abstract void setValue(String value);
}

/**
 * Class implements the word - a sequence of alphabet characters:
 * A..Z, a..z
 */
class Word extends SentenceElement {
    private String value;

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
        setValue("\0");
    }
    public PunctuationMark(char c) {
        this.value = c;
    }
    public PunctuationMark(String punctuationMark) {
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
        return String.valueOf(this.value);
    }

    @Override
    public void setValue(String value) {
        this.value = value.charAt(0);
    }

    @Override
    public String toString() {
        return getValue();
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
    public Sentence(Sentence other) {
        this.value = new ArrayList<>(other.getValue().size());
        for (SentenceElement se : other.getValue()) {
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
        this.value = new ArrayList<>(1);
    }

    public ArrayList<Sentence> getValue() {
        return value;
    }

    @Override
    public String toString() {
        String result = "";
        for (Sentence sentence : value) {
            if (sentence.toString().equals("\n")) {
                result += sentence;
            } else {
                result += sentence + "\n";
            }
        }
        return String.format("%s", result);
    }
}
