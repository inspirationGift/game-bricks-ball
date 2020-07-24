package main;

import main.builder.addons.BlockColor;
import main.builder.addons.PowerUpType;
import main.builder.entities.BlocksBuilder;
import main.core.GamePanel;
import main.levels.Level1;
import main.levels.Levels;


import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class test {

    static int q;
    private static Class<BlockColor> green;
    private static java.lang.Object Object;
    private static java.lang.Object GamePanel;

    static int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        int res;
        do {
            res = r.nextInt((max - min) + 1) + min;
        } while (res == 0);
        return res;
    }

    static TimerTask sout() {
        System.out.println("hello");
        return null;
    }


    public static int getRandomColor(int quantity, boolean isEqually) {
        q = quantity;
        int v = quantity % BlockColor.values().length;
        if (isEqually && v > 0)
            q = quantity - v;
        return q;
    }

    public static void f() {
    }


    public static void main(String[] args) {

    }

}
