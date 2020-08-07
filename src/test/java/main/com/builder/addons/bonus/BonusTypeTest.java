package main.com.builder.addons.bonus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BonusTypeTest {

    private BonusType type;

    @BeforeEach
    void setUp() {
        this.type = BonusType.ADD_BALL;
        this.type.resetBonus();
    }

    @Test
    void getValue_and_animate_Should_return_links() {
        assertAll(
                () -> assertTrue(type.getValue().contains("balloon")),
                () -> assertTrue(type.getAnimate().contains("ball"))
        );
    }

    @Test
    void setBonusQ_Should_Return_Q_bonus() {
        assertAll(
                () -> {
                    type.setBonusQ(5);
                    assertEquals(5, type.getBonusQ());
                },
                () -> {
                    type.setBonusQ(8);
                    assertEquals(13, type.getBonusQ());
                }
        );
    }

    @Test
    void Should_Reset_Hits_to_0() {
        long count = Arrays.stream(BonusType.values()).filter(v -> v.getBonusQ() > 0).count();
        assertEquals(0, count);
    }

    @Test
    void getAnyAndSetBonus_Should_Return_Q_of_Bonus() {

        Integer reduce = Arrays.stream(BonusType.values())
                .map(BonusType::getBonusQ)
                .reduce(0, Integer::sum);
        assertEquals(0, reduce);

        type.getAnyBonusAndSetIncrementalQ();
        Integer reduce1 = Arrays.stream(BonusType.values())
                .map(BonusType::getBonusQ)
                .reduce(0, Integer::sum);
        assertEquals(1, reduce1);

        type.getAnyBonusAndSetIncrementalQ();
        Integer reduce2 = Arrays.stream(BonusType.values())
                .map(BonusType::getBonusQ)
                .reduce(0, Integer::sum);
        assertEquals(2, reduce2);

    }

    @Test
    void getAnyAndSetBonus_Should_Skip_DO_NOTHING() {

        type.getAnyBonusAndSetIncrementalQ();
        type.getAnyBonusAndSetIncrementalQ();
        type.getAnyBonusAndSetIncrementalQ();
        type.getAnyBonusAndSetIncrementalQ();
        type.getAnyBonusAndSetIncrementalQ();
        type.getAnyBonusAndSetIncrementalQ();
        type.getAnyBonusAndSetIncrementalQ();

        boolean equals = Arrays.stream(BonusType.values())
                .filter(v -> v.getBonusQ() > 0)
                .findAny().equals(BonusType.DO_NOTHING);
        assertFalse(equals);

        long count = Arrays.stream(BonusType.values())
                .filter(v -> v.equals(BonusType.DO_NOTHING))
                .map(BonusType::getBonusQ)
                .count();
        assertEquals(1, count);
    }

    @Test
    void testToString() {
        assertTrue(type.toString().contains("BonusType"));
    }

}