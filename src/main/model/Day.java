package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// Represents a day which holds all the sporting events
public class Day implements Writable {
    private int day;
    private int month;
    private int year;
    private ArrayList<Sport> sportingEvents;
    
    // EFFECTS: creates a day with an empty lists of sporting events
    public Day(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
        sportingEvents = new ArrayList<>();
    }

    // REQURIES: sport.getName() to be unique
    // EFFECTS: adds sporting event to the list of sporting events
    public void addSportingEvent(Sport sport, int hour, int minute, int predictedHours, int predictedMinutes) {
        sportingEvents.add(sport);
        sport.setStartTime(hour, minute);
        sport.setTimePlayed(predictedHours, predictedMinutes);
        EventLog.getInstance().logEvent(new Event("New Sporting Event Added: " + sport.getName() + " to " 
                + getDay() + "/" + getMonth() + "/" + getYear()));
    }

    // EFFECTS: removes sporting event from the list of sporting events
    public void removeSportingEvent(String name) {
        int size = sportingEvents.size();
        for (int i = 0; i < size; i++) {
            if (sportingEvents.get(i).getName().equals(name)) {
                sportingEvents.remove(i);
                EventLog.getInstance().logEvent(new Event("Sporting Event Removed: " + name + " from " 
                        + getDay() + "/" + getMonth() + "/" + getYear()));
                break;
            }
        }
    }

    // EFFECTS: returns all the sporting events
    public ArrayList<Sport> getSportingEvents() {
        return sportingEvents;
    }

    // EFFECTS: returns all the sporting events
    public ArrayList<String> getSportingEventsNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Sport sport: sportingEvents) {
            names.add(sport.getName());
        }
        return names;
    }

    // EFFECTS: returns a specific sporting event through its name,
    // if it cannot find it returns null
    public Sport getSpecificSportingEvent(String name) {
        for (Sport sport: sportingEvents) {
            if (sport.getName().equals(name)) {
                return sport;
            }
        }
        return null;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    // Modeled by: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("day", day);
        json.put("month", month);
        json.put("year", year);
        json.put("events", eventsToJson());
        return json;
    }

    // Modeled by: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    // EFFECTS: returns events in this day as a JSON array
    private JSONArray eventsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Sport event : sportingEvents) {
            jsonArray.put(event.toJson());
        }

        return jsonArray;
    }
}
