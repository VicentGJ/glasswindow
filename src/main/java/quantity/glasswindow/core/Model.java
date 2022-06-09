package quantity.glasswindow.core;

public abstract class Model{
    protected String id;

    public String getId() {
        return this.id;
    }

    public String getType() {
        return this.getClass().getSimpleName();
    }
    public void setId(String name) {
        this.id = name;
    }
}
