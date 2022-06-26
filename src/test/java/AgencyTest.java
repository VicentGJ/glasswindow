import quantity.glasswindow.core.Agency;
import quantity.glasswindow.core.customExceptions.*;

import static org.junit.jupiter.api.Assertions.*;

public class AgencyTest {
    private Agency a;
    @org.junit.jupiter.api.BeforeEach
    void setUp() throws InvalidPhoneException, InvalidNameException, InvalidDateException, InvalidSalaryException, ModelNotFoundException, InvalidIDException, DuplicatedIDException, InvalidYearsOfExpException {
        this.a = Agency.getInstance();
        a.initTestData();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        this.a = null;
    }


    @org.junit.jupiter.api.Test
    void modelExists() {
        assertTrue(a.modelExists("01060568481"));
        assertTrue(a.modelExists("01060568482"));
        assertFalse(a.modelExists("01060568484"));
    }
}

