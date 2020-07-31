package main.com.utils;

import java.util.Date;

public class Scheduler {

    private static long delay = 0;
    private static long previousTime = 0;
    private static long currentTime = 0;


    public static boolean isDelayed() {
        currentTime = new Date().getTime();
        if (currentTime - previousTime > delay) {
            previousTime = currentTime;
            delay = 0;
            return true;
        }
        return false;
    }

    public static void setDelay(long delay) {
        Scheduler.delay += delay;
    }
}