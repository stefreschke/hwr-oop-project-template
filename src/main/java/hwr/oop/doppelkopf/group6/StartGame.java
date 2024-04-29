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

  public String getOperatingSystem() {
    return System.getProperty("os.name");
  }

  public void createGame(String gameID) {
    try {
      File file = new File("/Users/lukaskarsten/Desktop/test.txt");
      FileReader fileReader = new FileReader(file);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      FileWriter fw = new FileWriter(file, true);
      if (!file.exists()) {
        if (file.createNewFile()) {
          System.out.println("Die Datei und das Spiel " + gameID + " wird erstellt...");
        } else {
          System.out.println("Beim erstellen der Datei ist ein Fehler aufgetreten.");
        }
      } else {
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

        fw.close();
        bufferedReader.close();
        fileReader.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
