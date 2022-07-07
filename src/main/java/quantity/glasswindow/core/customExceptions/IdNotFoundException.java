package quantity.glasswindow.core.customExceptions;

public class IdNotFoundException extends Exception{
    public IdNotFoundException(String id) {
        super("ID "+id + " not found");
    }
}
