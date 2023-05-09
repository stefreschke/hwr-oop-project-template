package hwr.oop.cards;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

public class PersistenceAdapter implements PersistenceSavePort, PersistenceLoadPort {

    public void saveTrainingInstance(Collection<Box> boxes, String persistenceInstanceName){


    }

    @Override
    public Collection<Card> loadCards(String persistenceInstanceName) {

        if (persistenceInstanceName.isEmpty() || persistenceInstanceName == null){

            throw new IllegalArgumentException("persistenceInstanceName should not be empty or null.");
        }

        Collection<Card> cards = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(persistenceInstanceName));
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
    public Collection<Box> loadTrainingInstance(String persistenceInstanceName) {
        return null;
    }

    @Override
    public void saveCards(Collection<Card> cards, String persistenceInstanceName) {

        if (persistenceInstanceName.isEmpty() || persistenceInstanceName == null){

            throw new IllegalArgumentException("persistenceInstanceName should not be empty or null.");
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(persistenceInstanceName, false));
            for (Card card : cards) {
                writer.write(card.getQuestion() + "," + card.getAnswer() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Error saving cards to file: " + e.getMessage());
        }
    }
}
