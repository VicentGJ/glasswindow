package quantity.glasswindow.core;

import java.util.ArrayList;

public class Company extends Model {
    private String name;
    private String address;
    private ArrayList<Integer> phoneList;
    private Branch sector;
    private ArrayList<JobPost> jobPostList;

    public Company(String id, String name, String address, ArrayList<Integer> phoneList, Branch sector, ArrayList<JobPost> jobPostList) {
        super(id);
        this.setAddress(address);
        this.setName(name);
        this.setSector(sector);
        this.setPhoneList(phoneList);
        this.setJobPostList(jobPostList);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<Integer> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(ArrayList<Integer> phoneList) {
        this.phoneList = phoneList;
    }

    public Branch getSector() {
        return sector;
    }

    public void setSector(Branch sector) {
        this.sector = sector;
    }

    public ArrayList<JobPost> getJobPostList() {
        return jobPostList;
    }

    public void addJobPostToList(JobPost jobPost){
        jobPostList.add(jobPost);
    }
    public void setJobPostList(ArrayList<JobPost> jobPostList) {
        this.jobPostList = jobPostList;
    }
}
