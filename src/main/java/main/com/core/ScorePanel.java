package main.com.core;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;

public class ScorePanel extends JPanel {

    public JButton save;
    public JButton restart;
    public JButton next;

    private JTextArea ta;
    private FlowLayout layout;
    public int gameScoreText;
    public int levelText;
    public int lifeText;
    private int record;


    public ScorePanel(int record) {
        this.restart = new JButton("New");
        this.save = new JButton("Save");
        this.next = new JButton("Upload");
        this.ta = new JTextArea();
        this.layout = new FlowLayout();
        this.record = record;
        setPanel();
    }

    public boolean doesRewrite() {
        if (gameScoreText > record) {
            this.record = gameScoreText;
            return true;
        }
        return false;

    }

    public void reset() {
        this.levelText = 0;
        this.lifeText = 0;
        this.gameScoreText = 0;

    }

    public int getLife() {
        return lifeText;
    }


    public void setLife(int lifeText) {
        this.lifeText = lifeText;
    }

    public void update() {
        this.ta.setText("#" + this.levelText + " Score:  " + this.gameScoreText + " Life: "
                + this.lifeText + " Record: " + this.record);
    }

    public void setScore(PropertyChangeEvent evt) {
        this.gameScoreText += ((int) evt.getNewValue() - (int) evt.getOldValue());
        if (this.gameScoreText % 500 == 0) this.lifeText += 1;
        update();
    }

    private void setPanel() {
        this.ta.setEditable(false);
        this.ta.setVisible(true);
        this.ta.setFocusable(false);
        this.restart.setFocusable(false);
        this.next.setFocusable(false);
        this.save.setFocusable(false);
        setLayout(layout);
        this.layout.setAlignment(FlowLayout.LEFT);
        update();
        add(this.restart);
        add(this.ta);
        add(this.next);
        add(this.save);
    }

}
