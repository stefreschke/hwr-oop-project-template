package hwr.oop.dialogTests;

import hwr.oop.ConsoleUserInterface;
import hwr.oop.Program;
import hwr.oop.ToDoList;
import hwr.oop.dialog.WelcomeDialog;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
class WelcomeDialogTest {
    @Test
    void testEnvLoadSuccessful() {
        String[] testEnv = {"", ""};
        Program program = new Program();
        PrintStream out = new PrintStream(new ByteArrayOutputStream());
        ConsoleUserInterface cui = new ConsoleUserInterface(out, System.in);
        ToDoList toDoList = new WelcomeDialog(cui, "welcomeTestEnvLoadSuccessful.csv").envLoadSuccessful(testEnv, program);
        assertThat(toDoList).isNotNull();
        assertThat(toDoList.getItems()).isEmpty();
    }
    @Test
    void testMakeNewFile() throws IOException {
        String listName = "testList";
        String filePath = "testFile";
        Program program = new Program();
        String setupFile = "welcomeTestNewFileSetup.csv";
        PrintStream out = new PrintStream(new ByteArrayOutputStream());
        ByteArrayInputStream in = new ByteArrayInputStream((filePath + "\n" + listName).getBytes(StandardCharsets.UTF_8));
        ToDoList toDoList = new WelcomeDialog(new ConsoleUserInterface(out, in), setupFile).makeNewFile(listName, program);
        assertThat(program.getEnvironmentVariables(setupFile)).containsExactly(filePath, listName);
        assertThat(toDoList).isNotNull();
        assertThat(toDoList.getItems()).isEmpty();
    }
    @Test
    void testTakeExistingFile() throws WelcomeDialog.CannotLaunchSetupException {
        String filePath = "testFile";
        String listName = "testList";
        Program program = new Program();
        String setupFile = "welcomeTestExistingFileSetup.csv";
        PrintStream out = new PrintStream(new ByteArrayOutputStream());
        ToDoList toDoList = new WelcomeDialog(new ConsoleUserInterface(out, System.in), setupFile).takeExistingFile(filePath, listName, program);
        assertThat(program.getEnvironmentVariables(setupFile)).containsExactly(filePath, listName);
        assertThat(toDoList).isNotNull();
        assertThat(toDoList.getItems()).isEmpty();
    }
    @Test
    void testFirstTimeSetup() throws IOException, WelcomeDialog.CannotLaunchSetupException {
        String setupFile = "getEnvTestSetup.csv";
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Program program = new Program();
        String userInput = "testList\nwelcomeDialogTest.json\n";
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(out), new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
        ToDoList toDoList = new WelcomeDialog(cui, setupFile).firstTimeSetup(program);
        assertThat(out).hasToString(
                "Looks Like it is your first time using this program.\n" +
                        "Lets set you up first.\n" +
                        "Please enter a name for your list\n" +
                        "> \n" +
                        "Please provide a filePath to an existing .json file to Load your list from.\n" +
                        "If you don't have one press enter to create specify your path.\n" +
                        "> \n" +
                        "Welcome To Getting Things Done \uD83D\uDE80\n"
                );
        assertThat(toDoList).isNotNull();
        assertThat(toDoList.getItems()).isEmpty();
    }
    @Test
    void testStart() throws WelcomeDialog.CannotLaunchSetupException {
        String setupFile = "getEnvTestSetup.csv";
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ConsoleUserInterface cui = new ConsoleUserInterface(new PrintStream(out), System.in);
        ToDoList toDoList = new WelcomeDialog(cui, setupFile).start();
        assertThat(out.toString()).contains("Welcome To Getting Things Done 🚀");
        assertThat(toDoList).isNotNull();
        assertThat(toDoList.getItems()).isEmpty();
    }
}
