package main;

import java.util.Random;

public class test {

    static int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        int res = 0;
        do {
            res = r.nextInt((max - min) + 1) + min;
        } while (res == 0);
        return res;
    }


    public static void main(String[] args) {

        for (int i = 0; i < 25; i++) {
            System.out.println(getRandomNumberInRange(-3, 3));
        }


    }
}
