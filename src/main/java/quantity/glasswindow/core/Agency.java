package quantity.glasswindow.core;

import java.security.KeyException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

public class Agency implements IDataBase {
    //maybe add agency name and stuff like that
    private ArrayList<Model> models;
    private static Agency single_instance; //for singleton pattern

    //constructor
    private Agency() {//private constructor to ensure singleton pattern
        this.setModels(new ArrayList<>());
    }

    public static Agency create() {//method to create singleton object
        if (single_instance == null)
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
            if (current.getId().equals(id))
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
        if (!found)
            throw new Exception("ID not found");
    }

    @Override
    public void insertObject(Model object) {
        models.add(object);
    }

    @Override
    public ArrayList<Model> getObjectList(String type, OrderBy order, HashMap<String, Object> filter) {
        ArrayList<Model> orderedList = new ArrayList<>();
        ArrayList<Model> filteredList = new ArrayList<>();
        try {
            if (order == OrderBy.ID)
                orderedList = order_ID(type);
            else if (order == OrderBy.CREATION_DATE)
                orderedList = order_creationDate(type);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            filteredList.addAll(filterOrderedList(filter, orderedList));
        }catch (KeyException e){
            System.out.println(e.getMessage());
        }
        return filteredList;
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
        if (typeCorrect) {
            Collections.sort(ids);
            for (String id : ids)
                for (Model m : models)
                    if (id.equals(m.getId()))
                        result.add(m);
        } else throw new Exception("Incorrect type: \"" + type + "\".");
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
        if (!typeCorrect)
            throw new Exception("Incorrect type: \"" + type + "\".");
        return result;
    }

