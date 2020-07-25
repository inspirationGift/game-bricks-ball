package main.builder.entities;

import main.builder.addons.BlockColor;
import main.builder.addons.PowerUpType;
import main.builder.entities.block.Block;

public class BallMover {

    private int height;
    private int width;
    private Block intersectedBlock;

    public BallMover() {
    }

    public void activate(Block ball, Block bottom) {
        setBallMoveByX(ball);
        setBallMoveByY(ball);
        ballIntersectPaddle(ball, bottom);
        doesBallDestroy(ball);
    }

    public boolean isBlockToDestroyByBlockIntersection(Block ball, Block block) {
        return ballIntersectsBlock(ball, block) || ballIntersectsLeftRightBlock(ball, block);
    }

    public void doesBallDestroy(Block ball) {
        if (ball.y > this.height + 1 && !ball.isDestroyed())
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

    private void ballIntersectPaddle(Block ball, Block paddle) {
        if ((ball.y < 0) || ball.intersects(paddle)) {
            ball.dy *= -1;
        }
        if (ball.intersects(paddle.x, paddle.y, 1, paddle.height) ||
                ball.intersects(paddle.x + paddle.width, paddle.y, 1, paddle.height)) {
            ball.dx *= -1;
            ball.dy *= -1;
        }
    }

    private void blockDestroyer(Block ball, Block block) {
        int i = block.getqHits() - (ball.getPowerUpType() == PowerUpType.BIG_BALL ? 2 : 1);
        if (i < 1) {
            block.setDestroyed(true);
        }
        block.setqHits(i);
        for (BlockColor value : BlockColor.values()) {
            if (value.getRateTransition() == i) block.setPic(value);
        }
    }

    private boolean ballIntersectsLeftRightBlock(Block ball, Block block) {
        if ((block.left.intersects(ball) || block.right.intersects(ball)) && !block.destroyed) {
            ball.dx *= -1;
            blockDestroyer(ball, block);
            return true;
        }
        return false;

    }

    private boolean ballIntersectsBlock(Block ball, Block block) {
        if (block.intersects(ball) && !block.destroyed) {
            ball.dy *= -1;
            blockDestroyer(ball, block);
            return true;
        }
        return false;
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
