package org.example.hw.panel;

import javax.swing.*;
import java.awt.*;

public class InsertPanel extends JPanel {
    private SpringLayout layout;
    private final int hPad = 15;
    private final int vPad = 15;
    private JLabel titleLabel;
    private JLabel stuNumberLabel;
    private JTextField stuNumberField;
    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel ageLabel;
    private JTextField ageField;
    private JLabel sexLabel;
    private JTextField sexField;
    private JLabel resultLabel;

    public InsertPanel() {
        super(true);
    }

    public InsertPanel(SpringLayout layout) {
        super(layout);
        this.layout = layout;
    }

    public void init() {
        this.initTitle();
        this.initStuNumber();
        this.initStuName();
    }

    private void initTitle() {
        this.titleLabel = new JLabel("增加学生信息");
        this.add(this.titleLabel);
        this.titleLabel.setFont(new Font("宋体", Font.BOLD, 25));
        this.layout.putConstraint(SpringLayout.NORTH, this.titleLabel, vPad, SpringLayout.NORTH, this);
        this.layout.putConstraint(SpringLayout.WEST, this.titleLabel, hPad, SpringLayout.WEST, this);
    }

    private void initStuNumber() {
        Font font = new Font("宋体", Font.PLAIN, 20);

        this.stuNumberLabel = new JLabel("学号");
        this.add(this.stuNumberLabel);
        this.stuNumberLabel.setFont(font);
        this.layout.putConstraint(SpringLayout.NORTH, this.stuNumberLabel, vPad, SpringLayout.SOUTH, this.titleLabel);
        this.layout.putConstraint(SpringLayout.WEST, this.stuNumberLabel, hPad, SpringLayout.WEST, this);

        this.stuNumberField = new JTextField();
        this.add(this.stuNumberField);
        this.stuNumberField.setFont(font);
        SpringLayout.Constraints fieldCons = this.layout.getConstraints(this.stuNumberField);
        fieldCons.setWidth(Spring.constant(200));
        this.layout.putConstraint(SpringLayout.NORTH, this.stuNumberField, vPad, SpringLayout.SOUTH, this.titleLabel);
        this.layout.putConstraint(SpringLayout.WEST, this.stuNumberField, vPad, SpringLayout.EAST, this.stuNumberLabel);
    }

    private void initStuName() {
        Font font = new Font("宋体", Font.PLAIN, 20);
        this.nameLabel = new JLabel("姓名");
        this.add(this.nameLabel);
        this.nameLabel.setFont(font);
        this.layout.putConstraint(SpringLayout.NORTH, this.nameLabel, vPad, SpringLayout.SOUTH, this.stuNumberLabel);
        this.layout.putConstraint(SpringLayout.WEST, this.nameLabel, hPad, SpringLayout.WEST, this);

        this.nameField = new JTextField();
        this.add(nameField);
        this.nameField.setFont(font);
        SpringLayout.Constraints fieldCons = this.layout.getConstraints(this.nameField);
        fieldCons.setWidth(Spring.constant(200));
        this.layout.putConstraint(SpringLayout.NORTH, this.nameField, vPad, SpringLayout.SOUTH, this.stuNumberLabel);
        this.layout.putConstraint(SpringLayout.WEST, this.nameField, hPad, SpringLayout.EAST, this.nameLabel);
    }
}
