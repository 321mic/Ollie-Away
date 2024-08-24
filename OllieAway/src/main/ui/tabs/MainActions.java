package ui.tabs;

import model.AccountList;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.ActionsGUI;
import ui.SkateboardGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActions extends Tab {
    private JDesktopPane desktop;
    private JInternalFrame controlPanel;
    private final AccountList[] accountList;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    public MainActions(ActionsGUI controller) {
        super(controller);

        //final AccountList[] accountList = {new AccountList()};
        accountList = new AccountList[]{new AccountList()};
        jsonReader = new JsonReader("./data/accountList.json");
        jsonWriter = new JsonWriter("./data/accountList.json");

        desktop = new JDesktopPane();
        //desktop.addMouseListener(new DesktopFocusAction());
        controlPanel = new JInternalFrame("Control Panel", false, false, false, false);
        controlPanel.setLayout(new BorderLayout());

        JButton loadButton = new JButton("LOAD PROGRAM AND SHOW ACCOUNTS");
        loadButton.setActionCommand("Program loaded");
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    accountList[0] = jsonReader.read();
                    JOptionPane.showMessageDialog(null, accountList[0].printList());
                } catch (IOException ex) {
                    System.out.println("Unable to read from file: ./data/accountList.json");
                }
            }
        });
        add(loadButton, BorderLayout.NORTH);

        makeSaveButton(accountList, jsonReader, jsonWriter);

        setVisible(true);
    }

    public void makeSaveButton(AccountList[] accountList, JsonReader jsonReader, JsonWriter jsonWriter) {
        JButton saveButton = new JButton("SAVE PROGRAM");
        saveButton.setActionCommand("Program saved");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    accountList[0] = jsonReader.read();
                    jsonWriter.open();
                    jsonWriter.write(accountList[0]);
                    jsonWriter.close();
                    JOptionPane.showMessageDialog(null, "The state of application is successfully saved.");
                } catch (FileNotFoundException ex) {
                    System.out.println("Unable to write to file: ./data/accountList.json");
                } catch (IOException ex) {
                    System.out.println("Unable to read from file: ./data/accountList.json");
                }
            }
        });
        add(saveButton, BorderLayout.SOUTH);
    }

    public AccountList[] getAccountList() {
        return accountList;
    }
}
