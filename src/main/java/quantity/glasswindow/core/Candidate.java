package quantity.glasswindow.core;

public class Candidate extends Model {
    private String name;
    private Gender gender;
    private String address;
    private String phone;
    private Scholarship scholarship;
    private Specialty specialty;
    private Branch sector;

    public Candidate(String id, String name, Gender gender, String address, String phone, Scholarship scholarship,
                     Specialty specialty, Branch sector) {
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

    public void setName(String name) {
        this.name = name;
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

    public void setPhone(String phone) {
        this.phone = phone;
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
}
