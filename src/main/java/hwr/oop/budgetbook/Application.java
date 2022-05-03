package hwr.oop.budgetbook;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Application {
    private final int terminalLength = 150;

    public static void main() {
        System.out.println("Application started.");
    }

    public void printMainScreen() {
        System.out.println("=".repeat(terminalLength));
        System.out.println("Haushaltsbuch by Malte & Martin");
        System.out.println("Ein Java OOP-Projekt an der HWR Berlin");
        System.out.println("Studiengang Dual Informatik 2021");
        printMainScreenNavigationMenu();
        System.out.println("=".repeat(terminalLength));
    }

    public int createNumberPrompt(String message) {
        try {
            System.out.println(message);
            Scanner scanner = new Scanner(System.in);
            int input = scanner.nextInt();
            scanner.close();
            return input;
        } catch (InputMismatchException mismatchException) {
            System.out.println("Diese Eingabe war nicht gültig. Verwenden Sie eine andere oder beenden Sie die Eingabe mit 0");
            return createNumberPrompt(message);
        }
    }

    public String createStringPrompt(String message) {
        try {
            System.out.println(message);
            Scanner scanner = new Scanner(System.in);
            String input = scanner.next();
            scanner.close();
            return input;
        } catch (InputMismatchException mismatchException) {
            System.out.println("Diese Eingabe war nicht gültig. Verwenden Sie eine andere oder beenden Sie die Eingabe mit 'exit'");
            return createStringPrompt(message);
        }
    }

    private void printMainScreenNavigationMenu() {
        System.out.println("=".repeat(terminalLength));
        System.out.println("Geben Sie eine der folgenden Aktionen mit der jeweiligen Nummer ein und bestätigen mit Enter.");
        System.out.println("[1] Neuen Eintrag erstellen");
        System.out.println("[2] Alle Einträge ansehen");
    }
}
