package model;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class DayTest {
    private VolleyBall vb1;
    private VolleyBall vb2;
    private Day day;
    private ArrayList<Sport> lst;
    private ArrayList<String> names;
    
    @BeforeEach
    public void setup() {
        vb1 = new VolleyBall("VolleyBall World Cup", "Standley Park");
        vb2 = new VolleyBall("VolleyBall Club Cup", "Standley Park");
        day = new Day(13, 2, 2025);
        lst = new ArrayList<>();
        names = new ArrayList<>();
    }

    @Test
    public void testSetUpFields() {
        assertEquals(13, day.getDay());
        assertEquals(2, day.getMonth());
        assertEquals(2025, day.getYear());
        assertEquals(lst, day.getSportingEvents());
        assertEquals(names, day.getSportingEventsNames());
        assertNull(day.getSpecificSportingEvent("VolleyBall World Cup"));
    }

    @Test
    public void testModifyingSportingEvents() {
        day.addSportingEvent(vb1, 18, 20, 1, 40);
        lst.add(vb1);
        names.add("VolleyBall World Cup");
        day.addSportingEvent(vb2, 18, 20, 2, 40);
        lst.add(vb2);
        names.add("VolleyBall Club Cup");
        assertEquals(13, day.getDay());
        assertEquals(2, day.getMonth());
        assertEquals(lst, day.getSportingEvents());
        assertEquals(names, day.getSportingEventsNames());
        assertEquals(vb1, day.getSpecificSportingEvent("VolleyBall World Cup"));
        assertEquals(vb2, day.getSpecificSportingEvent("VolleyBall Club Cup"));

        day.removeSportingEvent("VolleyBall World Cup");
        day.removeSportingEvent("VolleyBall World Cup");
        lst.remove(vb1);
        names.remove("VolleyBall World Cup");
        assertEquals(13, day.getDay());
        assertEquals(2, day.getMonth());
        assertEquals(2025, day.getYear());
        assertEquals(lst, day.getSportingEvents());
        assertEquals(names, day.getSportingEventsNames());
        assertNull(day.getSpecificSportingEvent("VolleyBall World Cup"));
    }
}
