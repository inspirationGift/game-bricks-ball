package main.builder.entities;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;

public class ScorePanel extends JPanel {

    public JButton next;
    public JButton restart;
    private JTextArea ta;
    private FlowLayout layout;

    public int gameScoreText;
    public int levelText;
    public int lifeText;


    public int getLife() {
        return lifeText;
    }


    public void setLife(int lifeText) {
        this.lifeText = lifeText;
    }


    public ScorePanel() {
        this.restart = new JButton("Restart");
        this.next = new JButton("Next");
        this.ta = new JTextArea();
        this.layout = new FlowLayout();
        setPanel();
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
        this.next.setVisible(true);
        setLayout(layout);
        this.layout.setAlignment(FlowLayout.LEADING);
        this.ta.setText("Level #" + this.levelText + "  Score:  " + this.gameScoreText + " Life:  " + this.lifeText);
        add(this.restart);
        add(this.next);
        add(this.ta);
    }

}
