package edu.utdallas.wxz180008.autoindex;

import edu.utdallas.wxz180008.models.Sentence;
import edu.utdallas.wxz180008.operators.BatchProcess;

import java.io.IOException;
import java.util.List;

public class AutoIndexView implements AutoIndexContract.View {

    private AutoIndexContract.Presenter presenter;

    public AutoIndexView() {
        init();
    }

    @Override
    public void showLineStorage(List<Sentence> sentences) {

    }

    @Override
    public void showCircularShiftedSentences(List<Sentence> sentences) {

    }

    @Override
    public void showAlphabetizedSentences(List<Sentence> sentences) {
    }

    @Override
    public void processFile(String filename) throws IOException {
        presenter.processFile(filename);
    }

    @Override
    public void init() {
        new AutoIndexPresenter(this);
    }

    @Override
    public void setPresenter(AutoIndexContract.Presenter presenter) {
        this.presenter = presenter;
        this.presenter.setAlgorithm(new BatchProcess());
    }
}
