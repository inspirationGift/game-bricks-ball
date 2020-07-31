package main.com.utils;

import java.util.Map;
import java.util.Random;

public class Randomizer {
    private static Random r = new Random();


    public static int randomInRangeNotZero(int min, int max) {
        int res;
        do {
            res = r.nextInt((max - min) + 1) + min;
        } while (res == 0);
        return res;
    }

    public static int random(int i) {
        return r.nextInt(i);
    }

    public static int randomInRange(int min, int max) {
        return r.nextInt((max - min) + 1) + min;
    }

    public static boolean randomBool() {
        return randomInRange(0, 1) == 1;
    }

    public static Object pullElementFromMap(Map map, boolean isRandom) {
        int size = map.size();
        Object value;
        if (isRandom)
            value = map.keySet().toArray()[randomInRange(0, size - 1)];
        else
            value = map.keySet().toArray()[0];
        if (size > 0) {
            int i = (Integer) map.get(value) - 1;
            if (i == 0) {
                map.remove(value);
            } else {
                map.put(value, i);
            }
        }
        return value;
    }

}
