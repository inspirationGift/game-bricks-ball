package main;

import java.awt.*;

public class Block extends Rectangle {
    Image pic;
    int dx = 3;
    int dy = -3;
    Rectangle left, right;

    boolean destroyed = false;
    boolean powerup = false;

    public Block(int x, int y, int width, int height, String path) {
        super(x, y, width, height);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.pic = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource(path));

        left = new Rectangle(x - 1, y, 1, height);
        right = new Rectangle(x + width + 1, y, 1, height);
    }

    public void draw(Graphics graphics, Component component) {
        if (!destroyed)
            graphics.drawImage(this.pic, this.x, this.y, this.width, this.height, component);
    }

}
