package quantity.glasswindow.core.customExceptions;

public class InvalidNameException extends Exception{
    public InvalidNameException(String type) {
        super("Invalid name for "+ type +" name cant be empty or be only spaces");
    }
}
