package main.com.builder.addons.figures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FigureTest {
    private Figure figure;

    @BeforeEach
    void setUp() {
        this.figure = Figure.RECTANGLE;
        this.figure.set(100, 200, 50, 50, 10);
    }

    @Test
    void getFrameWidth() {
        assertEquals(100, this.figure.getFrameWidth());
    }

    @Test
    void getFrameHeight_And_Block_W_H() {
        assertEquals(200, this.figure.getFrameHeight());
        assertEquals(50, this.figure.getBlockWidth());
        assertEquals(50, this.figure.getBlockHeight());
        assertEquals(10, this.figure.getRequiredBlocks());
    }

    @Test
    void testToString() {
        assertTrue(figure.toString().contains("Figure"));
    }


}