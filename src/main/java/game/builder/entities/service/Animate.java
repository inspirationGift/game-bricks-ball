package game.builder.entities.service;


import game.core.GamePanel;


import static java.lang.Thread.sleep;

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
                sleep(12);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
