package quantity.glasswindow.core.customExceptions;
public class InvalidSalaryException extends Exception{
    public InvalidSalaryException(float salary) {
        super("Invalid salary: $" + salary);
    }
}
