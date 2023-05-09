package hwr.oop.cards;

import java.io.*;
import java.util.Collection;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonPersistenceAdapter implements PersistenceSavePort, PersistenceLoadPort {

    public void saveTrainingInstance(Collection<Box> boxes, String persistenceInstanceName){


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

    @Override
    public Collection<Box> loadTrainingInstance(String persistenceInstanceName) {
        return null;
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
