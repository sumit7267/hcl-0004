package Assignment2CoreJava;


public class ItemNotFoundException extends LibraryException {

    public ItemNotFoundException(String message) {
        super(message);
    }

    public ItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}