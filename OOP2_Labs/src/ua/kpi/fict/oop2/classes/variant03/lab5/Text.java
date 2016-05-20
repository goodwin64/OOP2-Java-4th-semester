package ua.kpi.fict.oop2.classes.variant03.lab5;

import java.util.ArrayList;

/**
 * Class implements the text - a sequence of sentences and/or punctuation marks.
 */
public class Text {
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
