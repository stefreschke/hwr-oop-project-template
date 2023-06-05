package hwr.oop.handler;

import hwr.oop.ConsoleUserInterface;
import hwr.oop.ToDoList;

public class WrongCommandHandler {
    WrongCommandHandler() {
    }
    public static void handleUserCommand(ToDoList toDoList, ConsoleUserInterface cui, String[] args) {
        cui.say("Here is a list of all commands:\n" +
                "gtd [command] [arguments]\n" +
                "Commands:\n" +
                "  help                            -  print this help\n" +
                "  add [Item Index]                -  add a new task\n" +
                "  remove [Item Index]             -  remove a task\n" +
                "  promote [Item Index]            -  promote a task to a further state\n" +
                "  demote [Item Index]             -  demote a task to a previous state\n" +
                "  hold [Item Index]               -  put a task on hold\n" +
                "  done [Item Index]               -  mark a task as done\n" +
                "  edit [Item Index]               -  edit a task\n" +
                "  list                            -  list all tasks\n" +
                "  sort                            -  sort your tasks\n" +
                "  createBucket [Name]             -  create a bucket for tasks\n" +
                "  showBuckets                     -  show buckets for tasks\n" +
                "  renameBucket [index] [Name]     -  changes bucket name\n" +
                "  clear                           -  clear all tasks\n" +
                "  exit                            -  exit the program\n");
    }
}
