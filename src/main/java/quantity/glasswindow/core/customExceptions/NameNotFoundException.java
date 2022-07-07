package quantity.glasswindow.core.customExceptions;

public class NameNotFoundException extends Exception{
    public NameNotFoundException(String name) {
        super("Model with name " + "\"name\"" + " not found.");
    }
}
