package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Day;
import model.ListOfDays;

// Modeled by SmartHome Application https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git
// represents the days tab in CallenderApp UI
public class DaysTab extends Tab implements ActionListener {
    private JLabel dayLabel;
    private JLabel monthLabel;
    private JLabel yearLabel;

    private JComboBox<String> dayField;
    private JComboBox<String> monthField;
    private JComboBox<String> yearField;

    private JButton createDayBtn;
    private JButton removeDayBtn;

    private JTextArea displayDetails;

    private JLabel settings;

    private GridLayout rowLayout;
    private JPanel row;
    
    // REQUIRES: CalenderApp controller that holds this tab
    // EFFECTS: creates days tab that allows to create, select, delete days from
    // list of days in controller.
    // creates the panel for the tab
    public DaysTab(CalenderApp controller) {
        super(controller);

        initalizeButtons();

        initalizeTools();

        addTools();
    }

    // MODIFIES: this
    // EFFECTS: creates all buttons for tab 
    public void initalizeButtons() {
        createDayBtn = new JButton("Select/Create Day");

        createDayBtn.setActionCommand("createDay");
        createDayBtn.addActionListener(this);

        removeDayBtn = new JButton("Remove Selected Day");

        removeDayBtn.setActionCommand("removeSelectedDay");
        removeDayBtn.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: creates all the labels, combo boxes and the text area
    public void initalizeTools() {
        String[] days = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17",
            "18","19","20","21","22","23","24","25","26","27","28","29","30","31"};

        String[] months = {"1","2","3","4","5","6","7","8","9","10","11","12"};

        String[] years = {"2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009",
                "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019",
                "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029",
                "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039",
                "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049",
                "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059",
                "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069",
                "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079",
                "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089",
                "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099","2100"};

        dayLabel = new JLabel("Day:");
        dayField = new JComboBox<>(days);
        monthLabel = new JLabel("Month:");
        monthField = new JComboBox<>(months);
        yearLabel = new JLabel("Year:");
        yearField = new JComboBox<>(years);
        settings = new JLabel("Add/Select or Remove Days:");

        displayDetails = new JTextArea();
        displayDetails.setEditable(false);
    }   

    // MODIFIES: this
    // EFFECTS: adds all the tools to the panel for this tab
    public void addTools() {
        row = new JPanel(rowLayout);
        row.setLayout(new GridBagLayout());
        row.add(settings);
        row.setBorder(BorderFactory.createEmptyBorder(10, 400, 10, 400));
        add(row);

        rowLayout = new GridLayout(1, 3);
        row = new JPanel(rowLayout);
        row.setPreferredSize(new Dimension(650, 50));
        row.add(dayLabel);
        row.add(monthLabel);
        row.add(yearLabel);
        add(row);

        row = new JPanel(rowLayout);
        row.setPreferredSize(new Dimension(650, 50));
        row.add(dayField);
        row.add(monthField);
        row.add(yearField);
        add(row);

        addRestTools();
    }

    // MODIFIES: this
    // EFFECTS: an extention of addTools
    // adds all the rest of the tools to the panel for this tab 
    public void addRestTools() {
        rowLayout = new GridLayout(1, 1);
        row = new JPanel(rowLayout);
        row.setBorder(BorderFactory.createEmptyBorder(10, 400, 10, 400));
        row.add(createDayBtn);
        add(row);
        
        row = new JPanel(rowLayout);
        row.setBorder(BorderFactory.createEmptyBorder(10, 400, 10, 400));
        row.add(removeDayBtn);
        add(row);

        add(displayDetails);
    }

    // MODIFIES: this, controller
    // EFFECTS: manages the action events for this tab
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("createDay")) {
            createDay();
        } else if (e.getActionCommand().equals("removeSelectedDay")) {
            removeSelectedDay();
        }
    }

    // MODIFIES: this, controller
    // EFFECTS: removes selected day from list of days if there is a selected day
    // and calls to update all tabs
    public void removeSelectedDay() {
        if (!(getController().getSelectedDay() == null)) {
            getController().getListOfDays().remove(getController().getSelectedDay());
        }
        getController().setSelectedEvent(null);
        getController().setSelectedDay(null);
        getController().update();
    }

    // MODIFIES: this, controller
    // EFFECTS: adds day to list of days if this date does not exist,
    // makes the date the selected day and calls to update all tabs
    public void createDay() {
        ListOfDays listOfDays = getController().getListOfDays();
        int day = Integer.parseInt(dayField.getSelectedItem() + "");
        int month = Integer.parseInt(monthField.getSelectedItem() + "");
        int year = Integer.parseInt(yearField.getSelectedItem() + "");

        boolean exists = false;
        Day existingDay = null;
        for (int i = 0; i < listOfDays.size(); i++) {
            Day event = listOfDays.get(i);
            if (event.getDay() == day && event.getMonth() == month && event.getYear() == year) {
                exists = true;
                existingDay = event;
                break;
            }
        }
        
        if (!exists) {
            Day newDay = new Day(day, month, year);
            getController().getListOfDays().add(newDay);
            getController().setSelectedDay(newDay);
        } else {
            getController().setSelectedDay(existingDay);
        }
        getController().setSelectedEvent(null);
        getController().update();
    }

    // MODIFIES: this
    // EFFECTS: updates the text area with the selected date and selected event name for this tab
    public void update() {
        displayDetails.setText(getController().getDisplayDetails());
    }
}
