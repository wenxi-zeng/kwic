package edu.utdallas.wxz180008.operators;

import edu.utdallas.wxz180008.models.Sentence;

import java.util.List;

public interface BaseProcessAlgorithm {

    void process(List<Sentence> newSentences, Callback callback);

    interface Callback {

        void onShifted(List<Sentence> sentences);

        void onAlphabetized(List<Sentence> sentences);
    }

}
