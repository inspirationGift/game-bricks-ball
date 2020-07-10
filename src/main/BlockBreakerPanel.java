package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockBreakerPanel extends JPanel implements KeyListener {

    private final List<Blocks> blocks = new ArrayList<>();
    private final List<Blocks> ball = new ArrayList<>();
    private final List<Blocks> powerUp = new ArrayList<>();
    private final Blocks paddle;
    private final Blocks gameOver;
    private final Blocks win;

    public BlockBreakerPanel() {

        this.paddle = new Blocks(175, 480, 150, 25, "paddle.png");
        this.gameOver = new Blocks(100, 150, 300, 150, "gameover.png");
        this.win = new Blocks(100, 150, 300, 150, "win.png");
        this.win.destroyed = true;
        this.gameOver.destroyed = true;


        for (int i = 0; i < 8; i++) {
            blocks.add(new Blocks((i * 60 + 2), 0, 60, 25, "blue.png"));
        }
        for (int i = 0; i < 8; i++) {
            blocks.add(new Blocks((i * 60 + 2), 25, 60, 25, "green.png"));
        }
        for (int i = 0; i < 8; i++) {
            blocks.add(new Blocks((i * 60 + 2), 50, 60, 25, "yellow.png"));
        }
        for (int i = 0; i < 8; i++) {
            blocks.add(new Blocks((i * 60 + 2), 75, 60, 25, "red.png"));
        }

        Random random = new Random();
        blocks.get(random.nextInt(32)).powerup = true;
        blocks.get(random.nextInt(32)).powerup = true;
        blocks.get(random.nextInt(32)).powerup = true;
        blocks.get(random.nextInt(32)).powerup = true;
        blocks.get(random.nextInt(32)).powerup = true;


        ball.add(new Blocks(237, 437, 30, 25, "ball.png"));
        addKeyListener(this);
        setFocusable(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Blocks block : this.blocks) block.draw(g, this);
        for (Blocks block : this.ball) block.draw(g, this);
        for (Blocks block : this.powerUp) block.draw(g, this);
        this.paddle.draw(g, this);
        this.gameOver.draw(g, this);
        this.win.draw(g, this);


    }

    public void update() {
        if (blockCount(ball) == 0) {
            this.gameOver.destroyed = false;
            this.paddle.destroyed = true;
        }
        if (blockCount(blocks) == 0) {
            this.win.destroyed = false;
            this.paddle.destroyed = true;
        }
        for (Blocks blockPower : powerUp) {
            blockPower.y += 1;
            if (blockPower.intersects(paddle) && !blockPower.destroyed) {
                blockPower.destroyed = true;
                ball.add(new Blocks(paddle.dx + 75, 437, 30, 25, "ball.png"));
            }
        }

        for (Blocks bal : ball) {
            bal.x += bal.dx;
            int size = 25;
            if (bal.x > (getWidth() - size) && (bal.dx > 0) || (bal.x < 0))
                bal.dx *= -1;

            bal.y += bal.dy;
            if (bal.y < 0 || bal.intersects(paddle))
                bal.dy *= -1;

            if (bal.y > getHeight() + 1 && !bal.destroyed) {
                bal.destroyed = true;
            }
            for (Blocks bl : blocks) {
                if ((bl.left.intersects(bal) || bl.right.intersects(bal)) && !bl.destroyed) {
                    bal.dx *= -1;
                    bl.destroyed = true;
                    if (bl.powerup)
                        powerUp.add(new Blocks(bl.x, bl.y, 25, 19, "extra.png"));

                } else if (bl.intersects(bal) && !bl.destroyed) {
                    bl.destroyed = true;
                    bal.dy *= -1;
                    if (bl.powerup)
                        powerUp.add(new Blocks(bl.x, bl.y, 25, 19, "extra.png"));
                }
            }
        }
        repaint();
    }

    public int blockCount(List<Blocks> blocks) {
        return blocks.stream().mapToInt(ball -> {
            if (!ball.destroyed) {
                return 1;
            }
            return 0;
        }).sum();
    }

    @Override
    public void keyTyped(KeyEvent e) {

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
    public void keyReleased(KeyEvent e) {
    }

}
