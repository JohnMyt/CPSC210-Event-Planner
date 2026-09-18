package model;

import java.util.ArrayList;

// Representation of a scoreboard for a sporting event
public class ScoreBoard {
    ArrayList<String> scoreBoard;
    ArrayList<Score> scores;
    
    // EFFECTS: Initiates an empty list of scores
    public ScoreBoard() {
        scoreBoard = new ArrayList<>();
        scores = new ArrayList<>();
    }

    // REQUIRES: (you >= 0 && opponent >= 0)
    // MODIFIES: this
    // EFFECTS: adds score of a portion (set) of the game to a list of scores
    public void addGameScore(int you, int opponent) {
        Score score = new Score(you, opponent);
        scores.add(score);
        scoreBoard.add(score.getYourScore() + ":" + score.getOpponentScore());
    }

    // MODIFIES: this
    // EFFECTS: if list of scores is empty return -1 otherwise
    // gets the average of all your scores from a game
    public int getYourAverageScores() {
        if (scores.isEmpty()) {
            return -1;
        }
        int sumOfScores = 0;
        for (Score score: scores) {
            sumOfScores += score.getYourScore();
        }
        return sumOfScores / scores.size();
    }

    // MODIFIES: this
    // EFFECTS: if list of scores is empty return -1 otherwise
    // gets the average of all your opponents scores from a game
    public int getOpponentAverageScores() {
        if (scores.isEmpty()) {
            return -1;
        }
        int sumOfScores = 0;
        for (Score score: scores) {
            sumOfScores += score.getOpponentScore();
        }
        return sumOfScores / scores.size();
    }

    public ArrayList<String> getScoreBoard() {
        return scoreBoard;
    }

    public ArrayList<Score> getScores() {
        return scores;
    }

    // REQUIRES: (set >= 1 && set <= listOfScores.size())
    // EFFECTS: gets your score form the nth set
    public int getYourNthScore(int set) {
        return scores.get(set - 1).getYourScore();
    }

    // REQUIRES: (set >= 1 && set <= listOfScores.size())
    // EFFECTS: gets oppenent's score form the nth set
    public int getOpponentNthScore(int set) {
        return scores.get(set - 1).getOpponentScore();
    }
}
