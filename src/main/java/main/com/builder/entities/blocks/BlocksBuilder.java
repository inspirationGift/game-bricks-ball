package main.com.builder.entities.blocks;

import main.com.builder.addons.bonus.BonusType;
import main.com.builder.addons.figures.BlockPosition;
import main.com.core.LevelSettings;
import main.com.utils.Scheduler;
import main.com.state.StateDTO;

import java.io.Serializable;
import java.util.*;
import java.util.List;

public class BlocksBuilder implements Serializable {

    private List<Block> horizontalBlocksList;
    private List<Block> ballList;
    private List<Block> bonuses;
    private Block paddle;
    private Block gameOverSign;
    private Block winSign;
    private LevelSettings levelSettings;

    public BlocksBuilder() {
        this.horizontalBlocksList = Collections.synchronizedList(new ArrayList<>());
        this.ballList = Collections.synchronizedList(new ArrayList<>());
        this.bonuses = Collections.synchronizedList(new ArrayList<>());
    }

    public BlocksBuilder(LevelSettings ls) {
        this();
        this.levelSettings = ls;
        horizontalBlocksBuilder(ls);
        paddleBuilder(ls.getFrameWidth(), ls.getFrameHeight());
        createBall(BonusType.ADD_BALL);
        signsBuilder(levelSettings.getFrameWidth());
    }

    public BlocksBuilder(StateDTO stateDTO) {
        this();


        stateDTO.blocks.forEach(b -> {
            Block block = new Block(b.x, b.y, b.width, b.height, b.color);
            block.setBlockHasBonus(b.isBonuses);
            block.setBonusType(b.bonus);
            this.horizontalBlocksList.add(block);
        });

        paddleBuilder(stateDTO.frameWidth, stateDTO.frameHeight);
        stateDTO.balls.forEach(b -> createBall(BonusType.ADD_BALL));
        signsBuilder(stateDTO.frameWidth);

        stateDTO.bonuses.forEach(b -> {
            Block newBlock = new Block(b.x, b.y, 25, 19, b.bonus.getValue());
            newBlock.setBonusType(b.bonus);
            this.bonuses.add(newBlock);
        });
    }

    public List<Block> getBonuses() {
        return bonuses;
    }

    public void setBonusBlock(Block block) {
        Block newBlock;
        block.setBlockHasBonus(false);
        newBlock = new Block(block.x, block.y, 25, 19, block.getBonusType().getValue());
        newBlock.setBonusType(block.getBonusType());
        this.bonuses.add(newBlock);
    }


    private void horizontalBlocksBuilder(LevelSettings ls) {
        List<BlockPosition> positions = ls.getFigure();
        for (BlockPosition pos : positions) {
            Block block = new Block(pos.x, pos.y, pos.width, pos.height, pos.color);
            block.setBlockHasBonus(pos.isBonus);
            block.setBonusType(pos.getBonus());
            this.horizontalBlocksList.add(block);
        }
    }

    public void createBall(BonusType bonusType) {
        ballList.add(new Block((this.paddle.x + paddle.width / 2), paddle.y - 25,
                25, 25, bonusType.getAnimate()));
    }

    private void paddleBuilder(int width, int height) {
        this.paddle = new Block((int) (width * 0.4),
                (int) (height * 0.85),
                150, 5, "blocks/paddle.png");
    }

    private void signsBuilder(int width) {
        this.gameOverSign = new Block((width / 2) - 150,
                20,
                300, 150, "signs/gameover.png");
        this.winSign = new Block((width / 2) - 150,
                20,
                300, 150, "signs/win.png");
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

    public List<Block> getHorizontalBlocksList() {
        return horizontalBlocksList;
    }

    public List<Block> getBallList() {
        return ballList;
    }

    public Block getGameOverSign() {
        return gameOverSign;
    }

    public Block getWinSign() {
        return winSign;
    }
}
