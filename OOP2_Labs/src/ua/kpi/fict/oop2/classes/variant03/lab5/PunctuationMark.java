package ua.kpi.fict.oop2.classes.variant03.lab5;

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
