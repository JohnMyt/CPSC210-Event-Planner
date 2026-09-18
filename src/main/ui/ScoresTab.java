package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Score;
import model.Sport;

// Modeled by SmartHome Application https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git
// represents the scores tab in CallenderApp UI
public class ScoresTab extends Tab implements ActionListener {

    private JLabel yourLabel;
    private JLabel opponentLabel;

    private JComboBox<String> yourField;
    private JComboBox<String> opponentField;

    private JButton createScoreBtn;

    private JLabel settings;

    private GridLayout rowLayout;
    private JPanel row;

    private JTextArea displayDetails;
    
    // REQUIRES: CalenderApp controller that holds this tab
    // EFFECTS: creates scores tab that allows to create scores from
    // selected event in controller.
    // creates the panel for the tab
    public ScoresTab(CalenderApp controller) {
        super(controller);

        initalizeButtons();

        initalizeTools();

        addTools();
    }

    // MODIFIES: this
    // EFFECTS: creates all buttons for tab 
    public void initalizeButtons() {
        createScoreBtn = new JButton("Add to Scoreboard");

        createScoreBtn.setActionCommand("addScore");
        createScoreBtn.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: creates all the labels, combo boxes and the text area
    public void initalizeTools() {
        String[] scores = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", 
            "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", 
            "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", 
            "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", 
            "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68",
            "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", 
            "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96",
            "97", "98", "99", "100", "101", "102", "103", "104", "105", "106", "107", "108", "109",
            "110", "111", "112", "113", "114", "115", "116", "117", "118", "119", "120", "121", "122", 
            "123", "124", "125", "126", "127", "128", "129", "130"};

        yourLabel = new JLabel("Your Score:");
        yourField = new JComboBox<>(scores);
        opponentLabel = new JLabel("Opponent's Score:");
        opponentField = new JComboBox<>(scores);
        settings = new JLabel("Add Scores:");

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

        rowLayout = new GridLayout(1, 2);
        row = new JPanel(rowLayout);
        row.setPreferredSize(new Dimension(650, 50));
        row.add(yourLabel);
        row.add(opponentLabel);
        add(row);

        row = new JPanel(rowLayout);
        row.setPreferredSize(new Dimension(650, 50));
        row.add(yourField);
        row.add(opponentField);
        add(row);

        rowLayout = new GridLayout(1, 1);
        row = new JPanel(rowLayout);
        row.setBorder(BorderFactory.createEmptyBorder(10, 400, 10, 400));
        row.add(createScoreBtn);
        add(row);

        add(displayDetails);
    }

    // MODIFIES: this, controller
    // EFFECTS: manages the action events for this tab
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("addScore")) {
            createScores();
        }
    }

    // MODIFIES: this, controller
    // EFFECTS: adds scores from selected sporting event if there is a selected sporting event
    // and calls to update all tabs
    public void createScores() {
        int yourScore = Integer.parseInt(yourField.getSelectedItem() + "");
        int opponentScore = Integer.parseInt(opponentField.getSelectedItem() + "");
        Sport selectedSport = getController().getSelectedSport();
        if (!(selectedSport == null)) {
            if (!(selectedSport.getScoreBoard().getScores().size() > 4)) {
                getController().getSelectedSport().recordScores(new Score(yourScore, opponentScore));
            }
        }
        getController().update();
    }

    // MODIFIES: this
    // EFFECTS: updates the text area with the selected date and selected event name for this tab
    public void update() {
        displayDetails.setText(getController().getDisplayDetails());
    }
}
