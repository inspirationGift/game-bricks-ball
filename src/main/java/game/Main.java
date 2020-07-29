package game;

import game.core.MainFrame;
import recorder.Recorder;

public class Main {

    public static void main(String[] args) {
        Recorder recorder = new Recorder();
        MainFrame frame = new MainFrame(recorder);
    }
}
