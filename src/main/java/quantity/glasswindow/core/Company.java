package quantity.glasswindow.core;

import java.util.ArrayList;

public class Company extends Model implements ICascadeDelete {
    private String name;
    private String address;
    private ArrayList<String> phoneList;
    private Branch sector;
    private ArrayList<JobPost> jobPostList;

    public Company(String id, String name, String address, ArrayList<String> phoneList, Branch sector, ArrayList<JobPost> jobPostList) {
        super(id);
        this.setAddress(address);
        this.setName(name);
        this.setSector(sector);
        this.setPhoneList(phoneList);
        this.setJobPostList(jobPostList);
    }

    @Override
    public void deleteNode() {
        try {
            for (JobPost jobPost : jobPostList) {
                jobPost.deleteNode();
            }
            Agency.create().deleteObject(this.id);
        }
        catch (Exception e) {
            System.exit(1);
        }
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

    public ArrayList<String> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(ArrayList<String> phoneList) {
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
