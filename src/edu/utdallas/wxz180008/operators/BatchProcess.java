package edu.utdallas.wxz180008.operators;

import edu.utdallas.wxz180008.models.Sentence;

import java.util.ArrayList;
import java.util.List;

public class BatchProcess implements BaseProcessAlgorithm {
    @Override
    public void process(List<Sentence> newSentences, Callback callback) {
        LineStorage.getInstance().execute(newSentences);
        List<Sentence> results = new ArrayList<>();

        for (Sentence sentence : newSentences) {
            results.addAll(CircularShifter.getInstance().execute(sentence));
        }

        if (callback != null) {
            callback.onShifted(results);
        }

        Alphabetizer.getInstance().execute(results);
        if (callback != null) {
            callback.onAlphabetized(results);
        }
    }
}
