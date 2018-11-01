package edu.utdallas.wxz180008.testpanel;

import edu.utdallas.wxz180008.models.Sentence;
import edu.utdallas.wxz180008.operators.Alphabetizer;
import edu.utdallas.wxz180008.operators.CircularShifter;
import edu.utdallas.wxz180008.operators.IncrementalProcess;
import edu.utdallas.wxz180008.operators.LineStorage;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class TestPanelView extends Applet implements TestPanelContract.View, KeyListener {

    private TextField nameField;
    private TestPanelContract.Presenter presenter;
    private int lineWidth = 100;
    private int padding = 20;

    @Override
    public void init() {
        super.init();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize);
        lineWidth = (screenSize.width / 3) - padding;

        nameField = new TextField("",200);
        nameField.setBounds(20,70,screenSize.width - 20,100);
        nameField.addKeyListener(this);
        add(nameField);

        new TestPanelPresenter(this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        drawSentences(g, LineStorage.getInstance().getSentences(),padding / 2, 100);
        drawSentences(g, CircularShifter.getInstance().getSentences(),lineWidth + padding, 100);
        drawSentences(g, Alphabetizer.getInstance().getSentences(),2 * lineWidth + 3 * padding / 2, 100);
    }

    @Override
    public void showLineStorage(List<Sentence> sentences) {
    }

    @Override
    public void showCircularShiftedSentences(List<Sentence> sentences) {
    }

    @Override
    public void showAlphabetizedSentences(List<Sentence> sentences) {
        repaint();
    }

    @Override
    public void setPresenter(TestPanelContract.Presenter presenter) {
        this.presenter = presenter;
        this.presenter.setAlgorithm(new IncrementalProcess());
    }

    public void drawSentences(Graphics g, List<Sentence> sentences, int x, int y) {
        for (Sentence sentence : sentences) {
            g.setColor(Color.decode(sentence.getUrl()));
            y = drawStringMultiLine(g, sentence.getOriginal(), lineWidth, x, y);
        }
    }

    public int drawStringMultiLine(Graphics g, String text, int lineWidth, int x, int y) {
        FontMetrics m = g.getFontMetrics();
        if(m.stringWidth(text) < lineWidth) {
            g.drawString(text, x, y);
        } else {
            String[] words = text.split(" ");
            String currentLine = words[0];
            for(int i = 1; i < words.length; i++) {
                if(m.stringWidth(currentLine+words[i]) < lineWidth) {
                    currentLine += " "+words[i];
                } else {
                    g.drawString(currentLine, x, y);
                    y += m.getHeight();
                    currentLine = words[i];
                }
            }
            if(currentLine.trim().length() > 0) {
                g.drawString(currentLine, x, y);
            }
        }

        return y + 3 * m.getHeight() / 2;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
            presenter.processSentence(nameField.getText());
            nameField.setText("");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


}
