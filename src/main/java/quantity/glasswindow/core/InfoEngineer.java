package quantity.glasswindow.core;

public class InfoEngineer implements IAdditionalInfo{
    private EngineerSpec specialization;

    public InfoEngineer(EngineerSpec specialization) {
        this.setSpecialization(specialization);
    }

    public EngineerSpec getSpecialization() {
        return specialization;
    }

    public void setSpecialization(EngineerSpec specialization) {
        this.specialization = specialization;
    }

    @Override
    public Enum<Specialty> getType() {
        return Specialty.ENGINEER;
    }
}
