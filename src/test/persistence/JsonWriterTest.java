package persistence;

import org.junit.jupiter.api.Test;

import model.ListOfDays;
import model.Score;
import model.Sport;
import model.VolleyBall;
import model.Day;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// Modeled by: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
class JsonWriterTest {

    @Test
    public void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testWriterEmptyListOfDays() {
        try {
            ListOfDays lst = new ListOfDays();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyListOfDays.json");
            writer.open();
            writer.write(lst);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyListOfDays.json");
            lst = reader.read();
            assertEquals(0, lst.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testReadingFirstWriterGeneralListOfDays() {
        ListOfDays lst = testWriterGeneralListOfDays();
        assertEquals(2, lst.size());
        assertEquals(11, lst.get(0).getDay());
        assertEquals(2, lst.get(0).getMonth());
        assertEquals(2025, lst.get(0).getYear());

        Sport event = lst.get(0).getSpecificSportingEvent("basket");
        assertEquals("basket", event.getName());
        assertEquals("src", event.getLocation());
        assertEquals(16, event.getStartHours());
        assertEquals(0, event.getStartMinutes());
        assertEquals(1, event.getHoursPlayed());
        assertEquals(30, event.getMinutesPlayed());

        assertEquals(25, event.getScoreBoard().getYourNthScore(1));
        assertEquals(18, event.getScoreBoard().getOpponentNthScore(1));
        assertEquals(20, event.getScoreBoard().getYourNthScore(2));
        assertEquals(25, event.getScoreBoard().getOpponentNthScore(2));
    }

    @Test
    public void testReadingSecondWriterGeneralListOfDays() {
        ListOfDays lst = testWriterGeneralListOfDays();
        assertEquals(2, lst.size());
        assertEquals(10, lst.get(1).getDay());
        assertEquals(2, lst.get(1).getMonth());
        assertEquals(2025, lst.get(1).getYear());

        Sport event = lst.get(1).getSpecificSportingEvent("basket");
        assertEquals("basket", event.getName());
        assertEquals("src", event.getLocation());
        assertEquals(16, event.getStartHours());
        assertEquals(0, event.getStartMinutes());
        assertEquals(1, event.getHoursPlayed());
        assertEquals(30, event.getMinutesPlayed());

        assertEquals(25, event.getScoreBoard().getYourNthScore(1));
        assertEquals(18, event.getScoreBoard().getOpponentNthScore(1));
    }

    public ListOfDays testWriterGeneralListOfDays() {
        try {
            ListOfDays lst = new ListOfDays();
            lst.add(new Day(11, 2, 2025));
            Sport sport = new VolleyBall("basket","src");
            lst.get(0).addSportingEvent(sport, 16, 0, 1, 30);
            lst.get(0).getSpecificSportingEvent("basket").recordScores(new Score(25, 18));
            lst.get(0).getSpecificSportingEvent("basket").recordScores(new Score(20, 25));
            lst.add(new Day(10, 2, 2025));

            Sport sport1 = new VolleyBall("basket","src");
            lst.get(1).addSportingEvent(sport1, 16, 0, 1, 30);
            lst.get(1).getSpecificSportingEvent("basket").recordScores(new Score(25, 18));

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralListOfDays.json");
            writer.open();
            writer.write(lst);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralListOfDays.json");
            lst = reader.read();
            return lst;
        } catch (IOException e) {
            // fail
            return null;
        }
    }
}