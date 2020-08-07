package main.com.core;

import main.com.levels.Levels;
import main.com.state.GameStateImpl;
import main.com.state.State;
import main.com.state.StateDTO;
import main.com.state.StateService;
import main.com.utils.BOException;
import main.com.utils.Recorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame {

    private final JFrame frame;
    private Levels levels;
    private GamePanel game;
    private ScorePanel scorePanel;
    private final State gameStateImpl;
    boolean uploaded;
    StateDTO state;

    public MainFrame() throws BOException {
        this.gameStateImpl = new GameStateImpl(new Recorder("records/state.json"), new StateService());
        this.frame = new JFrame("Blocks & Fun");
        this.scorePanel = new ScorePanel();
        this.game = new GamePanel();
        this.frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    scorePanel.updateRecord();
                } catch (BOException boException) {
                    boException.printStackTrace();
                }
                System.exit(0);
            }
        });
        addListeners();
        newGame();
        init();
    }

    public void init() {
        this.frame.getContentPane().add(BorderLayout.CENTER, this.game);
        this.frame.getContentPane().add(BorderLayout.NORTH, this.scorePanel);
        this.frame.setVisible(true);
        this.frame.setResizable(false);
    }

    private void newGame() {
        this.levels = new Levels(this.scorePanel.levelText);
        this.game.newGameLauncher(this.levels.levelSettings);
        this.frame.setSize(this.levels.frameWidth, this.levels.frameHeight);
    }

    public void addListeners() {
        this.game.addPropertyChangeListener(evt -> {
            if (evt.getPropertyName().equals("ScoreValue")) {
                this.scorePanel.setScore(evt);
                this.levels.life = this.scorePanel.getLife();
                refresh();
            }
            if (evt.getPropertyName().equals("GameOver")) {
                if (this.scorePanel.getLife() > 0) {
                    this.game.setStop();
                    this.scorePanel.setLife(this.scorePanel.lifeText - 1);
                    this.scorePanel.update();
                    this.game.continueGame();
                    refresh();
                }
            }
            if (evt.getPropertyName().equals("Win") && this.game.isWon) {
                this.scorePanel.next.setText("Continue");
                refresh();
            }
        });
        this.scorePanel.next.addActionListener(e -> {
            if (this.game.isWon && e.getActionCommand().equals("Continue")) {
                this.game.setStop();
                this.scorePanel.next.setText("Upload");
                this.scorePanel.levelText++;
                this.game.isWon = false;
                reLaunch();
                newGame();
                init();
                refresh();
            }
            if (e.getActionCommand().equals("Upload") && !uploaded) {
                this.uploaded = true;
                try {
                    this.state = gameStateImpl.getState();
                } catch (BOException boException) {
                    boException.printStackTrace();
                }
                if (state != null) {
                    this.game.setStop();
                    this.game.newGameLauncher(state);
                    this.frame.setSize(state.frameWidth, state.frameHeight);
                    this.scorePanel.gameScoreText = state.scoreTotal;
                    this.scorePanel.levelText = state.level;
                    this.scorePanel.lifeText = state.life;
                    this.state = null;
                    reLaunch();
                    init();
                    refresh();
                }
                this.uploaded = false;
            }
        });
        this.scorePanel.save.addActionListener(e -> {
            if (e.getActionCommand().equals("Save")) {
                this.game.setStop();
                try {
                    this.gameStateImpl.writeState(new StateDTO(this.game, this.scorePanel, this.levels.levelSettings));
                } catch (BOException boException) {
                    boException.printStackTrace();
                }
                this.game.isStateSent = true;
            }
        });
        this.scorePanel.restart.addActionListener(e -> {
            this.game.setStop();
            try {
                this.scorePanel.updateRecord();
            } catch (BOException boException) {
                boException.printStackTrace();
            }
            this.scorePanel.next.setText("Upload");
            this.scorePanel.reset();
            reLaunch();
            newGame();
            init();
            refresh();
        });
    }

    private void reLaunch() {
        this.game.setStop();
        this.frame.remove(this.game);
        this.frame.remove(this.scorePanel);
        this.frame.setVisible(false);
    }

    private void refresh() {
        this.scorePanel.update();
        this.scorePanel.revalidate();
        this.scorePanel.repaint();
        this.frame.revalidate();
        this.frame.repaint();
    }
}
