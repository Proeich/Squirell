package PROG2_SS2018.Aufgabe2.Utils;

import java.util.ArrayList;
import java.util.List;

public class Score {

    //ScoreBoard

    private List<ScoreEntry> ScoreBoard = new ArrayList();

    public Score(){

    }

    public void addEntry(String name, int points){
        ScoreBoard.add(new ScoreEntry(name, ScoreBoard.size(),points));
    }

    public List<ScoreEntry> getScoreBoard() {
        return ScoreBoard;
    }
}

class ScoreEntry {

    private String name;
    private int ID;
    private int score;

    public ScoreEntry(String name, int ID, int score){
        this.name = name;
        this.ID = ID;
        this.score = score;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}