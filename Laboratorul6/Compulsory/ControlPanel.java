package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ControlPanel extends JPanel {
        final MainFrame frame;
        JButton saveBtn = new JButton("Save");
        JButton loadBtn = new JButton("Load");
        JButton exitBtn = new JButton("Exit");
        JButton resetBtn = new JButton("Reset");
        JFormattedTextField text = new JFormattedTextField();

        public ControlPanel(MainFrame frame) {
            this.frame = frame;
            init();
        }

        private void init() {
            //change the default layout manager (just for fun)
            setLayout(new GridLayout(1, 5));
            //add all buttons...TODO
            add(saveBtn);
            add(loadBtn);
            add(exitBtn);
            add(resetBtn);
            add(text);

            // configure listeners for all buttons
            exitBtn.addActionListener(this::exit);
            saveBtn.addActionListener(this::save);
            loadBtn.addActionListener(this::load);
            resetBtn.addActionListener(this::reset);
        }
        private void load(ActionEvent e) {
            try {
                BufferedImage img = ImageIO.read(new File(text.getText()));
                this.frame.canvas.image = img;
                this.frame.canvas.graphics = img.createGraphics();
                frame.canvas.repaint();
            }
            catch (IOException e1) {
                System.out.println(e1.getMessage());
            }
        }

        private void reset(ActionEvent e) {
            frame.canvas.image = new BufferedImage(frame.canvas.getWidth(), frame.canvas.getHeight(), BufferedImage.TYPE_INT_ARGB);
            frame.canvas.graphics = frame.canvas.image.createGraphics();
            frame.canvas.graphics.setColor(Color.WHITE);  //fill the image with white
            frame.canvas.graphics.fillRect(0, 0, frame.canvas.getWidth(), frame.canvas.getHeight());
            frame.canvas.repaint();
        }

        private void exit(ActionEvent e) {
            System.exit(0);
        }

        private void save(ActionEvent e) {
            try {
                ImageIO.write(frame.canvas.image, "PNG", new File("d:/test.png"));
            }
            catch (IOException ex) {
                System.err.println(ex);
            }
        }
}