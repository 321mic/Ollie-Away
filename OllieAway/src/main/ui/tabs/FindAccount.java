package ui.tabs;

import model.Account;
import model.AccountList;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.ActionsGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class FindAccount extends Tab {
    private JTextField usernameInput;
    private JTextField passwordInput;
    private JLabel foundAccount;
    //private BufferedImage face;
    private JLabel happy;
    private JLabel sad;

    public FindAccount(ActionsGUI controller) throws IOException {
        super(controller);

        final AccountList[] accountList = {new AccountList()};
        JsonReader jsonReader = new JsonReader("./data/accountList.json");

        foundAccount = new JLabel("");

        happy = new JLabel(new ImageIcon("./lib/images/userFound-cpsc210.png"));
        happy.setBounds(50, 50, 50, 50);
        sad = new JLabel(new ImageIcon("./lib/images/userNotFound-cpsc210.png"));
        sad.setBounds(50, 50, 50, 50);

        usernameInput = new JTextField("username to find", 20);
        passwordInput = new JTextField("password to find",20);

        addActionListenerFind();

        makeSearchButton(accountList, jsonReader);
        add(usernameInput, BorderLayout.CENTER);
        add(passwordInput, BorderLayout.CENTER);
        add(foundAccount, BorderLayout.AFTER_LINE_ENDS);
        add(happy);
        add(sad);
        happy.setVisible(false);
        sad.setVisible(false);
    }

    public void makeSearchButton(AccountList[] accountList, JsonReader jsonReader) {
        JButton searchButton = new JButton("Search");
        searchButton.setActionCommand("Account found");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    accountList[0] = jsonReader.read();
                } catch (IOException ex) {
                    System.out.println("Unable to read from file: ./data/accountList.json");
                }
                Account findAccount = accountList[0].getAccount(usernameInput.getText(), passwordInput.getText());
                if (findAccount == null) {
                    foundAccount.setText("Couldn't find account.");
                    happy.setVisible(false);
                    sad.setVisible(true);
                } else {
                    foundAccount.setText("Account found.");
                    sad.setVisible(false);
                    happy.setVisible(true);
                }
            }
        });
        add(searchButton, BorderLayout.NORTH);
    }

    public void addActionListenerFind() {
        usernameInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // pass
            }
        });
        passwordInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // pass
            }
        });
    }
}
