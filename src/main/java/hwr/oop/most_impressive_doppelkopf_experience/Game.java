package hwr.oop.most_impressive_doppelkopf_experience;
import hwr.oop.most_impressive_doppelkopf_experience.enums.CardColours;
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
    gameLoop();
  }

  public void takeTurn(Player playerOnTurn) {
      for (int i = 0; i < playerOnTurn.getHand().size(); i++) {
          Card possibleCard = playerOnTurn.getHand().get(i);
          if (cardIsValidToBePlayed(possibleCard, playerOnTurn, stich)) {
              playCard(playerOnTurn.getHand().get(i), playerOnTurn);
              return;
          }
      }
  }


public void playRound() {
    for (int i = 0; i < NUM_PLAYERS; i++) {
      System.out.println(activePlayer.getName());
      takeTurn(activePlayer);
      activePlayer = Player.getNextPlayer(activePlayer);
    }

    activePlayer = decideWinner();
    activePlayer.playerHasWonStich(stich.discardCards);

    stich.discardCards.clear();
}

public void gameLoop() {
    while(true) {
      if (activePlayer.getHand().isEmpty()) {
      System.out.println("GRRRRRRR");
      System.out.println(players.get(0).getName() + " won so many cards: " + players.get(0).getCardsWon().size());
      System.out.println(players.get(1).getName() + " won so many cards: " + players.get(1).getCardsWon().size());
      System.out.println(players.get(2).getName() + " won so many cards: " + players.get(2).getCardsWon().size());
      System.out.println(players.get(3).getName() + " won so many cards: " + players.get(3).getCardsWon().size());
      System.out.println(players.get(0).getName() + " score: " + players.get(0).getScore());
      System.out.println(players.get(1).getName() + " score: " + players.get(1).getScore());
      System.out.println(players.get(2).getName() + " score: " + players.get(2).getScore());
      System.out.println(players.get(3).getName() + " score: " + players.get(3).getScore());
      System.out.println(players.get(0).getName() + " was team: " + players.get(0).getTeam());
      System.out.println(players.get(1).getName() + " was team: " + players.get(1).getTeam());
      System.out.println(players.get(2).getName() + " was team: " + players.get(2).getTeam());
      System.out.println(players.get(3).getName() + " was team: " + players.get(3).getTeam());
      System.out.println(findWinningTeam());

      break;
    } else {
        playRound();
      }
  }
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

  public String findWinningTeam() {
    int reScore = 0;
    int contraScore = 0;
    String winnerTeam = "CONTRA";
    for (int i = 0; i < players.size(); i++) {
      if (players.get(i).getTeam().equals(TeamNames.RE)) {
        reScore = reScore + players.get(i).getScore();

      } else {
        contraScore = contraScore + players.get(i).getScore();
      }
    }
    if (reScore > contraScore) {
      winnerTeam = TeamNames.RE.name();
    }
    return winnerTeam;
  }

  public static void main(String[] args) {
    Game game = new Game();
    game.startNewGame();
  }

    public List<Player> players() {
        Player player1 = new Player("Colin", 0, 0);
        Player player2 = new Player("Chrissi", 0, 1);
        Player player3 = new Player("Mihoshi", 0, 2);
        Player player4 = new Player("Josh", 0, 3);

        return List.of(player1, player2, player3, player4);
    }
}