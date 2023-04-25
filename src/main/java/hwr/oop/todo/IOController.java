package hwr.oop.todo;

import java.io.InputStream;
import java.util.Scanner;

public class IOController {

    Scanner scanner;

    public IOController (InputStream in){
        this.scanner = new Scanner(in);
    }

    public String getInput(){
        return this.scanner.nextLine();
    }

}
