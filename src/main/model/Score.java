package model;

import org.json.JSONObject;

import persistence.Writable;

// A represntation of your score and the oppenents score
public class Score implements Writable {
    private int yourScore;
    private int opponentScore;
    
    //EFFECTS Sets your score and oppenents score.
    public Score(int you, int opponent) {
        this.yourScore = you;
        this.opponentScore = opponent;
    }

    public int getYourScore() {
        return yourScore;
    }

    public int getOpponentScore() {
        return opponentScore;
    }

    // Modeled by: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("yourScore", yourScore);
        json.put("opponentScore", opponentScore);
        return json;
    }
}
