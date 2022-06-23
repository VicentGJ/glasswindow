package quantity.glasswindow.core.customExceptions;

import java.util.Date;

public class InvalidDateException extends Exception{
    public InvalidDateException(Date date) {
        super("Invalid date to set interview: "+date.getDay()+"/"+date.getMonth()+"/"+date.getYear());
    }

}
