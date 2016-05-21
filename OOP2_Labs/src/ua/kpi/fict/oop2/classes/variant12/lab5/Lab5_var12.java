package ua.kpi.fict.oop2.classes.variant12.lab5;

import ua.kpi.fict.oop2.classes.variant12.lab5.*;

import java.io.*;
import java.util.Scanner;

/**
 * Created by Rock(https://github.com/Filin-Rock) on 25.03.2016.
 */
public class Lab5_var12 {
    public static void main(String[] args) {
        String pathPrefix = "src\\ua\\kpi\\fict\\oop2\\Resources\\Variant12\\";
        String fileNameIn = "Lab5_var12_in";
        String fileNameOut = "Lab5_var12_out";
        String fileExtension = ".txt";

        String textFromFile = readTextFromFile(pathPrefix + fileNameIn + fileExtension);
        Text text = parseTextToObjects(textFromFile);

        System.out.println(text);

        Scanner scanner = new Scanner(System.in);
        int lenOfWord = 0;
        String sameWord = null;
        try {
            System.out.print("Укажите длину слова для замены: ");
            lenOfWord = scanner.nextInt();
            System.out.print("Укажите на какое слово будем менять: ");
            sameWord = scanner.next();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        replaceSomeWords(lenOfWord, sameWord, text);
        System.out.println(text);
        writeToFile(pathPrefix + fileNameOut + fileExtension, text);
    }

    public static String readTextFromFile(String path) {
        String result = "";
        int c;
        FileInputStream in;

        try {
            in = new FileInputStream(path);
            while ((c = in.read()) != -1) {
                result += (char) c;
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Text parseTextToObjects(String text) {
        int sentencesCount = 0;
        Text result = new Text(countSentences(text) + 1);
        char currentChar;
        Word lastWord = new Word();
        Word emptyWord = new Word();
        PunctuationMark lastPM = new PunctuationMark();
        Sentence lastSentence = new Sentence(0);

        for (int i = 0; i < text.length(); i++) {
            currentChar = text.charAt(i);
            if (Character.isLetter(currentChar)) {
                lastWord.add(new Letter(currentChar));
            } else {
                lastPM.setValue(String.valueOf(currentChar));
                boolean isSentenceDelimiter = new String(PunctuationMark.sentenceDelimiters).indexOf(currentChar) != -1;
                boolean isTextDelimiter = new String(PunctuationMark.textDelimiters).indexOf(currentChar) != -1;
                if (isSentenceDelimiter) {

                    if (!emptyWord.equals(lastWord)) {
                        lastSentence.add(new Word(lastWord));
                    }
                    lastSentence.add(new PunctuationMark(lastPM.getValue()));
                    lastWord.setValue("");
                } else if (isTextDelimiter) {

                    if (!emptyWord.equals(lastWord)) {
                        lastSentence.add(new Word(lastWord));
                    }
                    lastWord.setValue("");
                    lastSentence.add(new PunctuationMark(lastPM.getValue()));
                    result.value[sentencesCount++] = new Sentence(lastSentence.getValue());
                    lastSentence = new Sentence(0);
                } else {

                }
            }
        }
        if (!lastWord.equals(emptyWord)) {
            lastSentence.add(new Word(lastWord));
        }
        if (!lastSentence.equals(new Sentence(0))) {
            result.value[sentencesCount++] = new Sentence(lastSentence.getValue());
        }
        return result;
    }

    private static int countSentences(String text) {
        int result = 0;
        for (char c : text.toCharArray()) {
            for (char textDelimiter : PunctuationMark.textDelimiters) {
                if (c == textDelimiter) {
                    result++;
                    break;
                }
            }
        }
        return result;
    }

    public static void replaceSomeWords(int wordsLength, String replacer, Text text) {
        for (Sentence sentence : text.value) {
            for (int i = 0; i < sentence.value.length; i++) {
                SentenceElement se = sentence.value[i];
                if (se instanceof Word
                        && ((Word) se).getValue().length == wordsLength) {
                    sentence.value[i] = new Word(replacer);
                }
            }
        }
    }

    public static void writeToFile(String path, Text text) {
        File file = new File(path);
        PrintWriter printWriter = null;

        try {
            printWriter = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (printWriter != null) {
            for (Sentence sentence : text.value) {
                for (SentenceElement sentenceElement : sentence.value) {
                    if (sentenceElement instanceof PunctuationMark) {
                        char toWrite = ((PunctuationMark) sentenceElement).getValue();
                        printWriter.write(toWrite);
                    } else if (sentenceElement instanceof Word) {
                        for (Letter letter : ((Word) sentenceElement).getValue()) {
                            printWriter.write(letter.getValue());
                        }
                    }
                }
            }
            printWriter.close();
        }
    }
}

