package quantity.glasswindow.core;

public class DoctorProfile {
    private DoctorSpec specialiation;

    public DoctorProfile(DoctorSpec specialiation) {
        this.setSpecialiation(specialiation);
    }

    public DoctorSpec getSpecialiation() {
        return specialiation;
    }

    public void setSpecialiation(DoctorSpec specialiation) {
        this.specialiation = specialiation;
    }
}
