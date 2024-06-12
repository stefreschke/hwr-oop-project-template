package hwr.oop.most_impressive_doppelkopf_experience;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class GamePersistenceTest {

  private GamePersistence gamePersistence;
  private Game game;

  @BeforeEach
  void setUp() {
    gamePersistence = new GamePersistence();
    game = new Game();
  }

  @Test
  void testSaveGameIOException() {
    String filePath = "";
    assertDoesNotThrow(() -> gamePersistence.saveGame(game, filePath));

    File file = new File(filePath);
    assertFalse(file.exists());
  }

  @Test
  void saveGameTest(@TempDir Path tempDir) {
    String filePath = tempDir.resolve("testgame.ser").toString();
    assertDoesNotThrow(() -> gamePersistence.saveGame(game, filePath));

    File file = new File(filePath);
    assertTrue(file.exists());
    assertTrue(file.length() > 0);
  }

  @Test
  void testLoadGame(@TempDir Path tempDir) throws IOException {
    String filePath = tempDir.resolve("testgame.ser").toString();
    System.out.println(filePath);


    try (FileOutputStream fileOut = new FileOutputStream(filePath);
        ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
      out.writeObject(game);
    }

    Game loadedGame = GamePersistence.loadGame(filePath);
    assertNotNull(loadedGame);
  }

  @Test
  void testLoadGameIOException(@TempDir Path tempDir) {
    String filePath = tempDir.resolve("nonexistentgame.ser").toString();

    Logger logger = Logger.getLogger(GamePersistence.class.getName());
    Level originalLevel = logger.getLevel();
    logger.setLevel(Level.OFF);

    Game loadedGame = GamePersistence.loadGame(filePath);
    assertNull(loadedGame);

    logger.setLevel(originalLevel);
  }

  @Test
  void testLoadGameClassNotFoundException(@TempDir Path tempDir) throws IOException {
    String filePath = tempDir.resolve("testgame.ser").toString();

    try (FileOutputStream fileOut = new FileOutputStream(filePath);
        ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
      out.writeObject(game);
    }

    RandomAccessFile raf = new RandomAccessFile(filePath, "rw");
    long fileLength = raf.length();
    raf.setLength(fileLength - 10);
    raf.close();

    Logger logger = Logger.getLogger(GamePersistence.class.getName());
    Level originalLevel = logger.getLevel();
    logger.setLevel(Level.OFF);

    Game loadedGame = GamePersistence.loadGame(filePath);
    assertNull(loadedGame);

    logger.setLevel(originalLevel);
  }

  @Test
  void testLoadGameClassNotFoundException() {
    // Assumes the dummy.dat file has been created and the TestDummy class is not present in the project
    String filePath = "dummy.ser";

    // Act
    Game loadedGame = GamePersistence.loadGame(filePath);

    // Assert
    assertNull(loadedGame, "Game should be null when ClassNotFoundException occurs.");
  }
}
