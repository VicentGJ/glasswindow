package quantity.glasswindow;

import quantity.glasswindow.core.*;

import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        Agency agency = Agency.create();
        agency.initTestData();
        // Please ignore exception handling it´s not a requirement for this delivery,
        // but we were already playing and learning about it.
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
            ArrayList<Candidate> appliances = agency.getAppliances(c1.getId(), 6);
            for (Candidate i: appliances) {
                System.out.println(i.getName());
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        // Report 3
        ArrayList<ArrayList<Interview>> monthInterviews = agency.getInterviewsMonth("company-001", 6);
        System.out.println(String.valueOf(monthInterviews));
        // Report 4
        try {
            Company c1 = (Company) agency.getObject("company-001");
            ArrayList<ArrayList<Interview>> result = agency.getCompanyInterviews(c1);
            System.out.println(String.valueOf(result));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
