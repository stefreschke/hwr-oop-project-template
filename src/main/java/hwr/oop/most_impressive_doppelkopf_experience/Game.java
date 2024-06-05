package hwr.oop.most_impressive_doppelkopf_experience;
import hwr.oop.most_impressive_doppelkopf_experience.enums.CardColours;
import hwr.oop.most_impressive_doppelkopf_experience.enums.CardSymbols;
import hwr.oop.most_impressive_doppelkopf_experience.enums.TeamNames;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Game {

  private static final int NUM_PLAYERS = 4;
  private static final int NUM_CARDS_PER_PLAYER = 12;
  static List<Player> players = createPlayers();
  CardStack stack = new CardStack();
  List<Card> cardList = stack.getCardStack();
  List<Card> shuffledStack = stack.shuffleCardsStack(cardList);
  Stich stich = new Stich();
  Player activePlayer = players.getFirst();

  public List<Player> handOutCards() {

    for(int i = 0; i < NUM_PLAYERS; i++) {
      for (int j = NUM_CARDS_PER_PLAYER * i; j < NUM_CARDS_PER_PLAYER + NUM_CARDS_PER_PLAYER * i  ; j++) {
        players.get(i).getHand().add(shuffledStack.get(j));
      }
    }

    return players;
  }

  public void schmeissen(Player player) {
    if (!handOutCardsAreValid(player)) {
      handOutCards();
    }
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
      if (ninesAllColours.stream().noneMatch(card -> card.getColour() == playerCard.getColour())) {
        ninesAllColours.add(playerCard);
      }
    }
    if (ninesAllColours.size() >= 4) {return false;}


    //weniger oder gleuch 2 Trümpfe
    long TrumpCards = player.hand.stream().filter(card -> card.getColour() == CardColours.TRUMP).count();
    if (TrumpCards <= 2){return false;}

    //sieben oder mehr volle Karten (vollen Garten -> Zehnen oder Asse)
    long volleCards = player.hand.stream().filter(card -> card.getWorth() >= 10).count();
    if (volleCards >= 7) {return false;}

    //keine Trümpfe höher als der Karo Bube
    long trumpCardsHigherKing = player.hand.stream().filter(card -> card.getColour() == CardColours.TRUMP && card.getWorth() > 2).count();
    if (trumpCardsHigherKing == 0) {return false;}

    return true;
  }

  public static List<Player> createPlayers() {
    Player player1 = new Player("Colin", 0, 0);
    Player player2 = new Player("Chrissi", 0, 1);
    Player player3 = new Player("Mihoshi", 0, 2);
    Player player4 = new Player("Josh", 0, 3);

    return List.of(player1, player2, player3, player4);
  }

  public void playCard(Card cardToPlay, Player playerToPLay) {
    if (cardIsValidToBePlayed(cardToPlay, playerToPLay, stich)) {
        List<Card> mutableList = new ArrayList<>(playerToPLay.hand);
        mutableList.remove(cardToPlay);
        playerToPLay.setHand(mutableList);
      stich.discardCard(cardToPlay);
      System.out.println("Karte gespielt: " + cardToPlay.getName());
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

    public void startNewGame() {
        handOutCards();
        distributeTeams(players);
    }

public Player decideWinner() {
  int PositionOfHighestCardInDiscardPile = stich.getPositionOfHighestCardInDiscardPile();

  Player Winner = activePlayer;
  for (int i = 0; i < PositionOfHighestCardInDiscardPile; i++) {
    Winner = Player.getNextPlayer(Winner);
  }

  Card HighestCard = stich.getDiscardPile().get(PositionOfHighestCardInDiscardPile);
  System.out.println("Höchste Karte: " + HighestCard.getName() + "; Position der Karte: " + PositionOfHighestCardInDiscardPile);
  System.out.println("Sieger: " + Winner.getName());

  return Winner;
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

  public TeamNames findWinningTeam(List<Player> players) {
    int reScore = 0;
    int contraScore = 0;
    TeamNames winnerTeam = null;

    for (int i = 0; i < players.size(); i++) {
      if (players.get(i).getTeam().equals(TeamNames.RE)) {
        reScore += players.get(i).getScore();

      } else {
        contraScore += players.get(i).getScore();
      }
    }

    if (reScore > contraScore) {
      winnerTeam = TeamNames.RE;
    }
    else if (contraScore > reScore){
      winnerTeam = TeamNames.CONTRA;
    }
    return winnerTeam;
  }

  public static void main() {}
}