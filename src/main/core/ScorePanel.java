package main.core;

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


    public ScorePanel() {
        this.save = new JButton("Save");
        this.restart = new JButton("Restart");
        this.next = new JButton("Next");
        this.ta = new JTextArea();
        this.layout = new FlowLayout();
        setPanel();
    }

    public int getLife() {
        return lifeText;
    }


    public void setLife(int lifeText) {
        this.lifeText = lifeText;
    }

    public void update() {
        this.ta.setText("Level #" + this.levelText + "  Score:  " + this.gameScoreText + " Life:  " + this.lifeText);
    }

    public void setScore(PropertyChangeEvent evt) {
        this.gameScoreText += ((int) evt.getNewValue() - (int) evt.getOldValue());
        if (this.gameScoreText % 100 == 0) this.lifeText += 1;
        this.ta.setText("Level #" + this.levelText + "  Score:  " + this.gameScoreText + " Life:  " + this.lifeText);
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
        this.ta.setText("Level #" + this.levelText + "  Score:  " + this.gameScoreText + " Life:  " + this.lifeText);
        add(this.save);
        add(this.restart);
        add(this.next);
        add(this.ta);
    }

}
