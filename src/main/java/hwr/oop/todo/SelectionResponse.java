package hwr.oop.todo;

public class SelectionResponse {
    private boolean isSuccess;
    private String message;

    public SelectionResponse(String message, boolean isSuccess){
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public static SelectionResponse success(String message){
        return new SelectionResponse(message, true);
    }

    public static SelectionResponse error(String message){
        return new SelectionResponse(message, false);
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getMessage() {
        return message;
    }
}
