package edu.utdallas.wxz180008.operators;

import edu.utdallas.wxz180008.models.Sentence;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class Alphabetizer{

    private static volatile Alphabetizer instance = null;

    private List<Sentence> sentences;

    private Alphabetizer() {
        sentences = new ArrayList<>();
    }

    public static Alphabetizer getInstance() {
        if (instance == null) {
            synchronized(Alphabetizer.class) {
                if (instance == null) {
                    instance = new Alphabetizer();
                }
            }
        }

        return instance;
    }

    public List<Sentence> execute(List<Sentence> sentences) {
        this.sentences.addAll(sentences);
        this.sentences.sort(Comparator.comparing(o -> o.getOriginal().toLowerCase()));
        return sentences;
    }

    public List<Sentence> getSentences() {
        return sentences;
    }
}
