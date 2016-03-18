package KPI_FICT.OOP2.Classes;

import java.util.ArrayList;

/**
 * Created by Max Donchenko (https://github.com/goodwin64) on 09.03.2016.
 */
public class Lab5_var3 {
    public static void main(String[] args) {
        // sorting by vowels

        Word w1 = new Word("brr"); // 0
        Word w2 = new Word("Java"); // 2
        Word w3 = new Word("exciting"); // 3
        Word w4 = new Word("Python"); // 2

        System.out.println(w1.compareTo(w2)); // -2
        System.out.println(w1.compareTo(w3)); // -3
        System.out.println(w3.compareTo(w2)); // 1
        System.out.println(w2.compareTo(w4)); // 0
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
        this.value = "default";
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
            for (char vowel : this.vowels) {
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
}

/**
 * Class implements the punctuation mark:
 * [ ,:;-()"'] in sentences between words,
 * [.!?\n] in text between sentences.
 */
class PunctuationMark extends SentenceElement {
    private char value;

    public PunctuationMark() {
        super();
        setValue(" ");
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
        value.add(new Word());
        value.add(new PunctuationMark());
        value.add(new Word());
    }
    public Sentence(ArrayList<SentenceElement> sentence) {
        this.value = sentence;
    }

    @Override
    public String toString() {
        String result = "";
        for (SentenceElement se : value) {
            result += se;
        }
        return String.format("%s", result);
    }
}

/**
 * Class implements the text - a sequence of sentences and/or punctuation marks.
 */
class Text {
    protected ArrayList<Sentence> value;

    public Text() {
        this.value = new ArrayList<>(2);
        value.add(new Sentence());
        value.add(new Sentence());
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
