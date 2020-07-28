package main.builder.entities.blocks;

import main.builder.addons.bonus.BonusType;
import main.builder.addons.figures.BlockPosition;
import main.core.LevelSettings;
import main.utils.Scheduler;

import java.util.*;
import java.util.List;

public class BlocksBuilder {

    private List<Block> horizontalBlocksList;
    private List<Block> ballList;
    private List<Block> bonuses;
    private Block paddle;
    private Block gameOverSign;
    private Block winSign;

    public BlocksBuilder(LevelSettings ls) {
        this.horizontalBlocksList = Collections.synchronizedList(new ArrayList<>());
        this.ballList = Collections.synchronizedList(new ArrayList<>());
        this.bonuses = Collections.synchronizedList(new ArrayList<>());

        horizontalBlocksBuilder(ls);
        paddleBuilder();
        createBall(BonusType.ADD_BALL);
        signsBuilder();
    }

    public List<Block> getBonuses() {
        return bonuses;
    }

    private void horizontalBlocksBuilder(LevelSettings ls) {
        List<BlockPosition> positions = ls.getFigure();
        for (BlockPosition pos : positions) {
            Block block = new Block(pos.x, pos.y, pos.width, pos.height, pos.color);
            block.setBlockHasBonus(true);
            block.setBonusType(pos.getBonus());
            this.horizontalBlocksList.add(block);
        }
    }

    public void createBall(BonusType bonusType) {
        ballList.add(new Block((this.paddle.x + paddle.width / 2), paddle.y - 27, 25, 25, bonusType.getAnimate()));
    }

    private void paddleBuilder() {
        this.paddle = new Block(175, 530, 150, 3, "resources/blocks/paddle.png");
    }

    private void signsBuilder() {
        this.gameOverSign = new Block(100, 150, 300, 150, "resources/gameover.png");
        this.winSign = new Block(100, 150, 300, 150, "resources/win.png");
        this.winSign.destroyed = true;
        this.gameOverSign.destroyed = true;
    }

    public void giveActionIfBonusIsIntersected(Block paddle) {
        for (Block bonus : this.bonuses) {
            if (!bonus.destroyed) {
                synchronized (bonus) {
                    bonus.y += 1;
                    if (bonus.intersects(paddle)) {
                        bonus.destroyed = true;
                        bonusBlocksBuild(bonus.getBonusType());
                    }
                }
            }
        }
    }

    public void bonusBlocksBuild(BonusType bonusType) {
        switch (bonusType) {
            case ADD_BALL -> createBall(bonusType);
            case DOUBLE_BALL -> {
                long count = this.ballList.stream().filter(val -> !val.destroyed).count();
                for (int i = 0; i < count; i++) {
                    Scheduler.setDelay(5000);
                    createBall(bonusType);
                }
            }
            case BIG_BALL -> {
                this.ballList.forEach(ball -> {
                    if (ball.getBonusType() != BonusType.BIG_BALL && !ball.destroyed) {
                        ball.width *= 2;
                        ball.height *= 2;
                        ball.setPic(BonusType.BIG_BALL);
                        ball.setBonusType(BonusType.BIG_BALL);
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

    public void bonusBlockDestroy() {
        this.ballList.forEach(ball -> {
            if (!ball.destroyed) {
                switch (ball.getBonusType()) {
                    case BIG_BALL -> {
                        ball.width /= 2;
                        ball.height /= 2;
                        ball.setPic(BonusType.ADD_BALL);
                        ball.setBonusType(BonusType.ADD_BALL);
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

    public void setBonusBlock(Block block) {
        Block newBlock;
        block.setBlockHasBonus(false);
        newBlock = new Block(block.x, block.y, 25, 19, block.getBonusType().getValue());
        newBlock.setBonusType(block.getBonusType());
        this.bonuses.add(newBlock);
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
