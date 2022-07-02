package quantity.glasswindow.core;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import quantity.glasswindow.core.customExceptions.*;
import quantity.glasswindow.core.enumerations.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class AgencyTest {
    private Agency a;

    @BeforeEach
    void setUp(){
        a = Agency.getInstance();
       try {
           a.initTestData();
       }catch (Exception e){
           System.out.println("error during setup");
           throw new RuntimeException(e.getMessage());
       }
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
             25/7/2022 - Clark Kent - Gravy Table - jp4,
             ]
*/
    }

    @AfterEach
    void tearDown() {
        a.resetData();
    }

    //test for modelExists(String)
    @Test
    void modelExists() {
        System.out.println("---modelExists()---");
        assertTrue(a.modelExists("01060568481"));//Tonny Stark
        System.out.println("Test 1 succeed");
        assertFalse(a.modelExists("71060561482"));//none of the initialized data
        System.out.println("Test 2 succeed");
    }

    //tests for method getModelWithID(String)
    @Test
    void getModelWithID() {
        /*test cases
         * empty list
         * model to find is in 1st position
         * model to find is somewhere in the list
         * model to find is in the end of the list
         * model to find is not on list
         * */
        System.out.println("---getModelWithID()---");
        try {
            a.resetData();
            this.a = Agency.getInstance();
            //test for empty list of models
            Exception ex = assertThrows(IdNotFoundException.class, () -> a.getModelWithID("01060568482"));
            System.out.println("Test 1 succeed");
            Candidate carlos = new Candidate("01060568481", "Carlos Daniel Vilaseca", Gender.MASCULINE,
                    "", "", Scholarship.GRADE, Specialty.ENGINEER, Branch.INDUSTRY, 3);
            Candidate vicente = new Candidate("12121268489", "Vicente Samuel Garofalo", Gender.MASCULINE,
                    "", "", Scholarship.GRADE, Specialty.ENGINEER, Branch.INDUSTRY, 4);
            Candidate joseph = new Candidate("88012678561", "Joseph Woodburn", Gender.MASCULINE,
                    "", "", Scholarship.MASTER, Specialty.SCIENTIST, Branch.EDUCATION, 15);
            Candidate rupert = new Candidate("79060201801", "Ruppert Goodacre", Gender.MASCULINE, "",
                    "", Scholarship.GRADE, Specialty.ACCOUNTANT, Branch.AGRICULTURE, 20);
            //insert models to the list
            a.insertObject(carlos);
            a.insertObject(vicente);
            a.insertObject(joseph);
            a.insertObject(rupert);
            //current list: [carlos,vicente,joseph,rupert]

            //test method for a non-empty list
            assertEquals(carlos, a.getModelWithID("01060568481"));
            System.out.println("Test 2 succeed");
            //test method for a non-empty list but searching a model that is not on the list
            ex = assertThrows(IdNotFoundException.class, () -> a.getModelWithID("01060568482"));
            System.out.println("Test 3 succeed");
            assertEquals(joseph, a.getModelWithID("88012678561"));//finding a model somewhere in the list
            System.out.println("Test 4 succeed");
            assertEquals(rupert, a.getModelWithID("79060201801"));//finding a model in the end of the list
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Test
    void getAppliances(){
        System.out.println("---getAppliances()---");
        try {
            ArrayList<Candidate> appliances;
            ArrayList<Candidate> expectedResponse = new ArrayList<>();

            //test for a month were there are no interviews for AMD's jobposts
            appliances = a.getAppliances("company-006", 1);
            assertEquals(expectedResponse, appliances);//[] == []
            System.out.println("Test 1 succeed");
            //test if searching for appliances of a company, but company's id is wrong
            Exception ex =assertThrows(IdNotFoundException.class, () -> a.getAppliances("notACompanyID", 9));
            System.out.println("Test 2 succeed");

            //only one appliance in September for Flappy Table's jobposts
            appliances = a.getAppliances("company-005", 9); //Danvers has interview in september for a Flappy Touch jobpost
            expectedResponse.add((Candidate) a.getModelWithID("02061766497"));//[Carol Danvers]
            assertEquals(expectedResponse, appliances);
            System.out.println("Test 3 succeed");
            expectedResponse.clear();//[]

            //there are 2 appliances for an Avenue Studios jobposts in July.
            appliances = a.getAppliances("company-004", 8);//[Bruce Wayne, Clark Kent]
            expectedResponse.add((Candidate) a.getModelWithID("01022068706"));//bruce
            expectedResponse.add((Candidate) a.getModelWithID("01060568482"));//clark
            assertEquals(expectedResponse, appliances);
            System.out.println("Test 4 succeed");

            expectedResponse.clear();
            //there are 3 appliances for AMD's jobposts in August
            appliances = a.getAppliances("company-006", 8); //[Felicia Hardy, Tonny Stark, Bruce Banner]
            expectedResponse.add((Candidate) a.getModelWithID("01091368466"));//felicia
            expectedResponse.add((Candidate) a.getModelWithID("01060568481"));//tonny
            expectedResponse.add((Candidate) a.getModelWithID("01041266729"));//bruce
            assertEquals(expectedResponse, appliances);//test for 3 appliances for AMD's jobposts in August
            System.out.println("Test 5 succeed");
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        };
    }

    @Test
    public void insertObject(){
        System.out.println("---insertObject()---");
        a.resetData();
        this.a = Agency.getInstance();
        try {
            Candidate carlos = new Candidate("01060568481", "Carlos Daniel Vilaseca", Gender.MASCULINE,
                    "", "", Scholarship.GRADE, Specialty.ENGINEER, Branch.INDUSTRY, 3);
            a.insertObject(carlos);

            Company qISq = new Company("company-t","Quantity is Quality","Earth","",Branch.INDUSTRY,new ArrayList<>());
            a.insertObject(qISq);

            JobPost jpSoftwareDev = new JobPost("jobpost-t",Branch.SERVICES,50000, Status.OPEN,"","company-t",new ArrayList<>(),
                    Scholarship.MASTER,Specialty.ENGINEER);
            a.insertObject(jpSoftwareDev);
            Calendar c = Calendar.getInstance();
            Interview interview1 = new Interview("interview-t",new Date(c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH+1)),
                    "01060568481","company-t","jobpost-t");
            //date is set to tomorrow, so it passes the date validation
            a.insertObject(interview1);

            assertEquals(a.getCandidateList().get(0),carlos);
            System.out.println("Test 1 succeed");
            assertEquals(a.getCompanyList().get(0),qISq);
            System.out.println("Test 2 succeed");
            assertEquals(a.getJobPostList().get(0),jpSoftwareDev);
            System.out.println("Test 3 succeed");
            assertEquals(a.getInterviewList().get(0),interview1);
            System.out.println("Test 4 succeed");

        } catch (Exception e){
           throw new RuntimeException(e.getMessage());
        }

    }
}
