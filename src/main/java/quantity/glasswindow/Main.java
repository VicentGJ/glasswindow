package quantity.glasswindow;

import quantity.glasswindow.core.*;

import java.time.Month;
import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Agency agency = Agency.create();
        agency.initTestData();
        // Report 1
        try {
            Candidate candidate = (Candidate) agency.getObject("candidate-001");
            ArrayList<JobPost> qualifiedJobPostList = agency.getQualifiedJobPostList(candidate);
            for (JobPost i: qualifiedJobPostList) {
                System.out.println(i.getId() + ": " + i.getDescription());
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        // Report 2
        try {
            Company c1 = (Company) agency.getObject("company-001");
            ArrayList<Candidate> appliances = agency.getAppliances(c1, 6);
            for (Candidate i: appliances) {
                System.out.println(i.getName());
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
