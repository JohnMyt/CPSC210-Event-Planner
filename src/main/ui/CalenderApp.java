package ui;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

import model.Day;
import model.Event;
import model.EventLog;
import model.ListOfDays;
import model.Sport;

// Modeled by SmartHome Application https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git
// represenets CallenderApp UI
public class CalenderApp extends JFrame implements WindowListener {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private JTabbedPane sidebar;

    private ViewTab viewTab;
    private DaysTab daysTab;
    private EventsTab eventsTab;
    private ScoresTab scoresTab;
    private EventsChartTab eventsChartTab;

    private ListOfDays listOfDays;
    private Day selectedDay = null;
    private Sport selectedSport = null;
    
    // EFFECTS: creates CalenderApp UI, creates sidebar and loads its tabs
    public CalenderApp() {
        super("Calender");
        listOfDays = new ListOfDays();

        generateFrame();

        generateSidebar();

        addWindowListener(this);

        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates sidebar and loads its tabs to this UI
    public void generateSidebar() {
        sidebar = new JTabbedPane();
        sidebar.setTabPlacement(JTabbedPane.LEFT);
        loadTabs();
        add(sidebar);
    }

    // MODIFIES: this
    // EFFECTS: generates the frame and loads it to this UI
    public void generateFrame() {
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    //MODIFIES: this
    //EFFECTS: adds view tab, days tab, events tab and scores tab to this UI
    private void loadTabs() {
        eventsChartTab = new EventsChartTab(this);
        viewTab = new ViewTab(this);
        daysTab = new DaysTab(this);
        eventsTab = new EventsTab(this);
        scoresTab = new ScoresTab(this);

        sidebar.add(viewTab, 0);
        sidebar.setTitleAt(0, "View Events");
        sidebar.add(eventsChartTab, 1);
        sidebar.setTitleAt(1, "Events Chart");
        sidebar.add(daysTab, 2);
        sidebar.setTitleAt(2, "Modify Days");
        sidebar.add(eventsTab, 3);
        sidebar.setTitleAt(3, "Modify Events");
        sidebar.add(scoresTab, 4);
        sidebar.setTitleAt(4, "Modify Scores");
    }

    // MODIFIES: 
    // EFFECTS: updates each tab with the CallenderApp's fields
    public void update() {
        daysTab.update();
        eventsTab.update();
        scoresTab.update();
        viewTab.update();
    }

    // EFFECTS: replaces selected day with a new day
    public void setSelectedDay(Day day) {
        selectedDay = day;
    }

    // EFFECTS: replaces selected event with a new sporting event
    public void setSelectedEvent(Sport sport) {
        selectedSport = sport;
    }

    // EFFECTS: replaces list of day with a new list of day
    public void setListOfDays(ListOfDays list) {
        listOfDays = list;
    }

    // EFFECTS: returns selected day
    public Day getSelectedDay() {
        return selectedDay;
    }

    // EFFECTS: returns selected sporting event
    public Sport getSelectedSport() {
        return selectedSport;
    }

    // EFFECTS: returns the list of days
    public ListOfDays getListOfDays() {
        return listOfDays;
    }

    // EFFECTS: gets the selected day and event details from fields
    // and returns date and sporting event name as a string
    public String getDisplayDetails() {
        String text = "";
        if (!(getSelectedDay() == null)) {
            text += "Selected Date: " + getSelectedDay().getDay() + "/" + getSelectedDay().getMonth()
                + "/" + getSelectedDay().getYear();
        }
        if (!(getSelectedSport() == null)) {
            text += "\n Selected Sport Event: " + getSelectedSport().getName();
        }
        return text;
    }

    @Override
    public void windowOpened(WindowEvent e) {
        return;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        for (Event event: EventLog.getInstance()) {
            System.out.println(event.toString());
        }
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {
        return;
    }

    @Override
    public void windowIconified(WindowEvent e) {
        return;
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        return;
    }

    @Override
    public void windowActivated(WindowEvent e) {
        return;
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        return;
    }
}