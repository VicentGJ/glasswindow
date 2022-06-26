import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import quantity.glasswindow.core.Agency;
import quantity.glasswindow.core.Candidate;
import quantity.glasswindow.core.customExceptions.IdNotFoundException;
import quantity.glasswindow.core.enumerations.Branch;
import quantity.glasswindow.core.enumerations.Gender;
import quantity.glasswindow.core.enumerations.Scholarship;
import quantity.glasswindow.core.enumerations.Specialty;

import static org.junit.jupiter.api.Assertions.*;

public class AgencyTest {
    private Agency a;


    @AfterEach
    void tearDown() {
        try {
            a.deleteObject("01060568481");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        this.a = null;
    }

    @Test
    void modelExists() {
        this.a = Agency.getInstance();
        try {
            Candidate carlos = new Candidate("01060568481","Carlos Daniel Vilaseca", Gender.MASCULINE,
                    "","", Scholarship.GRADE, Specialty.ENGINEER, Branch.INDUSTRY,3);
            a.insertObject(carlos);
        }catch (Exception ignored){}

        assertTrue(a.modelExists("01060568481"));
        assertFalse(a.modelExists("01060568482"));
    }

    @Test
    void getModelWithID(){
        this.a = Agency.getInstance();
        try {
            Candidate carlos = new Candidate("01060568481","Carlos Daniel Vilaseca", Gender.MASCULINE,
                    "","", Scholarship.GRADE, Specialty.ENGINEER, Branch.INDUSTRY,3);
            a.insertObject(carlos);

            assertEquals(carlos,a.getModelWithID("01060568481"));
            assertThrows(IdNotFoundException.class, (Executable)a.getModelWithID("01060568482"));
        }catch (Exception ignored){}
    }
}

