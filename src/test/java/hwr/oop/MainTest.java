package hwr.oop;

import org.junit.jupiter.api.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MyAppTest {
    @Test
    void getEnvironmentVariablesTest() {
        Program testEnvProgram = new Program();
        String[] env = testEnvProgram.getEnvironmentVariables();
        if (env == null) {
            System.out.println("No environment variables found");
        } else {
            for (String envVar : env) {
                System.out.format("%s=%s%n",
                        envVar,
                        System.getenv(envVar));
            }
        }
    }

    @Test
    void setEnvironmentVariablesTest() {
        Program testEnvProgram = new Program();
        testEnvProgram.setEnvironmentVariables("data.json", "MyList");
        String[] env = testEnvProgram.getEnvironmentVariables();
        assertThat(env).contains("data.json");
        assertThat(env).contains("MyList");
    }
    @Test
    void testWelcome() throws IOException {
        // Redirect standard input and output streams to memory buffers
        InputStream sysInBackup = System.in;
        PrintStream sysOutBackup = System.out;

        Program testEnvProgram = new Program();
        String[] env = testEnvProgram.getEnvironmentVariables();

        try {
            String userInput = "MyList\n" + "" +"\n" + "data.json\n";
            System.setIn(new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outBuffer));

            List toDoList = Main.welcome();
            // Check the program output
            String expectedOutput;
            if (env == null) {
                expectedOutput = "Welcome To Getting Things Done \uD83D\uDE80\n" +
                        "Looks Like it is your first time using this program.\n" +
                        "Lets set you up first.\n" +
                        "Please enter a name for your list\n" +
                        "> " +
                        "Please provide a filePath to an existing .json file to Load your list from.\n" +
                        "If you don't have one press enter to create specify your path.\n" +
                        "> " +
                        "Please enter your a path to a file to save your list to.\n" +
                        "> ";


            } else {

                expectedOutput = "Welcome To Getting Things Done \uD83D\uDE80\n";
            }

            String actualOutput = outBuffer.toString();
            assertEquals(expectedOutput, actualOutput);

        } finally {
            // Restore standard input and output streams
            System.setIn(sysInBackup);
            System.setOut(sysOutBackup);
        }
    }
}
