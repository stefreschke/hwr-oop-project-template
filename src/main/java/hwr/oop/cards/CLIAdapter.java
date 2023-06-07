package hwr.oop.cards;

import java.util.Scanner;

public class CLIAdapter implements IOAdapter {
    @Override
    public void printMessage(String message) {
        System.out.println(message);
    }

    @Override
    public String getResponse(){
        return new Scanner(System.in).nextLine();
    }
}
