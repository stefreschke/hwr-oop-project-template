package hwr.oop.budgetbook;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ApplicationTest {
    @Test
    void checkOutputOfApplication() {
        Application application = new Application();
        application.printMainScreen();
    }

    @Test
    void checkIfNumberPromptReturnsCorrectInputValue() {
        Application application = new Application();
        int input = application.createNumberPrompt("Gebe eine Zahl 2 an.");
        Assertions.assertThat(input).isEqualTo(2);
    }

    @Test
    void checkIfStringPromptReturnsCorrectInputValue() {
        Application application = new Application();
        String input = application.createStringPrompt("Gebe eine Zahl 2 als Wort in ausschließlich kleinbuchstaben an.");
        Assertions.assertThat(input).isEqualTo("zwei");
    }
}
