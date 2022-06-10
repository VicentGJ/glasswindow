package quantity.glasswindow.core;

import java.util.ArrayList;

public class JobPost extends Model {
    private Branch branch;
    private float salary;
    private Status status;
    private String description;
    private Company company;
    private ArrayList<Interview> interviewList;

    public JobPost(String id, Branch branch, float salary, Status status, String description,
                   Company company, ArrayList<Interview> interviewList) {
        super(id);
        this.setBranch(branch);
        this.setSalary(salary);
        this.setStatus(status);
        this.setDescription(description);
        this.setCompany(company);
        this.setInterviewList(interviewList);
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
}
