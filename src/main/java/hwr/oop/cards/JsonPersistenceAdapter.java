package hwr.oop.cards;

import java.io.*;
import java.util.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonPersistenceAdapter implements PersistenceSavePort, PersistenceLoadPort {

    public void saveTrainingInstance(Collection<Box> boxes, String persistenceInstanceName) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(persistenceInstanceName), boxes);
    }

    @Override
    public Collection<Card> loadCards(String persistenceInstanceName) throws IOException {

        if (persistenceInstanceName.isEmpty() || persistenceInstanceName == null){

            throw new IllegalArgumentException("persistenceInstanceName should not be empty or null.");
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(persistenceInstanceName));
            ObjectMapper mapper = new ObjectMapper();
            String json = reader.readLine();
            Collection<Card> cards = mapper.readValue(json, new TypeReference<Collection<Card>>(){});
            reader.close();
            return cards;
        } catch (IOException e) {
            throw e;
        }
    }

    public Collection<Box> loadTrainingInstance(String persistenceInstanceName) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Box>> typeReference = new TypeReference<List<Box>>(){};
        List<Box> boxes = mapper.readValue(new File(persistenceInstanceName), typeReference);

        Map<Integer, Card> cards = new HashMap<Integer, Card>();
        for (Box box : boxes) {
            for (Card card : box.getLearnedCardList()) {
                cards.put(card.getId(), card);
            }
            for (Card card : box.getNotLearnedCardList()) {
                cards.put(card.getId(), card);
            }
        }

        List<Box> result = new ArrayList<Box>();
        for (Box box : boxes) {
            ArrayList<Card> learnedCardList = new ArrayList<Card>();
            ArrayList<Card> notLearnedCardList = new ArrayList<Card>();
            for (Card card : box.getLearnedCardList()) {
                learnedCardList.add(cards.get(card.getId()));
            }
            for (Card card : box.getNotLearnedCardList()) {
                notLearnedCardList.add(cards.get(card.getId()));
            }
            result.add(new Box(learnedCardList, notLearnedCardList));
        }

        return result;
    }
    @Override
    public void saveCards(Collection<Card> cards, String persistenceInstanceName) throws IOException {

        if (persistenceInstanceName.isEmpty() || persistenceInstanceName == null){

            throw new IllegalArgumentException("persistenceInstanceName should not be empty or null.");
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(persistenceInstanceName));
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(cards);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            throw e;
        }
    }
}
