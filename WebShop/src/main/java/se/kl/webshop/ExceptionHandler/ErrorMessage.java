package se.kl.webshop.ExceptionHandler;

public class ErrorMessage {

    private String message;
    private int status;

    //Constructor
    public ErrorMessage(String message, int status) {
        this.message = message;
        this.status = status;
    }

    //Getters
    public String getMessage() {
        return message;
    }
    public int getStatus() {
        return status;
    }

    //Setters
    public void setMessage(String message) {
        this.message = message;
    }
    public void setStatus(int status) {
        this.status = status;
    }
}
