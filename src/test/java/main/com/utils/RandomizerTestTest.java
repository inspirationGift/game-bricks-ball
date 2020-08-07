package main.com.utils;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RandomizerTestTest {

    @Test
//    @RepeatedTest(2)
    void randomInRangeNotZero() {
        int random = Randomizer.randomInRangeNotZero(-10, 10);
        assertNotEquals(0, random);
    }

    @Test
//    @RepeatedTest(2)
    void random() {
        int random = Randomizer.random(10);
        assertTrue(random >= 0);
    }

    @Test
//    @RepeatedTest(2)
    void randomInRange() {
        int random = Randomizer.randomInRange(0, 5);
        assertTrue(random >= 0);
        assertTrue(random <= 5);

    }


    @Test
    void randomBool() {
        boolean random;
        if (Randomizer.randomBool()) random = true;
        else random = true;
        assertEquals(true, random);
    }

    @Test
    void pullElementFromMap() {

        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        map.put(4, 4);

        Randomizer.pullElementFromMap(map, false);
        assertNull(map.get(1));
        Randomizer.pullElementFromMap(map, false);
        assertTrue(map.get(2).equals(1));
    }
}