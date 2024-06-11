package hwr.oop.most_impressive_doppelkopf_experience;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;

public class GamePersistence {

    public void saveGame(Game game, String filePath){
        try (FileOutputStream fileOut = new FileOutputStream(filePath);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(game);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e, () -> "IOException occurred while loading the game from " + filePath);
        }
    }
    private static final Logger LOGGER = Logger.getLogger(GamePersistence.class.getName());

    public static Game loadGame(String filePath) {
        Game game = null;
        try (FileInputStream fileIn = new FileInputStream(filePath);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            game = (Game) in.readObject();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e, () -> "IOException occurred while loading the game from " + filePath);
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, e, () -> "ClassNotFoundException occurred while loading the game from " + filePath);
        }
        return game;
    }
}
