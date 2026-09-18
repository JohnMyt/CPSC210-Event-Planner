package ui;

import java.awt.Color;
import java.awt.Graphics;

import model.Day;
import model.ListOfDays;

// Modeled by SmartHome Application https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git
// makes a bar chart of events for each day, for the CallenderApp UI
public class EventsChartTab extends Tab {
    private static final Color BACKGROUND_COLOR = Color.white;
    private static final Color BAR_COLOR = Color.blue;
    private static final Color TEXT_COLOR = Color.black;
    private static final int OUTER_MARGIN = 20;
    private static final int WIDTH = 800 - 2 * OUTER_MARGIN;
    private static final int HEIGHT = 600 - 2 * OUTER_MARGIN;

    // REQUIRES: CalenderApp controller that holds this tab
    // EFFECTS: creates event bar chart tab which creates
    // a bar chart of the number of events in each day
    public EventsChartTab(CalenderApp controller) {
        super(controller);
    }

    // EFFECTS: Calls the UI delegate's paint method
    // and creates a bar chart of the number of events in each day
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        drawBars(g);
    }

    // EFFECTS: creates a bar chart of the number of events in each day
    private void drawBars(final Graphics g) {
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(TEXT_COLOR);
        
        ListOfDays listOfDays = getController().getListOfDays();
        if (listOfDays.size() == 0) {
            g.drawString("No days in list of days!", 275, 250);
            return;
        }

        g.drawString("Number of Events", 300, OUTER_MARGIN);

        int barLength;
        if (getMaxEvents() == 0) {
            barLength = 0;
        } else {
            barLength = (WIDTH - 200) / getMaxEvents();
        }

        int barHeight = 20;

        int lastY = makeBars(listOfDays, g, barLength, barHeight);

        g.drawString("(Each subbar represents one sporting event)", 240, lastY + 45);
    }

    public int makeBars(ListOfDays listOfDays, Graphics g, int barLength, int barHeight) {
        int y = 0;
        for (int i = 0; i < listOfDays.size(); i++) {
            y = OUTER_MARGIN * 2 + 25 * i;
            Day day = listOfDays.get(i);
            int x = OUTER_MARGIN + 65;
            drawRect(g, x, y, barLength, barHeight, day.getSportingEvents().size() - 1);
            String date = day.getDay() + "/" + day.getMonth() + "/" + day.getYear();
            g.setColor(TEXT_COLOR);
            g.drawString(date, OUTER_MARGIN, y + 15);
        }
        return y;
    }

    // REQUIRES: (x >= 0 && y >= 0 && barLength >= 0 && barHeight >= 0 && n >= 0)
    // EFFECTS: Creates individual, same length bars for the number of event in a day
    public void drawRect(final Graphics g, int x, int y, int barLength, int barHeight, int n) {
        g.setColor(BAR_COLOR);
        for (int i = 0; i <= n; i++) {
            g.fillRect(x, y, barLength - 1, barHeight);
            x += barLength;
        }
    }

    // EFFECTS: returns the heighest amount of events in a day for the list of days
    public int getMaxEvents() {
        ListOfDays listOfDays = getController().getListOfDays();
        int max = 0;
        for (int i = 0; i < listOfDays.size(); i++) {
            Day day = listOfDays.get(i);
            int size = day.getSportingEvents().size();
            if (size > max) {
                max = size;
            }
        }
        return max;
    }
}
