package main.builder.addons;

import main.builder.entities.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class PowerUpBuilder {
    private List<Block> powerUpList;
    private Random random;


    public PowerUpBuilder() {
        this.powerUpList = new ArrayList<>();
        this.random = new Random();
    }

    public void markUpPowerUpBlock(List<Block> horizontalBlocks, Map<PowerUpType, Integer> powerUpTypeIntegerMap) {
        int length = horizontalBlocks.size();
        Integer reduce = powerUpTypeIntegerMap.values().stream().reduce(0, Integer::sum);

        powerUpTypeIntegerMap.forEach((k, v) -> {
            for (int i = 0; i < v; i++) {
                Block block;
                block = horizontalBlocks.get(this.random.nextInt(length));
                block.setBlockPoweredUp(true);
                block.setPowerUpType(k);
            }
        });
    }

    public void setNewPowerUpBlock(Block block) {
        Block newBlock;
        if (block.isBlockPoweredUp()) {
            newBlock = new Block(block.x, block.y, 25, 19, block.getPowerUpType().getValue());
            newBlock.setPowerUpType(block.getPowerUpType());
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
                return blockPower.getPowerUpType();
            }
        }
        return PowerUpType.DO_NOTHING;
    }
}
