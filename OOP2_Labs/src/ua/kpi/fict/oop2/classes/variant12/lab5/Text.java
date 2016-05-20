package ua.kpi.fict.oop2.classes.variant12.lab5;

/**
 * Class implements the text - a sequence of sentences and/or punctuation marks.
 */
public class Text {
    public Sentence[] value;

    public Text() {
        this.value = new Sentence[1];
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
            result += sentence;
        }
        return result;
    }
}
