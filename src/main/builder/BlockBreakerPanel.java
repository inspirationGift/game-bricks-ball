package main.builder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockBreakerPanel extends JPanel implements KeyListener {

    private List<Block> blocks;
    private List<Block> ballList;
    private List<Block> powerUpList;
    private Block paddle;
    private Block gameOver;
    private Block win;
    private boolean gameEnd;
    private Thread thread;
    private Animate animate;

    public BlockBreakerPanel() {
    }

    public void buildGame() {
        this.blocks = new ArrayList<>();
        this.ballList = new ArrayList<>();
        this.powerUpList = new ArrayList<>();
        this.thread = new Thread();

        this.paddle = new Block(175, 480, 150, 2, "resources/paddle.png");
        this.gameOver = new Block(100, 150, 300, 150, "resources/gameover.png");
        this.win = new Block(100, 150, 300, 150, "resources/win.png");
        this.win.destroyed = true;
        this.gameOver.destroyed = true;

        for (int i = 0; i < 8; i++)
            blocks.add(new Block((i * 60 + 2), 0, 60, 25, "resources/blue.png"));

        for (int i = 0; i < 8; i++)
            blocks.add(new Block((i * 60 + 2), 25, 60, 25, "resources/green.png"));

        for (int i = 0; i < 8; i++)
            blocks.add(new Block((i * 60 + 2), 50, 60, 25, "resources/yellow.png"));

        for (int i = 0; i < 8; i++)
            blocks.add(new Block((i * 60 + 2), 75, 60, 25, "resources/red.png"));

        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            blocks.get(random.nextInt(32)).isBlockPoweredUp = true;
        }

        ballList.add(new Block(237, 437, 25, 25, "resources/ball.png"));
        this.gameEnd = false;
        addKeyListener(this);
        setFocusable(true);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Block block : this.blocks) block.draw(g, this);
        for (Block block : this.ballList) block.draw(g, this);
        for (Block block : this.powerUpList) block.draw(g, this);
        this.paddle.draw(g, this);
        this.gameOver.draw(g, this);
        this.win.draw(g, this);
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
        if (blockCount(ballList) == 0 && !this.gameEnd) {
            this.gameOver.destroyed = false;
            this.paddle.destroyed = true;
        }
    }

    private void win() {
        if (blockCount(blocks) == 0) {
            this.win.destroyed = false;
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
                ballList.add(new Block(paddle.dx + 75, 437, 25, 30, "resources/ball.png"));
            }
        }
    }

    public int blockCount(List<Block> blocks) {
        return blocks.stream().mapToInt(ball -> {
            if (!ball.destroyed) {
                return 1;
            }
            return 0;
        }).sum();
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

    public boolean isGameOver() {
        return gameEnd;
    }

}
