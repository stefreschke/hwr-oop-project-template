package hwr.oop.most_impressive_doppelkopf_experience;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player implements Serializable {
    private static final long serialVersionUID = 1L;
    String name;
    int totalPoints;
    int score;
    private List<Card> cardsWon = new ArrayList<>();
    int id;
    TeamNames team;
    private List<Card> hand = new ArrayList<>();

    boolean angesagt;

    public boolean getAngesagt() {return angesagt;}

    public void setAngesagt(boolean angesagt) {this.angesagt = angesagt;}

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
        int tempScore = 0;
        for (int i = 0; i < cards.size(); i++) {
            tempScore += cards.get(i).getWorth();
        }
        return tempScore;
    }

    void playerHasWonStich(List<Card> stich) {
        score += calculateScore(stich);
        addCardsWon(stich);
    }
}