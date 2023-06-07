package hwr.oop.cards;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CLIAdapterTest {
    @AfterEach
    public void tearDown(){
        System.setOut(System.out);
        System.setIn(System.in);
    }
    @Test
    public void canCreateCLIAdapter(){
        IOAdapter cliAdapter = new CLIAdapter();
        assertThat(cliAdapter).isNotNull();
    }
    @Test
    public void canPrintMessage(){
        IOAdapter cliAdapter = new CLIAdapter();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(outputStream);
        System.setOut(output);
        cliAdapter.printMessage("Hallo Welt!");
        String string = "Hallo Welt!";
        assertThat(outputStream.toString().trim()).isEqualTo(string);
    }
    @Test
    public void canGetResponse(){
        IOAdapter cliAdapter = new CLIAdapter();
        ByteArrayInputStream inputStream = new ByteArrayInputStream("Hallo Welt!".getBytes());
        System.setIn(inputStream);
        String string = cliAdapter.getResponse();
        assertThat(string).isEqualTo("Hallo Welt!");
    }
}
