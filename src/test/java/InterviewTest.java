import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InterviewTest {
    @Test
    public void validateDate(){
        Date date_previous = new Date(2022,6,25);
        Date date_equals = new Date(2022,6,27);
        Date date_after = new Date(2022,6,30);
        Calendar today_calendar = Calendar.getInstance();
        Date today_date = new Date(today_calendar.get(Calendar.YEAR),today_calendar.get(Calendar.MONTH)+1,
                today_calendar.get(Calendar.DAY_OF_MONTH));

        System.out.println("today: "+today_calendar.get(Calendar.YEAR)+"/"+(today_calendar.get(Calendar.MONTH)+1)+"/"+
                today_calendar.get(Calendar.DAY_OF_MONTH));

        System.out.println("date previous: "+ date_previous.getYear()+"/"+date_previous.getMonth()+"/"+date_previous.getDate());
        System.out.println("date today: "+ date_equals.getYear()+"/"+date_equals.getMonth()+"/"+date_equals.getDate());
        System.out.println("date after: "+ date_after.getYear()+"/"+date_after.getMonth()+"/"+date_after.getDate());

        assertTrue(date_after.after(today_date));
        assertFalse(date_previous.after(today_date));
        assertFalse(date_equals.after(today_date));
    }
}