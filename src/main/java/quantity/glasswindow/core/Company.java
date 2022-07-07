package quantity.glasswindow.core;

import quantity.glasswindow.core.customExceptions.InvalidNameException;
import quantity.glasswindow.core.customExceptions.InvalidPhoneException;
import quantity.glasswindow.core.enumerations.Branch;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Company extends Model {
    private String name;
    private String address;
    private String phone;
    private Branch sector;
    private ArrayList<String> jobPostList;

    public Company(String id, String name, String address, String phone,
                   Branch sector)
            throws InvalidNameException, InvalidPhoneException {
        super(id);
        this.setAddress(address);
        this.setName(name);
        this.setSector(sector);
        this.setPhone(phone);
        this.jobPostList = new ArrayList<>();
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
/*    public void setJobPostList(ArrayList<String> jobPostList) {
        this.jobPostList = jobPostList;
    }*/

    private boolean phoneValidation(String phone){
        return (phone.length() == 8 && Pattern.matches("\\d+", phone)) || phone.isBlank();
    }

}
