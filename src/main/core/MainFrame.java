package main.core;

import main.levels.Levels;

import javax.swing.*;
import java.awt.*;

public class MainFrame {

    private final JFrame frame;
    private Levels levels;
    private GamePanel game;
    private ScorePanel scorePanel;

    public MainFrame() {
        this.frame = new JFrame("Blocks & Fun");
        this.scorePanel = new ScorePanel();
        init();
    }

    public void init() {
        newGame();
        this.frame.getContentPane().add(BorderLayout.CENTER, this.game);
        this.scorePanel.update();
        this.frame.getContentPane().add(BorderLayout.NORTH, this.scorePanel);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(490, 600);
        this.frame.setVisible(true);
        this.frame.setResizable(false);
    }

    private void newGame() {

        this.levels = new Levels(this.scorePanel.levelText);
        this.game = this.levels.getGamePanel();
        this.game.addPropertyChangeListener(evt -> {
            if (evt.getPropertyName().equals("ScoreValue")) {
                this.scorePanel.setScore(evt);
                this.levels.life = this.scorePanel.getLife();
                refresh();
            }
            if (evt.getPropertyName().equals("GameOver")) {
                if (this.scorePanel.getLife() > 0 && (int) evt.getNewValue() == 1) {
                    this.game.stopGame();
                    this.scorePanel.setLife(this.scorePanel.lifeText - 1);
                    this.scorePanel.update();
                    this.game.continueGame();
                    refresh();
                }
            }
        });
        this.scorePanel.next.addActionListener(e -> {
            if (this.game.isWon) {
                this.scorePanel.levelText++;
                reLaunch();
            }
        });
        this.scorePanel.save.addActionListener(e -> {
//            this.game.an();
        });
        this.scorePanel.restart.addActionListener(e -> {
            this.scorePanel.levelText = 0;
            this.scorePanel.lifeText = 0;
            this.scorePanel.gameScoreText = 0;
            this.scorePanel.update();
            reLaunch();
        });
    }

    private void reLaunch() {
        this.frame.remove(this.game);
        this.frame.remove(this.scorePanel);
        this.frame.setVisible(false);
        init();
        refresh();
    }

    private void refresh() {

        this.frame.revalidate();
        this.frame.repaint();
    }
}
