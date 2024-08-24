package ui;

import model.EventLog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AccountsGUI extends JPanel {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 1000;
    private static final int VGAP = 15;
    private JPanel panel;
    //private JLabel imageAsLabel;

    public AccountsGUI(ActionsGUI actionsGUI) {

        actionsGUI = actionsGUI;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalStrut(VGAP));

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        add(panel);
    }
}
