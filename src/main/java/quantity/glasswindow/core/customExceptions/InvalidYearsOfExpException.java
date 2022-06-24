package quantity.glasswindow.core.customExceptions;

public class InvalidYearsOfExpException extends Exception{
    public InvalidYearsOfExpException(int years) {
        super("Invalid years of experience: "+years+". Must be greater than 0.");
    }
}
