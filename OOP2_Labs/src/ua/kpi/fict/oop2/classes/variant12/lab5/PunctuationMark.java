package ua.kpi.fict.oop2.classes.variant12.lab5;

/**
 * Class implements the punctuation mark:
 * [ ,:;-()"'] in sentences between words,
 * [.!?\n] in text between sentences.
 */
public class PunctuationMark extends SentenceElement {
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
