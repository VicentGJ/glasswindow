package quantity.glasswindow.core;

import java.util.ArrayList;

public class JobPost extends Model implements ICascadeDelete {
    private Branch branch;
    private float salary;
    private Status status;
    private String description;
    private Company company;
    private ArrayList<Interview> interviewList;
    private Scholarship scholarship;
    private Specialty specialty;

    public JobPost(String id, Branch branch, float salary, Status status, String description,
                   Company company, ArrayList<Interview> interviewList, Scholarship scholarship,
                    Specialty specialty) {
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
        Agency agency = Agency.create();
        try {
            agency.deleteObject(this.id);
        }
        catch (Exception e) {
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

    public void setSalary(float salary) {
        this.salary = salary;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public ArrayList<Interview> getInterviewList() {
        return interviewList;
    }

    public void setInterviewList(ArrayList<Interview> interviewList) {
        this.interviewList = interviewList;
    }

    public void addInterview(Interview interview){
        this.interviewList.add(interview);
    }
}
