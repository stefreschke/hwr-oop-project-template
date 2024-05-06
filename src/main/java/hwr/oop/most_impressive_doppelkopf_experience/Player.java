package hwr.oop.most_impressive_doppelkopf_experience;

import hwr.oop.most_impressive_doppelkopf_experience.enums.TeamNames;

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
    int id;
    TeamNames team;
    List<Card> hand = new ArrayList<>();

    public List<Card> getWonTricks() {
        return wonTricks;
    }

    public void setWonTricks(List<Card> wonTricks) {
        this.wonTricks = wonTricks;
    }

    List<Card> wonTricks = new ArrayList<>();


    public Player(String name, int score, int id) {
        this.name = name;
        this.score = score;
        this.id = id;
    }


    public static Player getNextPlayer(Player player) {
       Player nextPlayer;
        if (player.id != 3) {
            nextPlayer = Game.players.get(player.id + 1);
        } else {
            nextPlayer = Game.players.getFirst();
        }
        return nextPlayer;
    }

    public int calculateScore() {
        for (int i = 0; i < wonTricks.size(); i++) {
            score = wonTricks.get(i).getWorth() + score;
        }
        return score;
    }

}