package quantity.glasswindow.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Agency implements IDataBase {
    //maybe add agency name and stuff like that
    private ArrayList<Model> models;
    private static Agency single_instance; //for singleton pattern

    //constructor
    private Agency() {//private constructor to ensure singleton pattern
        this.setModels(new ArrayList<>());
    }
    public static Agency create(){//method to create singleton object
        if(single_instance == null)
            single_instance = new Agency();
        return single_instance;
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
        boolean found = false;
        while (i < models.size() && !found) {
            Model current = models.get(i);
            if (current.getId().equals(id)) {
                models.remove(current);
                found = true;
            }
            i++;
        }
        if(!found)
            throw new Exception("ID not found");
    }
    
    @Override
    public void insertObject(Model object) {
        models.add(object);
    }

    @Override
    public ArrayList<Model> getObjectList(String type, OrderBy order, HashMap<String, Object> filter) {
        ArrayList<Model> orderedList = new ArrayList<>();
        try {
            if(order == OrderBy.ID) {
                orderedList = order_ID(type);
            }
            else if (order == OrderBy.CREATION_DATE) {
                orderedList = order_creationDate(type);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return orderedList;
    }

    private ArrayList<Model> order_ID(String type) throws Exception {
        ArrayList<String> ids = new ArrayList<>();
        ArrayList<Model> result = new ArrayList<>();
        boolean typeCorrect = false;
        for (Model m : models)
            if (m.getType().equalsIgnoreCase(type)) {
                ids.add(m.getId());
                typeCorrect = true;
            }
        if(typeCorrect) {
            Collections.sort(ids);
            for (String id : ids)
                for (Model m : models)
                    if (id.equals(m.getId()))
                        result.add(m);
        }else throw new Exception("Incorrect type: \""+type+"\".");
        return result;
    }

    private ArrayList<Model> order_creationDate(String type) throws Exception {//creation date is the same as the original list order
        ArrayList<Model> result = new ArrayList<>();
        boolean typeCorrect = false;
        for (Model m : models)
            if (m.getType().equalsIgnoreCase(type)) {
                result.add(m);
                typeCorrect = true;
                }
        if(!typeCorrect)
            throw new Exception("Incorrect type: \""+type+"\".");
        return result;
    }
}