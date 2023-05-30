package hwr.oop.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hwr.oop.application.Project;
import hwr.oop.application.User;
import java.io.Reader;
import java.io.Writer;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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
