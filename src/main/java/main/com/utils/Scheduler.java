package main.com.utils;

import java.util.Date;

public class Scheduler {

    private static long delay = 0;
    private static long previousTime = 0;
    private static long currentTime = 0;


    public static boolean isDelayed() {
        currentTime = new Date().getTime();
        if (currentTime - previousTime >= delay) {
            previousTime = currentTime;
            delay = 0;
            return false;
        }
        return true;
    }

    public static long getDelay() {
        return delay;
    }

    public static void setDelay(long delay) {
        currentTime = new Date().getTime();
        if (previousTime == 0) previousTime = currentTime;
        Scheduler.delay = delay;
    }

    public static long getPreviousTime() {
        return previousTime;
    }

    public static long getCurrentTime() {
        return currentTime;
    }

}