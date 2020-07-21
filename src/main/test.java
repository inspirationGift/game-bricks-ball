package main;

import main.utils.Scheduler;

import java.util.*;

public class test {

    static int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        int res = 0;
        do {
            res = r.nextInt((max - min) + 1) + min;
        } while (res == 0);
        return res;
    }

    static TimerTask sout() {
        System.out.println("hello");
        return null;
    }


    public static void main(String[] args) {

//        for (int i = 0; i < 25; i++) {
//            System.out.println(getRandomNumberInRange(-3, 3));
//        }
//
//        Map<String, Integer> map = new HashMap<>();
//        map.put("A", 20);
//        map.put("B", 40);
//        map.put("C", 40);
//
//        System.out.println(map.values().stream().reduce(0, Integer::sum));

        System.out.println(100);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println(200);
            }
        };

        timer.schedule(task, 2000,2000);
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        timer.cancel();

//        TimerTask task = sout();
//
//        Scheduler scheduler = new Scheduler(task
//                , 5000);
    }
}
