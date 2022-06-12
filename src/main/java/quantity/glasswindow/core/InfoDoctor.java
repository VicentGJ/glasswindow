package quantity.glasswindow.core;

public class InfoDoctor implements IAdditionalInfo {
    private DoctorSpec specialization;

    public InfoDoctor(DoctorSpec specialization) {
        this.setSpecialization(specialization);
    }

    public DoctorSpec getSpecialization() {
        return specialization;
    }

    public void setSpecialization(DoctorSpec specialization) {
        this.specialization = specialization;
    }

    @Override
    public Specialty getType() {
        return Specialty.DOCTOR;
    }
}
