package quantity.glasswindow.core.customExceptions;

public class IdNotFoundException extends Exception{
    public IdNotFoundException(String id) {
        super(id + "ID not found");
    }
}
