package quantity.glasswindow.core;

import quantity.glasswindow.core.customExceptions.IdNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;

public interface IDataBase {
    Model getObject(String id) throws IdNotFoundException;
    void deleteObject(String id) throws IdNotFoundException;
    void insertObject(Model object);
    ArrayList<Model> getObjectList(String type, HashMap<String, Object> filter) throws Exception;
}
