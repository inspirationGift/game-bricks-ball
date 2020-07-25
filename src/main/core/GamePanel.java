package main.core;

import main.builder.entities.Animate;
import main.builder.addons.PowerUpBuilder;
import main.builder.addons.PowerUpType;
import main.builder.entities.BallMover;
import main.builder.entities.block.BlocksBuilder;
import main.builder.entities.block.Block;
import main.utils.Scheduler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Iterator;
import java.util.List;

public class GamePanel extends JPanel implements KeyListener {

    private PowerUpBuilder powerUpBuilder;
    public BlocksBuilder blocksBuilder;

    private BallMover ballMover;
    private List<Block> blocks;
    private List<Block> ballList;
    private List<Block> powerUpList;
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

    public GamePanel() {
        this.ls = levelSetUp();
        buildGame();
        this.support = new PropertyChangeSupport(this);
    }

    public LevelSettings levelSetUp() {
        return new LevelSettings();
    }

    private void buildGame() {
        this.blocksBuilder = new BlocksBuilder(this.ls.getBlockSettings());
        this.powerUpBuilder = new PowerUpBuilder();
        this.ballMover = new BallMover();
        this.blocks = this.blocksBuilder.getHorizontalBlocksList();
        this.ballList = this.blocksBuilder.getBallList();
        this.paddle = this.blocksBuilder.getPaddle();
        this.winSign = this.blocksBuilder.getWinSign();
        this.gameOverSign = this.blocksBuilder.getGameOverSign();
        this.powerUpList = this.powerUpBuilder.getPowerUpList();
        this.powerUpBuilder.markUpPowerUpBlock(this.blocks, ls.getPowerUps());
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
        for (Block block : this.blocks) block.draw(g, this);
        Iterator<Block> iterator = this.powerUpList.iterator();
        synchronized (iterator) {
            while (iterator.hasNext()) {
                Block powers = iterator.next();
                powers.draw(g, this);
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
                }
            }
        }
        if (Scheduler.isDelayed()) this.blocksBuilder.poweredUpBlockDestroy();
        addPowerUpAction();
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
            this.powerUpBuilder.setNewPowerUpBlock(block);
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
        this.animate.flag = false;
    }

    public void continueGame() {
        this.gameOverCount = 0;
        this.paddle.x = 175;
        this.blocksBuilder.createBall(PowerUpType.ADD_BALL);
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

    private void addPowerUpAction() {
        PowerUpType powerUpType = this.powerUpBuilder.giveActionIfBlockPowerUpIsIntersected(this.paddle);
        if (powerUpType != PowerUpType.DO_NOTHING)
            this.blocksBuilder.poweredUPBlocksBuild(powerUpType);
    }

    private void animationStart() {
        if (this.animate == null) this.animate = new Animate(this);
        else this.animate.flag = true;
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
}

