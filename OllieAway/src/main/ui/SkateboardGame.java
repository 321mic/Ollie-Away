package ui;

import model.*;

import java.io.FileNotFoundException;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.Scanner;

// Skateboard game application
public class SkateboardGame {
    private static final String JSON_STORE = "./data/accountList.json";
    private Account account;
    //private static AccountList accountList;
    private AccountList accountList = new AccountList();
    private Item speedItem;
    private Item jumpItem;
    private Item transparentItem;
    private Time time;
    private Game game;
    private Scanner input;
    private String username;
    private String password;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: runs the skateboard game
    public SkateboardGame() throws FileNotFoundException {
        runGame();
    }

    //MODIFIES: this
    //EFFECTS: processes user's input
    public void runGame() {
        boolean keepGoing = true;
        String command = null;

        initialization();
        commands(command, keepGoing);
        System.out.println("\nBye!");
    }

    //EFFECTS: precesses commands
    public void commands(String command, boolean keepGoing) {
        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                while (true) {
                    System.out.println("Save your progress? (y/n)");
                    command = input.next().toLowerCase();
                    if (command.equals("y")) {
                        save();
                        keepGoing = false;
                        break;
                    } else if (command.equals("n")) {
                        keepGoing = false;
                        break;
                    }
                }
            } else {
                processCommand(command);
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: creates an account with a username and password created by user
    public void makeAccount() {
        while (true) {
            System.out.println("Create an account:");
            System.out.println("(l -> login to account, otherwise press enter)");
            String command = input.next().toLowerCase();
            if (command.equals("l")) {
                loginToAccount();
                break;
            }
            System.out.println("Username:");
            username = input.next();
            System.out.println("Password:");
            password = input.next();
            account = new Account(username, password);
            if (accountList.notEqualToAny(account)) {
                accountList.addAccount(account);
                save();
                System.out.println(accountList.printList());
                break;
            } else {
                System.out.println("Username already exists. Please choose a different username.");
            }
        }
    }

    //EFFECTS: chooses an account for saved items and times
    public void loginToAccount() {
        while (true) {
            System.out.println("login to your account:");
            System.out.println("(c -> create an account, otherwise press enter)");
            String command = input.next().toLowerCase();
            if (command.equals("c")) {
                makeAccount();
                break;
            }
            System.out.println("Username:");
            username = input.next();
            System.out.println("Password:");
            password = input.next();
            if (accountList.matchAccount(username, password)) {
                account = accountList.getAccount(username, password);
                System.out.println("Logging you in..." + account.getId());
                break;
            } else {
                System.out.println("Wrong username or password. Please try again.");
            }
        }
        System.out.println(accountList.printList());
    }

    //EFFECTS: asks player if they have account;
    //         if yes, goes to login. If no, creates an account
    public void haveAccount() {
        while (true) {
            System.out.println("Do you have an existing account? (y/n)");
            String command = input.next().toLowerCase();
            if (command.equals("y")) {
                loginToAccount();
                break;
            } else if (command.equals("n")) {
                makeAccount();
                break;
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: initializes accounts, items, and lists
    public void initialization() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        speedItem = new Item("Speeeeed!", "speed boost");
        jumpItem = new Item("Jumpy Jump", "jump boost");
        transparentItem = new Item("Move Through Objects", "become transparent");
        game = new Game();
        try {
            load();
            haveAccount();
        } catch (Exception e) {
            //new AccountList(); // TODO
            haveAccount();
        }
    }

    //EFFECTS: displays menu of options to user
    public void displayMenu() {
        System.out.println("\nMenu:");
        System.out.println("\tc -> View Collected Items");
        System.out.println("\tt -> View Best Time");
        System.out.println("\tg -> Play Game!");
        System.out.println("\ts -> save");
        System.out.println("\tl -> load");
        System.out.println("\tq -> quit");
    }

    //MODIFIES: this
    //EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("c")) {
            viewCollectedItems();
        } else if (command.equals("t")) {
            viewBestTime();
        } else if (command.equals("s")) {
            save();
        } else if (command.equals("l")) {
            load();
        } else if (command.equals("g")) {
            playGame(command);
        }
    }

    //EFFECTS: displays list of items
    //         give option of going back to menu
    private void viewCollectedItems() {
        System.out.println("Items Collected:" + account.printList());
        backToMenu();
    }

    //EFFECTS: displays smallest time it took a player to play the game
    //         gives option of going back to menu
    private void viewBestTime() {
        if (account.getNumTime() == 0) {
            System.out.println("You haven't played the game yet! \nGo play, then come back to see your high score.");
        } else {
            System.out.println("Your best time is: " + account.bestTime() + " seconds");
            backToMenu();
        }
    }

    //MODIFIES: this
    //EFFECTS: begins the game
    private void playGame(String command) {
        Random random = new Random();
        String [] avoidedObstacle = {"Good job!", "Nicceee", "Woop woop", "Look at you go!"};
        int selectCongrats = random.nextInt(avoidedObstacle.length);//randomly selects an index from the avoidedObstacle
        String [] hitObstacle = {"Aww you hit it :(", "Better luck next time", "It's ok, try again"};
        int selectHit = random.nextInt(hitObstacle.length);//randomly selects an index from the avoidedObstacle
        String [] jumpDuck = {"Jump! (space or j)", "Duck! (d)"};
        game = new Game();
        int i = 1;

        System.out.println("The rules of the game are simple.");
        System.out.println("Simply jump when you should jump and duck when you should duck.");
        System.out.println("Start skating!");
        startGame(command, random, i, jumpDuck, avoidedObstacle, selectCongrats, hitObstacle, selectHit);
    }

    //EFFECTS: brings user back to menu
    //         (back to runGame())
    public void backToMenu() {
        System.out.println("Press any button to go back to Menu");
        input.next();
        game.gameOver();
    }

    //MODIFIES: this
    //EFFECTS: adds a collected item to itemList
    public void collectItem(int i) {
        if ((i == 3) && (!game.isGameOver())) {
            System.out.println("You got an Item! --> " + speedItem.getName());
            account.addItem(speedItem);
        } else if ((i == 7) && (!game.isGameOver())) {
            System.out.println("You got an Item! --> " + jumpItem.getName());
            account.addItem(jumpItem);
        } else if ((i == 13) && (!game.isGameOver())) {
            System.out.println("You got an Item! --> " + transparentItem.getName());
            account.addItem(transparentItem);
        }
    }

    //MODIFIES: this
    //EFFECTS: plays the game, brings user back to menu once game is over
    public void startGame(String command, Random random, int i, String [] jumpDuck,
                          String [] avoidedObstacle, int selectCongrats, String [] hitObstacle, int selectHit) {
        final long startTime = System.currentTimeMillis();
        while (!game.isGameOver()) {
            int move = random.nextInt(jumpDuck.length);
            System.out.println(jumpDuck[move]);
            command = input.next().toLowerCase();
            if ((jumpDuck[move] == "Jump! (space or j)") && ((command.equals(" ")) || (command.equals("j")))) {
                System.out.println(avoidedObstacle[selectCongrats]);
            } else if ((jumpDuck[move] == "Duck! (d)") && (command.equals("d"))) {
                System.out.println(avoidedObstacle[selectCongrats]);
            } else {
                System.out.println(hitObstacle[selectHit]);
                final long endTime = System.currentTimeMillis();
                time = new Time((int) (endTime - startTime));
                account.addTime(time);
                System.out.println("Game Time: " + Integer.toString(time.getTime()) + " seconds");
                backToMenu();
            }
            collectItem(i);
            i++;
        }
    }

    // EFFECTS: saves the itemList to file
    private void save() {
        try {
            //JSONObject json = new JSONObject();
            jsonWriter.open();
//            account.addItem(speedItem);
//            account.addTime(new Time(8000));
//            System.out.println("Items Collected:" + account.printList());
//            System.out.println("Your best time is: " + account.bestTime() + " seconds");
            jsonWriter.write(accountList);
//            if (accountList.notEqualToAny(account)) {
//                jsonWriter.write(accountList);
//            } else {
//                json.put("accounts", accountList);
//            }
            //jsonWriter.write(account);
            jsonWriter.close();
            System.out.println("Saved accounts to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads itemList from file
    //TODO
    // made load public, check if messed up
    public void load() {
        try {
            accountList = jsonReader.read();
            System.out.println("Loaded accounts from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
