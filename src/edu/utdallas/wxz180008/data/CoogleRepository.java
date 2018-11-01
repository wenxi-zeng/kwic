package edu.utdallas.wxz180008.data;

import com.datastax.driver.core.Session;
import edu.utdallas.wxz180008.models.Sentence;

import java.util.List;

public class CoogleRepository {
    private static final String TABLE_NAME = "indices";

    private Session session;

    public CoogleRepository(Session session) {
        this.session = session;
    }

    /**
     * Insert a list of sentences using a batch query.
     *
     * @param sentences
     */
    public void insertSentencesBatch(List<Sentence> sentences) {
        for (Sentence sentence : sentences) {
            insertSentence(sentence);
        }
    }

    public void insertSentence(Sentence sentence) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ").append(TABLE_NAME)
                .append("(idx, clicks, description, title, url) ")
                .append("VALUES ('")
                .append(sentence.getOriginal()).append("', ")
                .append(0).append(", '")
                .append(sentence.getDescription()).append("', '")
                .append(sentence.getTitle()).append("', '")
                .append(sentence.getUrl()).append("');");

        session.execute(sb.toString());
    }
}
