package ua.kpi.fict.oop2.classes.variant12;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Rock(https://github.com/Filin-Rock) on 25.03.2016.
 */
public class Lab5_var12 {
    public static void main(String[] args) {
        String pathPrefix = "src\\ua\\kpi\\fict\\oop2\\Resources\\Variant12\\";
        String fileNameIn = "Lab5_var12_in";
        String fileNameOut = "Lab5_var12_out";
        String fileExtension = ".txt";

        String textFromFile = readTextFromFile(pathPrefix + fileNameIn + fileExtension);
        Text text = parseTextToObjects(textFromFile);
        replaceSomeWords(4, "exampleWord");
        writeToFile(pathPrefix + fileNameOut + fileExtension, text);

    }

    public static String readTextFromFile(String path) {
        String result = "";
        //BufferedReader br;
        int c;
        FileInputStream in;

        try {
            //br = new BufferedReader(new FileReader(file));
            in = new FileInputStream(path);
            while ((c = in.read()) != -1) {
                result += (char) c;
                //text.value.addAll(parseLine(line));
                //line = br.readLine();
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Text parseTextToObjects(String text) {
        int sentencesCount = 0;
        Text result = new Text(countSentences(text));
        char currentChar;
        Word lastWord = new Word();
        Word emptyWord = new Word();
        PunctuationMark lastPM = new PunctuationMark();
        Sentence lastSentence = new Sentence(0);

        for (int i = 0; i < text.length(); i++) {
            currentChar = text.charAt(i);
            if (Character.isLetter(currentChar)) {
                lastWord.add(new Letter(currentChar));
            } else {
                lastPM.setValue(String.valueOf(currentChar));
                boolean isSentenceDelimiter = new String(PunctuationMark.sentenceDelimiters).indexOf(currentChar) != -1;
                boolean isTextDelimiter = new String(PunctuationMark.textDelimiters).indexOf(currentChar) != -1;
                if (isSentenceDelimiter) {
                    // TODO: add special case - the hyphen (-)
                    if (!emptyWord.equals(lastWord)) {
                        lastSentence.add(new Word(lastWord));
                    }
                    lastSentence.add(new PunctuationMark(lastPM.getValue()));
                    lastWord.setValue("");
                } else if (isTextDelimiter) {
                    // TODO: add special case - Shortened word (Mr. Sherlock)
                    if (!emptyWord.equals(lastWord)) {
                        lastSentence.add(new Word(lastWord));
                    }
                    lastWord.setValue("");
                    lastSentence.add(new PunctuationMark(lastPM.getValue()));
                    result.value[sentencesCount++] = new Sentence(lastSentence.getValue());
                    lastSentence = new Sentence(0);
                } else {
                    // TODO special case: numbers within word (2nd, 5th)
                }
            }
        }
        return result;
    }

    private static int countSentences(String text) {
        int result = 0;
        for (char c : text.toCharArray()) {
            for (char textDelimiter : PunctuationMark.textDelimiters) {
                if (c == textDelimiter) {
                    result++;
                    break;
                }
            }
        }
        return result;
    }

    public static void replaceSomeWords(int wordsLength, String replacer) {
        // TODO: make some replaces in Text.
    }

    public static void writeToFile(String path, Text text) {
        // TODO: write text from Text() instance to file.
    }
}

/**
 * Class implements the letter - a part of word.
 */
class Letter {
    private char value;

    public Letter(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
}

/**
 * Class implements the element of sentence: a word or a punctuation mark.
 *
 * Fundamental difference between Word and PunctuationMark
 * is that the Word contains array of Letters value, PM contains char value.
 */
abstract class SentenceElement {
    public abstract String toString();
}

/**
 * Class implements the word - a sequence of alphabet characters:
 * A..Z, a..z
 */
class Word extends SentenceElement {
    private Letter[] value;

    public Word() {
        this.value = new Letter[0];
    }
    public Word(Word other) {
        this.value = new Letter[other.value.length];
        for (int i = 0; i < other.value.length; i++) {
            this.value[i] = other.value[i];
        }
    }

    public Letter[] getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = new Letter[value.length()];
        for (int i = 0; i < value.length(); i++) {
            this.value[i] = new Letter(value.charAt(i));
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(value);
    }

    public void add(Letter letter) {
        int newSize = this.value.length + 1;
        Letter[] temp = new Letter[newSize];
        for (int i = 0; i < this.value.length; i++) {
            temp[i] = this.value[i];
        }
        this.value = temp;
        this.value[newSize - 1] = new Letter(letter.getValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word word = (Word) o;

        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(getValue(), word.getValue());

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

    public char getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value.charAt(0);
    }

    @Override
    public String toString() {
        return String.valueOf(getValue());
    }
}

/**
 * Class implements the sentence - a sequence of sentence elements:
 * words and/or punctuation marks.
 */
class Sentence {
    protected SentenceElement[] value;

    public Sentence(int elementsCount) {
        this.value = new SentenceElement[elementsCount];
        for (int i = 0; i < elementsCount; i++) {
            this.value[i] = null;
        }
    }
    public Sentence(SentenceElement[] sentElements) {
        this.value = new SentenceElement[sentElements.length];
        for (int i = 0; i < sentElements.length; i++) {
            this.value[i] = sentElements[i];
        }
    }

    public SentenceElement[] getValue() {
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

    private void incrementSize() {
        int newSize = this.value.length + 1;
        SentenceElement[] temp = new SentenceElement[newSize];
        for (int i = 0; i < this.value.length; i++) {
            temp[i] = this.value[i];
        }
        this.value = temp;
    }

    public void add(Word word) {
        incrementSize();
        this.value[this.value.length - 1] = new Word(word);
    }

    public void add(PunctuationMark pm) {
        incrementSize();
        this.value[this.value.length - 1] = new PunctuationMark(pm.getValue());
    }
}

/**
 * Class implements the text - a sequence of sentences and/or punctuation marks.
 */
class Text {
    protected Sentence[] value;

    public Text() {
        this.value = new Sentence[0];
    }
    public Text(int sentencesCount) {
        this.value = new Sentence[sentencesCount];
    }

    public Sentence[] getValue() {
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
        return result;
    }
}
