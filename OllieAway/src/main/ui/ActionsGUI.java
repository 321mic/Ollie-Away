package ui;

import model.AccountList;
import model.EventLog;
import ui.tabs.AddAccount;
import ui.tabs.FindAccount;
import ui.tabs.MainActions;
import ui.tabs.PlayGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ActionsGUI extends JFrame {
    private static final String STATUS_OK = "System OK";
    private static final int WIDTH = 200;
    private static final int HEIGHT = 200;
    private AccountList accountList;
    private AccountsGUI accountsGUI;
    private JLabel statusLabel;
    private static final int ACC_PANEL_WIDTH = 200;
    private static final int ACC_PANEL_HEIGHT = 400;
    private JTabbedPane sidebar;
    public static final int MAIN_TAB_INDEX = 0;
    public static final int ADD_TAB_INDEX = 1;
    public static final int FIND_TAB_INDEX = 2;
    public static final int PLAY_TAB_INDEX = 3;
    private JPanel mainActionsTab;
    private JPanel findAccountTab;
    private JPanel addAccountTab;
    private JPanel playGameTab;


    public ActionsGUI() throws IOException {
        super("Skateboard game");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        statusLabel = new JLabel(STATUS_OK);
        add(statusLabel, BorderLayout.NORTH);

        // Create the intersection and GUI for intersection
        createActions();

        JPanel accountsPanel = new JPanel();
        accountsPanel.setPreferredSize(new Dimension(ACC_PANEL_WIDTH, ACC_PANEL_HEIGHT));
        add(accountsPanel, BorderLayout.EAST);
        accountsPanel.repaint(100, 200, ACC_PANEL_WIDTH, ACC_PANEL_HEIGHT);

        sidebar = new JTabbedPane();
        sidebar.setTabPlacement(JTabbedPane.LEFT);

        loadTabs();
        add(sidebar);

        pack();
        setVisible(true);
        printLog();
    }

    private void createActions() {
        accountList = new AccountList();
        accountsGUI = new AccountsGUI(this);
        add(accountsGUI, BorderLayout.CENTER);
    }

    //MODIFIES: this
    //EFFECTS: adds main tab, addAccount tab, find account, and PlayGame tab to this UI
    private void loadTabs() throws IOException {

        mainActionsTab = new MainActions(this);
        findAccountTab = new FindAccount(this);
        addAccountTab = new AddAccount(this);
        playGameTab = new PlayGame(this);

        sidebar.add(mainActionsTab, MAIN_TAB_INDEX);
        sidebar.setTitleAt(MAIN_TAB_INDEX, "Home");
        sidebar.add(addAccountTab, ADD_TAB_INDEX);
        sidebar.setTitleAt(ADD_TAB_INDEX, "Add Account");
        sidebar.add(findAccountTab, FIND_TAB_INDEX);
        sidebar.setTitleAt(FIND_TAB_INDEX, "Find Account");
        sidebar.add(playGameTab, PLAY_TAB_INDEX);
        sidebar.setTitleAt(PLAY_TAB_INDEX, "Play Game");
    }

    //EFFECTS: prints log event to console
    public void printLog() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                LogPrinter lp = new LogPrinter();
                System.out.println(lp.printLog(EventLog.getInstance()));
                System.exit(0);
            }
        });
    }
}
