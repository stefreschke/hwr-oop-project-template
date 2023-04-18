package hwr.oop;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class Program {
    public List loadList(String fileName) {
        Gson gson = new Gson();
        List list = new List();
        try {
            JsonReader reader = new JsonReader(new FileReader(fileName));
            list = gson.fromJson(reader, List.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
}
