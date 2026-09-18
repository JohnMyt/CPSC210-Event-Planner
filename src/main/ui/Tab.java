package ui;

import javax.swing.*;

// Modeled by SmartHome Application https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git
// a holder for the tabs to use the CallenderApp
public abstract class Tab extends JPanel {
    private final CalenderApp controller;

    // REQUIRES: CalenderApp controller that holds this tab
    // EFFECTS: holds controller
    public Tab(CalenderApp controller) {
        this.controller = controller;
    }

    // EFFECTS: returns the CallenderAPP controller for this tab
    public CalenderApp getController() {
        return controller;
    }
}
