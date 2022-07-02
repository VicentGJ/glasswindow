package quantity.glasswindow.core;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import quantity.glasswindow.core.customExceptions.InvalidSalaryException;
import quantity.glasswindow.core.enumerations.Branch;
import quantity.glasswindow.core.enumerations.Scholarship;
import quantity.glasswindow.core.enumerations.Specialty;
import quantity.glasswindow.core.enumerations.Status;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JobPostTest {
    private JobPost jp;
    private Agency a;
    @BeforeEach
    void setUp(){
        try {
            a = Agency.getInstance();
            Company c = new Company("cid","qISq","","",Branch.INDUSTRY,
                    new ArrayList<>());
            a.insertObject(c);//so jobpost constructor passes validation
            jp = new JobPost("jpbpost-t", Branch.INDUSTRY,200, Status.APPLICATION_ACTIVE,"",
                    "cid",new ArrayList<>(), Scholarship.MASTER, Specialty.ENGINEER);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    @AfterEach
    void tearDown(){
        a.resetData();
    }

    @Test
    void setSalary() {
        System.out.println("---setSalary()---");
        float validSalary = 1;
        float invalidSalary1 = 0;
        float invalidSalary2 = -1;

        assertThrows(InvalidSalaryException.class, () -> jp.setSalary(invalidSalary1));
        System.out.println("Test 1 succeed");
        assertThrows(InvalidSalaryException.class, () -> jp.setSalary(invalidSalary2));
        System.out.println("Test 2 succeed");

        try{
            jp.setSalary(validSalary);
            System.out.println("Test 3 succeed");
        }catch (Exception e){
            System.out.println("Test 3 fail");
            throw new RuntimeException(e.getMessage());
        }
    }
}