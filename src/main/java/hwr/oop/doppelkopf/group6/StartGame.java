package hwr.oop.doppelkopf.group6;

import java.io.*;

class StartGame {

  @SuppressWarnings("java:S106")
  public static void main(String[] args) {
    if (args[0].equals("create")) {
      StartGame start = new StartGame();
      start.createGame(args[1]);
    }
  }

  public void createGame(String gameID) {
    String fileName = "/Users/lukaskarsten/Desktop/test.txt";
    File file = new File(fileName);

    try {
      if (!file.exists()) {
        if (file.createNewFile()) {
          System.out.println("Die Datei und das Spiel " + gameID + " wird erstellt...");
        }
      } else {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
             FileWriter fw = new FileWriter(file, true)) {
          String zeile;
          while ((zeile = bufferedReader.readLine()) != null) {
            String[] woerter = zeile.split("\\s+");
            if (woerter.length > 0 && woerter[0].equals(gameID)) {
              System.out.println("Das Spiel existiert bereits, wähle eine andere ID für das Spiel!");
              return;
            }
          }
          fw.write(gameID);
          fw.write("\n");
          System.out.println("Spiel " + gameID + " wird erstellt...");
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
