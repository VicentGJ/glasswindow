import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import quantity.glasswindow.core.Agency;
import quantity.glasswindow.core.Candidate;
import quantity.glasswindow.core.customExceptions.*;
import quantity.glasswindow.core.enumerations.Branch;
import quantity.glasswindow.core.enumerations.Gender;
import quantity.glasswindow.core.enumerations.Scholarship;
import quantity.glasswindow.core.enumerations.Specialty;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AgencyTest {
    private Agency a;

    @BeforeEach
    void getInstance(){
        a = Agency.getInstance();
    }

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

    @Test
    void getAppliances(){
        try {
            a.initTestData();
            /* ---RESUMED INITIALIZED DATA---
            candidates: [
             Bruce Banner - 01041266729,
             Tonny Stark - 01060568481,
             Clark Kent - 01060568482,
             Bruce Wayne - 01022068706,
             Carol Danvers - 02061766497,
             Felicia Hardy - 01091368466
             ]
            companies:[
             Last Quarter - company-001,
             Avenue Studios - company-002,
             Icy Mountain - company-003,
             Gravy Table - company-004,
             Flappy Touch - company-005,
             AMD - company-006
             ]
            jobposts: [
             jp1 - Last Quarter,
             jp2 - Avenue Studios,
             jp3 - Icy Mountain,
             jp4 - Gravy Table,
             jp5 - Flappy Touch,
             jp6 - AMD
             ]

            interviews: [
             30/6/2022 - Bruce Banner - Last Quarter - jp1,
             6/7/2022 - Tonny Stark - Avenue Studios - jp2,
             14/11/2022 - Clark Kent - Icy Mountain - jp3,
             15/8/2022 - Bruce Wayne - Gravy Table - jp4,
             16/9/2022 - Carol Danvers - Flappy Touch - jp5,
             17/8/2022 - Felicia hardy - AMD - jp6,
             10/8/2022 - Tonny Stark - AMD - jp6,
             1/8/2022 - Bruce Banner - AMD - jp6,
             25/7/2022 - Clark Kent - Avenue Studios - jp2,
             ]
*/
            ArrayList<Candidate> appliances;
            ArrayList<Candidate> expectedResponse = new ArrayList<>();

            //skip the loop
            //test for a month were there are no interviews for AMD's jobposts
            appliances = a.getAppliances("company-006",1);
            assertEquals(expectedResponse,appliances);//[] == []

            //test if searching for appliances of a company, but company's id is wrong
            assertThrows(IdNotFoundException.class, (Executable) a.getAppliances("notACompanyID",9));

            //loop once
            //only one appliance in September for Flappy Table's jobposts
            appliances = a.getAppliances("company-005", 9); //Danvers has interview in september for a Flappy Touch jobpost
            expectedResponse.add((Candidate) a.getModelWithID("02061766497"));//[Carol Danvers]
            assertEquals(expectedResponse,appliances);
            expectedResponse.clear();//[]

            //loop twice
            //there are 2 appliances for an Avenue Studios jobposts in July.
            appliances = a.getAppliances("company-002", 7);//[Tonny Stark, Clark Kent]
            expectedResponse.add((Candidate)a.getModelWithID("01060568481"));//tonny
            expectedResponse.add((Candidate)a.getModelWithID("01060568482"));//clark
            assertEquals(expectedResponse,appliances);

            //loop n-m times
            //there are 3 appliances for AMD's jobposts in August
            appliances = a.getAppliances("company-006", 8); //[Felicia Hardy, Tonny Stark, Bruce Banner]
            expectedResponse.add((Candidate) a.getModelWithID("01091368466"));//felicia
            expectedResponse.add((Candidate) a.getModelWithID("01060568481"));//tonny
            expectedResponse.add((Candidate) a.getModelWithID("01041266729"));//bruce
            assertEquals(expectedResponse,appliances);//test for 3 appliances for AMD's jobposts in August

        } catch (InvalidIDException | InvalidDateException | InvalidNameException | InvalidSalaryException |
                 DuplicatedIDException | InvalidPhoneException | InvalidYearsOfExpException | ModelNotFoundException |
                 IdNotFoundException ignored) {}
    }
}

