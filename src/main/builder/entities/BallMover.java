package main.builder.entities;


import java.util.List;

public class BallMover {

    private int height;
    private int width;
    private Block intersectedBlock;

    public Block getIntersectedBlock() {
        return intersectedBlock;
    }

    public void setIntersectedBlock(Block intersectedBlock) {
        this.intersectedBlock = intersectedBlock;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public BallMover() {
    }

    public void activate(Block ball, Block bottom) {
        setBallMoveByX(ball);
        setBallMoveByY(ball);
        ballIntersectTopAndBottom(ball, bottom);
    }

    public boolean isIntersectedBlock(Block ball, Block block) {
        if (ballIntersectLeftAndRight(ball, block) ||
                ballIntersectCenter(ball, block)) {
            return true;
        } else return false;
    }

    private void setBallMoveByY(Block ball) {
        ball.y += ball.dy;
        doBallDestroy(ball);
    }

    private void setBallMoveByX(Block ball) {
        ball.x += ball.dx;
//        int size = 25;
        int size = ball.getSize().width;
        if (ball.x > (this.width - size) && (ball.dx > 0) || (ball.x < 0)) ball.dx *= -1;

    }

    private void doBallDestroy(Block ball) {
        if (ball.y > this.height + 1 && !ball.destroyed) ball.destroyed = true;

    }

    private void ballIntersectTopAndBottom(Block ball, Block paddle) {
        if (ball.y < 0 || ball.intersects(paddle)) ball.dy *= -1;
    }

    public boolean ballIntersectLeftAndRight(Block ball, Block block) {
        boolean var = false;

        if (block.left.intersects(ball) || block.right.intersects(ball) && !block.destroyed) {
            ball.dx *= -1;
            block.destroyed = true;
            var = true;
        }
        return var;
    }

    public boolean ballIntersectCenter(Block ball, Block block) {
        boolean var = false;
        if (block.intersects(ball) && !block.destroyed) {
            ball.dy *= -1;
            block.destroyed = true;
            var = true;
        }
        return var;
    }


}
