package hwr.oop.cards;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CLIAdapterTest {
    @Test
    public void canCreateCLIAdapter(){
        IOAdapter cliAdapter = new CLIAdapter();
        assertThat(cliAdapter).isNotNull();
    }
    public void canPutString(){
        IOAdapter cliAdapter = new CLIAdapter();
        cliAdapter.printString("Hallo Welt!");

    }
}
