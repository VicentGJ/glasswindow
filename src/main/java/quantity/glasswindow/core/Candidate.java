package quantity.glasswindow.core;

import java.util.ArrayList;
import java.util.regex.*;

import quantity.glasswindow.core.customExceptions.*;
import quantity.glasswindow.core.enumerations.Branch;
import quantity.glasswindow.core.enumerations.Gender;
import quantity.glasswindow.core.enumerations.Scholarship;
import quantity.glasswindow.core.enumerations.Specialty;

public class Candidate extends Model {
    private String name;
    private Gender gender;
    private String address;
    private String phone;
    private Scholarship scholarship;
    private Specialty specialty;
    private Branch sector;

    private int yearsOfExp;
    private ArrayList<IAdditionalInfo> addtionalInfo;
    public Candidate(String id, String name, Gender gender, String address, String phone, Scholarship scholarship,
                     Specialty specialty, Branch sector, int yearsOfExp)
            throws InvalidIDException, InvalidNameException, DuplicatedIDException, InvalidPhoneException,
            InvalidYearsOfExpException {
        super(id);
        this.setAddress(address);
        this.setName(name);
        this.setGender(gender);
        this.setPhone(phone);
        this.setSector(sector);
        this.setScholarship(scholarship);
        this.setSpecialty(specialty);
        this.setYearsOfExp(yearsOfExp);
        this.addtionalInfo = new ArrayList<>();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws InvalidNameException {
      /*StringUtils.isBlank(null)      = true
        StringUtils.isBlank("")        = true
        StringUtils.isBlank(" ")       = true
        StringUtils.isBlank("bob")     = false
        StringUtils.isBlank("  bob  ") = false*/
        if(nameValidation(name))
            this.name = name;
        else throw new InvalidNameException(getType());
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
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

    public Scholarship getScholarship() {
        return scholarship;
    }

    public void setScholarship(Scholarship scholarship) {
        this.scholarship = scholarship;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public Branch getSector() {
        return sector;
    }

    public void setSector(Branch sector) {
        this.sector = sector;
    }

    public ArrayList<IAdditionalInfo> getAddtionalInfo() {
        return addtionalInfo;
    }

    public void addAdditionalInfo(IAdditionalInfo info){
        this.addtionalInfo.add(info);
    }

    @Override
    public void setId(String id) throws InvalidIDException, DuplicatedIDException {
        if(Agency.getInstance().modelExists(id))
            throw new DuplicatedIDException(id);
        else {
            boolean onlyNumbers = Pattern.matches("\\d+", id);
            if (onlyNumbers) {
                if (id.length() == 11) {
                    boolean dateValid = dateValidationID(id.substring(0, 7));//[0-1]Year, [2-3]Month, [4-5]Day, [6]Century
                    if (dateValid) {
                            this.id = id;
                    } else
                        throw new InvalidIDException(id, "Error during validation of digits 1-7");
                } else throw new InvalidIDException(id, "Error during validation of ID length, must be 11 digits");
            } else throw new InvalidIDException(id, "Error during validation of ID: must be only numbers");
        }
    }

    public int getYearsOfExp() {
        return yearsOfExp;
    }

    public void setYearsOfExp(int yearsOfExp) throws InvalidYearsOfExpException {
        if(yearsOfExp >= 0)
            this.yearsOfExp = yearsOfExp;
        else throw new InvalidYearsOfExpException(yearsOfExp);
    }

    private boolean dateValidationID(String idDate){
        //boolean isValidYear = true;//no way to validate this with 2 digits
        boolean isValidMonth = true;
        boolean isValidDay = false;
        //boolean isValidCentury = true;//depends on year, so no way to validate this
        //int year = Integer.parseInt(idDate.substring(0,1));
        int month = Integer.parseInt(idDate.substring(2,4));
        int day = Integer.parseInt(idDate.substring(4,6));
        //int century = Integer.parseInt(String.valueOf(idDate.charAt(6)));
        if(day > 0) {
            if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) && day <= 31)
                isValidDay = true;
            else if ((month == 4 || month == 6 || month == 9 || month == 11) && day <= 30)
                isValidDay = true;
            else if (month == 2 && day <= 29)
                isValidDay = true;
            else isValidMonth = false;
        }
        return (/*isValidYear &&*/  isValidMonth && isValidDay  /*&& isValidCentury*/);
    }

    private boolean phoneValidation(String phone){
        return phone.length() == 8 || phone.isBlank();
    }

    private boolean nameValidation(String name){//TODO add to uml
        return !name.isBlank() && Pattern.matches("[a-zA-z ]+", name);
    }
}
