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
import java.io.FileNotFoundException;
import java.io.IOException;

public class AddAccount extends Tab {
    private JTextField usernameInput;
    private JTextField passwordInput;

    public AddAccount(ActionsGUI controller) {
        super(controller);

        final AccountList[] accountList = {new AccountList()};
        JsonReader jsonReader = new JsonReader("./data/accountList.json");
        JsonWriter jsonWriter = new JsonWriter("./data/accountList.json");

        usernameInput = new JTextField("enter username", 20);
        passwordInput = new JTextField("enter password",20);
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

        makeAddButton(accountList, jsonReader, jsonWriter);

        add(usernameInput, BorderLayout.CENTER);
        add(passwordInput, BorderLayout.CENTER);
    }

    public void makeAddButton(AccountList[] accountList, JsonReader jsonReader, JsonWriter jsonWriter) {
        JButton addButton = new JButton("Add");
        addButton.setActionCommand("Account added");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    accountList[0] = jsonReader.read();
                    accountList[0].addAccount(new Account(usernameInput.getText(), passwordInput.getText()));
                    jsonWriter.open();
                    jsonWriter.write(accountList[0]);
                    jsonWriter.close();
                    JOptionPane.showMessageDialog(null, "Account added and saved");
                } catch (FileNotFoundException ex) {
                    System.out.println("Unable to write to file: ./data/accountList.json");
                } catch (IOException ex) {
                    System.out.println("Unable to read from file: ./data/accountList.json");
                }
            }
        });
        add(addButton, BorderLayout.NORTH);
    }
}
