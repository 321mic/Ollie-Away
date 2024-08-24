package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.ls.LSOutput;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Represents a list of accounts
public class AccountList implements Writable {
    private List<Account> accountList;      // a list of accounts
    private Account account;

    //EFFECTS: creates a new instance of an ArrayList of AddAccount
    public AccountList() {
        accountList = new ArrayList<>();
    }

    //REQUIRES: notEqualToAny(account) == true;
    //EFFECTS: adds an account to the list, each account has a different username (no duplicates)
    public void addAccount(Account account) {
        accountList.add(account);
        EventLog.getInstance().logEvent(new Event("Account added."));
    }

    //EFFECTS: returns false if given account is equal to an account in list (name)
    public boolean notEqualToAny(Account account) {
        for (Account acc : accountList) {
            if (acc.equals(account)) {
                return false;
            }
        }
        return true;
    }

    //EFFECTS: returns true if given account is equal to an account in list (name and password)
    public boolean matchAccount(String name, String password) {
        Account account = new Account(name, password);
        for (int i = 0; accountList.size() > i; i++) {
//            if (acc.equalsMatch(account)) {
//                return true;
//            }
            if ((accountList.get(i).getName().equals(name)) && (accountList.get(i).getPassword().equals(password))) {
                return true;
            }
        }
        return false;
    }

    //EFFECTS: returns wanted account
    public Account getAccount(String name, String password) {
        Account account = new Account(name, password);
        for (Account acc : accountList) {
            if (acc.equalsMatch(account)) {
                EventLog.getInstance().logEvent(new Event("Account found."));
                return account;
            }
        }
        EventLog.getInstance().logEvent(new Event("Account not found."));
        return null;
    }

    //EFFECTS: returns wanted account
    public Account getAccount(Account account) {
        for (Account acc : accountList) {
            if (acc.equalsMatch(account)) {
                return account;
            }
        }
        return null;
    }

//    //TEST !!!
//    //EFFECTS: returns wanted account
//    public Account getAccountById(int id) {
//        for (Account acc : accountList) {
//            if (acc.getId() == id) {
//                return acc;
//            }
//        }
//        return null;
//    }

    //EFFECTS: returns true if list is empty, false otherwise
    public boolean isEmpty() {
        if (accountList.size() == 0) {
            return true;
        }
        return false;
    }

    //EFFECTS: returns an account at a specified index in the list
    public Account getSpecificAccount(int i) {
        return accountList.get(i);
    }

    //EFFECTS: returns accounts in accountList in order added,
    //         unmodifiable list
    public List<Account> getAccounts() {
        return accountList;
    }

    //EFFECTS: returns the number of items currently in list
    public int getNumAccounts() {
        return accountList.size();
    }

    //EFFECTS: prints list
    public String printList() {
        String username;
        String password;
        int id;
        String returnAccounts = "";

        for (int i = 0; i < accountList.size(); i++) {
            account = accountList.get(i);
            username = account.getName();
            password = account.getPassword();
            id = account.getId();
            returnAccounts += "\nAccount: "
                    + Integer.toString(id)
                    + ", Username: "
                    + username
                    + ", Password: "
                    + password;

        }
        return returnAccounts;
    }

    //Json stuff:
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("accounts", accountsToJson());
        return json;
    }

    // EFFECTS: returns accounts in this accountList as a JSON array
    private JSONArray accountsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Account account : accountList) {
            jsonArray.put(account.toJson());
        }

        return jsonArray;
    }
}
