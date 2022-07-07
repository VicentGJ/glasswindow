package quantity.glasswindow.core.customExceptions;

import java.time.LocalDate;

public class InvalidDateException extends Exception{
    public InvalidDateException(LocalDate date) {
        super("Invalid date to set interview: "+date.getDayOfMonth()+"/"+date.getMonth()+"/"+date.getYear());
    }

}
