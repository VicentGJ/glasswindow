package quantity.glasswindow.core;

import quantity.glasswindow.core.customExceptions.InvalidIDException;

public abstract class Model{
    protected String id;

    public Model(String id) throws InvalidIDException {
        this.setId(id);
    }

    public String getId() {
        return this.id;
    }

    public String getType() {
        return this.getClass().getSimpleName();
    }

    public void setId(String id) throws InvalidIDException {
        if(Agency.create().modelExists(id))
            throw new InvalidIDException("Cant create "+getType()+" with ID:" + id + "because it already exists.");
        this.id = id;
    }
}
