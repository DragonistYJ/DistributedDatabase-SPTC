package org.example.hw;

import org.example.hw.panel.InsertPanel;
import org.example.hw.panel.SelectPanel;

import javax.swing.*;

public class InterfaceMain {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(900, 600);

        SelectPanel selectPanel = new SelectPanel(new SpringLayout());
        selectPanel.init();
        InsertPanel insertPanel = new InsertPanel(new SpringLayout());
        insertPanel.init();

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("查询数据", selectPanel);
        tabbedPane.addTab("增加数据", insertPanel);
        frame.setContentPane(tabbedPane);

        frame.setVisible(true);
    }
}
