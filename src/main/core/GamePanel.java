package main.core;

import main.builder.entities.service.Animate;
import main.builder.addons.bonus.BonusType;
import main.builder.entities.service.BallMover;
import main.builder.entities.blocks.BlocksBuilder;
import main.builder.entities.blocks.Block;
import main.utils.Scheduler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;


public class GamePanel extends JPanel implements KeyListener {

    private List<Block> bonusList;
    public BlocksBuilder blocksBuilder;

    private BallMover ballMover;
    private List<Block> blocks;
    private List<Block> ballList;
    private Block paddle;
    private Block gameOverSign;
    private Block winSign;
    private Thread thread;
    private Animate animate;
    private LevelSettings ls;
    public boolean isWon;
    public int gameOverCount;
    private PropertyChangeSupport support;
    private int score;
    public boolean pauseFlag = false;

    public GamePanel(LevelSettings levelSettings) {
        this.support = new PropertyChangeSupport(this);
        buildGame(levelSettings);
    }

    public void buildGame(LevelSettings levelSettings) {
        this.score = 0;
        this.ls = levelSettings;
        this.blocksBuilder = new BlocksBuilder(ls);
        this.bonusList = this.blocksBuilder.getBonuses();
        this.ballMover = new BallMover();
        this.blocks = this.blocksBuilder.getHorizontalBlocksList();
        this.ballList = this.blocksBuilder.getBallList();
        this.paddle = this.blocksBuilder.getPaddle();
        this.winSign = this.blocksBuilder.getWinSign();
        this.gameOverSign = this.blocksBuilder.getGameOverSign();

        addKeyListener(this);
        setFocusable(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Block block : this.ballList) {
            synchronized (block) {
                block.draw(g, this);
            }
        }
        for (Block block : this.blocks) {
            synchronized (block) {
                block.draw(g, this);
            }
        }
        for (Block block : this.bonusList) {
            synchronized (block) {
                block.draw(g, this);
            }
        }

        this.paddle.draw(g, this);
        this.gameOverSign.draw(g, this);
        this.winSign.draw(g, this);
    }

    public void update() {
        this.ballMover.setHeight(getHeight());
        this.ballMover.setWidth(getWidth());

        for (Block ball : this.ballList) {
            this.ballMover.activate(ball, this.paddle);

            for (Block block : this.blocks) {
                if (isBlockIntersected(ball, block)) {
                    setScore(5);
                    if (block.hasBlockBonus()) {
                        blocksBuilder.setBonusBlock(block);
                    }
                }
            }
        }
        if (Scheduler.isDelayed()) this.blocksBuilder.bonusBlockDestroy();
        synchronized (thread) {
            addBonusAction();
        }
        gameOver(1);
        this.isWon = win();
        repaint();
    }

    private void setScore(int score) {
        int old = this.score;
        this.score += score;
        this.support.firePropertyChange("ScoreValue", old, this.score);
    }


    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.support.addPropertyChangeListener(listener);
    }

    private boolean isBlockIntersected(Block ball, Block block) {
        if (this.ballMover.isBlockToDestroyByBlockIntersection(ball, block)) {
            this.blocksBuilder.setBonusBlock(block);
            return true;
        }
        return false;
    }

    private void gameOver(int i) {

        if (this.blocksBuilder.destroyedBlocksCountInList(this.ballList) == 0 && !this.isWon) {
            int old = this.gameOverCount;
            this.gameOverCount += i;
            this.gameOverSign.destroyed = false;
            this.paddle.destroyed = true;
            this.isWon = false;
            this.support.firePropertyChange("GameOver", old, this.gameOverCount);
        }
    }

    public void stopGame() {
        this.pauseFlag = false;
        this.animate.setFlag();
    }

    public void continueGame() {
        this.gameOverCount = 0;
        this.paddle.x = 175;
        this.blocksBuilder.createBall(BonusType.ADD_BALL);
        this.gameOverSign.destroyed = true;
        this.paddle.destroyed = false;
        this.isWon = false;
    }

    private boolean win() {
        if (this.blocksBuilder.destroyedBlocksCountInList(this.blocks) == 0) {
            this.winSign.destroyed = false;
            this.paddle.destroyed = true;
            return true;
        }
        return false;
    }

    private void addBonusAction() {
        this.blocksBuilder.giveActionIfBonusIsIntersected(this.paddle);
    }

    private void animationStart() {
        if (this.animate == null)
            this.animate = new Animate(this);
        else
            this.animate.setFlag();
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
            this.paddle.x -= this.ls.getPaddleSpeed();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && paddle.x < (getWidth() - paddle.width)) {
            this.paddle.x += this.ls.getPaddleSpeed();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public LevelSettings getLs() {
        return ls;
    }

    public void setLs(LevelSettings ls) {
        this.ls = ls;
    }
}

