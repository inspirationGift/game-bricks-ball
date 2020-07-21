package main.utils;

import java.util.Date;

public class Scheduler {


    private static long delay;
    private static long previousTime;
    private static long currentTime;


    public static boolean isDelayed() {
        currentTime = new Date().getTime();
        if (currentTime - previousTime >= delay) {
            previousTime = currentTime;
            delay = 0;
            return true;
        }
        return false;
    }

    public static long getDelay() {
        return delay;
    }

    public static void setDelay(long delay) {
        Scheduler.delay += delay;
    }

    public static long getPreviousTime() {
        return previousTime;
    }

    public static void setPreviousTime(long previousTime) {
        Scheduler.previousTime = previousTime;
    }

    public static long getCurrentTime() {
        return currentTime;
    }

    public static void setCurrentTime(long currentTime) {
        Scheduler.currentTime = currentTime;
    }
}