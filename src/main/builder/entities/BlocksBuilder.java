package main.builder.entities;

import main.builder.addons.PowerUpType;
import main.utils.Scheduler;

import java.util.*;
import java.util.List;

public class BlocksBuilder {
    private List<Block> horizontalBlocksList;
    private List<Block> ballList;
    private Block paddle;
    private Block gameOverSign;
    private Block winSign;
    private int delay;

    public BlocksBuilder() {
        this.horizontalBlocksList = new ArrayList<>();
        this.ballList = new ArrayList<>();
        horizontalBlocksBuilder();
        createBall(PowerUpType.ADD_BALL);
        paddleBuilder();
        signsBuilder();
    }

    private void horizontalBlocksBuilder() {
        for (int i = 0; i < 8; i++) {
            horizontalBlocksList.add(new Block((i * 60 + 2), 0, 60, 25, "resources/blue.png"));
            horizontalBlocksList.add(new Block((i * 60 + 2), 25, 60, 25, "resources/green.png"));
            horizontalBlocksList.add(new Block((i * 60 + 2), 50, 60, 25, "resources/yellow.png"));
            horizontalBlocksList.add(new Block((i * 60 + 2), 75, 60, 25, "resources/red.png"));
        }
    }

    private void createBall(PowerUpType powerUpType) {
        ballList.add(new Block(237, 437, 25, 25, powerUpType.getAnimate()));
    }

    private void paddleBuilder() {
        this.paddle = new Block(175, 530, 150, 2, "resources/paddle.png");
    }

    private void signsBuilder() {
        this.gameOverSign = new Block(100, 150, 300, 150, "resources/gameover.png");
        this.winSign = new Block(100, 150, 300, 150, "resources/win.png");
        this.winSign.destroyed = true;
        this.gameOverSign.destroyed = true;
    }

    public int destroyedBlocksCountInList(List<Block> blocks) {
        return blocks.stream().mapToInt(ball -> {
            if (!ball.destroyed) {
                return 1;
            }
            return 0;
        }).sum();
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

    public void poweredUPBlocksBuild(PowerUpType powerUpType) {
        switch (powerUpType) {
            case ADD_BALL -> createBall(powerUpType);
            case DOUBLE_BALL -> {
                int qBall = this.ballList.size();
                for (int i = 0; i < qBall; i++) {
                    this.delay += 5000;
                    createBall(powerUpType);
                }
            }
            case BIG_BALL -> {
                this.ballList.forEach(ball -> {
                    if (ball.getPowerUpType() != PowerUpType.BIG_BALL) {
                        ball.width *= 2;
                        ball.height *= 2;
                        ball.setPic(PowerUpType.BIG_BALL);
                        ball.setPowerUpType(PowerUpType.BIG_BALL);
                    }
                });
                this.delay += 5000;
                Scheduler.setDelay(this.delay);
            }
            case ENLARGE_PADDLE -> {
                this.paddle.width += this.paddle.width == 150 ? 30 : 0;
                this.delay += 5000;
            }

        }
    }

    public void poweredUpBlockDestroy() {
        this.ballList.forEach(ball -> {
            switch (ball.getPowerUpType()) {
                case BIG_BALL -> {
                    ball.width /= 2;
                    ball.height /= 2;
                    ball.setPic(PowerUpType.ADD_BALL);
                    ball.setPowerUpType(PowerUpType.ADD_BALL);
                }
                case DOUBLE_BALL -> ball.setDestroyed(true);
            }
        });
        this.paddle.width = 150;
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
