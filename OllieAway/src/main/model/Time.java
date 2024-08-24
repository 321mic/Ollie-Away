package model;

import org.json.JSONObject;
import persistence.Writable;

// Records how long a game takes a player once they press play game (in seconds)
public class Time implements Writable {
    private int time;       // the amount of time it takes the player to complete a game

    // EFFECTS: time is set to lengthOfGame
    public Time(int lengthOfGame) {
        this.time = lengthOfGame / 1000;
    }

    public int getTime() {
        return time;
    }

    //JSON stuff:
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("time", time);
        return json;
    }
}
