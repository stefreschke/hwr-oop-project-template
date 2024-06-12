package hwr.oop.most_impressive_doppelkopf_experience;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Game implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;
  private static final int NUM_PLAYERS = 4;
  private static final int NUM_CARDS_PER_PLAYER = 12;
  private final List<Player> players = new ArrayList<>();
  CardStack stack = new CardStack();
  private final List<Card> cardList = stack.getCardStack();
  private final List<Card> shuffledStack = stack.shuffleCardsStack(cardList);
  Stich stich = new Stich();

  Player activePlayer;

  public Stich getStich() {return stich;}

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

  public void handOutCards() {

    for (Player player : players) {
      player.getHand().clear();
    }

    for(int i = 0; i < NUM_PLAYERS; i++) {
      for (int j = NUM_CARDS_PER_PLAYER * i; j < NUM_CARDS_PER_PLAYER + NUM_CARDS_PER_PLAYER * i  ; j++) {
        players.get(i).getHand().add(shuffledStack.get(j));
      }
    }
    revaluePlayerWithTwoDiamondAces();
  }
  public void revaluePlayerWithTwoDiamondAces(){
    for (Player player : players) {
      List<Card> diamondAces = player.getHand().stream().
              filter(card -> card.getColour() == CardColours.TRUMP && card.getSymbol() == CardSymbols.ACE).toList();

      if (diamondAces.size() >= 2) {
        diamondAces.forEach(card -> card.setValue(101));
      }
    }
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
      return trumpCardsHigherJack != 0;
  }

  public void playCard(Card cardToPlay) {

    if (cardIsValidToBePlayed(cardToPlay, activePlayer, stich)) {
      List<Card> mutableList = new ArrayList<>(activePlayer.getHand());
      mutableList.remove(cardToPlay);
      activePlayer.setHand(mutableList);
      stich.discardCard(cardToPlay);

      setNextPlayer();

      if (stich.getDiscardCards().size() == 4) {
        evaluateRound();
      }
    }
  }

  public boolean cardIsValidToBePlayed(Card cardToPlay, Player playerWhoPlays, Stich stich) {
      if (!playerWhoPlays.getHand().contains(cardToPlay)) {
          return false;
      }

      return cardFollowsSuit(cardToPlay, playerWhoPlays, stich);
  }

  //Gibt true zurück, wenn die Karte nach Bedienregeln gespielt werden darf
  public boolean cardFollowsSuit(Card cardToPlay, Player playerWhoPlaysTheCard, Stich stich) {
      if (!stich.getDiscardCards().isEmpty())
      {
          CardColours firstCardColor = stich.getDiscardCards().getFirst().getColour();

          boolean playedCardColourEqualsFirstCardColorOfDiscardPile = firstCardColor.equals(cardToPlay.getColour());
          boolean playerHasCardWithFirstCardColorOfDiscardPile = playerWhoPlaysTheCard.getHand().stream()
                  .anyMatch(obj -> obj.getColour().equals(firstCardColor));

          return playedCardColourEqualsFirstCardColorOfDiscardPile || !playerHasCardWithFirstCardColorOfDiscardPile;
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

    if (activePlayer.getHand().isEmpty()) {
      evaluateGame();
    }
  }

  public void evaluateGame() {
    setPlayerPoints();
  }

public Player decideWinner() {
  List<Card> thisStich = stich.getDiscardCards();
  CardColours mainColour = thisStich.getFirst().getColour();

  Card winnerCard = thisStich.getFirst();

    for (Card card : thisStich) {
        if (card.getColour() == CardColours.TRUMP) {
            mainColour = CardColours.TRUMP;
        }

        boolean cardHasRightColour = card.getColour() == mainColour;
        if (cardHasRightColour && card.getValue() > winnerCard.getValue()) {
            winnerCard = card;
        }
    }

  int indexOfWinner = players.indexOf(activePlayer);
  for (int i = 0; i < thisStich.indexOf(winnerCard); i++) {
    indexOfWinner += 1;
    if (indexOfWinner >= players.size()) {
      indexOfWinner = 0;
    }
  }

  return players.get(indexOfWinner);
}

public void distributeTeams() {
      String old = "CQ";
      players.stream()
              .map(player -> {
                boolean foundCQ = player.getHand().stream()
                        .anyMatch(card -> card.getName().equals(old));
                player.setTeam(foundCQ ? TeamNames.RE : TeamNames.CONTRA);
                return null;
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

    TeamNames otherTeam;

    if (teamName == TeamNames.RE) {
      otherTeam = TeamNames.CONTRA;
    }
    else {
      otherTeam = TeamNames.RE;
    }

    if (teamName == TeamNames.CONTRA && calculateTeamScore(TeamNames.RE) <= 120) {
      points += 2;
    }
    else if (teamName == TeamNames.RE && calculateTeamScore(TeamNames.CONTRA) < 120) {
      points += 1;
    }

    if (calculateTeamScore(otherTeam) < 90) {
      points += 1;
    }
    if (calculateTeamScore(otherTeam) < 60) {
      points += 1;
    }
    if (calculateTeamScore(otherTeam) < 30) {
      points += 1;
    }
    if (calculateTeamScore(otherTeam) == 0) {
      points += 1;
    }

    points *= getTeamPointsFactor(teamName);

    return points;
  }

  int getTeamPointsFactor(TeamNames teamName) {
    int factor = 1;
    for (Player player : players) {
      if (player.getTeam() == teamName && player.getAngesagt()) {
        factor *= 2;
      }
    }
    return factor;
  }
}