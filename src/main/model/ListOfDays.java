package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// Stores the list of Days
public class ListOfDays implements Writable {
    private ArrayList<Day> listOfDays;

    // EFFECTS: Generates an empty list of days
    public ListOfDays() {
        listOfDays = new ArrayList<>();
    }

    // REQUIRES: (day !== null) 
    // EFFECTS: Adds a day to the list of days
    public void add(Day day) {
        listOfDays.add(day);
        EventLog.getInstance().logEvent(new Event("New Day Added: " + day.getDay()
                + "/" + day.getMonth() + "/" + day.getYear()));
    }

    // REQUIRES: (lstOfDays.size > i)
    // Simple getter
    public Day get(int i) {
        return listOfDays.get(i);
    }

    // Simple getter
    public int size() {
        return listOfDays.size();
    }

    // EFFECTS: removes specified day from listOfDays
    public void remove(Day day) {
        listOfDays.remove(day);
        EventLog.getInstance().logEvent(new Event("Day Removed: " + day.getDay()
                + "/" + day.getMonth() + "/" + day.getYear()));
    }

    // EFFECTS: makes listOfDays an empty list of days
    public void clear() {
        listOfDays = new ArrayList<>();
        EventLog.getInstance().logEvent(new Event("List of Days Cleared"));
    }

    // Modeled by: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("listOfDays", daysToJson());
        return json;
    }

    // Modeled by: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    // EFFECTS: returns days in this listOfDays as a JSON array
    private JSONArray daysToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Day day : listOfDays) {
            jsonArray.put(day.toJson());
        }

        return jsonArray;
    }
}
