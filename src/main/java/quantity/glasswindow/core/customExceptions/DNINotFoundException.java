package quantity.glasswindow.core.customExceptions;

public class DNINotFoundException extends Exception{
    public DNINotFoundException(String dni) {
        super("Candidate with DNI: "+ dni + " not found.");
    }
}
