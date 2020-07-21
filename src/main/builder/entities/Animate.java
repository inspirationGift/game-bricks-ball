package main.builder.entities;


import main.core.GamePanel;

public class Animate implements Runnable {

    private final GamePanel panel;

    public Animate(GamePanel panel) {
        this.panel = panel;
    }

    @Override
    public void run() {
        while (true) {
            this.panel.update();
            try {
                Thread.sleep(12);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
