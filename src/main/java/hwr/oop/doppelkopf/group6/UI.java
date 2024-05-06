package hwr.oop.doppelkopf.group6;

public class UI {
    DoppelkopfGame doppelkopfGame = new DoppelkopfGame();
    int i = 0;
    public void showSortedCards() {
        if (!doppelkopfGame.getTrumpCards().isEmpty()){
            System.out.println("Deck:" +"  "+ doppelkopfGame.getTrumpCards().get(i) +" "+ doppelkopfGame.getHerzCards() +" "+ doppelkopfGame.getPikCards() +" "+ doppelkopfGame.getKreuzCards());
        }
    }

}
