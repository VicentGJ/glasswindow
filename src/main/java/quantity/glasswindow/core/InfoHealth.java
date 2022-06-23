package quantity.glasswindow.core;

import quantity.glasswindow.core.enumerations.Branch;

public class InfoHealth implements IAdditionalInfo {
    private boolean hasCertificate;

    public InfoHealth(boolean hasCertificate) {
        this.setCertificate(hasCertificate);
    }

    public boolean hasCertificate() {
        return hasCertificate;
    }

    public void setCertificate(boolean hasCertificate) {
        this.hasCertificate = hasCertificate;
    }

    @Override
    public Enum<Branch> getType() {
        return Branch.HEALTH;
    }
}
