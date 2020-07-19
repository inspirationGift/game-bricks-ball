package main.builder;

import main.builder.entities.BlocksBuilder;
import main.builder.entities.Block;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockBreakerPanel extends JPanel implements KeyListener {

    private BlocksBuilder blocksBuilder;
    private List<Block> blocks;
    private List<Block> ballList;
    private List<Block> powerUpList;
    private Block paddle;
    private Block gameOverSign;
    private Block winSign;
    private boolean gameEnd;
    private Thread thread;
    private Animate animate;

    public BlockBreakerPanel() {
    }

    public void buildGame() {
        this.thread = new Thread();

        this.blocksBuilder = new BlocksBuilder();
        this.blocks = this.blocksBuilder.getHorizontalBlocksList();
        this.ballList = this.blocksBuilder.getBallList();
        this.paddle = this.blocksBuilder.getPaddle();
        this.winSign = this.blocksBuilder.getWinSign();
        this.gameOverSign = this.blocksBuilder.getGameOverSign();
        this.gameEnd = false;

        this.powerUpList = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < 5; i++)
            blocks.get(random.nextInt(32)).isBlockPoweredUp = true;

        addKeyListener(this);
        setFocusable(true);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Block block : this.blocks) block.draw(g, this);
        for (Block block : this.ballList) block.draw(g, this);
        for (Block block : this.powerUpList) block.draw(g, this);
        this.paddle.draw(g, this);
        this.gameOverSign.draw(g, this);
        this.winSign.draw(g, this);
    }

    public void update() {
        addBall();
        gameOver();
        win();
        for (Block bal : ballList) {
            setBallMoveByX(bal);
            setBallMoveByY(bal);
            blocksIntersectionByBallXY(bal, blocks);
        }
        repaint();
    }

    private void setBallMoveByY(Block ball) {
        ball.y += ball.dy;
        if (ball.y < 0 || ball.intersects(paddle)) ball.dy *= -1;
        if (ball.y > getHeight() + 1 && !ball.destroyed) ball.destroyed = true;
    }

    private void setBallMoveByX(Block bal) {
        bal.x += bal.dx;
        int size = 25;
        if (bal.x > (getWidth() - size) && (bal.dx > 0) || (bal.x < 0)) bal.dx *= -1;
    }

    private void blocksIntersectionByBallXY(Block bal, List<Block> blocks) {
        for (Block bl : blocks) {
            if ((bl.left.intersects(bal) || bl.right.intersects(bal)) && !bl.destroyed) {
                bal.dx *= -1;
                bl.destroyed = true;
                addExtraPowerUp(bl);
            } else if (bl.intersects(bal) && !bl.destroyed) {
                bl.destroyed = true;
                bal.dy *= -1;
                addExtraPowerUp(bl);
            }
        }
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
        }
    }

    private void addExtraPowerUp(Block bl) {
        if (bl.isBlockPoweredUp) powerUpList.add(new Block(bl.x, bl.y, 25, 19, "resources/extra.png"));
    }

    private void addBall() {
        for (Block blockPower : powerUpList) {
            blockPower.y += 1;
            if (blockPower.intersects(paddle) && !blockPower.destroyed) {
                blockPower.destroyed = true;
                this.blocksBuilder.setAdditionalBall();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            animationStart();
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT && paddle.x > 0) {
            this.paddle.x -= 25;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && paddle.x < (getWidth() - paddle.width)) {
            this.paddle.x += 25;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private void animationStart() {
        if (this.animate == null) this.animate = new Animate(this);
        if (!this.thread.isAlive()) {
            this.thread = new Thread(this.animate);
            this.thread.start();
        }
    }
}
