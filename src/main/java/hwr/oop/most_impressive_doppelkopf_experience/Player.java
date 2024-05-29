package hwr.oop.most_impressive_doppelkopf_experience;

import hwr.oop.most_impressive_doppelkopf_experience.enums.CardSymbols;
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

    public void setCardsWon(List<Card> cardsWon) {this.cardsWon = cardsWon;}
    public List<Card> getCardsWon() {return cardsWon;}

    void addCardsWon(List<Card> cardsWon) {
        this.cardsWon.addAll(cardsWon);
    }

    int score;
    List<Card> cardsWon = new ArrayList<>();
    int id;
    TeamNames team;
    List<Card> hand = new ArrayList<>();


    public Player(String name, int score, int id) {
        this.name = name;
        this.score = score;
        this.id = id;
    }


    public static Player getNextPlayer(Player player) {
        Player nextPlayer;
        if (player.id < 3) {
            nextPlayer = Game.players.get(player.id + 1);
        } else {
            nextPlayer = Game.players.getFirst();
        }
        return nextPlayer;
    }

    public int calculateScore(List<Card> Stich) {
        int score = 0;
        for (int i = 0; i < Stich.size(); i++) {
            score += Stich.get(i).getWorth();
        }
        return score;
    }

    void playerHasWonStich(List<Card> Stich) {
        score += calculateScore(Stich);
        addCardsWon(Stich);
    }
}