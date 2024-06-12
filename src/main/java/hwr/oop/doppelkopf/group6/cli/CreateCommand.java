package hwr.oop.doppelkopf.group6.cli;

import hwr.oop.doppelkopf.group6.Player;
import hwr.oop.doppelkopf.group6.persistence.SaveToFile;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CreateCommand implements Command {
  private final PrintStream out;
  private final IOExceptionBomb ioExceptionBomb;
  private String gameID;
  public final ParseCommand parse;
  String fileName = "doppelkopf.csv";
  Path currentRelativePath = Paths.get("");
  String currentDir = currentRelativePath.toAbsolutePath().toString();
  File file = new File(currentDir + File.separator + fileName);

  public CreateCommand(OutputStream out, IOExceptionBomb ioExceptionBomb) {
    this.out = new PrintStream(out);
    this.ioExceptionBomb = ioExceptionBomb;
    this.parse = new ParseCommand(out);
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
      out.println("IOException aufgetreten: " + e.getMessage());
    }
  }

  private void createFile() throws IOException {
    if (file.createNewFile()) {
      out.println("Die Datei wird erstellt...");
    }
  }

  private void createGame(List<String> args) throws IOException {
    List<Player> players = parse.players(args);
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {

      String line;
      while ((line = bufferedReader.readLine()) != null) {
        List<String> columns = List.of(line.split(","));
        if (!columns.isEmpty() && columns.get(0).equals(this.gameID)) {
          throw new IOException("Das Spiel existiert bereits! Probiere eine andere Spiel ID.");
        }
      }
      SaveToFile save = new SaveToFile();
      save.players(players, this.gameID);

      out.println("Spiel " + this.gameID + " wird erstellt...");
    }
  }
}
