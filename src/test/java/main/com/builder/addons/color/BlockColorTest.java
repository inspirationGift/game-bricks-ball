package main.com.builder.addons.color;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BlockColorTest {

    private BlockColor color;

    @BeforeEach
    void setUp() {
        this.color = BlockColor.GREEN;
        this.color.resetBlockColor();
        this.color.setRateTransition(5);
        this.color.setqHits(5);
    }

    @Test
    void getRateTransition_Should_Set_Get_Transition() {
        int rateTransition = this.color.getRateTransition();
        assertEquals(5, rateTransition);
    }

    @Test
    void getPic_Should_Set_Get_Pic_Red() {
        boolean green = this.color.getPic().toLowerCase().contains("green");
        assertTrue(green);

        this.color.setPic("red");
        boolean red = this.color.getPic().contains("red");
        assertTrue(red);

        boolean equals = this.color.name().equals(BlockColor.GREEN.name());
        assertTrue(equals);
    }

    @Test
    void getqHits_Should_Set_Get_hits() {
        int i = this.color.getqHits();
        assertEquals(5, i);
    }

    @Test
    void getZeroHits_Should_less_1_Default_1_With_Hits() {
        Object[] zeroHits = this.color.getZeroHits();
        assertEquals(BlockColor.values().length - 2, zeroHits.length);
    }

    @Test
    void getNotZeroHits_Should_Be_1() {
        int length = this.color.getNotZeroHits().length;
        assertEquals(1, length);
    }

    @Test
    void getNotZeroHits_Should_Be_Hits() {
        BlockColor.RED.setqHits(8);
        assertEquals(2, this.color.getNotZeroHits().length);

        BlockColor.YELLOW.setqHits(100);
        assertEquals(3, this.color.getNotZeroHits().length);
    }

    @Test
    void getAllColors_Should_Not_Contain_Default() {
        boolean equals = Arrays.stream(this.color.getAllColors()).findAny().equals(BlockColor.DEFAULT);
        assertFalse(equals);
    }

    @Test
    void resetBlockColor_Should_Set_Hits_to_0() {
        BlockColor.RED.setqHits(8);
        BlockColor.YELLOW.setqHits(100);

        this.color.resetBlockColor();
        int length = this.color.getNotZeroHits().length;
        assertEquals(0, length);
    }

    @Test
    void setRandomHits_Set_Hit_To_Each_color() {
        this.color.resetBlockColor();

        long sum = Arrays.stream(BlockColor.values())
                .map(BlockColor::getqHits)
                .reduce(0, Integer::sum);
        assertEquals(0, sum);

        this.color.setRandomHits();
        int length = this.color.getAllColors().length;
        int length1 = this.color.getNotZeroHits().length;
        assertEquals(length, length1);
    }

    @Test
    void setRandomBlockHits_Should_Set_Random_Hit_To_Random_Color() {
        this.color.resetBlockColor();
        this.color.setRandomBlockHits(4);

        BlockColor color1 = (BlockColor) this.color.getNotZeroHits()[0];
        int length = this.color.getNotZeroHits().length;
        int i = color1.getqHits();
        assertEquals(1, length);
        assertTrue(i > 0);
        assertTrue(i < 5);
    }

    @Test
    void testToString() {
        assertTrue(color.toString().contains("Color"));
    }
}