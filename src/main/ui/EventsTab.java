package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Day;
import model.Sport;
import model.VolleyBall;

// Modeled by SmartHome Application https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git
// represents the events tab in CallenderApp UI
public class EventsTab extends Tab implements ActionListener {

    private JLabel nameLabel;
    private JLabel locationLabel;
    private JLabel startHourLabel;
    private JLabel startMinuteLabel;
    private JLabel hourLabel;
    private JLabel minuteLabel;

    private JTextField nameField;
    private JTextField locationField;
    private JComboBox<String> startHourField;
    private JComboBox<String> startMinuteField;
    private JComboBox<String> hourField;
    private JComboBox<String> minuteField;

    private JButton createEventBtn;
    private JButton removeEventBtn;
    
    private JTextArea displayDetails;

    private GridLayout rowLayout;
    private JLabel settings;
    private JPanel row;
    
    // REQUIRES: CalenderApp controller that holds this tab
    // EFFECTS: creates events tab that allows to create, select, delete events from
    // selected day in controller.
    // creates the panel for the tab
    public EventsTab(CalenderApp controller) {
        super(controller);

        initalizeButtons();

        initalizeTools();

        addTools();
    }

    // MODIFIES: this
    // EFFECTS: creates all buttons for tab 
    public void initalizeButtons() {
        createEventBtn = new JButton("Select/Create Event");

        createEventBtn.setActionCommand("creatEvent");
        createEventBtn.addActionListener(this);

        removeEventBtn = new JButton("Remove Selected Event");

        removeEventBtn.setActionCommand("removeSelectedEvent");
        removeEventBtn.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: creates all the labels, combo boxes and the text area
    public void initalizeTools() {
        nameLabel = new JLabel("Name:");
        locationLabel = new JLabel("Location:");
        startHourLabel = new JLabel("StartHour:");
        startMinuteLabel = new JLabel("StartMinute:");
        hourLabel = new JLabel("HoursPlayed:");
        minuteLabel = new JLabel("MinutesPlayed:");
        settings = new JLabel("Add/Select or Remove Events:");

        nameField = new JTextField(5);
        locationField = new JTextField(5);

        intializeComboBoxes(); 

        displayDetails = new JTextArea();
        displayDetails.setEditable(false);
    }   

    // MODIFIES: this
    // EFFECTS: creates all the combo boxes
    public void intializeComboBoxes() {
        String[] hour = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", 
            "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};

        String[] minute = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", 
            "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", 
            "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", 
            "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};

        String[] hours = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", 
            "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};

        String[] minutes = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", 
            "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", 
            "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", 
            "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};

        startHourField = new JComboBox<>(hour);
        startMinuteField = new JComboBox<>(minute);
        hourField = new JComboBox<>(hours);
        minuteField = new JComboBox<>(minutes);
    }

    // MODIFIES: this
    // EFFECTS: adds all the tools to the panel for this tab
    public void addTools() {
        row = new JPanel(rowLayout);
        row.setLayout(new GridBagLayout());
        row.add(settings);
        row.setBorder(BorderFactory.createEmptyBorder(10, 400, 10, 400));
        add(row);

        rowLayout = new GridLayout(1, 2);
        row = new JPanel(rowLayout);
        row.setPreferredSize(new Dimension(400, 50));
        row.add(nameLabel);
        row.add(locationLabel);
        add(row);

        row = new JPanel(rowLayout);
        row.setPreferredSize(new Dimension(400, 50));
        row.add(nameField);
        row.add(locationField);
        add(row);

        addRestTools();
    }

    // MODIFIES: this
    // EFFECTS: an extention of addTools
    // adds all the rest of the tools to the panel for this tab 
    public void addRestTools() {
        rowLayout = new GridLayout(1, 4);
        row = new JPanel(rowLayout);
        row.setPreferredSize(new Dimension(650, 50));
        row.add(startHourLabel);
        row.add(startMinuteLabel);
        row.add(hourLabel);
        row.add(minuteLabel);
        add(row);

        row = new JPanel(rowLayout);
        row.setPreferredSize(new Dimension(650, 50));
        row.add(startHourField);
        row.add(startMinuteField);
        row.add(hourField);
        row.add(minuteField);
        add(row);

        addRestOfRestTools();
    }

    // MODIFIES: this
    // EFFECTS: an extention of addRestTools
    // adds all the rest of the tools to the panel for this tab 
    public void addRestOfRestTools() {
        row = new JPanel(rowLayout);
        row.setLayout(new GridBagLayout());
        row.add(createEventBtn);
        row.setBorder(BorderFactory.createEmptyBorder(10, 400, 10, 400));
        add(row);

        row = new JPanel(rowLayout);
        row.setLayout(new GridBagLayout());
        row.add(removeEventBtn);
        row.setBorder(BorderFactory.createEmptyBorder(10, 400, 10, 400));
        add(row);

        add(displayDetails);
    }

    // MODIFIES: this, controller
    // EFFECTS: manages the action events for this tab
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("creatEvent")) {
            createEvent();
        } else if (e.getActionCommand().equals("removeSelectedEvent")) {
            removeSelectedEvent();
        }
    }

    // MODIFIES: this, controller
    // EFFECTS: removes selected event from list of days if there is a selected day
    // and calls to update all tabs
    public void removeSelectedEvent() {
        if (!(getController().getSelectedSport() == null)) {
            getController().getSelectedDay().removeSportingEvent(getController().getSelectedSport().getName());
        }
        getController().setSelectedEvent(null);
        getController().update();
    }

    // MODIFIES: this, controller
    // EFFECTS: adds event to selected day if this event name does not exist,
    // makes the event the selected event and calls to update all tabs
    public void createEvent() {
        String name = nameField.getText();
        String location = locationField.getText();
        int startHour = Integer.parseInt(startHourField.getSelectedItem() + "");
        int startMinute = Integer.parseInt(startMinuteField.getSelectedItem() + "");
        int hours = Integer.parseInt(hourField.getSelectedItem() + "");
        int minutes = Integer.parseInt(minuteField.getSelectedItem() + "");

        Day selectedDay = getController().getSelectedDay();
        if (!(selectedDay == null)) {
            Sport event = selectedDay.getSpecificSportingEvent(name);

            if (event == null) {
                Sport sport = new VolleyBall(name, location);
                getController().getSelectedDay().addSportingEvent(sport, startHour, startMinute, hours, minutes);
                getController().setSelectedEvent(sport);
            } else {
                getController().setSelectedEvent(event);
            }
            getController().update();
        }
    }

    // MODIFIES: this
    // EFFECTS: updates the text area with the selected date and selected event name for this tab
    public void update() {
        displayDetails.setText(getController().getDisplayDetails());
    }
}
