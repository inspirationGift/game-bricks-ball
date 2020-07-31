package main.com.core;

import main.com.builder.entities.service.Animate;
import main.com.builder.addons.bonus.BonusType;
import main.com.builder.entities.service.BallMover;
import main.com.builder.entities.blocks.BlocksBuilder;
import main.com.builder.entities.blocks.Block;
import main.com.utils.Scheduler;
import main.com.utils.recorder.Recorder;
import main.com.utils.recorder.StateDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class GamePanel extends JPanel implements KeyListener {

    private int paddleSpeed;
    public boolean isStateSent;
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
    private boolean pause;

    public GamePanel() {
        this.support = new PropertyChangeSupport(this);
        this.isWon = false;
        this.score = 0;
        this.ballMover = new BallMover();
        this.pause = true;
        addKeyListener(this);
        setFocusable(true);
    }

    public void newGameLauncher(LevelSettings levelSettings) {
        this.ls = levelSettings;
        this.blocksBuilder = null;
        this.blocksBuilder = new BlocksBuilder(ls);
        this.paddleSpeed = this.ls.getPaddleSpeed();
        buildGame();
    }

    public void setStop() {
        this.pause = true;
    }

    public void newGameLauncher(StateDTO stateDTO) {
        this.ls = null;
        this.blocksBuilder = null;
        this.blocksBuilder = new BlocksBuilder(stateDTO);
        this.paddleSpeed = stateDTO.paddleSpeed;
        buildGame();
    }

    public void buildGame() {
        this.bonusList = this.blocksBuilder.getBonuses();
        this.blocks = this.blocksBuilder.getHorizontalBlocksList();
        this.ballList = this.blocksBuilder.getBallList();
        this.paddle = this.blocksBuilder.getPaddle();
        this.winSign = this.blocksBuilder.getWinSign();
        this.gameOverSign = this.blocksBuilder.getGameOverSign();
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
        if (!this.pause) {
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
            if (Scheduler.isDelayed()) this.blocksBuilder.bonusBlockDestroy();
            addBonusAction();
            gameOver(1);
            win();
            repaint();
        }
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
            if (block.hasBlockBonus())
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

    public void pause() {
        this.pause = !this.pause;
    }

    public void continueGame() {
        this.gameOverCount = 0;
        this.paddle.x = 175;
        this.blocksBuilder.createBall(BonusType.ADD_BALL);
        this.gameOverSign.destroyed = true;
        this.paddle.destroyed = false;
        this.isWon = false;
    }

    private void win() {
        if (this.blocksBuilder.destroyedBlocksCountInList(this.blocks) == 0) {
            boolean old = this.isWon;
            this.isWon = true;
            this.winSign.destroyed = false;
            this.paddle.destroyed = true;
            this.support.firePropertyChange("Win", old, this.isWon);
        }
    }

    private void addBonusAction() {
        this.blocksBuilder.giveActionIfBonusIsIntersected(this.paddle);
    }

    private void animationStart() {
        if (this.animate == null) {
            pause();
            this.animate = new Animate(this);
            this.thread = new Thread(this.animate);
            this.thread.start();
        } else
            pause();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            animationStart();
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT && paddle.x > 0) {
            this.paddle.x -= paddleSpeed;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && paddle.x < (getWidth() - paddle.width)) {
            this.paddle.x += paddleSpeed;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public void setState(Recorder recorder, int frameWidth, int frameHeight, ScorePanel scorePanel) {
        recorder.writeState(new StateDTO(this.ballList, this.bonusList, this.blocks,
                frameWidth, frameHeight, scorePanel, this.paddleSpeed));
    }
}

