package hwr.oop;

import hwr.oop.enums.TeamNames;

import java.util.ArrayList;
import java.util.List;

public class Player {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public TeamNames getTeam() {
        return team;
    }

    public void setTeam(TeamNames team) {
        this.team = team;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }

    int score;
    TeamNames team;
    public List<Card> hand = new ArrayList<>();

    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }


}