package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.Objects;
import java.util.Optional;

// Represents an account having an id, username and password
public class Account implements Writable {
    private static int nextAccountId = 1;       // tracks id of next account created
    private int id;                       // account id
    private String name;                  // account username
    private String password;              // account password
    private ItemList itemList;            // account's own items list of items collected
    private TimeList times;               // account's own times list playing game

    // REQUIRES: name to have a non-zero length
    // EFFECTS: account name is set to name
    //          account id is a positive integer not assigned to any other account
    //          account password is set to password
    //          initializes ItemList, initializes AllTimes
    public Account(int id, String name, String password, ItemList itemList, TimeList timeList) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.itemList = itemList;
        this.times = timeList;
    }

    public Account(String name, String password) {
        this.id = nextAccountId++;
        this.name = name;
        this.password = password;
        this.itemList = new ItemList();
        this.times = new TimeList();
    }

//    //MODIFIES: itemList
//    //EFFECTS: sets item list to an itemlist
//    public void setItemList(ItemList itemList) {
//        this.itemList = itemList;
//    }
//
//    //MODIFIES: timeList
//    //EFFECTS: sets time list to an timelist
//    public void setTimeList(TimeList timeList) {
//        this.times = timeList;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(name, account.name);
    }

    public boolean equalsMatch(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(name, account.name) && Objects.equals(password, account.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    //TODO: TEST !!!
    public ItemList getItemList() {
        return itemList;
    }

    //TODO: TEST !!!
    public TimeList getTimes() {
        return times;
    }

    //Item stuff:
    //adding item to account
    public void addItem(Item item) {
        itemList.addItem(item);
    }

    //EFFECTS: returns true if list is empty, false otherwise
    public boolean itemsIsEmpty() {
        return itemList.isEmpty();
    }

    //EFFECTS: returns an item at a specified index in the list
    public Item getSpecificItem(int i) {
        return itemList.getSpecificItem(i);
    }

    //EFFECTS: returns the number of items currently in list
    public int getNumItems() {
        return itemList.getNumItems();
    }

    //EFFECTS: prints a list of all items in itemList in format "\n\tItem: " + name + ", Ability: " + ability
    public String printList() {
        return itemList.printList();
    }

    //AllTimes stuff:
    //EFFECTS: adds a time to the list
    public void addTime(Time time) {
        times.addTime(time);
    }

    //EFFECTS: returns true if list is empty, false otherwise
    public boolean timesIsEmpty() {
        return times.isEmpty();
    }

    //EFFECTS: returns the number of items currently in list
    public int getNumTime() {
        return times.getNumTime();
    }

    //REQUIRES: timesList.size() > 0
    //EFFECTS: gets the smallest time in the list
    public int bestTime() {
        return times.bestTime();
    }

    //EFFECTS: returns an account at a specified index in the list
    public Time getSpecificTime(int i) {
        return times.getSpecificTime(i);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("name", name);
        json.put("password", password);
//        try {
//            json.put("itemList", itemList);
//            json.put("times", times);
//        } catch (Exception e) {
//            return json;
//        }
        json.put("itemList", itemList.itemListToJson());
        json.put("times", times.timesToJson());
        return json;
    }
}
