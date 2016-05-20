package ua.kpi.fict.oop2.classes.variant03.lab5;

/**
 * Created by Max Donchenko (https://github.com/goodwin64) on 21.05.2016.
 */
public class Letter {
    private char value;

    public Letter() {

    }
    public Letter(char value) {
        try {
            setValue(value);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) throws IllegalArgumentException {
        if (Character.isLetter(value)) {
            this.value = value;
        } else {
            throw new IllegalArgumentException("Not a letter");
        }
    }
}
