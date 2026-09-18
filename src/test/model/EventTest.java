package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

// Taken from AlarmSystem application
/**
 * Unit tests for the Event class
 */
public class EventTest {
    private Event event;
    private Event otherEvent1;
    private Event otherEvent2;
    private Event otherEvent3;
    private Date date;
	
    @BeforeEach
	public void runBefore() {
        event = new Event("Sensor open at door");
        otherEvent1 = event;
        otherEvent2 = new Event("Open at door");
        date = event.getDate();
    }
	
    @Test
	public void testEvent() {
        assertEquals("Sensor open at door", event.getDescription());
        assertEquals(date, event.getDate());
        otherEvent2.getDate().setTime(0);
        assertFalse(otherEvent2.getDate().equals(date));
    }

    @Test
	public void testEquals() {
        assertFalse(event.equals(date));
        assertFalse(event.equals(otherEvent3));
        assertTrue(event.equals(otherEvent1));
        assertFalse(event.equals(otherEvent2));
        otherEvent3 = new Event("Sensor open at door");
        otherEvent3.getDate().setTime(0);
        assertFalse(event.equals(otherEvent3));
    }

    @Test
	public void testHasCode() {
        assertEquals(13 * event.getDate().hashCode() + event.getDescription().hashCode(), event.hashCode());
    }

    @Test
	public void testToString() {
        assertEquals(date.toString() + "\n" + "Sensor open at door", event.toString());
    }
}
