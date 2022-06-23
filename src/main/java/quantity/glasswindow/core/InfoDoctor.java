package quantity.glasswindow.core;

import quantity.glasswindow.core.enumerations.DoctorSpec;
import quantity.glasswindow.core.enumerations.Specialty;

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
    public Enum<Specialty> getType() {
        return Specialty.DOCTOR;
    }
}
