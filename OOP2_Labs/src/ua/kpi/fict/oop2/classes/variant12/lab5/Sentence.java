package ua.kpi.fict.oop2.classes.variant12.lab5;

/**
 * Class implements the sentence - a sequence of sentence elements:
 * words and/or punctuation marks.
 */
public class Sentence {
    public SentenceElement[] value;

    public Sentence(int elementsCount) {
        this.value = new SentenceElement[elementsCount];
        for (int i = 0; i < elementsCount; i++) {
            this.value[i] = null;
        }
    }
    public Sentence(SentenceElement[] sentElements) {
        this.value = new SentenceElement[sentElements.length];
        for (int i = 0; i < sentElements.length; i++) {
            this.value[i] = sentElements[i];
        }
    }

    public SentenceElement[] getValue() {
        return value;
    }

    @Override
    public String toString() {
        String result = "";
        for (SentenceElement se : value) {
            result += se;
        }
        return result;
    }

    private void incrementSize() {
        int newSize = this.value.length + 1;
        SentenceElement[] temp = new SentenceElement[newSize];
        for (int i = 0; i < this.value.length; i++) {
            temp[i] = this.value[i];
        }
        this.value = temp;
    }

    public void add(Word word) {
        incrementSize();
        this.value[this.value.length - 1] = new Word(word);
    }

    public void add(PunctuationMark pm) {
        incrementSize();
        this.value[this.value.length - 1] = new PunctuationMark(pm.getValue());
    }
}
