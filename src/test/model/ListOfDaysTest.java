package model;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class ListOfDaysTest {
    private Day day1 = new Day(11, 5, 2025);
    private Day day2 = new Day(22, 4, 2024);
    private ListOfDays lstOfDays = new ListOfDays();

    @Test 
    public void testAddAndRemove() {
        assertEquals(0, lstOfDays.size());
        lstOfDays.add(day1);
        assertEquals(1, lstOfDays.size());
        assertEquals(day1, lstOfDays.get(0));
        lstOfDays.add(day2);
        assertEquals(2, lstOfDays.size());
        assertEquals(day1, lstOfDays.get(0));
        assertEquals(day2, lstOfDays.get(1));

        lstOfDays.remove(day1);
        assertEquals(1, lstOfDays.size());
        assertEquals(day2, lstOfDays.get(0));
        lstOfDays.remove(day2);
        assertEquals(0, lstOfDays.size());

        lstOfDays.clear();
        assertEquals(0, lstOfDays.size());
    }
}
