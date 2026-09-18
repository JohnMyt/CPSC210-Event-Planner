package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import model.Day;
import model.ScoreBoard;
import model.Sport;
import model.VolleyBall;
import persistence.JsonReader;
import persistence.JsonWriter;
import model.ListOfDays;

// Modeled by https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
// Calender application
public class OldCalenderApp {
    private static final String JSON_STORE = "./data/listofdays.json";
    private Scanner input;
    private ListOfDays listOfDays;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    
    // EFFECTS: runs the calender application
    public OldCalenderApp() {
        listOfDays = new ListOfDays();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runCalender();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runCalender() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            viewMenu();

            input = new Scanner(System.in);
            input.useDelimiter("\r?\n|\r");

            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
        System.exit(0);
    }

    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("d")) {
            createDay();
        } else if (command.equals("v")) {
            displayDays();
        } else if (command.equals("s")) {
            saveListOfDays();
        } else if (command.equals("l")) {
            loadListOfDays();
        } else {
            System.out.println("\nSelection not valid...");
        }
    }

    // Modeled by: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    // EFFECTS: saves the list of days to file
    private void saveListOfDays() {
        try {
            jsonWriter.open();
            jsonWriter.write(listOfDays);
            jsonWriter.close();
            System.out.println("Saved the list of days to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // Modeled by: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    // MODIFIES: this
    // EFFECTS: loads list of days from file
    private void loadListOfDays() {
        try {
            listOfDays = jsonReader.read();
            System.out.println("Loaded lst of Days from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: Hanndles user inputs setting up whether or not a
    // new day will be created or a day will be selected
    private void createDay() {
        boolean dayExists = true;
        Day dayEvent = null;
        System.out.println("\nInput the following stuff as numbers:");
        System.out.println("Input the day:");
        int dayInput = input.nextInt();
        System.out.println("\nInput the month:");
        int monthInput = input.nextInt();
        System.out.println("\nInput the year:");
        int yearInput = input.nextInt();
        for (int i = 0; i < listOfDays.size(); i++) {
            Day event = listOfDays.get(i);
            if (event.getDay() == dayInput && event.getMonth() == monthInput && event.getYear() == yearInput) {
                System.out.print("\nthis day was already added");
                dayExists = false;
                dayEvent = event;
                break;
            }
        }
        dayExists(dayExists, dayEvent, dayInput, monthInput, yearInput);
    }

    // EFFECTS: displays the days that have been added so far
    // with a list of their sporting event names 
    private void displayDays() {
        if (listOfDays.size() == 0) {
            System.out.println("\nNo Days Added");
        } else {
            System.out.println("\nDays:");
            for (int i = 0; i < listOfDays.size(); i++) {
                Day day = listOfDays.get(i);
                System.out.println("\nThe Date: " + day.getDay() + "/" + day.getMonth() + "/" + day.getYear());
                System.out.println("Sporting Events for the Day: " + day.getSportingEventsNames());
            }
        }
    }

    // EFFECTS: Creates a new day or selects a day
    private void dayExists(Boolean dayExists, Day dayEvent, int dayInput, int monthInput, int yearInput) {
        Day day;
        if (dayExists) {
            day = new Day(dayInput, monthInput, yearInput);
            listOfDays.add(day);
        } else {
            day = dayEvent;
        }
        viewDay(day);
    }

    // EFFECTS: displays menu of possible actions for a calender
    private void viewMenu() {
        System.out.println("\nPress d to select/create day");
        System.out.println("Press v to view previously selected days");
        System.out.println("Press s to save the list of days to file");
        System.out.println("Press l to load the list of days from file");
        System.out.println("Press q to exit");
    }

    // EFFECTS: displays menu of possible actions for a day
    private void viewOptions(Day day) {
        if (day.getSportingEvents().isEmpty()) {
            System.out.println("\nNo Events Scheduled for the Day");
        }
        System.out.println("\nInput v to view all sporting events");
        System.out.println("Input a to add a sporting event");
        if (!(day.getSportingEvents().isEmpty())) {
            System.out.println("Input r to remove a sporting event");
            System.out.println("Input m to modify a sporting event's scoreboard");
        }
        System.out.println("Input q to select a new day");
    }

    // REQUIRES: (!(day == null))
    // MODIFIES: this
    // EFFECTS: displays and responds to user inputs
    private void viewDay(Day day) {
        viewOptions(day);
        String command = input.next();
        if (command.equals("q")) {
            runCalender();
        } else {
            handleCases(day, command);
            viewDay(day);
        }
    }

    // REQUIRES: (!(day == null))
    // MODIFIES: this
    // EFFECTS: handles user inputs for possible actions for selected day
    private void handleCases(Day day, String command) {
        switch (command) {
            case "r": {
                removeEvent(day);
                break;
            } case "m": {
                modifyScoreBoard(day);
                break;
            } case "a": {
                addEvent(day);
                break;
            } case "v": {
                viewEvents(day);
                break;
            } default: {
                System.out.println("Please chose a valid option");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a sporting event to the list of sporting events of selected day
    // of such a sporting event exists
    private void addEvent(Day day) {
        System.out.println("\nInput the event name:");
        String nameInput = input.next();
        boolean condition = true;
        for (Sport event: day.getSportingEvents()) {
            if (event.getName().equals(nameInput)) {
                System.out.println("\nThere is already such an event");
                condition = false;
                break;
            }
        }
        if (condition) {
            createSportingEvent(day, nameInput);
        }
    }

    // REQUIRES (!(day == null))
    // MODIFIES: this
    // EFFECTS: based off user inputs generate a new sporting event for the selected day
    private void createSportingEvent(Day day, String nameInput) {
        Sport sport;
        System.out.println("\nInput the event location:");
        String locationInput = input.next();
        sport = new VolleyBall(nameInput, locationInput);
        System.out.println("\nInput the following stuff as numbers:");
        System.out.println("Input the event's start hour:");
        int hour = input.nextInt();
        System.out.println("\nInput the event's start minute:");
        int minute = input.nextInt();
        System.out.println("\nInput the event's predicted duration of hours:");
        int hours = input.nextInt();
        System.out.println("\nInput the event's predicted duration of minutes:");
        int minutes = input.nextInt();
        day.addSportingEvent(sport, hour, minute, hours, minutes);
    }
    
    // MODIFIES: this
    // EFFECTS: removes a sporting event from the list of sporting events
    private void removeEvent(Day day) {
        if (day.getSportingEvents().isEmpty()) {
            System.out.println("Invalid option");
        } else {
            System.out.println("\nInput the event name:");
            String nameInput = input.next();
            day.removeSportingEvent(nameInput);
        }
    }
    
    // MODIFIES: this
    // EFFECTS: view all the sports events and their statistics
    private void viewEvents(Day day) {
        for (Sport event: day.getSportingEvents()) {
            ScoreBoard sb = event.getScoreBoard();
            System.out.println("\nEvent Name: " + event.getName());
            System.out.println("Event location: " + event.getLocation());
            System.out.println("Event StartTime: " + event.getStartHours() + ":" + event.getStartMinutes());
            System.out.println("Event PlayTime: " + event.getHoursPlayed() + ":" + event.getMinutesPlayed());
            System.out.println("ScoreBoard: " + sb.getScoreBoard());
        }
    }

    // MODIFIES: this
    // EFFECTS: determines whether or not to add a scores to the scoreboard
    private void modifyScoreBoard(Day day) {
        if (day.getSportingEvents().isEmpty()) {
            System.out.println("Invalid option");
        } else {
            Sport sportEvent;
            sportEvent = selectSportingEvent(day);
            if (sportEvent == null) {
                System.out.println("No Such Event Exists");
            } else {
                modifyScores(sportEvent);
            }
        }
    }
    
    // MODIFIES: this
    // EFFECTS: returns a sporting event in the list that matches the user input
    private Sport selectSportingEvent(Day day) {
        System.out.println("\nInput the event name:");
        String nameInput = input.next();
        return day.getSpecificSportingEvent(nameInput);
    }
    
    // MODIFIES: this
    // EFFECTS: adds score to the scoreboard of a sporting event
    private void modifyScores(Sport sportEvent) {
        System.out.println("\nInput the following stuff as numbers:");
        System.out.println("Input your score:");
        int yourInput = input.nextInt();
        System.out.println("\nInput opponent score:");
        int opponentInput = input.nextInt();
        ScoreBoard sb = sportEvent.getScoreBoard();
        sb.addGameScore(yourInput, opponentInput);
    }
}