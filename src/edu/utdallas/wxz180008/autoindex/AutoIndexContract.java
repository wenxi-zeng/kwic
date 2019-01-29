package edu.utdallas.wxz180008.autoindex;

import edu.utdallas.wxz180008.interfaces.BasePresenter;
import edu.utdallas.wxz180008.interfaces.BaseView;
import edu.utdallas.wxz180008.models.Sentence;
import edu.utdallas.wxz180008.operators.BaseProcessAlgorithm;

import java.io.IOException;
import java.util.List;

public interface AutoIndexContract {
    interface View extends BaseView<Presenter> {

        void showLineStorage(List<Sentence> sentences);

        void showCircularShiftedSentences(List<Sentence> sentences);

        void showAlphabetizedSentences(List<Sentence> sentences);

        void processFile(String filename) throws IOException;

        void init();

        void reset();

    }

    interface Presenter extends BasePresenter {

        void processSentence(String sentence);

        void processSentence(Sentence sentence);

        void processSentences(List<Sentence> sentences);

        void setAlgorithm(BaseProcessAlgorithm algorithm);

        void processFile(String filename) throws IOException;

    }
}
