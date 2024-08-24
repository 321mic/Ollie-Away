package model;

import org.json.JSONObject;
import persistence.Writable;

// Creates an item to be added to an items list (AllItems) and later collected
public class Item implements Writable {
    private final String name;        // Item name
    private final String ability;     // Item ability

    // EFFECTS: creates a name for an item
    //          adds an ability of an item
    public Item(String name, String ability) {
        this.name = name;
        this.ability = ability; // !!! not quite sure what to do with this yet
    }

    public String getName() {
        return name;
    }

    public String getAbility() {
        return ability;
    }

    //JSON stuff:
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("ability", ability);
        return json;
    }
}
