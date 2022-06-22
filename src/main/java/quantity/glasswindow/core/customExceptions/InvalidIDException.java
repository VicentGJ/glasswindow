package quantity.glasswindow.core.customExceptions;

public class InvalidIDException extends Exception {
    public InvalidIDException(String id,String reason) {
        super("Invalid id:" + id +" "+ reason);
    }
}
