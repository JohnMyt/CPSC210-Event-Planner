package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class VolleyBallTest {
    private VolleyBall vb;
    private ArrayList<String> lst;
    private ScoreBoard sb;
    private Score score1;
    private Score score2;
    
    @BeforeEach
    public void setup() {
        vb = new VolleyBall("VolleyBall World Cup", "Standley Park");
        lst = new ArrayList<>();
        sb = new ScoreBoard();
        score1 = new Score(25, 22);
        score2 = new Score(22, 25);
    }

    @Test
    public void testSetUpFields() {
        assertEquals(0, vb.getStartHours());
        assertEquals(0, vb.getStartMinutes());
        assertEquals(0, vb.getHoursPlayed());
        assertEquals(0, vb.getMinutesPlayed());
        assertEquals(lst, vb.getScoreBoard().getScoreBoard());
        assertEquals("VolleyBall World Cup", vb.getName());
        assertEquals("Standley Park", vb.getLocation());
    }

    @Test
    public void testSetters() {
        vb.setStartTime(16, 30);
        vb.setTimePlayed(2, 40);
        assertEquals(16, vb.getStartHours());
        assertEquals(30, vb.getStartMinutes());
        assertEquals(2, vb.getHoursPlayed());
        assertEquals(40, vb.getMinutesPlayed());
        assertEquals(lst, vb.getScoreBoard().getScoreBoard());
        assertEquals("VolleyBall World Cup", vb.getName());
        assertEquals("Standley Park", vb.getLocation());

        vb.setName("VolleyBall Club Cup");
        vb.setLocation("Six Pack");
        assertEquals(16, vb.getStartHours());
        assertEquals(30, vb.getStartMinutes());
        assertEquals(2, vb.getHoursPlayed());
        assertEquals(40, vb.getMinutesPlayed());
        assertEquals(lst, vb.getScoreBoard().getScoreBoard());
        assertEquals("VolleyBall Club Cup", vb.getName());
        assertEquals("Six Pack", vb.getLocation());
    }

    @Test
    public void testRecordScores() {
        sb.addGameScore(22, 25);
        vb.recordScores(score2);
        assertEquals(0, vb.getStartHours());
        assertEquals(0, vb.getStartMinutes());
        assertEquals(0, vb.getHoursPlayed());
        assertEquals(0, vb.getMinutesPlayed());
        assertEquals(sb.getScoreBoard(), vb.getScoreBoard().getScoreBoard());
        assertEquals("VolleyBall World Cup", vb.getName());
        assertEquals("Standley Park", vb.getLocation());

        sb.addGameScore(25, 22);
        vb.recordScores(score1);
        assertEquals(0, vb.getStartHours());
        assertEquals(0, vb.getStartMinutes());
        assertEquals(0, vb.getMinutesPlayed());
        assertEquals(0, vb.getHoursPlayed());
        assertEquals(0, vb.getMinutesPlayed());
        assertEquals(sb.getScoreBoard(), vb.getScoreBoard().getScoreBoard());
        assertEquals("VolleyBall World Cup", vb.getName());
        assertEquals("Standley Park", vb.getLocation());

        vb.clear();
        sb = new ScoreBoard();
        assertEquals(sb.getScoreBoard(), vb.getScoreBoard().getScoreBoard());
    }
}
