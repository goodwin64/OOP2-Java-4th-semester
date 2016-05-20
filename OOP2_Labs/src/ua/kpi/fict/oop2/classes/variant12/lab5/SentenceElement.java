package ua.kpi.fict.oop2.classes.variant12.lab5;

/**
 * Class implements the element of sentence: a word or a punctuation mark.
 *
 * Fundamental difference between Word and PunctuationMark
 * is that the Word contains array of Letters value, PM contains char value.
 */
public abstract class SentenceElement {
    public abstract String toString();
}
