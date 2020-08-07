package main.com.builder.entities.blocks;

import main.com.builder.addons.bonus.BonusType;
import main.com.builder.addons.color.BlockColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlockTest {
    private Block block;

    @BeforeEach
    void setUp() {
        this.block = new Block(100, 100, 200, 200, BlockColor.BLUE);
    }

    @Test
    void isDestroyed_Should_False() {
        assertFalse(this.block.isDestroyed());
    }

    @Test
    void setDestroyed_Should_True() {
        this.block.setDestroyed(true);
        assertTrue(this.block.isDestroyed());
    }

    @Test
    void hasBlockBonus_Should_False() {
        assertFalse(this.block.hasBlockBonus());
    }

    @Test
    void setBlockHasBonus_Should_True() {
        this.block.setBlockHasBonus(true);
        assertTrue(this.block.hasBlockBonus());
    }

    @Test
    void getBonusType_equals_BIG_BALL() {
        this.block.setBonusType(BonusType.BIG_BALL);
        assertEquals(BonusType.BIG_BALL, this.block.getBonusType());
    }

    @Test
    void setBonusType_Should_ENLARGE_PADDLE() {
        this.block.setBonusType(BonusType.ENLARGE_PADDLE);
        assertEquals(BonusType.ENLARGE_PADDLE, this.block.getBonusType());
    }

    @Test
    void setqHits() {
        this.block.setqHits(8);
        assertEquals(8, this.block.getqHits());
    }

}