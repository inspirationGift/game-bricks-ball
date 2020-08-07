package main.com.builder.entities.service;

import main.com.builder.addons.color.BlockColor;
import main.com.builder.addons.bonus.BonusType;
import main.com.builder.entities.blocks.Block;

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
        if (ball.y > this.height - 1 && !ball.isDestroyed())
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
        if ((ball.y < 1) || ball.intersects(paddle)) {
            ball.dy *= -1;
        }
        if (ball.intersects(paddle.x - 1, paddle.y, 1, paddle.height) ||
                ball.intersects(paddle.x + paddle.width + 1, paddle.y, 1, paddle.height)) {
            ball.dy *= -1;
        }
    }


    private void blockDestroyer(Block ball, Block block) {
        int i = block.getqHits() - (ball.getBonusType() == BonusType.BIG_BALL ? 2 : 1);
        if (i <= 0) {
            block.setDestroyed(true);
            i = 1;
        }
        block.setqHits(i);
        for (BlockColor value : BlockColor.values()) {
            if (value.getRateTransition() == i) block.setPic(value);
        }
    }

    private boolean ballIntersectsLeftRightBlock(Block ball, Block block) {
        if ((block.left.intersects(ball) || block.right.intersects(ball)) && !block.destroyed) {
            ball.dy *= -1;
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

    public void setWidth(int width) {
        this.width = width;
    }


    public void setHeight(int height) {
        this.height = height;
    }
}
