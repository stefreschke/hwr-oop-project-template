package hwr.oop.doppelkopf.group6.persistenz;

import hwr.oop.doppelkopf.group6.Player;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
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
      }
      newLine.append("\n");

      fw.write(newLine.toString());
    }
  }


  public void cards(List<Player> players, String gameID) throws IOException {
    List<String> fileContent = readFile();

    List<String> updatedContent = updateFileContent(fileContent, players, gameID);

    writeFile(updatedContent);
  }

  private List<String> readFile() throws IOException {
    List<String> fileContent = new ArrayList<>();
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        fileContent.add(line);
      }
    }
    return fileContent;
  }

  public List<String> updateFileContent(List<String> fileContent, List<Player> players, String gameID) {
    List<String> updatedContent = new ArrayList<>();
    for (String line : fileContent) {
      List<String> columns = Arrays.asList(line.split(","));
      if (!columns.isEmpty() && columns.getFirst().equals(gameID)) {
        updatedContent.add(updateLine(line, players));
      } else {
        updatedContent.add(line);
      }
    }
    return updatedContent;
  }


  public String updateLine(String line, List<Player> players) {
    for (Player player : players) {
      String playerName = player.getName();
      String playerCards = formatPlayerCards(player.getOwnCards().toString());

      if (line.contains(playerName)) {
        line = line.replace(playerName, playerName + "," + playerCards);
      }
    }
    return line;
  }

  public String formatPlayerCards(String playerCards) {
    if (playerCards.startsWith("[") && playerCards.endsWith("]")) {
      return playerCards.substring(1, playerCards.length() - 1);
    }
    return playerCards;
  }

  private void writeFile(List<String> fileContent) throws IOException {
    try (FileWriter fw = new FileWriter(file)) {
      for (String updatedLine : fileContent) {
        fw.write(updatedLine + System.lineSeparator());
      }
    }
  }
}
