package hwr.oop.most_impressive_doppelkopf_experience;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Game implements Serializable {
  private static final long serialVersionUID = 1L;

  private static final int NUM_PLAYERS = 4;
  private static final int NUM_CARDS_PER_PLAYER = 12;
  List<Player> players = new ArrayList<>();
  CardStack stack = new CardStack();
  List<Card> cardList = stack.getCardStack();
  List<Card> shuffledStack = stack.shuffleCardsStack(cardList);
  Stich stich = new Stich();

  Player activePlayer;

  public boolean addPlayer(String name) {
    if (players.size() < 4) {
      players.add(new Player(name, 0, players.size()));
      return true;
    }

    return false;
  }

  public void setStartPlayer(Player startPlayer) {
    activePlayer = startPlayer;
  }

  public List<Player> handOutCards() {

    for(int i = 0; i < NUM_PLAYERS; i++) {
      players.get(i).getHand().clear();
      for (int j = NUM_CARDS_PER_PLAYER * i; j < NUM_CARDS_PER_PLAYER + NUM_CARDS_PER_PLAYER * i  ; j++) {
        players.get(i).getHand().add(shuffledStack.get(j));
      }
    }

    return distributeTeams(players);
  }

  public boolean schmeissen(Player player) {
    if (!handOutCardsAreValid(player)) {
      handOutCards();
      return true;
    }

    return false;
  }

  public boolean handOutCardsAreValid(Player player) {
    //fünf oder mehr 9er Karten
    long nineCards = player.hand.stream().filter(card -> card.getSymbol() == CardSymbols.NINE).count();
    if (nineCards >= 5) {return false;}

    //vier oder mehr 9er und vier oder mehr Könige
    long kingCards = player.hand.stream().filter(card -> card.getSymbol() == CardSymbols.KING).count();
    if (nineCards >= 4 && kingCards >= 4) {return false;}

    //vier Neunen aller Farben
    List<Card> ninesAllColours = new ArrayList<>();
    for (Card playerCard : player.hand) {
      if (playerCard.getSymbol() == CardSymbols.NINE && ninesAllColours.stream().noneMatch(card -> card.getColour() == playerCard.getColour())) {
        ninesAllColours.add(playerCard);
      }
    }

    if (ninesAllColours.size() >= 4) {return false;}

    //weniger oder gleich 2 Trümpfe
    long TrumpCards = player.hand.stream().filter(card -> card.getColour() == CardColours.TRUMP).count();
    if (TrumpCards <= 2){return false;}

    //sieben oder mehr volle Karten (vollen Garten -> Zehnen oder Asse)
    long volleCards = player.hand.stream().filter(card -> card.getWorth() >= 10).count();
    if (volleCards >= 7) {return false;}

    //keine Trümpfe höher als der Karo Bube
    long trumpCardsHigherJack = player.hand.stream().filter(card -> card.getColour() == CardColours.TRUMP && card.getWorth() > 2).count();
    if (trumpCardsHigherJack == 0) {return false;}

    return true;
  }

  public void playCard(Card cardToPlay) {

    if (cardIsValidToBePlayed(cardToPlay, activePlayer, stich)) {
      List<Card> mutableList = new ArrayList<>(activePlayer.hand);
      mutableList.remove(cardToPlay);
      activePlayer.setHand(mutableList);
      stich.discardCard(cardToPlay);

      setNextPlayer();
    }
  }

  public boolean cardIsValidToBePlayed(Card cardToPlay, Player playerWhoPlays, Stich stich) {
      if (!playerWhoPlays.hand.contains(cardToPlay)) {
          return false;
      }

      if (!cardFollowsSuit(cardToPlay, playerWhoPlays, stich)) {
          return false;
      }

      return true;
  }

  //Gibt true zurück wenn die Karte nach Bedienregeln gespielt werden darf
  public boolean cardFollowsSuit(Card cardToPlay, Player playerWhoPlaysTheCard, Stich stich) {
      if (!stich.getDiscardPile().isEmpty())
      {
          CardColours firstCardColor = stich.getDiscardPile().getFirst().getColour();

          boolean playedCardColourEqualsFirstCardColorOfDiscardPile = firstCardColor.equals(cardToPlay.getColour());
          boolean playerHasCardWithFirstCardColorOfDiscardPile = playerWhoPlaysTheCard.getHand().stream()
                  .anyMatch(obj -> obj.getColour().equals(firstCardColor));

          if (!playedCardColourEqualsFirstCardColorOfDiscardPile && playerHasCardWithFirstCardColorOfDiscardPile) {
              return false;
          }
      }

      return true;
  }

  public void setNextPlayer() {
    int nextPlayerIndex = players.indexOf(activePlayer) + 1;

    if (nextPlayerIndex >= players.size()) {
      nextPlayerIndex = 0;
    }

    activePlayer = players.get(nextPlayerIndex);
  }

  public void evaluateRound() {
    Player winner = decideWinner();
    winner.playerHasWonStich(stich.discardCards);
    stich.discardCards.clear();

    activePlayer = winner;
  }

  public TeamNames evaluateGame() {
    //TODO: Methode um das Spiel auszuwerten; Gewinnende ermitteln und Punkte verteilen
    return findWinningTeam();
  }

public Player decideWinner() {
  List<Card> thisStich = stich.getDiscardPile();
  CardColours SuitColour = thisStich.getFirst().getColour();

  Card winnerCard = thisStich.getFirst();

  for (int i = 0; i < thisStich.size(); i++) {
    if (thisStich.get(i).colour == CardColours.TRUMP) {
      SuitColour = CardColours.TRUMP;
      winnerCard = thisStich.get(i);
    }

    boolean cardHasRightColour = thisStich.get(i).colour == SuitColour;
    if (cardHasRightColour && thisStich.get(i).value > winnerCard.getValue()) {
      winnerCard = thisStich.get(i);
    }
  }

  int IndexOfWinner = players.indexOf(activePlayer);
  for (int i = 0; i < thisStich.indexOf(winnerCard); i++) {
    IndexOfWinner += 1;
    if (IndexOfWinner >= players.size()) {
      IndexOfWinner = 0;
    }
  }

  return players.get(IndexOfWinner);
}

public List<Player> distributeTeams(List<Player> players) {
      String old = "CQ";
      return players.stream()
              .map(player -> {
                boolean foundCQ = player.getHand().stream()
                        .anyMatch(card -> card.getName().equals(old));
                player.setTeam(foundCQ ? TeamNames.RE : TeamNames.CONTRA);
                return player;
              })
              .collect(Collectors.toList());
    }

  public TeamNames findWinningTeam() {
    if (calculateTeamScore(TeamNames.RE) > calculateTeamScore(TeamNames.CONTRA)) {
      return TeamNames.RE;
    }
    return TeamNames.CONTRA;
  }

  int calculateTeamScore(TeamNames teamName) {
    int score = 0;
    for (Player player : players) {
        if (player.getTeam() == teamName) {
          score += player.getHand().stream()
                  .mapToInt(Card::getWorth)
                  .sum();
        }
    }

    return score;
  }

  void calculatePoints() {
    int scoreRe = 0;
    int scoreContra = 0;

    if (calculateTeamScore(TeamNames.RE) < 120) {
      scoreContra += 1;
    }
    if (calculateTeamScore(TeamNames.RE) < 90) {
      scoreContra += 1;
    }
    if (calculateTeamScore(TeamNames.RE) < 60) {
      scoreContra += 1;
    }
    if (calculateTeamScore(TeamNames.RE) < 30) {
      scoreContra += 1;
    }

    if (calculateTeamScore(TeamNames.CONTRA) < 120) {
      scoreRe += 1;
    }
    if (calculateTeamScore(TeamNames.CONTRA) < 90) {
      scoreRe += 1;
    }
    if (calculateTeamScore(TeamNames.CONTRA) < 60) {
      scoreRe += 1;
    }
    if (calculateTeamScore(TeamNames.CONTRA) < 30) {
      scoreRe += 1;
    }

    for (Player player : players) {
        if (player.getTeam() == TeamNames.RE) {
          player.setPoint(player.getPoints() + scoreRe);
        }
        else {
          player.setPoint(player.getPoints() + scoreContra);
        }
    }
  }





  public static void main(String[] args) {

  }

}