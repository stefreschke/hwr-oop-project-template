package hwr.oop.most_impressive_doppelkopf_experience;

import java.io.*;

public class GamePersistence {

    public void saveGame(Game game, String filePath) {
        try (FileOutputStream fileOut = new FileOutputStream(filePath);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(game);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Game loadGame(String filePath) {
        Game game = null;
        try (FileInputStream fileIn = new FileInputStream(filePath);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            game = (Game) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return game;
    }
}
