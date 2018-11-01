package edu.utdallas.wxz180008.operators;

import edu.utdallas.wxz180008.models.Sentence;

import java.util.ArrayList;
import java.util.List;

public final class CircularShifter {

    private static volatile CircularShifter instance = null;

    private List<Sentence> sentences;

    private CircularShifter() {
        sentences = new ArrayList<>();
    }

    public static CircularShifter getInstance() {
        if (instance == null) {
            synchronized(CircularShifter.class) {
                if (instance == null) {
                    instance = new CircularShifter();
                }
            }
        }

        return instance;
    }

    public List<Sentence> execute(Sentence sentence) {

        String words[] = sentence.getOriginal().split("[^a-zA-Z0-9]");
        List<Sentence> shiftedSentences = new ArrayList<>();

        for (int counter = 0; counter < words.length; counter++) {
            StringBuilder shiftedPermutation = new StringBuilder();
            int cursor = counter;
            int len = 0;

            while (len != words.length) {
                shiftedPermutation.append(words[cursor]).append(" ");
                cursor = (cursor + 1 )% words.length;
                len++;
            }

            shiftedSentences.add(new Sentence(shiftedPermutation.toString().trim(), sentence.getUrl()));
        }

        sentences.addAll(shiftedSentences);
        return shiftedSentences;
    }

    public List<Sentence> getSentences() {
        return sentences;
    }
}
