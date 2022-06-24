package quantity.glasswindow.core;

import quantity.glasswindow.core.customExceptions.*;
import quantity.glasswindow.core.enumerations.Branch;
import quantity.glasswindow.core.enumerations.Scholarship;
import quantity.glasswindow.core.enumerations.Specialty;
import quantity.glasswindow.core.enumerations.Status;

import java.util.ArrayList;

public class JobPost extends Model implements ICascadeDelete {
    private Branch branch;
    private float salary;
    private Status status;
    private String description;
    private String company;
    private ArrayList<String> interviewList;
    private Scholarship scholarship;
    private Specialty specialty;

    public JobPost(String id, Branch branch, float salary, Status status, String description,
                   String company, ArrayList<String> interviewList, Scholarship scholarship,
                    Specialty specialty)
            throws InvalidIDException, InvalidSalaryException, DuplicatedIDException, ModelNotFoundException {
        super(id);
        this.setBranch(branch);
        this.setSalary(salary);
        this.setStatus(status);
        this.setDescription(description);
        this.setCompany(company);
        this.setInterviewList(interviewList);
        this.setScholarship(scholarship);
        this.setSpecialty(specialty);
    }

    @Override
    public void deleteNode() {
        Agency agency = Agency.getInstance();
        try {
            agency.deleteObject(this.id);
        }
        catch (IdNotFoundException e) {
            System.exit(1);
        }
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public Scholarship getScholarship() {
        return scholarship;
    }

    public void setScholarship(Scholarship scholarship) {
        this.scholarship = scholarship;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) throws InvalidSalaryException {
        if(salary > 0)
            this.salary = salary;
        else throw new InvalidSalaryException(salary);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) throws ModelNotFoundException {
        if(Agency.getInstance().modelExists(company))
            this.company = company;
        else throw new ModelNotFoundException(company);
    }

    public ArrayList<String> getInterviewList() {
        return interviewList;
    }

    public void setInterviewList(ArrayList<String> interviewList) {
        this.interviewList = interviewList;
    }

    public void addInterview(String interview){
        this.interviewList.add(interview);
    }
}
