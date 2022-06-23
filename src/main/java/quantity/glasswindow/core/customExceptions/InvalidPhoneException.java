package quantity.glasswindow.core.customExceptions;

public class InvalidPhoneException extends Exception{
    public InvalidPhoneException(String phone) {
        super("Invalid phone number: "+ phone);
    }
}
