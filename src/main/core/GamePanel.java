package main.core;

import main.builder.entities.Animate;
import main.builder.addons.PowerUpBuilder;
import main.builder.addons.PowerUpType;
import main.builder.entities.BallMover;
import main.builder.entities.BlocksBuilder;
import main.builder.entities.Block;
import main.utils.Scheduler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class GamePanel extends JPanel implements KeyListener {

    private BlocksBuilder blocksBuilder;
    private PowerUpBuilder powerUpBuilder;
    private BallMover ballMover;
    private List<Block> blocks;
    private List<Block> ballList;
    private List<Block> powerUpList;
    private Block paddle;
    private Block gameOverSign;
    private Block winSign;
    private boolean gameEnd;
    private Thread thread;
    private Animate animate;
    private LevelSettings levelSettings;
    public boolean changeLevel;


    private int score = 0;
    private PropertyChangeSupport support;


    public GamePanel() {
        buildGame();
        this.support = new PropertyChangeSupport(this);
        this.changeLevel = false;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        int odlValue = this.score;
        this.score += score;
        support.firePropertyChange("ScoreValue", odlValue, this.score);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.support.addPropertyChangeListener(listener);
    }

    private void buildGame() {
        this.levelSettings = new LevelSettings();
        levelSettings(this.levelSettings);

        this.blocksBuilder = new BlocksBuilder();
        this.powerUpBuilder = new PowerUpBuilder();
        this.ballMover = new BallMover();

        this.blocks = this.blocksBuilder.getHorizontalBlocksList();
        this.ballList = this.blocksBuilder.getBallList();
        this.paddle = this.blocksBuilder.getPaddle();
        this.winSign = this.blocksBuilder.getWinSign();
        this.gameOverSign = this.blocksBuilder.getGameOverSign();
        this.powerUpList = this.powerUpBuilder.getPowerUpList();
        this.powerUpBuilder.markUpPowerUpBlock(this.blocks, levelSettings.getPowerUps());

        addKeyListener(this);
        setFocusable(true);
    }


    public void levelSettings(LevelSettings levelSettings) {
        levelSettings.setPaddleSpeed(30);
        levelSettings.setPowerUps(PowerUpType.ADD_BALL, 1);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Block block : this.blocks) block.draw(g, this);
        for (Block block : this.ballList) {
            synchronized (block) {
                block.draw(g, this);
            }
        }
        for (Block block : this.powerUpList)
            block.draw(g, this);
        this.paddle.draw(g, this);
        this.gameOverSign.draw(g, this);
        this.winSign.draw(g, this);
    }

    public void update() {
        addPowerUpAction();
        gameOver();
        win();

        this.ballMover.setHeight(getHeight());
        this.ballMover.setWidth(getWidth());
        for (Block ball : this.ballList) {
            this.ballMover.activate(ball, this.paddle);
            for (Block block : this.blocks) {
                if (this.ballMover.doesBlockToDestroy(ball, block)) {
                    this.powerUpBuilder.setNewPowerUpBlock(block);
                    setScore(5);
                }
            }
        }
        if (Scheduler.isDelayed())
            this.blocksBuilder.poweredUpBlockDestroy();
        repaint();
    }

    private void gameOver() {
        if (this.blocksBuilder.destroyedBlocksCountInList(this.ballList) == 0 && !this.gameEnd) {
            this.gameOverSign.destroyed = false;
            this.paddle.destroyed = true;
        }
    }

    private void win() {
        if (this.blocksBuilder.destroyedBlocksCountInList(this.blocks) == 0) {
            this.winSign.destroyed = false;
            this.paddle.destroyed = true;
            this.gameEnd = true;
            this.changeLevel = true;
        }
    }

    private void addPowerUpAction() {
        PowerUpType powerUpType = this.powerUpBuilder.giveActionIfBlockPowerUpIsIntersect(this.paddle);
        if (powerUpType != PowerUpType.DO_NOTHING)
            this.blocksBuilder.poweredUPBlocksBuild(powerUpType);
    }

    private void animationStart() {
        if (this.animate == null) this.animate = new Animate(this);
        if (this.thread == null || !this.thread.isAlive()) {
            this.thread = new Thread(this.animate);
            this.thread.start();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            animationStart();
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT && paddle.x > 0) {
            this.paddle.x -= levelSettings.getPaddleSpeed();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && paddle.x < (getWidth() - paddle.width)) {
            this.paddle.x += levelSettings.getPaddleSpeed();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public boolean isChangeLevel() {
        return changeLevel;
    }

    public void setChangeLevel(boolean changeLevel) {
        this.changeLevel = changeLevel;
    }


}
