package edu.utdallas.wxz180008.autoindex;

import com.datastax.driver.core.Session;
import com.google.common.base.Strings;
import edu.utdallas.wxz180008.data.CassandraConnector;
import edu.utdallas.wxz180008.data.CoogleRepository;
import edu.utdallas.wxz180008.data.SchemaRepository;
import edu.utdallas.wxz180008.models.Sentence;
import edu.utdallas.wxz180008.operators.BaseProcessAlgorithm;
import edu.utdallas.wxz180008.operators.BatchProcess;
import edu.utdallas.wxz180008.util.CassandraHelper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class AutoIndexPresenter implements AutoIndexContract.Presenter, BaseProcessAlgorithm.Callback {
    private final AutoIndexContract.View view;

    private BaseProcessAlgorithm algorithm;

    public AutoIndexPresenter(AutoIndexContract.View view) {
        this.view = view;
        this.algorithm = new BatchProcess();
        this.view.setPresenter(this);
    }

    @Override
    public void processSentence(String sentence) {
        Sentence newSentence = new Sentence()
                .withOriginal(sentence);
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
    public void processFile(String filename) throws IOException {
        List<Sentence> sentences = new ArrayList<>();

        Reader in = new FileReader(filename);
        Iterable<CSVRecord> records = CSVFormat.RFC4180.parse(in);
        for (CSVRecord record : records) {
            if (record.size() < 3) continue;

            Sentence sentence = new Sentence()
                    .withUrl(record.get(0))
                    .withOriginal(record.get(1))
                    .withTitle(record.get(1))
                    .withDescription(record.get(2));

            if (isValidSentence(sentence))
                sentences.add(sentence);
        }

        processSentences(sentences);
    }

    private boolean isValidSentence(Sentence sentence) {
        return !Strings.isNullOrEmpty(sentence.getUrl()) && !Strings.isNullOrEmpty(sentence.getTitle()) && !Strings.isNullOrEmpty(sentence.getDescription());
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

        CassandraConnector connector = new CassandraConnector();
        connector.connect(CassandraHelper.getInstance().getIp(), CassandraHelper.getInstance().getPort());
        Session session = connector.getSession();

        SchemaRepository sr = new SchemaRepository(session);
        sr.useKeyspace("coogle");

        CoogleRepository cr = new CoogleRepository(session);
        cr.insertSentencesBatch(sentences);
        connector.close();
    }
}
