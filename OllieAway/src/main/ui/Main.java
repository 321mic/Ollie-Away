package ui;

import model.EventLog;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        new AccountsGUI(new ActionsGUI());
        try {
            new SkateboardGame();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
