package hwr.oop;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;
public class Program {
    public List loadList(String fileName) {
        Gson gson = new Gson();
        String json = null;
        try (FileReader reader = new FileReader(fileName + ".json")) {
            char[] buffer = new char[1024];
            int len = reader.read(buffer);
            json = new String(buffer, 0, len);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return gson.fromJson(json, List.class);
    }
}