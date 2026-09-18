
package persistence;

import model.Event;
import model.EventLog;
import model.Day;
import model.ScoreBoard;
import model.Sport;
import model.VolleyBall;
import model.ListOfDays;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads the list of days from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {   
        this.source = source;
    }

    // EFFECTS: reads listOfDays from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ListOfDays read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        EventLog.getInstance().logEvent(new Event("Started loading a new list of days from file"));
        ListOfDays listOfDays = parseListOfDays(jsonObject);
        EventLog.getInstance().logEvent(new Event("Finished loading the list of days from file"));
        return listOfDays;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses the listOfDays from JSON object and returns it
    private ListOfDays parseListOfDays(JSONObject jsonObject) {
        ListOfDays listOfDays = new ListOfDays();
        addLstOfDays(listOfDays, jsonObject);
        return listOfDays;
    }

    // MODIFIES: listOfDays, day, scoreBoard
    // EFFECTS: parses days from JSON object and adds them to listOfDays
    private void addLstOfDays(ListOfDays lstOfDays, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("listOfDays");
        for (Object json : jsonArray) {
            JSONObject nextDay = (JSONObject) json;
            addDay(lstOfDays, nextDay);
        }
    }

    // MODIFIES: listOfDays, day, scoreBoard
    // EFFECTS: parses day from JSON object and adds it to the listOfDays
    private void addDay(ListOfDays lstOfDays, JSONObject jsonObject) {
        int dayinput = jsonObject.getInt("day");
        int month = jsonObject.getInt("month");
        int year = jsonObject.getInt("year");
        Day day = new Day(dayinput, month, year);
        lstOfDays.add(day);
        addEvents(day, jsonObject);
    }

    // MODIFIES: day, scoreBoard
    // EFFECTS: parses events from JSON object and adds them to a day
    private void addEvents(Day day, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("events");
        for (Object json : jsonArray) {
            JSONObject nextEvent = (JSONObject) json;
            addEvent(day, nextEvent);
        }
    }

    // MODIFIES: day, scoreBoard
    // EFFECTS: parses event from JSON object and adds it to the events
    private void addEvent(Day day, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String location = jsonObject.getString("location");
        int startHour = jsonObject.getInt("startHour");
        int startMinute = jsonObject.getInt("startMinute");
        int predHours = jsonObject.getInt("predictedHours");
        int predMinutes = jsonObject.getInt("predictedMinutes");
        Sport sport = new VolleyBall(name, location);
        ScoreBoard sb = sport.getScoreBoard();
        day.addSportingEvent(sport, startHour, startMinute, predHours, predMinutes);
        addScoreBoard(sb, sport, jsonObject);
    }

    // MODIFIES: scoreBoard
    // EFFECTS: parses scores from JSON object and adds them to a event
    private void addScoreBoard(ScoreBoard sb, Sport event, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("scoreBoard");
        for (Object json : jsonArray) {
            JSONObject nextScore = (JSONObject) json;
            addScore(sb, event, nextScore);
        }
    }

    // MODIFIES: scoreBoard
    // EFFECTS: parses score from JSON object and adds it to the scores
    private void addScore(ScoreBoard sb, Sport event, JSONObject jsonObject) {
        int yourScore = jsonObject.getInt("yourScore");
        int opponentScore = jsonObject.getInt("opponentScore");
        sb.addGameScore(yourScore, opponentScore);
    }
}
