package quantity.glasswindow.core.customExceptions;

public class IncorrectTypeException extends Exception{
    public IncorrectTypeException(String type) {
        super("Incorrect type: " + type + ".");
    }
}
