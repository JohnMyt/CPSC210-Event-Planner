package persistence;

import org.junit.jupiter.api.Test;

import model.ListOfDays;
import model.Sport;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// Modeled by: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
class JsonReaderTest {

    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testReaderEmptyListOfDays() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyListOfDays.json");
        try {
            ListOfDays lst = reader.read();
            assertEquals(0, lst.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderGeneralListOfDays() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralListOfDays.json");
        try {
            ListOfDays lst = reader.read();
            assertEquals(2, lst.size());
            assertEquals(3, lst.get(1).getDay());
            assertEquals(5, lst.get(1).getMonth());
            assertEquals(2024, lst.get(1).getYear());

            Sport event = lst.get(1).getSpecificSportingEvent("basket");
            assertEquals("basket", event.getName());
            assertEquals("src", event.getLocation());
            assertEquals(15, event.getStartHours());
            assertEquals(15, event.getStartMinutes());
            assertEquals(1, event.getHoursPlayed());
            assertEquals(25, event.getMinutesPlayed());
            
            assertEquals(22, event.getScoreBoard().getYourNthScore(2));
            assertEquals(25, event.getScoreBoard().getOpponentNthScore(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}