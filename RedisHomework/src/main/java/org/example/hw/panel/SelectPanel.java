package org.example.hw.panel;

import redis.clients.jedis.Jedis;

import javax.swing.*;
import java.util.Map;

public class SelectPanel extends JPanel {
    private SpringLayout layout;
    private final int hPad = 15;
    private final int vPad = 15;
    private JLabel informationLabel;
    private JLabel stuNumberLabel;
    private JTextField stuNumberField;
    private JLabel stuNameLabel;
    private JTextField stuNameField;
    private JButton searchButton;
    private JTextArea resultArea;

    public SelectPanel() {
        super(true);
    }

    public SelectPanel(SpringLayout layout) {
        super(layout);
        this.layout = layout;
    }

    public void init() {
        this.initTitle();
        this.initStuNumber();
        this.initStuName();
        this.initSearchButton();
        this.initResultArea();
    }

    private void initTitle() {
        this.informationLabel = new JLabel("学生信息查询");
        this.add(informationLabel);
        this.layout.putConstraint(SpringLayout.NORTH, this.informationLabel, vPad, SpringLayout.NORTH, this);
        this.layout.putConstraint(SpringLayout.WEST, this.informationLabel, hPad, SpringLayout.WEST, this);
    }

    private void initStuNumber() {
        this.stuNumberLabel = new JLabel("学号");
        this.stuNumberField = new JTextField();
        this.add(stuNumberLabel);
        this.add(stuNumberField);
        SpringLayout.Constraints stuNumberFieldCons = this.layout.getConstraints(this.stuNumberField);
        stuNumberFieldCons.setWidth(Spring.constant(200));
        this.layout.putConstraint(SpringLayout.NORTH, this.stuNumberLabel, vPad, SpringLayout.SOUTH, this.informationLabel);
        this.layout.putConstraint(SpringLayout.WEST, this.stuNumberLabel, hPad, SpringLayout.WEST, this);
        this.layout.putConstraint(SpringLayout.NORTH, this.stuNumberField, vPad, SpringLayout.SOUTH, this.informationLabel);
        this.layout.putConstraint(SpringLayout.WEST, this.stuNumberField, hPad, SpringLayout.EAST, this.stuNumberLabel);
    }

    private void initStuName() {
        this.stuNameLabel = new JLabel("姓名");
        this.stuNameField = new JTextField();
        this.add(this.stuNameLabel);
        this.add(this.stuNameField);
        SpringLayout.Constraints stuNameFieldCons = this.layout.getConstraints(this.stuNameField);
        stuNameFieldCons.setWidth(Spring.constant(200));
        this.layout.putConstraint(SpringLayout.NORTH, this.stuNameLabel, vPad, SpringLayout.SOUTH, this.stuNumberLabel);
        this.layout.putConstraint(SpringLayout.WEST, this.stuNameLabel, hPad, SpringLayout.WEST, this);
        this.layout.putConstraint(SpringLayout.NORTH, this.stuNameField, vPad, SpringLayout.SOUTH, this.stuNumberLabel);
        this.layout.putConstraint(SpringLayout.WEST, this.stuNameField, hPad, SpringLayout.EAST, this.stuNumberLabel);
    }

    private void initSearchButton() {
        this.searchButton = new JButton("查询");
        this.layout.putConstraint(SpringLayout.NORTH, this.searchButton, vPad, SpringLayout.SOUTH, this.stuNameLabel);
        this.layout.putConstraint(SpringLayout.WEST, this.searchButton, hPad, SpringLayout.WEST, this);
        this.searchButton.addActionListener(event -> {
            String stuNumber = stuNumberField.getText();
            Jedis jedis = new Jedis("127.0.0.1", 6379);
            Map<String, String> stuInfo = jedis.hgetAll(stuNumber);
            StringBuilder result = new StringBuilder();
            for (String key : stuInfo.keySet()) {
                String value = stuInfo.get(key);
                result.append(key).append(": ").append(value).append("\n");
            }
            resultArea.setText(result.toString());
        });
        this.add(this.searchButton);
    }

    private void initResultArea() {
        this.resultArea = new JTextArea();
        this.add(this.resultArea);
        this.layout.putConstraint(SpringLayout.NORTH, this.resultArea, vPad, SpringLayout.NORTH, this);
        this.layout.putConstraint(SpringLayout.SOUTH, this.resultArea, 0, SpringLayout.SOUTH, this.searchButton);
        this.layout.putConstraint(SpringLayout.WEST, this.resultArea, hPad, SpringLayout.EAST, this.stuNameField);
        this.layout.putConstraint(SpringLayout.EAST, this.resultArea, -hPad, SpringLayout.EAST, this);
    }
}
