package ua.kpi.fict.oop2.classes.variant03.lab5;

import java.util.ArrayList;

/**
 * Class implements the word - a sequence of alphabet characters:
 * A..Z, a..z
 */
public class Word extends SentenceElement implements Comparable<Word> {
    private ArrayList<Letter> value;
    public static final char[] vowels = {
            'a', 'e', 'i', 'o', 'u', 'y'
    };

    public Word() {
        this.value = new ArrayList<>(1);
    }
    public Word(String word) {
        try {
            setValue(word);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getValue() {
        String result = "";
        for (Letter letter : value) {
            result += letter.getValue();
        }
        return result;
    }

    @Override
    public void setValue(String value) {
        this.value = new ArrayList<>(value.length());
        for (char c : value.toCharArray()) {
            this.value.add(new Letter(c));
        }
    }

    @Override
    public String toString() {
        return getValue();
    }

    public int countVowels() {
        int count = 0;
        for (char c : this.getValue().toCharArray()) {
            char cLower = Character.toLowerCase(c);
            for (char vowel : vowels) {
                if (cLower == vowel) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    @Override
    /**
     * Comparing by (in queue order):
     * 1) number of vowels (descending)
     * 2) length (descending)
     * 3) abc rules (ascending)
     */
    public int compareTo(Word other) {
        int vowelsDiff = other.countVowels() - this.countVowels();
        if (vowelsDiff != 0) {
            return vowelsDiff;
        }

        int lengthDiff = other.getValue().length() - this.getValue().length();
        if (lengthDiff != 0) {
            return lengthDiff;
        }

        return this.getValue().compareTo(other.getValue());
    }

    public void add(char c) {
        setValue(getValue() + c);
    }
}
