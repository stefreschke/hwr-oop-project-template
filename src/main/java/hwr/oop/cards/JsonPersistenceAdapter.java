package hwr.oop.cards;

import wqjava.io.*;
import java.util.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonPersistenceAdapter implements PersistenceSavePort, PersistenceLoadPort {

    static final String EXCEPTION_TEXT = "persistenceInstanceName should not be empty.";

    public void saveTrainingInstance(Collection<Box> boxes, String persistenceInstanceName) throws IOException {

        if (persistenceInstanceName.isEmpty()){

            throw new IllegalArgumentException(EXCEPTION_TEXT);
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(persistenceInstanceName), boxes);
    }

    @Override
    public Topic loadTopic(String persistenceInstanceName) throws IOException {

        if (persistenceInstanceName.isEmpty()){

            throw new IllegalArgumentException(EXCEPTION_TEXT);
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(persistenceInstanceName))) {
            ObjectMapper mapper = new ObjectMapper();
            String json = reader.readLine();
            return mapper.readValue(json, new TypeReference<>() {
            });
        }
    }

    public Collection<Box> loadTrainingInstance(String persistenceInstanceName) throws IOException {

        if (persistenceInstanceName.isEmpty()){

            throw new IllegalArgumentException(EXCEPTION_TEXT);
        }

        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Box>> typeReference = new TypeReference<List<Box>>(){};
        List<Box> boxes = mapper.readValue(new File(persistenceInstanceName), typeReference);

        Map<Integer, Card> cards = new HashMap<>();
        for (Box box : boxes) {
            for (Card card : box.getLearnedCardList()) {
                cards.put(card.getId(), card);
            }
            for (Card card : box.getUnlearnedCardList()) {
                cards.put(card.getId(), card);
            }
        }

        List<Box> result = new ArrayList<>();
        for (Box box : boxes) {
            ArrayList<Card> learnedCardList = new ArrayList<>();
            ArrayList<Card> notLearnedCardList = new ArrayList<>();
            for (Card card : box.getLearnedCardList()) {
                learnedCardList.add(cards.get(card.getId()));
            }
            for (Card card : box.getUnlearnedCardList()) {
                notLearnedCardList.add(cards.get(card.getId()));
            }
            result.add(new Box(learnedCardList, notLearnedCardList));
        }

        return result;
    }
    @Override
    public void saveTopic(Topic topic, String persistenceInstanceName) throws IOException {

        if (persistenceInstanceName.isEmpty()){

            throw new IllegalArgumentException(EXCEPTION_TEXT);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(persistenceInstanceName))) {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(topic);
            writer.write(json);
        }
    }
}
