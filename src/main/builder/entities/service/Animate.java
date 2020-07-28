package main.builder.entities.service;


import main.core.GamePanel;

import static java.lang.Thread.sleep;

public class Animate implements Runnable {

    private final GamePanel panel;
    private boolean flag;

    public Animate(GamePanel panel) {
        this.panel = panel;
        this.flag = true;
    }


    public boolean getFlag() {
        return flag;
    }

    public void setFlag() {
        this.flag = !flag;
    }

    @Override
    public void run() {
        while (this.flag) {
            this.panel.update();
            try {
                sleep(12);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
