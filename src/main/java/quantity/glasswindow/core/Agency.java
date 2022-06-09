package quantity.glasswindow.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Agency implements IDataBase {
    //maybe add agency name and stuff like that
    private ArrayList<Model> models;

    //constructor
    public Agency() {
        this.setModels(new ArrayList<>());
    }

    //getters & setters

    public void setModels(ArrayList<Model> models) {
        this.models = models;
    }

    public ArrayList<Model> getModels() {
        return models;
    }

    //from interface
    @Override
    public Model getObject(String id, String[] args) throws Exception {
        int i = 0;
        while (i < models.size()) {
            Model current = models.get(i);
            if(current.getId().equals(id))
                return current;
            i++;
        }
        throw new Exception("ID not found");

    }

    @Override
    public void deleteObject(String id) throws Exception {
        int i = 0;
        while (i < models.size()) {
            Model current = models.get(i);
            if (current.getId().equals(id)) {
                models.remove(current);
                break;
            }
            i++;
        }
        throw new Exception("ID not found");
    }

    @Override
    public void insertObject(Model object) {
        models.add(object);
    }

    @Override
    public ArrayList<Model> getObjectList(String type, OrderBy order, HashMap<String, Object> filter) throws Exception {
        if(order == OrderBy.ID)
            return order_ID(type);
        else if (order == OrderBy.CREATION_DATE)
            return order_creationDate(type);
        else throw new Exception(order + "is not a valid sort");
    }

    private ArrayList<Model> order_ID(String type){
        ArrayList<String> ids = new ArrayList<>();
        ArrayList<Model> result = new ArrayList<>();
        for (Model m : models)
            if (m.getType().equalsIgnoreCase(type))
                ids.add(m.getId());
        Collections.sort(ids);
        for (String id : ids)
            for (Model m : models)
                if (id.equals(m.getId()))
                    result.add(m);
        return result;
    }

    private ArrayList<Model> order_creationDate(String type){
        ArrayList<Model> result = new ArrayList<>();
        for (Model m : models)
            if (m.getType().equalsIgnoreCase(type))
                result.add(m);
        return result;
    }
}