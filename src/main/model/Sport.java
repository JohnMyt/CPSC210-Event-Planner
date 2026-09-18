package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// A representation of an abstract sporting event
public abstract class Sport implements Writable {
    private String name;
    private int hour;
    private int minute;
    private int hoursPlayed;
    private int minutesPlayed;
    private String location;
    private ScoreBoard scoreBoard;

    //EFFECTS: Initilizes a sport event
    public Sport(String name, String location) {
        this.name = name;
        this.location = location;
        scoreBoard = new ScoreBoard();
    }

    // MODIFIES: this
    // EFFECTS: records the score of a sporting event
    public void recordScores(Score score) {
        this.scoreBoard.addGameScore(score.getYourScore(), score.getOpponentScore());
        EventLog.getInstance().logEvent(new Event("New Score Added: " + score.getYourScore() 
                + ":" + score.getOpponentScore() + " for " + getName()));
    }

    // REQUIRES: (hour >= 0 && minute >= 0)
    public void setTimePlayed(int hours, int minutes) {
        this.hoursPlayed = hours;
        this.minutesPlayed = minutes;
    }

    // REQUIRES: (hour < 24 && hour >= 0 && minute >= 0 && minute < 60)
    public void setStartTime(int hours, int minutes) {
        this.hour = hours;
        this.minute = minutes;
    }

    // EFFECTS: resets scoreboard to empty state
    public void clear() {
        scoreBoard = new ScoreBoard();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getStartHours() {
        return hour;
    }

    public int getStartMinutes() {
        return minute;
    }

    public int getHoursPlayed() {
        return hoursPlayed;
    }
    
    public int getMinutesPlayed() {
        return minutesPlayed;
    }

    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    // Modeled by: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("location", location);
        json.put("startHour", hour);
        json.put("startMinute", minute);
        json.put("predictedHours", hoursPlayed);
        json.put("predictedMinutes", minutesPlayed);
        json.put("scoreBoard", scoreBoardToJson());
        return json;
    }

    // Modeled by: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    // EFFECTS: returns scores in this scoreBoard as a JSON array
    private JSONArray scoreBoardToJson() {
        JSONArray jsonArray = new JSONArray();
        

        for (Score score : scoreBoard.getScores()) {
            jsonArray.put(score.toJson());
        }

        return jsonArray;
    }
}
