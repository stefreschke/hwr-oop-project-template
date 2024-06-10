package hwr.oop.doppelkopf.group6.persistenz;

import hwr.oop.doppelkopf.group6.Player;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class SaveToFile {
  String fileName = "doppelkopf.csv";
  Path currentRelativePath = Paths.get("");
  String currentDir = currentRelativePath.toAbsolutePath().toString();
  File file = new File(currentDir + File.separator + fileName);

  public void players(List<Player> players, String gameID) throws IOException {
    try (FileWriter fw = new FileWriter(file, true)) {
      StringBuilder newLine = new StringBuilder();
      newLine.append(gameID);
      for (Player player : players) {
        newLine.append(",");
        newLine.append(player.getName());
        newLine.append(",");
        newLine.append("cards: ");
      }
      newLine.append("\n");

      fw.write(newLine.toString());
    }
  }
}
