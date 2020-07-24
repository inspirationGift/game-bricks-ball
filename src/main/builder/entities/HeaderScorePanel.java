package main.builder.entities;

import javax.security.auth.callback.Callback;
import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;

public class HeaderScorePanel extends JPanel {

    public JButton next;
    public JButton restart;
    private JTextArea ta;
    private FlowLayout layout;

    public int levelScore;
    public int gameScore;
    public int level;
    public int life;


    public HeaderScorePanel() {
        this.restart = new JButton("Restart");
        this.next = new JButton("Next");
        this.ta = new JTextArea();
        this.layout = new FlowLayout();
        setPanel();
    }

    public void setScore(PropertyChangeEvent evt) {
        this.levelScore = (int) evt.getNewValue();
        this.gameScore += (int) evt.getNewValue() - (int) evt.getOldValue();
        this.ta.setText("Level #" + this.level + "  Score:  " + this.gameScore + " Life:  " + this.life);
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
        this.ta.setText("Level #" + this.level + "  Score:  " + this.gameScore + " Life:  " + this.life);
        add(this.restart);
        add(this.next);
        add(this.ta);
    }

    public boolean addListeners(boolean changeLevel) {
        var ref = new Object() {
            boolean val = false;
        };

        this.restart.addActionListener(e -> {
            this.level = 0;
            ref.val = true;
        });
        return ref.val;
    }


}