    /**
     * filter the given ordered list according to the given hashmap
     * @param filter: HashMap with String keys and Object values
     * @param orderedList: the ordered list to filter
     * @return the ordered list with the elements that match with the filter
     */
    private ArrayList<Model> filterOrderedList(HashMap<String, Object> filter, ArrayList<Model> orderedList) throws KeyException {
        ArrayList<Model> result = (ArrayList<Model>) orderedList.clone();
        if (!filter.isEmpty())
            for (String key : filter.keySet()) {
                switch (key) {
                    case "id" -> {
                        String filter_id = (String) filter.get(key);
                        for (Model m : orderedList)
                            if (!m.getId().contains(filter_id))
                                result.remove(m);
                    }
                    case "name" -> {
                        String filter_name = (String) filter.get(key);
                        for (Model m : orderedList)
                            if ((m instanceof Candidate && !((Candidate) m).getName().toLowerCase().contains(filter_name.toLowerCase())) ||
                                    (m instanceof Company && !((Company) m).getName().toLowerCase().contains(filter_name.toLowerCase())))
                                result.remove(m);
                    }
                    case "gender" -> {
                        Gender filter_gender = (Gender) filter.get(key);
                        for (Model m : orderedList)
                            if (m instanceof Candidate && !((Candidate) m).getGender().equals(filter_gender))
                                result.remove(m);
                    }
                    case "address" -> {
                        String filter_address = (String) filter.get(key);
                        for (Model m : orderedList)
                            if ((m instanceof Candidate && !((Candidate) m).getAddress().toLowerCase().contains(filter_address.toLowerCase())) ||
                                    (m instanceof Company && !((Company) m).getAddress().toLowerCase().contains(filter_address.toLowerCase())))
                                result.remove(m);
                    }
                    case "phone" -> {
                        String filter_phone = (String) filter.get(key);
                        for (Model m : orderedList) {
                            if ((m instanceof Candidate && !((Candidate) m).getPhone().toLowerCase().contains(filter_phone.toLowerCase())))
                                result.remove(m);
                            else if (m instanceof Company) {
                                boolean match_found = false;
                                for (String s : ((Company) m).getPhoneList())
                                    if (s.contains(filter_phone)) {
                                        match_found = true;
                                        break;
                                    }
                                if (!match_found)
                                    result.remove(m);
                            }
                        }
                    }
                    case "scholarship" -> {
                        Scholarship filter_scholarship = (Scholarship) filter.get(key);
                        for (Model m : orderedList)
                            if (m instanceof Candidate && !(((Candidate) m).getScholarship().equals(filter_scholarship)) ||
                                    (m instanceof JobPost && !(((JobPost) m).getScholarship().equals(filter_scholarship))))
                                result.remove(m);
                    }
                    case "specialty" -> {
                        Specialty filter_specialty = (Specialty) filter.get(key);
                        for (Model m : orderedList)
                            if (m instanceof Candidate && !(((Candidate) m).getSpecialty().equals(filter_specialty)) ||
                                    (m instanceof JobPost && !(((JobPost) m).getSpecialty().equals(filter_specialty))))
                                result.remove(m);
                    }
                    case "branch" -> {
                        Branch filter_branch = (Branch) filter.get(key);
                        for (Model m : orderedList)
                            if (m instanceof Candidate && !(((Candidate) m).getSector().equals(filter_branch)) ||
                                    (m instanceof JobPost && !(((JobPost) m).getBranch().equals(filter_branch))) ||
                                    (m instanceof Company && !(((Company) m).getSector().equals(filter_branch))))
                                result.remove(m);
                    }
                    case "salary" -> {
                        float filter_salary = (float) filter.get(key);
                        for (Model m : orderedList)
                            if (m instanceof JobPost && ((JobPost) m).getSalary() != filter_salary)
                                result.remove(m);
                    }
                    case "status" -> {
                        Status filter_status = (Status) filter.get(key);
                        for (Model m : orderedList)
                            if (m instanceof JobPost && !((JobPost) m).getStatus().equals(filter_status))
                                result.remove(m);
                    }
                    case "description" -> {
                        String filter_description = (String) filter.get(key);
                        for (Model m : orderedList)
                            if (m instanceof JobPost && !((JobPost) m).getDescription().contains(filter_description))
                                result.remove(m);
                    }
                    case "company" -> {
                        Company filter_company = (Company) filter.get(key);
                        for (Model m : orderedList)
                            if (m instanceof Company && !(m.getId().equals(filter_company.getId())) ||
                                    (m instanceof JobPost && !(((JobPost) m).getCompany().getId().equals(filter_company.getId()))) ||
                                    (m instanceof Interview && !(((Interview) m).getCompany().getId().equals(filter_company.getId()))))
                                result.remove(m);
                    }
                    case "date" -> {
                        Date filter_date = (Date) filter.get(key);
                        for (Model m : orderedList)
                            if (m instanceof Interview && !(((Interview) m).getDate().equals(filter_date)))
                                result.remove(m);
                    }
                    case "candidate" -> {
                        Candidate filter_candidate = (Candidate) filter.get(key);
                        for (Model m : orderedList)
                            if ((m instanceof Candidate && !(m.getId().equals(filter_candidate.getId()))) ||
                                    (m instanceof Interview && !(((Interview) m).getCandidate().getId().equals(filter_candidate.getId()))))
                                result.remove(m);
                    }
                    case "jobpost" -> {
                        JobPost filter_jobpost = (JobPost) filter.get(key);
                        for (Model m : orderedList) {
                            if ((m instanceof JobPost && !(m.getId().equals(filter_jobpost.getId()))) ||
                                    (m instanceof Interview && !(m.getId().equals((filter_jobpost.getId())))))
                                result.remove(m);
                            else if (m instanceof Company) {
                                boolean match_found = false;
                                for (JobPost j : ((Company) m).getJobPostList())
                                    if (j.getId().equals(filter_jobpost.getId())) {
                                        match_found = true;
                                        break;
                                    }
                                if (!match_found)
                                    result.remove(m);
                            }
                        }
                    }
                    default -> throw new KeyException("Invalid key: " + "\"" + key + "\"");
                }
            }
        return result;
    }
}