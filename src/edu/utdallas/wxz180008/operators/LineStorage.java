package edu.utdallas.wxz180008.operators;

import edu.utdallas.wxz180008.models.Sentence;

import java.util.ArrayList;
import java.util.List;

public final class LineStorage {

    private List<Sentence> sentences;

    private static volatile LineStorage instance = null;

    private LineStorage() {
        sentences = new ArrayList<>();
    }

    public static LineStorage getInstance() {
        if (instance == null) {
            synchronized(LineStorage.class) {
                if (instance == null) {
                    instance = new LineStorage();
                }
            }
        }

        return instance;
    }

    public List<Sentence> getSentences() {
        return sentences;
    }

    public void execute(List<Sentence> sentences) {
        this.sentences.addAll(sentences);
    }

}
