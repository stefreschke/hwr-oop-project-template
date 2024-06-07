package hwr.oop.doppelkopf.group6.cli;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
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
      } else {
        ioExceptionBomb.fire();
        createGame();
      }
    } catch (IOException e) {
      System.err.println("IOException aufgetreten: " + e.getMessage());
    }
  }

  private void createFile() throws IOException {
    if (file.createNewFile()) {
      System.out.println("Die Datei und das Spiel " + this.gameID + " wird erstellt...");
    }
  }

  private void createGame() throws IOException {
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
      fw.write(this.gameID);
      fw.write("\n");
      System.out.println("Spiel " + this.gameID + " wird erstellt...");
    }
  }
}
