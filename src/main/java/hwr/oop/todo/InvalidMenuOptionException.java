package hwr.oop.todo;

public class InvalidMenuOptionException extends RuntimeException {
    public InvalidMenuOptionException(String message){
        super(message);
    }
}
