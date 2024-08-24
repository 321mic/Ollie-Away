package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// A list of all items
public class ItemList implements Writable {
    private ArrayList<Item> itemList;       // a list of items
    private Item item;

    //EFFECTS: creates a new instance of an ArrayList of Items
    public ItemList() {
        itemList = new ArrayList<>();
    }

    //EFFECTS: adds an item to the list
    public void addItem(Item item) {
        itemList.add(item);
    }

    //EFFECTS: returns true if list is empty, false otherwise
    public boolean isEmpty() {
        if (itemList.size() == 0) {
            return true;
        }
        return false;
    }

    //EFFECTS: returns an item at a specified index in the list
    public Item getSpecificItem(int i) {
        return itemList.get(i);
    }

    //EFFECTS: returns the number of items currently in list
    public int getNumItems() {
        return itemList.size();
    }

    //EFFECTS: prints a list of all items in itemList in format "\n\tItem: " + name + ", Ability: " + ability
    public String printList() {
        String name;
        String ability;
        String returnItems = "";

        for (int i = 0; i < itemList.size(); i++) {
            item = itemList.get(i);
            name = item.getName();
            ability = item.getAbility();
            returnItems += "\n\tItem: " + name + ", Ability: " + ability;
        }
        return returnItems;
    }

    //JSON stuff:
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("items", itemListToJson());
        return json;
    }

    // EFFECTS: returns items in this itemList as a JSON array
    public JSONArray itemListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Item item : itemList) {
            jsonArray.put(item.toJson());
        }

        return jsonArray;
    }
}
