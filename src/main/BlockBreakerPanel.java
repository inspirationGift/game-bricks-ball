package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockBreakerPanel extends JPanel implements KeyListener {

    private final List<Block> blocks = new ArrayList<>();
    private final List<Block> ball = new ArrayList<>();
    private final List<Block> powerUp = new ArrayList<>();
    private final Block paddle;
    private final Block gameOver;
    private final Block win;
    private boolean gameEnd;

    public BlockBreakerPanel() {

        this.paddle = new Block(175, 480, 150, 25, "resources/paddle.png");
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
        blocks.get(random.nextInt(32)).powerup = true;
        blocks.get(random.nextInt(32)).powerup = true;
        blocks.get(random.nextInt(32)).powerup = true;
        blocks.get(random.nextInt(32)).powerup = true;
        blocks.get(random.nextInt(32)).powerup = true;

        ball.add(new Block(237, 437, 30, 25, "resources/ball.png"));
        addKeyListener(this);
        setFocusable(true);
        this.gameEnd = false;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Block block : this.blocks) block.draw(g, this);
        for (Block block : this.ball) block.draw(g, this);
        for (Block block : this.powerUp) block.draw(g, this);
        this.paddle.draw(g, this);
        this.gameOver.draw(g, this);
        this.win.draw(g, this);
    }

    public void update() {
        addBall();
        gameOver();
        win();
        for (Block bal : ball) {
            setBallMoveByX(bal);
            setBallMoveByY(bal);
            blocksIntersectionByBallXY(bal, blocks);
        }
        repaint();
    }

    private void setBallMoveByY(Block bal) {
        bal.y += bal.dy;
        if (bal.y < 0 || bal.intersects(paddle)) bal.dy *= -1;
        if (bal.y > getHeight() + 1 && !bal.destroyed) bal.destroyed = true;
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
        if (blockCount(ball) == 0 && !this.gameEnd) {
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

    private void addBall() {
        for (Block blockPower : powerUp) {
            blockPower.y += 1;
            if (blockPower.intersects(paddle) && !blockPower.destroyed) {
                blockPower.destroyed = true;
                ball.add(new Block(paddle.dx + 75, 437, 30, 25, "resources/ball.png"));
            }
        }
    }

    private void addExtraPowerUp(Block bl) {
        if (bl.powerup) powerUp.add(new Block(bl.x, bl.y, 25, 19, "resources/extra.png"));
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
            Animate animate = new Animate(this);
            Thread thread = new Thread(animate);
            thread.start();
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
}
