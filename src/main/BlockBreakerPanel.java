package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockBreakerPanel extends JPanel implements KeyListener {

    private List<Blocks> blocks = new ArrayList<>();
    private List<Blocks> ball = new ArrayList<>();
    private List<Blocks> powerUp = new ArrayList<>();


    private Blocks paddle;
    private Thread thread;
    private Animate animate;
    private int size = 25;

    public BlockBreakerPanel() {

        this.paddle = new Blocks(175, 480, 150, 25, "paddle.png");

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
    }

    public void update() {
        for (Blocks blockPower : powerUp) {
            blockPower.y += 1;
            if (blockPower.intersects(paddle) && !blockPower.destroyed) {
                blockPower.destroyed = true;
                ball.add(new Blocks(paddle.dx + 75, 437, 30, 25, "ball.png"));
            }
        }

        for (Blocks bal : ball) {
            bal.x += bal.dx;
            if (bal.x > (getWidth() - size) && (bal.dx > 0) || (bal.x < 0))
                bal.dx *= -1;
            bal.y += bal.dy;
            if (bal.y < 0 || bal.intersects(paddle))
                bal.dy *= -1;
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

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            this.animate = new Animate(this);
            this.thread = new Thread(animate);
            this.thread.start();
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
