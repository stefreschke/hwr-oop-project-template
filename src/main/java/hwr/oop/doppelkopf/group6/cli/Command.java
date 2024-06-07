package hwr.oop.doppelkopf.group6.cli;

import java.util.List;

public interface Command {
  void execute(List<String> args);

  String parseGameID(List<String> args);
}
