package quantity.glasswindow.core;

import quantity.glasswindow.core.customExceptions.DuplicatedIDException;
import quantity.glasswindow.core.customExceptions.InvalidIDException;

public abstract class Model{
    protected String id;

    public Model(String id) throws InvalidIDException, DuplicatedIDException {
        this.setId(id);
    }

    public String getId() {
        return this.id;
    }

    public String getType() {
        return this.getClass().getSimpleName();
    }

    public void setId(String id) throws InvalidIDException, DuplicatedIDException {
        if(Agency.create().modelExists(id))
            throw new DuplicatedIDException(id);
        this.id = id;
    }
}
