package edu.utdallas.wxz180008.testpanel;

import edu.utdallas.wxz180008.models.Sentence;
import edu.utdallas.wxz180008.operators.BaseProcessAlgorithm;
import edu.utdallas.wxz180008.operators.BatchProcess;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestPanelPresenter implements TestPanelContract.Presenter, BaseProcessAlgorithm.Callback {

    private final TestPanelContract.View view;

    private BaseProcessAlgorithm algorithm;

    public TestPanelPresenter(TestPanelContract.View view) {
        this.view = view;
        this.algorithm = new BatchProcess();
        this.view.setPresenter(this);
    }

    @Override
    public void processSentence(String sentence) {
        Random random = new Random();
        int nextInt = random.nextInt(0xffffff + 1);
        String colorCode = String.format("#%06x", nextInt);

        Sentence newSentence = new Sentence()
                .withOriginal(sentence)
                .withUrl(colorCode);
        processSentence(newSentence);
    }

    @Override
    public void processSentence(Sentence sentence) {
        List<Sentence> sentences = new ArrayList<>();
        sentences.add(sentence);
        algorithm.process(sentences, this);
    }

    @Override
    public void processSentences(List<Sentence> sentences) {
        algorithm.process(sentences, this);
    }

    @Override
    public void setAlgorithm(BaseProcessAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public void start() {

    }

    @Override
    public void onShifted(List<Sentence> sentences) {
        view.showCircularShiftedSentences(sentences);
    }

    @Override
    public void onAlphabetized(List<Sentence> sentences) {
        view.showAlphabetizedSentences(sentences);
    }
}
