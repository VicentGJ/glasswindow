package quantity.glasswindow.core;

import java.util.ArrayList;
import java.util.regex.*;
import quantity.glasswindow.core.customExceptions.DuplicatedIDException;
import quantity.glasswindow.core.customExceptions.InvalidIDException;
import quantity.glasswindow.core.customExceptions.InvalidNameException;
import quantity.glasswindow.core.customExceptions.InvalidPhoneException;

public class Candidate extends Model {
    private String name;
    private Gender gender;
    private String address;
    private String phone;
    private Scholarship scholarship;
    private Specialty specialty;
    private Branch sector;
    private ArrayList<IAdditionalInfo> addtionalInfo;
    public Candidate(String id, String name, Gender gender, String address, String phone, Scholarship scholarship,
                     Specialty specialty, Branch sector) throws InvalidIDException, InvalidNameException, DuplicatedIDException, InvalidPhoneException {
        super(id);
        this.setAddress(address);
        this.setName(name);
        this.setGender(gender);
        this.setPhone(phone);
        this.setSector(sector);
        this.setScholarship(scholarship);
        this.setSpecialty(specialty);

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
        if(!name.isBlank())
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
        if(Agency.create().modelExists(id))
            throw new DuplicatedIDException(id);
        else {
            boolean onlyNumbers = Pattern.matches("\\d", id);
            if (onlyNumbers) {
                if (id.length() == 11) {
                    boolean dateValid = dateValidationID(id.substring(0, 6));//[0-1]Year, [2-3]Month, [4-5]Day, [6]Century
                    if (dateValid) {
                        boolean genderValid = genderValidateID(id.charAt(9));
                        if (!genderValid)
                            throw new InvalidIDException(id, "Error during validation of digit 10");
                    } else
                        throw new InvalidIDException(id, "Error during validation of digits 1-7");
                } else throw new InvalidIDException(id, "Error during validation of ID length, must be 11 digits");
            } else throw new InvalidIDException(id, "Error during validation of ID: must be only numbers");
        }
    }

    private boolean dateValidationID(String idDate){
        //boolean isValidYear = true;//no way to validate this with 2 digits
        boolean isValidMonth = true;
        boolean isValidDay = false;
        //boolean isValidCentury = true;//depends on year, so no way to validate this
        //int year = Integer.parseInt(idDate.substring(0,1));
        int month = Integer.parseInt(idDate.substring(2,3));
        int day = Integer.parseInt(idDate.substring(4,5));
        //int century = Integer.parseInt(String.valueOf(idDate.charAt(6)));
        if(day > 0)
            switch (month){
                case 1:case 3:case 5:case 7:
                case 8:case 10:case 12:
                    if(day <= 31)
                        isValidDay = true;
                    break;
                case 4:case 6:
                case 9:case 11:
                    if(day <= 30)
                        isValidDay = true;
                    break;
                case 2:
                    if(day <= 29)
                        isValidDay = true;
                    break;
                default:
                    isValidMonth = false;
                    break;
            }
        return (/*isValidYear &&*/  isValidMonth && isValidDay  /*&& isValidCentury*/);
    }

    private boolean genderValidateID(char idGender){
        int gender = Integer.parseInt(String.valueOf(idGender));
        Gender idG = gender % 2 == 0? Gender.MASCULINE : Gender.FEMININE;
        return this.getGender() == idG || this.getGender() == null;
    }

    private boolean phoneValidation(String phone){
        return phone.length() == 8 || phone.isBlank();
    }
}
