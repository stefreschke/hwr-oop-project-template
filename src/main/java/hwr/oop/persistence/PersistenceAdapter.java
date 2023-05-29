package hwr.oop.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.Reader;
import java.io.Writer;
import java.time.LocalDateTime;

public class PersistenceAdapter implements LoadPort, SavePort {
    @Override
    public AppData loadData(Reader fileReader) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();
        return gson.fromJson(fileReader, AppData.class);
    }

    @Override
    public void saveData(AppData appData, Writer fileWriter) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .enableComplexMapKeySerialization()
                .create();
        gson.toJson(appData, fileWriter);
    }
}
