package quantity.glasswindow.core;

import quantity.glasswindow.core.enumerations.Branch;

public class InfoTourism implements IAdditionalInfo{
    private boolean hasLicence;

    public InfoTourism(boolean hasLicence) {
        this.setLicence(hasLicence);
    }

    public boolean hasLicence() {
        return hasLicence;
    }

    public void setLicence(boolean hasLicence) {
        this.hasLicence = hasLicence;
    }

    @Override
    public Enum<Branch> getType() {
        return Branch.TOURISM;
    }
}
