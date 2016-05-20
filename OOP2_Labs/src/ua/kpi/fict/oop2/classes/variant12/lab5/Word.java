package ua.kpi.fict.oop2.classes.variant12.lab5;

import java.util.Arrays;

/**
 * Class implements the word - a sequence of alphabet characters:
 * A..Z, a..z
 */
public class Word extends SentenceElement {
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
    public Word(String word) {
        this.value = new Letter[word.length()];
        for (int i = 0; i < word.length(); i++) {
            this.value[i] = new Letter(word.charAt(i));
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
        String result = "";
        for (Letter l : this.value) {
            result += l;
        }
        return result;
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
