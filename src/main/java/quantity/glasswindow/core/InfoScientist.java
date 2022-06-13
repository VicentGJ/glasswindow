package quantity.glasswindow.core;

import java.util.ArrayList;

public class InfoScientist implements IAdditionalInfo{
    private ArrayList<ScientistSpec> specialization;

    public InfoScientist(ArrayList<ScientistSpec> specialization) {
        this.setSpecialization(specialization);
    }

    public ArrayList<ScientistSpec> getSpecialization() {
        return specialization;
    }

    public void setSpecialization(ArrayList<ScientistSpec> specialization) {
        this.specialization = specialization;
    }

    public void addSpec(ScientistSpec spec){
        specialization.add(spec);
    }
    public void removeSpec(ScientistSpec spec){
        specialization.remove(spec);
    }
    @Override
    public Enum<Specialty> getType() {
        return Specialty.SCIENTIST;
    }
}
