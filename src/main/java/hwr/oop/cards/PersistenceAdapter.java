package hwr.oop.cards;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

public class PersistenceAdapter implements PersistenceSavePort, PersistenceLoadPort {

    public void saveTrainingInstance(Collection<Box> boxes, String filename){


    }

    @Override
    public Collection<Card> loadCards(String filename) {

        Collection<Card> cards = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String question = parts[0];
                    String answer = parts[1];
                    cards.add(new Card(question, answer));
                }
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Error loading cards from file: " + e.getMessage());
        }
        return cards;
    }

    @Override
    public Collection<Box> loadTrainingInstance(String filename) {
        return null;
    }

    @Override
    public void saveCards(Collection<Card> cards, String filename) {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            for (Card card : cards) {
                writer.write(card.getQuestion() + "," + card.getAnswer() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Error saving cards to file: " + e.getMessage());
        }
    }
}
