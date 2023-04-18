package hwr.oop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class List {
    public String Name;
    String json;

    public void setName(String name) {
        this.Name = name;
    }

    public void writeToJSON() {
        Gson gson = new Gson();
        this.json = gson.toJson(this);
        try {
            // später kann der User seinen Speicherort selber wählen (?)
            gson.toJson(123.45, new FileWriter("./output.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}