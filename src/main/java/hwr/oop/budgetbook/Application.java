package hwr.oop.budgetbook;

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

    private void printMainScreenNavigationMenu() {
        System.out.println("=".repeat(terminalLength));
        System.out.println("Geben Sie eine der folgenden Aktionen mit der jeweiligen Nummer ein und bestätigen mit Enter.");
        System.out.println("[1] Neuen Eintrag erstellen");
        System.out.println("[2] Alle Einträge ansehen");
    }
}
