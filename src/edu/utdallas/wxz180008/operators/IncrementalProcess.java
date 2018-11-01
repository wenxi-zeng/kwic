package edu.utdallas.wxz180008.operators;

import edu.utdallas.wxz180008.models.Sentence;

import java.util.List;

public class IncrementalProcess implements BaseProcessAlgorithm {
    @Override
    public void process(List<Sentence> newSentences,  Callback callback) {
        LineStorage.getInstance().execute(newSentences);

        for (Sentence sentence : newSentences) {
            List<Sentence> results = CircularShifter.getInstance().execute(sentence);
            if (callback != null)
                callback.onShifted(results);

            Alphabetizer.getInstance().execute(results);
            if (callback != null)
                callback.onAlphabetized(results);
        }
    }
}
