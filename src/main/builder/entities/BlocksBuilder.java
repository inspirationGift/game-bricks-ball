package main.builder.entities;

import main.builder.addons.PowerUpType;
import main.utils.BlockLocation;
import main.utils.Scheduler;

import java.util.*;
import java.util.List;

public class BlocksBuilder {
    private List<Block> horizontalBlocksList;
    private List<Block> ballList;
    private Block paddle;
    private Block gameOverSign;
    private Block winSign;

    public BlocksBuilder() {
        this.horizontalBlocksList = new ArrayList<>();
        this.ballList = new ArrayList<>();
        horizontalBlocksBuilder(32, 8, 25, true);
        createBall(PowerUpType.ADD_BALL);
        paddleBuilder();
        signsBuilder();
    }

    private void horizontalBlocksBuilder(int qBlocks, int qBlocksInRow, int rowStep, boolean isEquallyDistribute) {
        BlockLocation location = new BlockLocation(qBlocks, isEquallyDistribute);
        location.setRowMap(rowStep, qBlocksInRow);

        int u = 0;
        for (int i = 0; i < qBlocks; i++) {
            Integer y = location.pullRowFromRowMap();
            String color = location.pullRowFromColorMap().getPic();
            if (u == qBlocksInRow) u = 0;
            horizontalBlocksList.add(new Block((u * 60 + 2), y, 60, 25, color));
            u++;
        }
    }

    private void createBall(PowerUpType powerUpType) {
        ballList.add(new Block(237, 500, 25, 25, powerUpType.getAnimate()));
    }

    private void paddleBuilder() {
        this.paddle = new Block(175, 530, 150, 2, "resources/blocks/paddle.png");
    }

    private void signsBuilder() {
        this.gameOverSign = new Block(100, 150, 300, 150, "resources/gameover.png");
        this.winSign = new Block(100, 150, 300, 150, "resources/win.png");
        this.winSign.destroyed = true;
        this.gameOverSign.destroyed = true;
    }

    public void poweredUPBlocksBuild(PowerUpType powerUpType) {
        switch (powerUpType) {
            case ADD_BALL -> createBall(powerUpType);
            case DOUBLE_BALL -> {
                long count = this.ballList.stream().filter(val -> !val.destroyed).count();
                for (int i = 0; i < count; i++) {
                    Scheduler.setDelay(5000);
                    createBall(powerUpType);
                }
            }
            case BIG_BALL -> {
                this.ballList.forEach(ball -> {
                    if (ball.getPowerUpType() != PowerUpType.BIG_BALL && !ball.destroyed) {
                        ball.width *= 2;
                        ball.height *= 2;
                        ball.setPic(PowerUpType.BIG_BALL);
                        ball.setPowerUpType(PowerUpType.BIG_BALL);
                    }
                });
                Scheduler.setDelay(5000);
            }
            case ENLARGE_PADDLE -> {
                if (this.paddle.width == 150)
                    this.paddle.width = 250;
                Scheduler.setDelay(5000);
            }

        }
    }

    public void poweredUpBlockDestroy() {
        this.ballList.forEach(ball -> {
            if (!ball.destroyed) {
                switch (ball.getPowerUpType()) {
                    case BIG_BALL -> {
                        ball.width /= 2;
                        ball.height /= 2;
                        ball.setPic(PowerUpType.ADD_BALL);
                        ball.setPowerUpType(PowerUpType.ADD_BALL);
                    }
                    case DOUBLE_BALL -> ball.setDestroyed(true);
                }
            }
        });
        this.paddle.width = 150;
    }

    public long destroyedBlocksCountInList(List<Block> blocks) {
        return blocks.stream()
                .filter(val -> !val.destroyed)
                .count();
    }

    public Block getPaddle() {
        return paddle;
    }

    public void setPaddle(Block paddle) {
        this.paddle = paddle;
    }

    public List<Block> getHorizontalBlocksList() {
        return horizontalBlocksList;
    }

    public void setHorizontalBlocksList(Block horizontalBlock) {
        this.horizontalBlocksList.add(horizontalBlock);
    }

    public List<Block> getBallList() {
        return ballList;
    }

    public Block getGameOverSign() {
        return gameOverSign;
    }

    public void setGameOverSign(Block gameOverSign) {
        this.gameOverSign = gameOverSign;
    }

    public Block getWinSign() {
        return winSign;
    }

    public void setWinSign(Block winSign) {
        this.winSign = winSign;
    }

}
