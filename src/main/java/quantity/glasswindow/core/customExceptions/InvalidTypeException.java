package quantity.glasswindow.core.customExceptions;

public class InvalidTypeException extends Exception{
    public InvalidTypeException(String type) {
        super("Incorrect type: " + type + ".");
    }
}
