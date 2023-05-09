package hwr.oop.cards;

import java.util.ArrayList;

public interface IPersistenceAdapter {

    public void saveCards();

    public void saveTrainingInstance();

    public ArrayList<Card> loadCards();

    public ArrayList<Box> loadTrainingInstance();
}
