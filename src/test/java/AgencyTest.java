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


/*
    @AfterEach
    void tearDown() {
        try {
            a.deleteObject("01060568481");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        this.a = null;
    }
*/

    //test for modelExists(String)
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

    //tests for method getModelWithID(String)
    @Test
    void getModelWithID(){
        /*test cases
        * empty list
        * model to find is in 1st position
        * model to find is somewhere in the list
        * model to find is in the end of the list
        * model to find is not on list
        * */
        this.a = Agency.getInstance();
        try {
            assertThrows(IdNotFoundException.class, (Executable)a.getModelWithID("01060568482"));//test for empty list of models
            Candidate carlos = new Candidate("01060568481","Carlos Daniel Vilaseca", Gender.MASCULINE,
                    "","", Scholarship.GRADE, Specialty.ENGINEER, Branch.INDUSTRY,3);
            Candidate vicente = new Candidate("12121268489","Vicente Samuel Garofalo", Gender.MASCULINE,
                    "","", Scholarship.GRADE, Specialty.ENGINEER, Branch.INDUSTRY,4);
            Candidate joseph = new Candidate("88012678561","Joseph Woodburn", Gender.MASCULINE,
                    "","", Scholarship.MASTER, Specialty.SCIENTIST, Branch.EDUCATION,15);

            //insert models to the list
            a.insertObject(carlos);
            a.insertObject(vicente);
            a.insertObject(joseph);
            //current list: [carlos,vicente,joseph]

            //test method for a non-empty list
            assertEquals(carlos,a.getModelWithID("01060568481"));
            //test method for a non-empty list but searching a model that is not on the list
            assertThrows(IdNotFoundException.class, (Executable)a.getModelWithID("01060568482"));
            assertEquals(vicente,a.getModelWithID("12121268489"));//finding a model somewhere in the list
            assertEquals(joseph,a.getModelWithID("98012678561"));//finding a model in the end of the list

        }catch (Exception ignored){}
    }
}

