package quantity.glasswindow.core;

public class EngineerProfile extends AdditionalProfile {
    private EngineerSpec specialization;

    public EngineerProfile(EngineerSpec specialization) {
        this.setSpecialization(specialization);
    }

    public EngineerSpec getSpecialization() {
        return specialization;
    }

    public void setSpecialization(EngineerSpec specialization) {
        this.specialization = specialization;
    }
}
