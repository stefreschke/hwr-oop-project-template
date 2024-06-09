package hwr.oop.doppelkopf.group6.cli;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("java:S106")
public class CreateCommand implements Command {
  private final IOExceptionBomb ioExceptionBomb;
  private String gameID;
  String fileName = "doppelkopf.csv";
  Path currentRelativePath = Paths.get("");
  String currentDir = currentRelativePath.toAbsolutePath().toString();
  File file = new File(currentDir + File.separator + fileName);

  public CreateCommand(IOExceptionBomb ioExceptionBomb) {
    this.ioExceptionBomb = ioExceptionBomb;
  }

  @Override
  public String parseGameID(List<String> args) {
    if (args.get(1) == null || args.get(1).isBlank() || !args.get(1).matches("\\d+")) {
      System.out.println(
          "Game ID: " + "\"" + args.get(1) + "\"" + " is not a valid game ID. Please use numbers!");
      return null;
    }
    return args.get(1);
  }

  @Override
  public void execute(List<String> arguments) {
    this.gameID = parseGameID(arguments);
    if (this.gameID == null) {
      return;
    }

    try {
      if (!file.exists()) {
        ioExceptionBomb.fire();
        createFile();
        createGame(parsePlayer(arguments));
      } else {
        ioExceptionBomb.fire();
        createGame(parsePlayer(arguments));
      }
    } catch (IOException e) {
      System.err.println("IOException aufgetreten: " + e.getMessage());
    }
  }

  public List<String> parsePlayer(List<String> args) throws IOException {
    List<String> players = new ArrayList<>();
    String regularExpression = "[a-zA-Z]+";
    if (args.size() != 7 || (args.get(3).isBlank() || !args.get(3).matches(regularExpression)) || (args.get(4).isBlank() || !args.get(4).matches(regularExpression)) || (args.get(5).isBlank() || !args.get(5).matches(regularExpression)) || (args.get(6).isBlank() || !args.get(6).matches(regularExpression))) {
      throw new IOException("Something went wrong regarding the Players.");
    }
    players.add(args.get(3));
    players.add(args.get(4));
    players.add(args.get(5));
    players.add(args.get(6));
    return players;
  }

  private void createFile() throws IOException {
    if (file.createNewFile()) {
      System.out.println("Die Datei wird erstellt...");
    }
  }

  private void createGame(List<String> players) throws IOException {
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
         FileWriter fw = new FileWriter(file, true)) {

      String line;
      while ((line = bufferedReader.readLine()) != null) {
        String[] columns = line.split(",");
        if (columns.length > 0 && columns[0].equals(this.gameID)) {
          System.out.println("Das Spiel existiert bereits, wähle eine andere ID für das Spiel!");
          return;
        }
      }

      StringBuilder newLine = new StringBuilder();
      newLine.append(this.gameID);
      for (String player : players) {
        newLine.append(",");
        newLine.append(player);
        newLine.append(",");
        newLine.append("cards: ");
      }
      newLine.append("\n");

      // Schreiben der neuen Zeile in die Datei
      fw.write(newLine.toString());
      System.out.println("Spiel " + this.gameID + " wird erstellt...");
    }
  }
}
