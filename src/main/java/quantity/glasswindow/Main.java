package quantity.glasswindow;

import quantity.glasswindow.core.*;
import quantity.glasswindow.core.customExceptions.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Agency agency = Agency.getInstance();
        try {
            agency.initTestData();
        }catch (InvalidIDException | InvalidDateException | InvalidNameException | InvalidSalaryException | InvalidTypeException|
                DuplicatedIDException | InvalidPhoneException | InvalidYearsOfExpException | ModelNotFoundException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
        // Please ignore exception handling it´s not a requirement for this delivery, TODO:remove this comment
        // but we were already playing and learning about it.
        // Report 1
        try {
            Candidate candidate = (Candidate) agency.getObject("candidate-1");
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
        try {//TODO: check on this
            Company c1 = (Company) agency.getObject("company-1");
            ArrayList<Candidate> appliances = agency.getAppliances(c1.getId(), LocalDate.of(2022, 8, 24));
            for (Candidate i: appliances) {
                System.out.println(i.getName());
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        // Report 3
        ArrayList<ArrayList<Interview>> monthInterviews = agency.getInterviewsMonth("company-1", 7);
        System.out.println(monthInterviews);
        // Report 4
        try {
            Company c1 = (Company) agency.getObject("company-1");
            ArrayList<ArrayList<Interview>> result = agency.getCompanyInterviews(c1);
            System.out.println(result);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
