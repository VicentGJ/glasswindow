package quantity.glasswindow.core.customExceptions;

import java.time.LocalDate;

public class InvalidDateException extends Exception{
    public InvalidDateException(LocalDate date){
        super("Invalid date to set interview: "+date.getYear()+"/"+date.getMonth()+"/"+date.getYear());
    }
    public InvalidDateException(LocalDate date, String reason) {
        super("Invalid date to set interview: "+date.getYear()+"/"+date.getMonth()+"/"+date.getYear()+" "+reason);
    }

}
