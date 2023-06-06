package hwr.oop.cards;

import java.io.*;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

//@JsonIdentityInfo(generator = ObjectIdGenerators.None.class)
public class NewJsonPersistenceAdapter implements NewPersistenceSavePort, NewPersistenceLoadPort {

    static final String EXCEPTION_TEXT = "persistenceInstanceName should not be empty.";

    public void saveLernsessionInstance(Collection<BoxInterface> boxes, String persistenceInstanceName) throws IOException {

        if (persistenceInstanceName.isEmpty()){

            throw new IllegalArgumentException(EXCEPTION_TEXT);
        }

        ObjectMapper mapper = new ObjectMapper();
        /*mapper.disable(MapperFeature.CAN_OVERRIDE_ACCESS_MODIFIERS);
        mapper.disable(SerializationFeature.FAIL_ON_SELF_REFERENCES);*/
        mapper.writeValue(new File(persistenceInstanceName), boxes);
        System.out.println("funktioniert");
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

    public Collection<BoxInterface> loadLernsessionInstance(String persistenceInstanceName) throws IOException {

        if (persistenceInstanceName.isEmpty()){

            throw new IllegalArgumentException(EXCEPTION_TEXT);
        }

        ObjectMapper mapper = new ObjectMapper();
        //mapper.configure(SerializationFeature.FAIL_ON_SELF_REFERENCES, false);
        TypeReference<List<BoxInterface>> typeReference = new TypeReference<List<BoxInterface>>(){};
        List<BoxInterface> boxes = mapper.readValue(new File(persistenceInstanceName), typeReference);
        List<BoxInterface> result = new ArrayList<>(boxes);
    /*
        Map<Integer, Card> cards = new HashMap<>();
        for (BoxInterface box : boxes) {
            for (Card card : box.getLearnedCardList()) {
                cards.put(card.getId(), card);
            }
            for (Card card : box.getUnlearnedCardList()) {
                cards.put(card.getId(), card);
            }
        }

        List<Box> result = new ArrayList<>();
        for (BoxInterface box : boxes) {
            ArrayList<Card> learnedCardList = new ArrayList<>();
            ArrayList<Card> notLearnedCardList = new ArrayList<>();
            for (Card card : box.getLearnedCardList()) {
                learnedCardList.add(cards.get(card.getId()));
            }
            for (Card card : box.getUnlearnedCardList()) {
                notLearnedCardList.add(cards.get(card.getId()));
            }
            result.add(new Box(learnedCardList, notLearnedCardList));
        }*/
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

