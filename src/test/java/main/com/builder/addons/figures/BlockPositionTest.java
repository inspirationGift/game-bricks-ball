package main.com.builder.addons.figures;

import main.com.builder.addons.bonus.BonusType;
import main.com.builder.addons.color.BlockColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlockPositionTest {

    private BlockPosition position;

    @BeforeEach
    void setUp() {
        this.position = new BlockPosition(10, 10, 10, 10);
        this.position.setColor(BlockColor.GREEN);
        this.position.setBonus(BonusType.ADD_BALL);

    }

    @Test
    void getBonus_Should_Equals_to_Set_UP() {
        assertEquals(this.position.getBonus(), BonusType.ADD_BALL);
        assertEquals(this.position.color, BlockColor.GREEN);
        assertEquals(10, this.position.x);
        assertEquals(10, this.position.y);
        assertEquals(10, this.position.width);
        assertEquals(10, this.position.height);
        assertTrue(this.position.isBonus);
    }

    @Test
    void testToString() {
        assertTrue(position.toString().contains("BlockPosition"));
    }
}