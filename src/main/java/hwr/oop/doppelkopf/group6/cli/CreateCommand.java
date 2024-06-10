package hwr.oop.doppelkopf.group6.cli;

import hwr.oop.doppelkopf.group6.Deck;
import hwr.oop.doppelkopf.group6.Player;
import hwr.oop.doppelkopf.group6.persistenz.SaveToFile;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CreateCommand implements Command {
  private final IOExceptionBomb ioExceptionBomb;
  public ParseCommand parse = new ParseCommand();
  private String gameID;
  String fileName = "doppelkopf.csv";
  Path currentRelativePath = Paths.get("");
  String currentDir = currentRelativePath.toAbsolutePath().toString();
  File file = new File(currentDir + File.separator + fileName);

  public CreateCommand(IOExceptionBomb ioExceptionBomb) {
    this.ioExceptionBomb = ioExceptionBomb;
  }

  @Override
  public void execute(List<String> args) {
    try {
      this.gameID = parse.gameID(args);

      if (!file.exists()) {
        ioExceptionBomb.fire();
        createFile();
        createGame(args);
      } else {
        ioExceptionBomb.fire();
        createGame(args);
      }
    } catch (IOException e) {
      System.err.println("IOException aufgetreten: " + e.getMessage());
    }
  }

  private void createFile() throws IOException {
    if (file.createNewFile()) {
      System.out.println("Die Datei wird erstellt...");
    }
  }

  private void createGame(List<String> args) throws IOException {
    List<Player> players = parse.players(args);
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {

      String line;
      while ((line = bufferedReader.readLine()) != null) {
        String[] columns = line.split(",");
        if (columns.length > 0 && columns[0].equals(this.gameID)) {
          throw new IOException("Das Spiel existiert bereits! Probiere eine andere Spiel ID.");
        }
      }

      SaveToFile save = new SaveToFile();
      save.players(players, this.gameID);

      System.out.println("Spiel " + this.gameID + " wird erstellt...");
      PlayCommand play = new PlayCommand(IOExceptionBomb.DONT, new Deck());
      play.execute(args);
    }
  }
}
