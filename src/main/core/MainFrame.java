package main.core;

import main.levels.Level1;

import javax.swing.*;
import java.awt.*;

public class MainFrame {

    private JFrame frame;
    private JButton restart;
    private GamePanel panel;

    public MainFrame() {
        this.frame = new JFrame("Blocks & Fun");
        this.restart = new JButton("Restart");
        init();
    }

    public void init() {
        this.restart.setFocusable(false);
        this.restart.addActionListener(e -> reLaunch());
        this.panel = new Level1();
        this.frame.getContentPane().add(BorderLayout.CENTER, this.panel);
        this.frame.getContentPane().add(BorderLayout.NORTH, restart);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(490, 600);
        this.frame.setVisible(true);
        this.frame.setResizable(false);
    }

    private void reLaunch() {
        this.frame.remove(this.panel);
        this.frame.remove(this.restart);
        this.frame.setVisible(false);
        init();
    }


}
