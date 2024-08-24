package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.*;
import org.json.*;


// Represents a reader that reads accountList from JSON data stored in file
public class JsonReader {
    private String source;
    //private ItemList itemList;
    //private TimeList times;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads accountList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public AccountList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        EventLog.getInstance().logEvent(new Event("File loaded."));
        return parseAccountList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses account list from JSON object and returns it
    private AccountList parseAccountList(JSONObject jsonObject) {
        AccountList accountList = new AccountList();
        addAccounts(accountList, jsonObject);
        return accountList;
    }

    // MODIFIES: accountList
    // EFFECTS: parses accounts from JSON object and adds them to accountList
    private void addAccounts(AccountList accountList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("accounts");
        for (Object json : jsonArray) {
            JSONObject nextAccount = (JSONObject) json;
            addAccount(accountList, nextAccount);
        }
    }

    // MODIFIES: accountList
    // EFFECTS: parses accounts from JSON object and adds it to accountList
    private void addAccount(AccountList accountList, JSONObject jsonObject) {
        int id = jsonObject.getInt("id");
        String name = jsonObject.getString("name");
        String password = jsonObject.getString("password");
        ItemList itemList = new ItemList();
        addItems(itemList, jsonObject);
        TimeList timeList = new TimeList();
        addTimes(timeList, jsonObject);
        Account account = new Account(id, name, password, itemList, timeList);
//        account.setItemList(itemList);
//        account.setTimeList(timeList);
        accountList.addAccount(account);
    }

    // MODIFIES: itemList
    // EFFECTS: parses itemList from JSON object and adds it to accounts
    private void addItems(ItemList itemList, JSONObject jsonObject) {
        JSONArray jsonArrayItems = jsonObject.getJSONArray("itemList");
        for (Object json : jsonArrayItems) {
            JSONObject nextItem = (JSONObject) json;
            addItem(itemList, nextItem);
        }
    }

    //MODIFIES: itemList
    //EFFECTS: parses accounts from JSON object and adds it to itemList
    public void addItem(ItemList itemList, JSONObject jsonObject) {
        String nameItem = jsonObject.getString("name");
        String ability = jsonObject.getString("ability");
        itemList.addItem(new Item(nameItem, ability));
    }

    // MODIFIES: allTimes
    // EFFECTS: parses times from JSON object and adds it to allTimes
    private void addTimes(TimeList timeList, JSONObject jsonObject) {
        JSONArray jsonArrayItems = jsonObject.getJSONArray("times");
        for (Object json : jsonArrayItems) {
            JSONObject nextTime = (JSONObject) json;
            addTime(timeList, nextTime);
        }
    }

    //MODIFIES: itemList
    //EFFECTS: parses accounts from JSON object and adds it to accountList
    public void addTime(TimeList timeList, JSONObject jsonObject) {
        int time = jsonObject.getInt("time");
        timeList.addTime(new Time(time));
    }

//    public ItemList getItemList() {
//        return itemList;
//    }
}

