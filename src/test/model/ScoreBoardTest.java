package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

public class ScoreBoardTest {
    private ScoreBoard scores;
    private ArrayList<String> lst;
    private ArrayList<Score> scoresLst;
    
    @BeforeEach
    public void setup() {
        scores = new ScoreBoard();
        lst = new ArrayList<>();
        scoresLst = new ArrayList<>();
    }

    @Test
    public void testSetUpFields() {
        assertEquals(lst, scores.getScoreBoard());
        assertEquals(-1, scores.getYourAverageScores());
        assertEquals(-1, scores.getOpponentAverageScores());
    }

    @Test
    public void testSetGameScore() {
        scores.addGameScore(25, 22);
        lst.add("25:22");
        scoresLst = scores.getScores();
        assertEquals(lst, scores.getScoreBoard());
        assertEquals(scoresLst, scores.getScores());
        assertEquals(25, scores.getYourAverageScores());
        assertEquals(22, scores.getOpponentAverageScores());
        assertEquals(25, scores.getYourNthScore(1));
        assertEquals(22, scores.getOpponentNthScore(1));
        
        scores.addGameScore(22, 25);
        lst.add("22:25");
        scoresLst = scores.getScores();
        assertEquals(lst, scores.getScoreBoard());
        assertEquals(scoresLst, scores.getScores());
        assertEquals(23, scores.getYourAverageScores());
        assertEquals(23, scores.getOpponentAverageScores());
        assertEquals(25, scores.getYourNthScore(1));
        assertEquals(22, scores.getOpponentNthScore(1));
        assertEquals(22, scores.getYourNthScore(2));
        assertEquals(25, scores.getOpponentNthScore(2));
    }
}
