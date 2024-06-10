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

  public List<Player> getPlayers() {return players;}

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

  public void ansagen(Player player) {
    player.setAngesagt(true);
  }

  public boolean handOutCardsAreValid(Player player) {
    //fünf oder mehr 9er Karten
    long nineCards = player.getHand().stream().filter(card -> card.getSymbol() == CardSymbols.NINE).count();
    if (nineCards >= 5) {return false;}

    //vier oder mehr 9er und vier oder mehr Könige
    long kingCards = player.getHand().stream().filter(card -> card.getSymbol() == CardSymbols.KING).count();
    if (nineCards >= 4 && kingCards >= 4) {return false;}

    //vier Neunen aller Farben
    List<Card> ninesAllColours = new ArrayList<>();
    for (Card playerCard : player.getHand()) {
      if (playerCard.getSymbol() == CardSymbols.NINE && ninesAllColours.stream().noneMatch(card -> card.getColour() == playerCard.getColour())) {
        ninesAllColours.add(playerCard);
      }
    }

    if (ninesAllColours.size() >= 4) {return false;}

    //weniger oder gleich 2 Trümpfe
    long trumpCards = player.getHand().stream().filter(card -> card.getColour() == CardColours.TRUMP).count();
    if (trumpCards <= 2){return false;}

    //sieben oder mehr volle Karten (vollen Garten -> Zehnen oder Asse)
    long volleCards = player.getHand().stream().filter(card -> card.getWorth() >= 10).count();
    if (volleCards >= 7) {return false;}

    //keine Trümpfe höher als der Karo Bube
    long trumpCardsHigherJack = player.getHand().stream().filter(card -> card.getColour() == CardColours.TRUMP && card.getWorth() > 2).count();
    if (trumpCardsHigherJack == 0) {return false;}

    return true;
  }

  public void playCard(Card cardToPlay) {

    if (cardIsValidToBePlayed(cardToPlay, activePlayer, stich)) {
      List<Card> mutableList = new ArrayList<>(activePlayer.getHand());
      mutableList.remove(cardToPlay);
      activePlayer.setHand(mutableList);
      stich.discardCard(cardToPlay);

      setNextPlayer();
    }
  }

  public boolean cardIsValidToBePlayed(Card cardToPlay, Player playerWhoPlays, Stich stich) {
      if (!playerWhoPlays.getHand().contains(cardToPlay)) {
          return false;
      }

      if (!cardFollowsSuit(cardToPlay, playerWhoPlays, stich)) {
          return false;
      }

      return true;
  }

  //Gibt true zurück, wenn die Karte nach Bedienregeln gespielt werden darf
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
    winner.playerHasWonStich(stich.getDiscardCards());
    stich.getDiscardCards().clear();

    activePlayer = winner;
  }

  public TeamNames evaluateGame() {
    setPlayerPoints();
    return findWinningTeam();
  }

public Player decideWinner() {
  List<Card> thisStich = stich.getDiscardPile();
  CardColours suitColour = thisStich.getFirst().getColour();

  Card winnerCard = thisStich.getFirst();

  for (int i = 0; i < thisStich.size(); i++) {
    if (thisStich.get(i).colour == CardColours.TRUMP) {
      suitColour = CardColours.TRUMP;
      winnerCard = thisStich.get(i);
    }

    boolean cardHasRightColour = thisStich.get(i).colour == suitColour;
    if (cardHasRightColour && thisStich.get(i).value > winnerCard.getValue()) {
      winnerCard = thisStich.get(i);
    }
  }

  int indexOfWinner = players.indexOf(activePlayer);
  for (int i = 1; i < thisStich.indexOf(winnerCard); i++) {
    indexOfWinner += 1;
    if (indexOfWinner >= players.size()) {
      indexOfWinner = 0;
    }
  }

  return players.get(indexOfWinner);
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
          score += player.score;
        }
    }

    return score;
  }

  void setPlayerPoints() {
    for (Player player : players) {
      player.setPoint(player.getPoints() + calculateTeamPoints(player.getTeam()));
    }
  }

  int calculateTeamPoints(TeamNames teamName) {
    int points = 0;

    TeamNames otherTeam = (teamName == TeamNames.CONTRA) ? TeamNames.RE : teamName;
    otherTeam = (teamName == TeamNames.RE) ? TeamNames.CONTRA : teamName;

    if (calculateTeamScore(TeamNames.RE) <= 120) {
      points += 1;
    }
    if (calculateTeamScore(TeamNames.CONTRA) < 120) {
      points += 1;
    }
    if (calculateTeamScore(teamName) < 90) {
      points += 1;
    }
    if (calculateTeamScore(teamName) < 60) {
      points += 1;
    }
    if (calculateTeamScore(teamName) < 30) {
      points += 1;
    }

    points *= getTeamPointsFactor(teamName);


    return points;
  }

  int getTeamPointsFactor(TeamNames teamName) {
    int Factor = 1;
    for (Player player : players) {
      if (player.getTeam() == teamName) {
        Factor *= 2;
      }
    }
    return Factor;
  }

  public static void main(String[] args) {
//Nicht benutzt
  }

}