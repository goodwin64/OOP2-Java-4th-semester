package ua.kpi.fict.oop2.classes.variant12.lab5;

/**
 * Class implements the letter - a part of word.
 */
public class Letter {
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
