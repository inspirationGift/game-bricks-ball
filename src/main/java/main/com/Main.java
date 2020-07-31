package main.com;

import main.com.core.MainFrame;
import main.com.utils.recorder.Recorder;

public class Main {

    public static void main(String[] args) {
        Recorder recorder = new Recorder();
        MainFrame frame = new MainFrame(recorder);
    }
}
