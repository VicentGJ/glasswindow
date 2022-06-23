package quantity.glasswindow.core;

import quantity.glasswindow.core.customExceptions.*;
import quantity.glasswindow.core.enumerations.Branch;

import java.util.ArrayList;

public class Company extends Model implements ICascadeDelete {
    private String name;
    private String address;
    private String phone;
    private Branch sector;
    private ArrayList<String> jobPostList;

    public Company(String id, String name, String address, String phone,
                   Branch sector, ArrayList<String> jobPostList)
            throws InvalidIDException, InvalidNameException, DuplicatedIDException, InvalidPhoneException {
        super(id);
        this.setAddress(address);
        this.setName(name);
        this.setSector(sector);
        this.setPhone(phone);
        this.setJobPostList(jobPostList);
    }

    @Override
    public void deleteNode() {
        try {
            Agency a = Agency.create();
            for (String jobPost : jobPostList) {
                ((JobPost) a.getModelWithID(jobPost)).deleteNode();
            }
            a.deleteObject(this.id);
        }
        catch (IdNotFoundException e) {
            System.exit(1);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws InvalidNameException {
        if(!name.isBlank())
            this.name = name;
        else throw new InvalidNameException("Invalid name for Company: name cant be empty");
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) throws InvalidPhoneException {
        if(phoneValidation(phone))
            this.phone = phone;
        else throw new InvalidPhoneException(phone);
    }

    public Branch getSector() {
        return sector;
    }

    public void setSector(Branch sector) {
        this.sector = sector;
    }

    public ArrayList<String> getJobPostList() {
        return jobPostList;
    }

    public void addJobPostToList(String jobPost){
        jobPostList.add(jobPost);
    }
    public void setJobPostList(ArrayList<String> jobPostList) {
        this.jobPostList = jobPostList;
    }

    private boolean phoneValidation(String phone){
        return phone.length() == 8 || phone.isBlank();
    }

}
