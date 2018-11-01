package edu.utdallas.wxz180008.testpanel;

import edu.utdallas.wxz180008.interfaces.BasePresenter;
import edu.utdallas.wxz180008.interfaces.BaseView;
import edu.utdallas.wxz180008.models.Sentence;
import edu.utdallas.wxz180008.operators.BaseProcessAlgorithm;

import java.util.List;

public class TestPanelContract {

    interface View extends BaseView<Presenter> {

        void showLineStorage(List<Sentence> sentences);

        void showCircularShiftedSentences(List<Sentence> sentences);

        void showAlphabetizedSentences(List<Sentence> sentences);

    }

    interface Presenter extends BasePresenter {

        void processSentence(String sentence);

        void processSentence(Sentence sentence);

        void processSentences(List<Sentence> sentences);

        void setAlgorithm(BaseProcessAlgorithm algorithm);

    }

}
