package quantity.glasswindow.core;

public abstract class Model{
    protected String id;

    public Model(String id) {
        this.setId(id);
    }

    public String getId() {
        return this.id;
    }

    public String getType() {
        return this.getClass().getSimpleName();
    }
    public void setId(String id) {
        this.id = id;
    }
}
