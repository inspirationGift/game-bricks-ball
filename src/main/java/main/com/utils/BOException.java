package main.com.utils;

public class BOException extends Exception {
    public BOException(Exception e) {
        super(e);
    }

    public BOException() {
    }

    public static void log(Exception e) {

    }
}
