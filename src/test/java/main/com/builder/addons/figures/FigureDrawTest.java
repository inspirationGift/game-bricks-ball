package main.com.builder.addons.figures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FigureDrawTest {
    private Figure figure;
    private FigureDraw draw;
    private List<BlockPosition> positions;

    @BeforeEach
    void setUp() {
        this.figure = Figure.RECTANGLE;
        this.figure.set(100, 100, 5, 5, 2);
        this.draw = new FigureDraw(this.figure);
        this.positions = this.draw.getBlockPositionsList();
    }

    @Test
    void getFrameWidth() {
        assertEquals(100, this.draw.getFrameWidth());

    }

    @Test
    void getFrameHeight() {
        assertEquals(100, this.draw.getFrameHeight());
    }

    @Test
    void draw_Should_Return_2_Blocks_Only_Or_Less() {
        assertEquals(2, this.positions.size());
    }

    @Test
    void Square_Should_Return_8_Blocks_Only_Or_Less() {
        this.figure = Figure.SQUARE;
        this.figure.set(100, 100, 5, 5, 8);
        this.draw = new FigureDraw(this.figure);
        assertEquals(8, this.draw.getBlockPositionsList().size());
    }

    @Test
    void Triangle_Top_Should_Return_8_Blocks_Only_Or_Less() {
        this.figure = Figure.TRIANGLE_TOP;
        this.figure.set(10, 100, 5, 5, 8);
        this.draw = new FigureDraw(this.figure);
        assertTrue(this.draw.getBlockPositionsList().size() <= 8);

    }

    @Test
    void Triangle_Bottom_Should_Return_8_Blocks_Only_Or_Less() {
        this.figure = Figure.TRIANGLE_BOTTOM;
        this.figure.set(100, 100, 5, 5, 8);
        this.draw = new FigureDraw(this.figure);
        assertTrue(this.draw.getBlockPositionsList().size() <= 8);

    }

    @Test
    void Rhombus_Should_Return_8_Blocks_Only_Or_Less() {
        this.figure = Figure.RHOMBUS;
        this.figure.set(10, 10, 5, 5, 8);
        this.draw = new FigureDraw(this.figure);
        assertTrue(this.draw.getBlockPositionsList().size() <= 8);

    }

    @Test
    void Rectangle_Should_Return_8_Blocks_Only_Or_Less() {
        this.figure = Figure.RECTANGLE;
        this.figure.set(10, 10, 5, 5, 8);
        this.draw = new FigureDraw(this.figure);
        assertTrue(this.draw.getBlockPositionsList().size() <= 8);
    }

    @Test
    void Rhombus_Should_Return_Blocks_Depends_On_H_W_when_Required_Blocks_Is_0() {
        this.figure = Figure.RHOMBUS;
        this.figure.set(100, 100, 5, 5, 0);
        this.draw = new FigureDraw(this.figure);
        assertTrue(this.draw.getBlockPositionsList().size() > 0);

    }


}
