package edu.utdallas.wxz180008.data;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import edu.utdallas.wxz180008.models.Sentence;

import java.util.List;
import java.util.UUID;

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
            try {
                insertSentence(sentence);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void insertSentence(Sentence sentence) {
        PreparedStatement statement = session.prepare(
                "INSERT INTO " + TABLE_NAME + " (dupid, idx, clicks, description, title, url) " +
                        "VALUES (?, ?, ?, ? , ? , ?) IF NOT EXISTS;");

        BoundStatement boundStatement = statement.bind(
                sentence.getOriginal(), sentence.getOriginal(), 0, sentence.getDescription(), sentence.getTitle(), sentence.getUrl());

        session.execute(boundStatement);
    }
}
