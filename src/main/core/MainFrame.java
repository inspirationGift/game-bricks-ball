package main.core;

import main.builder.entities.HeaderScorePanel;
import main.levels.Levels;

import javax.swing.*;
import java.awt.*;

public class MainFrame {

    private final JFrame frame;
    private Levels levelPanel;
    private GamePanel game;
    private HeaderScorePanel headerScorePanel;


    public MainFrame() {
        this.frame = new JFrame("Blocks & Fun");
        this.headerScorePanel = new HeaderScorePanel();
        init();
    }

    public void init() {
        newGame();
        this.frame.getContentPane().add(BorderLayout.CENTER, this.game);
        this.frame.getContentPane().add(BorderLayout.NORTH, this.headerScorePanel);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(490, 600);
        this.frame.setVisible(true);
        this.frame.setResizable(false);
        refresh();
    }

    private void newGame() {
        this.levelPanel = new Levels(this.headerScorePanel.level);
        this.game = levelPanel.getGamePanel();
        this.game.addPropertyChangeListener(evt -> {
            if (evt.getPropertyName().equals("ScoreValue")) {
                this.headerScorePanel.setScore(evt);
                refresh();
            }
        });
        this.headerScorePanel.next.addActionListener(e -> {
            if (this.game.changeLevel) {
                this.headerScorePanel.level++;
                reLaunch();
            }
        });
        this.headerScorePanel.restart.addActionListener(e -> {
            this.headerScorePanel.level = 0;
            reLaunch();
        });
    }

    private void reLaunch() {
        this.frame.remove(this.game);
        this.levelPanel.destroy();
        this.frame.setVisible(false);
        init();
    }

    private void refresh() {
        this.frame.revalidate();
        this.frame.repaint();
    }

}
