package hwr.oop.most_impressive_doppelkopf_experience;

import hwr.oop.most_impressive_doppelkopf_experience.enums.CardSymbols;
import hwr.oop.most_impressive_doppelkopf_experience.enums.TeamNames;

import java.util.ArrayList;
import java.util.List;

public class Player {
    String name;
    int totalPoints;
    int score;
    List<Card> cardsWon = new ArrayList<>();
    int id;
    TeamNames team;
    List<Card> hand = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return totalPoints;
    }

    public void setPoint(int totalPoints) {
        this.totalPoints = totalPoints;
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

    public void setHand(List<Card> hand) {this.hand = hand;}
    public void addToHand(List<Card> hand) {
        this.hand.addAll(hand);
    }

    public void setCardsWon(List<Card> cardsWon) {this.cardsWon = cardsWon;}
    public List<Card> getCardsWon() {return cardsWon;}

    void addCardsWon(List<Card> cardsWon) {
        this.cardsWon.addAll(cardsWon);
    }



    public Player(String name, int score, int id) {
        this.name = name;
        this.score = score;
        this.id = id;
    }

    public int calculateScore(List<Card> cards) {
        int score = 0;
        for (int i = 0; i < cards.size(); i++) {
            score += cards.get(i).getWorth();
        }
        return score;
    }

    void playerHasWonStich(List<Card> Stich) {
        score += calculateScore(Stich);
        addCardsWon(Stich);
    }
}