package main.builder.entities;

import java.util.ArrayList;
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
        horizontalBlocksBuilder();
        ballBuilder();
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

    private void ballBuilder() {
        ballList.add(new Block(237, 437, 25, 25, "resources/ball.png"));
    }

    private void paddleBuilder() {
        this.paddle = new Block(175, 480, 150, 2, "resources/paddle.png");
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

    public void setAdditionalBall() {
        ballBuilder();
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
