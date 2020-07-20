package main.builder.addons;

import main.builder.entities.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PowerUpBuilder {
    private List<Block> powerUpList;
    private Random random;


    public PowerUpBuilder() {
        this.powerUpList = new ArrayList<>();
        this.random = new Random();
    }

    public void markUpPowerUpBlock(List<Block> horizontalBlocks, int qImpact) {
        int length = horizontalBlocks.size();
        Block block;
        for (int i = 0; i < qImpact; i++) {
            block = horizontalBlocks.get(this.random.nextInt(length));
            block.isBlockPoweredUp = true;
        }
    }

    public void setNewPowerUpBlock(Block block, PowerUpType type) {
        Block newBlock;
        if (block.isBlockPoweredUp) {
            newBlock = new Block(block.x, block.y, 25, 19, "resources/extra.png");
            newBlock.powerUpType = type;
            this.powerUpList.add(newBlock);
        }
    }

    public List<Block> getPowerUpList() {
        return powerUpList;
    }

    public void setPowerUpList(List<Block> powerUpList) {
        this.powerUpList = powerUpList;
    }

    public PowerUpType giveActionIfBlockPowerUpIsIntersect(Block block) {
        for (Block blockPower : this.powerUpList) {
            blockPower.y += 1;
            if (blockPower.intersects(block) && !blockPower.destroyed) {
                blockPower.destroyed = true;
                return blockPower.powerUpType;
            }
        }
        return PowerUpType.DO_NOTHING;
    }
}
