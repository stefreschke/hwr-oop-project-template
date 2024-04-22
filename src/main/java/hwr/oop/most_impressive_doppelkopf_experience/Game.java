package hwr.oop.most_impressive_doppelkopf_experience;

import java.util.List;


public class Game {

  private static final int NUM_PLAYERS = 4;
  private static final int NUM_CARDS_PER_PLAYER = 12;
  static List<Player> players = createPlayers();
  CardStack stack = new CardStack();
  List<Card> cardList = stack.getCardStack();
  List<Card> shuffledStack = stack.shuffleCardsStack(cardList);
  DiscardPile discardPile = new DiscardPile();
  Player activePlayer = players.getFirst();

  public List<Player> handOutCards() {

    for(int i = 0; i < 4; i++) {
      for (int j = 0 + 12 * i; j < 12 + 12 * i  ; j++) {
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

  public Card playCard(Card cardToPlay) {
    if (activePlayer.hand.contains(cardToPlay)) {
      activePlayer.hand.remove(cardToPlay);
      discardPile.discardCard(cardToPlay);
      System.out.println("Karte gespielt: " + cardToPlay.getName());
    } else {
      System.out.println("GRRRRR");
      return null;
    }
    return cardToPlay;
  }

  public void startNewGame() {
    handOutCards();
    gameLoop();
  }

  public void takeTurn(Player playerOnTurn) {
    playCard(playerOnTurn.getHand().getFirst());
  }


public void playRound() {
    for (int i = 0; i < NUM_PLAYERS; i++) {
      System.out.println(activePlayer.getName());
      System.out.println(activePlayer.getHand().size());
      takeTurn(activePlayer);
      System.out.println(activePlayer.getHand().size());
      activePlayer = Player.getNextPlayer(activePlayer);
  }
}

public void gameLoop() {
    while(true) {
      if (activePlayer.getHand().isEmpty()) {
      System.out.println("GRRRRRRR");
      break;
    } else {
        playRound();
      }
  }
}

  public static void main(String[] args) {
    Game game = new Game();
    game.startNewGame();
  }
}
