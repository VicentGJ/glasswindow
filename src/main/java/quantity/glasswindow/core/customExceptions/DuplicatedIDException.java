package quantity.glasswindow.core.customExceptions;

public class DuplicatedIDException extends Exception{
    public DuplicatedIDException(String id) {
        super("A model with id: "+ id + "already exists.");
    }
}
