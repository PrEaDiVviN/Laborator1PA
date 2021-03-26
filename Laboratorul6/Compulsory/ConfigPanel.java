package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Vector;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel label;         // we’re drawing regular polygons
    JSpinner sidesField;  // number of sides
    JComboBox colorCombo; // the color of the shape
    JLabel label2;
    JSpinner sizeField;
    JButton square;
    int type = 0;
    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }
    private void init() {
        //create the label and the spinner
        label = new JLabel("Number of sides:");
        sidesField = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        sidesField.setValue(6); //default number of sides
        label2 = new JLabel("Size:");
        sizeField = new JSpinner(new SpinnerNumberModel(0,0,250,1));
        square = new JButton("Square");
        square.addActionListener(this::setType);

        // create the colorCombo, containing the values: Random and Black...TODO
        List<String> list= new Vector<>();
        list.add("Random");
        list.add("Black");
        colorCombo =  new JComboBox(list.toArray());

        add(label); //JPanel uses FlowLayout by default
        add(sidesField);
        add(colorCombo);
        add(label2);
        add(sizeField);
        add(square);
    }
    private void setType(ActionEvent e) {
        this.type = 1 - this.type;
    }
}