package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a list of recorded times
public class TimeList implements Writable {
    private ArrayList<Time> timesList;      // a list of all the times recorded playing the game
    private int maximum;                    // smallest time found in timesList

    //EFFECTS: creates a new instance of an ArrayList of Time
    public TimeList() {
        timesList = new ArrayList<>();
    }

    //EFFECTS: adds a time to the list
    public void addTime(Time time) {
        timesList.add(time);
    }

    //EFFECTS: returns true if list is empty, false otherwise
    public boolean isEmpty() {
        if (timesList.size() == 0) {
            return true;
        }
        return false;
    }

    //EFFECTS: returns the number of items currently in list
    public int getNumTime() {
        return timesList.size();
    }

    //REQUIRES: timesList.size() > 0
    //EFFECTS: gets the smallest time in the list
    public int bestTime() {
        maximum = timesList.get(0).getTime();
        for (int i = 1; i < timesList.size(); i++) {
            if (maximum < timesList.get(i).getTime()) {
                maximum = timesList.get(i).getTime();
            }
        }
        return maximum;
    }

    //EFFECTS: returns an account at a specified index in the list
    public Time getSpecificTime(int i) {
        return timesList.get(i);
    }

    //JSON stuff:
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("times", timesToJson());
        return json;
    }

    // EFFECTS: returns all times in times list as a JSON array
    public JSONArray timesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Time time : timesList) {
            jsonArray.put(time.toJson());
        }

        return jsonArray;
    }
}
