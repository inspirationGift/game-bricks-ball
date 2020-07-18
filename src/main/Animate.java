package main;


public class Animate implements Runnable {

    private final BlockBreakerPanel panel;

    public Animate(BlockBreakerPanel panel) {
        this.panel = panel;
    }

    @Override
    public void run() {
        while (true) {
            this.panel.update();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
