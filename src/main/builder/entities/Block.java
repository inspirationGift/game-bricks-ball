package main.builder.entities;

import java.awt.*;

public class Block extends Rectangle {
    Image pic;
    public int dx = 3;
    public int dy = -3;
    public Rectangle left, right;

    public boolean destroyed = false;
    public boolean isBlockPoweredUp = false;

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
