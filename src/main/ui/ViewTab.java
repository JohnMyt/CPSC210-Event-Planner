package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import model.Day;
import model.ListOfDays;
import model.ScoreBoard;
import model.Sport;
import persistence.JsonReader;
import persistence.JsonWriter;

// Modeled by SmartHome Application https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git
// represents the view tab in CallenderApp UI
public class ViewTab extends Tab implements ActionListener {
    private static final String JSON_STORE = "./data/listofdays.json";

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JLabel settings;
    private JLabel listOfDaysLabel;

    private DefaultListModel<String> dayModel;
    private JList<String> eventList;

    private JButton saveDaysBtn;
    private JButton loadDaysBtn;
    private JButton clearDaysBtn;

    private GridLayout rowLayout;
    private JPanel row;
        
    // REQUIRES: CalenderApp controller that holds this tab
    // EFFECTS: creates view tab that allows to save, load list of days from file and clear list of days. 
    // displays a list of events with their dates, information, time and scores
    // creates the panel for the tab
    public ViewTab(CalenderApp controller) {
        super(controller);

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        initalizeButtons();

        initalizeTools();

        addTools();
    }

    // MODIFIES: this
    // EFFECTS: creates all buttons for tab 
    public void initalizeButtons() {
        settings = new JLabel("View/Clear/Save/Load the List of Days:");
        clearDaysBtn = new JButton("Clear List of Days (!(Clear File))");
        saveDaysBtn = new JButton("Save the List of Days to File");
        loadDaysBtn = new JButton("Load the List of Days from File");
        listOfDaysLabel = new JLabel("The List of Events:");

        clearDaysBtn.setActionCommand("clearDays");
        clearDaysBtn.addActionListener(this);

        saveDaysBtn.setActionCommand("saveDays");
        saveDaysBtn.addActionListener(this); 

        loadDaysBtn.setActionCommand("loadDays");
        loadDaysBtn.addActionListener(this); 
    }

    // MODIFIES: this
    // EFFECTS: initalizes the list of events display
    public void initalizeTools() {
        dayModel = new DefaultListModel<>();
        eventList = new JList<>(dayModel);
    }  

    // MODIFIES: this
    // EFFECTS: adds all the tools to the panel for this tab
    public void addTools() {
        row = new JPanel(rowLayout);
        row.setLayout(new GridBagLayout());
        row.add(settings);
        row.setBorder(BorderFactory.createEmptyBorder(10, 400, 25, 400));
        add(row);

        rowLayout = new GridLayout(1, 3);
        row = new JPanel(rowLayout);
        row.setPreferredSize(new Dimension(650, 50));
        row.add(clearDaysBtn);
        row.add(saveDaysBtn);
        row.add(loadDaysBtn);
        add(row);

        row = new JPanel(rowLayout);
        row.setLayout(new GridBagLayout());
        row.add(listOfDaysLabel);
        row.setBorder(BorderFactory.createEmptyBorder(20, 400, 0, 400));
        add(row);

        add(eventList);
    }

    // MODIFIES: this, controller
    // EFFECTS: manages the action events for this tab
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("saveDays")) {
            saveDays();
        } else if (e.getActionCommand().equals("loadDays")) {
            loadDays();
        } else if (e.getActionCommand().equals("clearDays")) {
            clearDays();
        }
    }

    // MODIFIES: this, controller
    // EFFECTS: clears list of day of all information
    // and calls to update all tabs
    public void clearDays() {
        getController().getListOfDays().clear();
        getController().update();
    }

    // MODIFIES: this, controller
    // EFFECTS: loads list of days information from list of days file
    // and calls to update all tabs
    public void loadDays() {
        try {
            ListOfDays listOfDays = jsonReader.read();
            getController().setListOfDays(listOfDays);
            getController().setSelectedDay(null);
            getController().setSelectedEvent(null);
            getController().update();
        } catch (IOException e2) {
            JOptionPane.showMessageDialog(null,
                    "An error has occured");
        }
    }

    // MODIFIES: this, controller
    // EFFECTS: saves list of days information to list of days file
    // and calls to update all tabs
    public void saveDays() {
        ListOfDays listOfDays = getController().getListOfDays();
        try {
            jsonWriter.open();
            jsonWriter.write(listOfDays);
            jsonWriter.close();
        } catch (FileNotFoundException e1) {
            JOptionPane.showMessageDialog(null,
                    "An error has occured");
        }
    }

    // MODIFIES: this
    // EFFECTS: grabs information from controller's list of day and 
    // displays it as a list of events with their dates, information, time and scores
    public void viewEvents() {
        dayModel.clear();
        ListOfDays listOfDays = getController().getListOfDays();
        for (int i = 0; i < listOfDays.size(); i++) {
            String dayText = "";
            Day day = listOfDays.get(i);
            dayText += "Date: " + day.getDay() + "/" + day.getMonth() + "/" + day.getYear();
            if (day.getSportingEvents().isEmpty()) {
                dayModel.addElement(dayText);
            }
            for (Sport event: day.getSportingEvents()) {
                ScoreBoard sb = event.getScoreBoard();
                String text = dayText;
                text += ", Event: " + event.getName();
                text += ", Location: " + event.getLocation();
                text += ", Starts: " + event.getStartHours() + ":" + event.getStartMinutes();
                text += ", Duration: " + event.getHoursPlayed() + ":" + event.getMinutesPlayed();
                text += ", Scores: " + sb.getScoreBoard();
                dayModel.addElement(text);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: grabs information from controller's list of day and updates the display of the list of events
    public void update() {
        viewEvents();
    }
}
