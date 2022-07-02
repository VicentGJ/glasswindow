package quantity.glasswindow.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import quantity.glasswindow.core.customExceptions.InvalidIDException;
import quantity.glasswindow.core.customExceptions.InvalidPhoneException;
import quantity.glasswindow.core.enumerations.Branch;
import quantity.glasswindow.core.enumerations.Gender;
import quantity.glasswindow.core.enumerations.Scholarship;
import quantity.glasswindow.core.enumerations.Specialty;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CandidateTest {
    private Candidate candidate;
    @BeforeEach
    public void setUp(){
        try {
            this.candidate = new Candidate("01060568481","Carlos Vilaseca", Gender.MASCULINE,"","72710053",
                    Scholarship.GRADE, Specialty.ACCOUNTANT, Branch.AGRICULTURE,3);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    @Test
    public void setID(){
        System.out.println("---setID()---");
        String validID = "02070668417";//11 digits - no letters - valid date
        String tooLongID = "01060568481111";// > 11 digits
        String tooShortID = "0106056848";// < 11 digits
        String withLettersID = "0106056848a";// has letters
        String invalidDateID1 = "99131268481";// month > 12
        String invalidDateID2 = "99013268481";// month=01, days > 31
        String invalidDateID3 = "99023068481";// month=02, days > 29
        String invalidDateID4 = "99043168481";// month=04, days > 30

        assertThrows(InvalidIDException.class,() -> candidate.setId(tooLongID));
        System.out.println("Test 1 succeed");
        assertThrows(InvalidIDException.class,() -> candidate.setId(tooShortID));
        System.out.println("Test 2 succeed");
        assertThrows(InvalidIDException.class,() -> candidate.setId(withLettersID));
        System.out.println("Test 3 succeed");
        assertThrows(InvalidIDException.class,() -> candidate.setId(invalidDateID1));
        System.out.println("Test 4 succeed");
        assertThrows(InvalidIDException.class,() -> candidate.setId(invalidDateID2));
        System.out.println("Test 5 succeed");
        assertThrows(InvalidIDException.class,() -> candidate.setId(invalidDateID3));
        System.out.println("Test 6 succeed");
        assertThrows(InvalidIDException.class,() -> candidate.setId(invalidDateID4));
        System.out.println("Test 7 succeed");

        try{
            candidate.setId(validID);
            System.out.println("Test 8 succeed");
        } catch (Exception e) {
            System.out.println("Test 8 Fail");
        }

    }

    @Test
    public void setPone(){
        String validPhone = "72710053";
        String tooLongPhone = "727100531";
        String tooShortPhone = "7271005";
        String withLettersPhone = "a7278005";

        assertThrows(InvalidPhoneException.class,() -> candidate.setPhone(tooLongPhone));
        System.out.println("Test 1 succeed");;
        assertThrows(InvalidPhoneException.class,() -> candidate.setPhone(tooShortPhone));
        System.out.println("Test 2 succeed");;
        assertThrows(InvalidPhoneException.class,() -> candidate.setPhone(withLettersPhone));
        System.out.println("Test 3 succeed");;

        try {
            candidate.setPhone(validPhone);
            System.out.println("Test 4 succeed");;
        } catch (InvalidPhoneException e) {
            System.out.println("Test 4 failed");;
        }

    }
}