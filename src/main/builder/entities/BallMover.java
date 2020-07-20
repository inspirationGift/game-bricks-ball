package main.builder.entities;

public class BallMover {

    private int height;
    private int width;
    private Block intersectedBlock;

    public BallMover() {
    }

    public void activate(Block ball, Block bottom) {
        setBallMoveByX(ball);
        setBallMoveByY(ball);
        ballIntersectsFrame(ball, bottom);
        doesBallDestroy(ball);
    }

    public boolean doesBlockToDestroy(Block ball, Block block) {
        return ballIntersectsBlock(ball, block) || ballIntersectsLeftRightBlock(ball, block);
    }

    public void doesBallDestroy(Block ball) {
        if (ball.y > this.height + 2 && !ball.isDestroyed())
            ball.setDestroyed(true);
    }

    private void setBallMoveByX(Block ball) {
        ball.x += ball.dx;
        int size = ball.getSize().width;
        if (ball.x > (this.width - size) && (ball.dx > 0) || (ball.x < 0)) ball.dx *= -1;

    }

    private void setBallMoveByY(Block ball) {
        ball.y += ball.dy;
    }

    private void ballIntersectsFrame(Block ball, Block paddle) {
        if (ball.y < 0 || ball.intersects(paddle)) ball.dy *= -1;
    }

    private boolean ballIntersectsLeftRightBlock(Block ball, Block block) {
        boolean flag = false;
        if ((block.left.intersects(ball) || block.right.intersects(ball)) && !block.destroyed) {
            ball.dx *= -1;
            flag = true;
            block.setDestroyed(true);
        }
        return flag;
    }

    private boolean ballIntersectsBlock(Block ball, Block block) {
        boolean flag = false;
        if (block.intersects(ball) && !block.destroyed) {
            ball.dy *= -1;
            block.setDestroyed(true);
            flag = true;
        }
        return flag;
    }

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


}
