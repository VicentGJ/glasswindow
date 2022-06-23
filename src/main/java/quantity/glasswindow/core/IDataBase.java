package quantity.glasswindow.core;

import quantity.glasswindow.core.enumerations.OrderBy;

import java.util.ArrayList;
import java.util.HashMap;

public interface IDataBase {
    Model getObject(String id) throws Exception;
    void deleteObject(String id) throws Exception;
    void insertObject(Model object);
    ArrayList<Model> getObjectList(String type, OrderBy order, HashMap<String, Object> filter) throws Exception;
}
