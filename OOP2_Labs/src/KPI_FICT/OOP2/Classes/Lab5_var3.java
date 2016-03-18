package KPI_FICT.OOP2.Classes;

/**
 * Created by Max Donchenko (https://github.com/goodwin64) on 09.03.2016.
 */
public class Lab5_var3 {
    public static void main(String[] args) {

    }
}

/**
 * Class implements the word - a sequence of alphabet characters:
 * A..Z, a..z
 */
class Word extends SentenceElement {
}

/**
 * Class implements the punctuation mark:
 * [ ,:;-()"'] in sentences between words,
 * [.!?\n] in text between sentences.
 */
class PunctuationMark extends SentenceElement {
}

/**
 * Class implements the element of sentence: a word or a punctuation mark.
 *
 * Fundamental difference between Word and PunctuationMark
 * is that the Word contains String value, PM contains char value.
 */
class SentenceElement {
}

/**
 * Class implements the sentence - a sequence of sentence elements:
 * words and/or punctuation marks.
 */
class Sentence {
}

/**
 * Class implements the text - a sequence of sentences and/or punctuation marks.
 */
class Text {
}
