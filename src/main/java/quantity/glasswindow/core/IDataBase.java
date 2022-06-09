package quantity.glasswindow.core;

import java.util.ArrayList;
import java.util.HashMap;

public interface IDataBase {
    public Model getObject(String name, String[] args);
    public void deleteObject(String name);
    public void insertObject(Model object);
    public ArrayList<Model> getObjectList(String type, String orderBy, HashMap<String, Object> filter);
}
