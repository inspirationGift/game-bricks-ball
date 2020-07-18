package main;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;

public class Main {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Blocks & Fun");
        JButton button = new JButton("Start");
        button.setSize(100, 25);
        button.setFocusable(false);

        BlockBreakerPanel panel = new BlockBreakerPanel();

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.getContentPane().add(BorderLayout.SOUTH, button);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        button.addActionListener(e -> {
//            System.exit(5);
        });

        frame.setVisible(true);
        frame.setSize(490, 600);
        frame.setResizable(false);

    }
}
