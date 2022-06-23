package quantity.glasswindow.core;

import quantity.glasswindow.core.customExceptions.*;
import quantity.glasswindow.core.enumerations.*;

import java.security.KeyException;
import java.time.Month;
import java.time.Year;
import java.util.*;


public class Agency implements IDataBase {
    //maybe add agency name and stuff like that
    private final ArrayList<Candidate> candidateList;//TODO: ADD NEW LIST-ATTRIBUTES TO UML
    private final ArrayList<Company> companyList;
    private final ArrayList<JobPost> jobPostList;
    private final ArrayList<Interview> interviewList;
    private static Agency single_instance; //for singleton pattern

    //constructor
    private Agency() {//private constructor to ensure singleton pattern
        companyList = new ArrayList<>();
        candidateList = new ArrayList<>();
        jobPostList = new ArrayList<>();
        interviewList = new ArrayList<>();
    }

    public static Agency create() {//method to create singleton object
        if (single_instance == null)
            single_instance = new Agency();
        return single_instance;
    }

    //REFACTORING...TODO:FIX UML

    public ArrayList<Candidate> getCandidateList() {
        return candidateList;
    }

    public ArrayList<Company> getCompanyList() {
        return companyList;
    }

    public ArrayList<JobPost> getJobPostList() {
        return jobPostList;
    }

    public ArrayList<Interview> getInterviewList() {
        return interviewList;
    }

    private void addCandidate(Candidate c){
        candidateList.add(c);
    }

    private void addCompany(Company c){
        companyList.add(c);
    }

    private void addJobPost(JobPost j){
        jobPostList.add(j);
    }

    private void addInterview(Interview i){
        interviewList.add(i);
    }

    private void removeCandidate(String id) throws IdNotFoundException {
        if (!candidateList.isEmpty()) {
            boolean found = false;
            int i = 0;
            while (i < candidateList.size() && !found) {
                Candidate current = candidateList.get(i);
                if (current.getId().equals(id)) {
                    found = true;
                    candidateList.remove(current);
                }
                i++;
            }
            if (!found) throw new IdNotFoundException(id);
        }
    }

    private void removeCompany(String id) throws IdNotFoundException {
        if (!companyList.isEmpty()) {
            boolean found = false;
            int i = 0;
            while (i < companyList.size() && !found) {
                Company current = companyList.get(i);
                if (current.getId().equals(id)) {
                    found = true;
                    companyList.remove(current);
                }
                i++;
            }
            if (!found) throw new IdNotFoundException(id);
        }
    }

    private void removeJobPost(String id) throws IdNotFoundException {
        if (!jobPostList.isEmpty()) {
            boolean found = false;
            int i = 0;
            while (i < jobPostList.size() && !found) {
                JobPost current = jobPostList.get(i);
                if (current.getId().equals(id)) {
                    found = true;
                    jobPostList.remove(current);
                }
                i++;
            }
            if (!found) throw new IdNotFoundException(id);
        }
    }

    private void removeInterview(String id) throws IdNotFoundException {
        if (!interviewList.isEmpty()) {
            boolean found = false;
            int i = 0;
            while (i < interviewList.size() && !found) {
                Interview current = interviewList.get(i);
                if (current.getId().equals(id)) {
                    found = true;
                    interviewList.remove(current);
                }
                i++;
            }
            if (!found) throw new IdNotFoundException(id);
        }
    }

    private void removeCandidate(Candidate c) throws ModelNotFoundException {
        if (!candidateList.isEmpty()) {
            boolean found = false;
            int i = 0;
            while (i < candidateList.size() && !found) {
                Candidate current = candidateList.get(i);
                if (current.getId().equals(c.getId())) {
                    found = true;
                    candidateList.remove(current);
                }
                i++;
            }
            if (!found) throw new ModelNotFoundException(c);
        }
    }

    private void removeCompany(Company c) throws ModelNotFoundException {
        if (!companyList.isEmpty()) {
            boolean found = false;
            int i = 0;
            while (i < companyList.size() && !found) {
                Company current = companyList.get(i);
                if (current.getId().equals(c.getId())) {
                    found = true;
                    companyList.remove(current);
                }
                i++;
            }
            if (!found) throw new ModelNotFoundException(c);
        }
    }

    private void removeJobPost(JobPost j) throws ModelNotFoundException {
        if (!jobPostList.isEmpty()) {
            boolean found = false;
            int i = 0;
            while (i < jobPostList.size() && !found) {
                JobPost current = jobPostList.get(i);
                if (current.getId().equals(j.getId())) {
                    found = true;
                    jobPostList.remove(current);
                }
                i++;
            }
            if (!found) throw new ModelNotFoundException(j);
        }
    }

    private void removeInterview(Interview interview) throws ModelNotFoundException {
        if (!interviewList.isEmpty()) {
            boolean found = false;
            int i = 0;
            while (i < interviewList.size() && !found) {
                Interview current = interviewList.get(i);
                if (current.getId().equals(interview.getId())) {
                    found = true;
                    interviewList.remove(current);
                }
                i++;
            }
            if (!found) throw new ModelNotFoundException(interview);
        }
    }

    public void simpleAddModel(Model m){
        if (m instanceof Candidate)
            addCandidate((Candidate)m);
        else if(m instanceof Company)
            addCompany((Company)m);
        else if(m instanceof JobPost)
            addJobPost((JobPost)m);
        else if(m instanceof Interview)
            addInterview((Interview)m);
    }

    //method overloading
    public void simpleRemoveModel(String id) throws IdNotFoundException {
        Model m = getModelWithID(id);
        if (m instanceof Candidate)
            removeCandidate(m.getId());
        else if(m instanceof Company)
            removeCompany(m.getId());
        else if(m instanceof JobPost)
            removeJobPost(m.getId());
        else if(m instanceof Interview)
            removeInterview(m.getId());
    }

    public void simpleRemoveModel(Model m) throws ModelNotFoundException {
        if (m instanceof Candidate)
            removeCandidate((Candidate) m);
        else if(m instanceof Company)
            removeCompany((Company) m);
        else if(m instanceof JobPost)
            removeJobPost((JobPost) m);
        else if(m instanceof Interview)
            removeInterview((Interview) m);
    }

    public void setModels(ArrayList<Model> models) {
        if(!models.isEmpty()){
            for(Model m : models){
                if(m instanceof Candidate)
                    candidateList.add((Candidate) m);
                else if(m instanceof Company)
                    companyList.add((Company)m);
                else if(m instanceof JobPost)
                    jobPostList.add((JobPost)m);
                else if(m instanceof Interview)
                    interviewList.add((Interview)m);
            }
        }
    }

    private ArrayList<Model> getModels(){//TODO:UML
        ArrayList<Model> models = new ArrayList<>();
        models.addAll(candidateList);
        models.addAll(companyList);
        models.addAll(jobPostList);
        models.addAll(interviewList);
        return models;
    }
    //from interface

    @Override
    public Model getObject(String id) throws IdNotFoundException {
        if (!candidateList.isEmpty())
            for(Candidate c : candidateList)
                if(c.getId().equals(id))
                    return c;
        if(!companyList.isEmpty())
            for (Company c : companyList)
                if (c.getId().equals(id))
                    return c;
        if(!jobPostList.isEmpty())
            for (JobPost j : jobPostList)
                if(j.getId().equals(id))
                    return j;
        if(!interviewList.isEmpty())
            for(Interview i : interviewList)
                if(i.getId().equals(id))
                    return i;
        throw new IdNotFoundException(id);
    }

    @Override
    public void deleteObject(String id) throws IdNotFoundException {
        simpleRemoveModel(id);
    }

    @Override
    public void insertObject(Model object) {
        simpleAddModel(object);
    }

    @Override
    public ArrayList<Model> getObjectList(String type, HashMap<String, Object> filter) throws InvalidTypeException {
        ArrayList<Model> filteredList = new ArrayList<>();
        ArrayList<Model> toFilter = new ArrayList<>();
        if(type.equalsIgnoreCase("candidate")){
            toFilter.addAll(candidateList);
        } else if (type.equalsIgnoreCase("company")) {
            toFilter.addAll(companyList);
        } else if (type.equalsIgnoreCase("jobpost")) {
            toFilter.addAll(jobPostList);
        } else if (type.equalsIgnoreCase("interview")) {
            toFilter.addAll(interviewList);
        }else throw new InvalidTypeException(type);

        try {
            filteredList.addAll(filterList(filter, toFilter));
        }catch (KeyException e){
            System.out.println(e.getMessage());
        }
        return filteredList;
    }

    public ArrayList<String> gerObjectListIDs(String type, HashMap<String, Object> filter) throws InvalidTypeException {
        ArrayList<Model> filtered = getObjectList(type,filter);
        ArrayList<String> filteredID = new ArrayList<>();
        for(Model m : filtered)
            filteredID.add(m.getId());
        return filteredID;
    }

    /**
     * filter the list according to the given hashmap
     * @param filter: HashMap with String keys and Object values
     * @param lists: the list to filter composed of all the lists(candidates,companies, jobposts,interviews)
     * @return the ordered list with the elements that match with the filter
     */
    private ArrayList<Model> filterList(HashMap<String, Object> filter, ArrayList<Model> lists) throws KeyException {
        @SuppressWarnings("unchecked") ArrayList<Model> result = (ArrayList<Model>)lists.clone();
        if (!filter.isEmpty())
            for (String key : filter.keySet()) {
                switch (key) {
                    case "id" -> {
                        String filter_id = (String) filter.get(key);
                        for (Model m : lists)
                            if (!m.getId().contains(filter_id))
                                result.remove(m);
                    }
                    case "name" -> {
                        String filter_name = (String) filter.get(key);
                        for (Model m : lists)
                            if ((m instanceof Candidate && !((Candidate) m).getName().toLowerCase().contains(filter_name.toLowerCase())) ||
                                    (m instanceof Company && !((Company) m).getName().toLowerCase().contains(filter_name.toLowerCase())))
                                result.remove(m);
                    }
                    case "gender" -> {
                        Gender filter_gender = (Gender) filter.get(key);
                        for (Model m : lists)
                            if (m instanceof Candidate && !((Candidate) m).getGender().equals(filter_gender))
                                result.remove(m);
                    }
                    case "address" -> {
                        String filter_address = (String) filter.get(key);
                        for (Model m : lists)
                            if ((m instanceof Candidate && !((Candidate) m).getAddress().toLowerCase().contains(filter_address.toLowerCase())) ||
                                    (m instanceof Company && !((Company) m).getAddress().toLowerCase().contains(filter_address.toLowerCase())))
                                result.remove(m);
                    }
                    case "phone" -> {
                        String filter_phone = (String) filter.get(key);
                        for (Model m : lists) {
                            if ((m instanceof Candidate && !((Candidate) m).getPhone().toLowerCase().contains(filter_phone.toLowerCase())))
                                result.remove(m);
                            else if (m instanceof Company) {
                                boolean match_found = ((Company)m).getPhone().contains(filter_phone);
                                if (!match_found)
                                    result.remove(m);
                            }
                        }
                    }
                    case "scholarship" -> {
                        Scholarship filter_scholarship = (Scholarship) filter.get(key);
                        for (Model m : lists)
                            if (m instanceof Candidate && !(((Candidate) m).getScholarship().equals(filter_scholarship)) ||
                                    (m instanceof JobPost && !(((JobPost) m).getScholarship().equals(filter_scholarship))))
                                result.remove(m);
                    }
                    case "specialty" -> {
                        Specialty filter_specialty = (Specialty) filter.get(key);
                        for (Model m : lists)
                            if (m instanceof Candidate && !(((Candidate) m).getSpecialty().equals(filter_specialty)) ||
                                    (m instanceof JobPost && !(((JobPost) m).getSpecialty().equals(filter_specialty))))
                                result.remove(m);
                    }
                    case "branch" -> {
                        Branch filter_branch = (Branch) filter.get(key);
                        for (Model m : lists)
                            if (m instanceof Candidate && !(((Candidate) m).getSector().equals(filter_branch)) ||
                                    (m instanceof JobPost && !(((JobPost) m).getBranch().equals(filter_branch))) ||
                                    (m instanceof Company && !(((Company) m).getSector().equals(filter_branch))))
                                result.remove(m);
                    }
                    case "salary" -> {
                        float filter_salary = (float) filter.get(key);
                        for (Model m : lists)
                            if (m instanceof JobPost && ((JobPost) m).getSalary() != filter_salary)
                                result.remove(m);
                    }
                    case "status" -> {
                        Status filter_status = (Status) filter.get(key);
                        for (Model m : lists)
                            if (m instanceof JobPost && !((JobPost) m).getStatus().equals(filter_status))
                                result.remove(m);
                    }
                    case "description" -> {
                        String filter_description = (String) filter.get(key);
                        for (Model m : lists)
                            if (m instanceof JobPost && !((JobPost) m).getDescription().contains(filter_description))
                                result.remove(m);
                    }
                    case "company" -> {
                        Company filter_company = (Company) filter.get(key);
                        for (Model m : lists)
                            if (m instanceof Company && !(m.getId().equals(filter_company.getId())) ||
                                    (m instanceof JobPost && !(((JobPost) m).getCompany().equals(filter_company.getId()))) ||
                                    (m instanceof Interview && !(((Interview) m).getCompany().equals(filter_company.getId()))))
                                result.remove(m);
                    }
                    case "date" -> {
                        // TODO: Right now this is specific to getAppliances(), need to refactor to work more generally later
                        int filter_month = (int) filter.get(key);
                        for (Model m : lists)
                            if (m instanceof Interview && !(((Interview) m).getDate().getMonth() == filter_month))
                                result.remove(m);
                    }
                    case "candidate" -> {
                        Candidate filter_candidate = (Candidate) filter.get(key);
                        for (Model m : lists)
                            if ((m instanceof Candidate && !(m.getId().equals(filter_candidate.getId()))) ||
                                    (m instanceof Interview && !(((Interview) m).getCandidate().equals(filter_candidate.getId()))))
                                result.remove(m);
                    }
                    case "jobpost" -> {
                        JobPost filter_jobpost = (JobPost) filter.get(key);
                        for (Model m : lists) {
                            if ((m instanceof JobPost && !(m.getId().equals(filter_jobpost.getId()))) ||
                                    (m instanceof Interview && !(m.getId().equals((filter_jobpost.getId())))))
                                result.remove(m);
                            else if (m instanceof Company) {
                                boolean match_found = false;
                                for (String jp : ((Company) m).getJobPostList())
                                    if (jp.equals(filter_jobpost.getId())) {
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

    public ArrayList<JobPost> getQualifiedJobPostList (Candidate candidate) throws InvalidTypeException {
        HashMap<String, Object> filter = new HashMap<>();
        ArrayList<JobPost> result = new ArrayList<>();
        filter.put("branch", candidate.getSector());
        filter.put("scholarship", candidate.getScholarship());
        filter.put("specialty", candidate.getSpecialty());
        filter.put("status", Status.OPEN);
        for (Model i: this.getObjectList("JobPost", filter)) {
            result.add((JobPost) i);
        }
        return result;
    }

    public ArrayList<Candidate> getAppliances (String company, int month) {
        month--;
        ArrayList<Candidate> result = new ArrayList<>();
        Company c = (Company)getModelWithID(company);
        ArrayList<String> jobPosts = new ArrayList<>(c.getJobPostList());
        ArrayList<String> interviews;
        JobPost tempJP;
        for (String j: jobPosts) {
            tempJP = (JobPost)getModelWithID(j);
            interviews = tempJP.getInterviewList();
            for (String i: interviews) {
                Interview interview = (Interview) getModelWithID(i);
                if (interview.getDate().getMonth() == month) {
                    Candidate candidate = (Candidate)getModelWithID(interview.getCandidate());
                    result.add(candidate);
                }
            }
        }
        return result;
    }

    public ArrayList<ArrayList<Interview>> getInterviewsMonth (String id, int month) {
        int length = Month.of(month).length(Year.now().isLeap());
        ArrayList<ArrayList<Interview>> result = new ArrayList<>(
                Collections.nCopies(length, null)
        );
        ArrayList<Interview> dailyInterviews = new ArrayList<>();
        try {
            Model object = this.getObject(id);
            // TODO: review if a pattern can be applied here in the future
            HashMap<String, Object> filter = new HashMap<>();
            if (object.getType().equals("Company")) {
                filter.put("company", object);
            }
            else {
                filter.put("candidate", object);
            }
            filter.put("date", month-1);
            ArrayList<Model> monthInterviews = new ArrayList<>(this.getObjectList(
                    "Interview", filter)
            );
            for (Model i: monthInterviews) {
                Interview interview = (Interview) i;
                int index = interview.getDate().getDate() - 1;
                if (result.get(index) == null){
                    ArrayList<Interview> temp = new ArrayList<>();
                    temp.add(interview);
                    result.set(index, temp);
                }
                else result.get(index).add(interview);
            }
        }
        catch (IdNotFoundException | InvalidTypeException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        return result;
    }

    public ArrayList<ArrayList<Interview>> getCompanyInterviews(Company company) {
        ArrayList<ArrayList<Interview>> result = new ArrayList<>(
                Collections.nCopies(company.getJobPostList().size(), null)
        );
        ArrayList<String> jpList = company.getJobPostList();
        for(String jp : jpList){
            JobPost jobPost = ((JobPost)(getModelWithID(jp)));
            ArrayList<String> interviews = jobPost.getInterviewList();
            for(String interview: interviews){
                Interview i = (Interview)getModelWithID(interview);
                if(result.get(jpList.indexOf(jp)) == null){
                    ArrayList<Interview> temp = new ArrayList<>();
                    temp.add(i);
                    result.set(jpList.indexOf(jp),temp);
                }else result.get(jpList.indexOf(jp)).add(i);
            }
        }
        return result;
    }

    public Model getModelWithID(String id){
        ArrayList<Model> models = getModels();
        if(!models.isEmpty()){
            for(Model model:models){
                if(model.getId().equals(id))
                    return model;
            }
        }
        return null;
    }

    public boolean modelExists(String id){
        ArrayList<Model> models = getModels();
        for(Model m : models)
            if(m.getId().equals(id))
                return true;
        return false;
    }

    public void initTestData()
            throws InvalidIDException, InvalidDateException, InvalidNameException,
            InvalidSalaryException, DuplicatedIDException, InvalidPhoneException {
        ArrayList<Model> models = new ArrayList<>();
        //candidates
        Candidate candidate1 = new Candidate("01041266729","Bruce Banner", Gender.MASCULINE,"New York",
                "05158899", Scholarship.PHD, Specialty.SCIENTIST, Branch.INDUSTRY);
        Candidate candidate2 = new Candidate("01060568481","Tonny Stark", Gender.MASCULINE,"New York",
                "05155229", Scholarship.PHD, Specialty.ECONOMIST,Branch.INDUSTRY);
        Candidate candidate3 = new Candidate("01060568482","Clark Kent", Gender.MASCULINE,"Kansas",
                "33156899", Scholarship.BASIC, Specialty.ECONOMIST,Branch.SERVICES);
        Candidate candidate4 = new Candidate("01022068706","Bruce Wayne", Gender.MASCULINE,"Gotham",
                "05675799", Scholarship.PHD, Specialty.ARCHITECT,Branch.INDUSTRY);
        Candidate candidate5 = new Candidate("02061766497","Carol Danvers", Gender.FEMININE, "Outer Space",
                "05133339", Scholarship.MASTER, Specialty.ENGINEER,Branch.TOURISM);
        Candidate candidate6 = new Candidate("01091368466","Felicia Hardy", Gender.FEMININE,"New York",
                "05158449", Scholarship.GRADE, Specialty.TRANSLATOR,Branch.TOURISM);
        //companies
        Company company1 = new Company("company-001","Last Quarter","Nebraska",
                "", Branch.INDUSTRY, new ArrayList<>());
        Company company2 = new Company("company-002","Avenue Studios","New York",
                "", Branch.TOURISM, new ArrayList<>());
        Company company3 = new Company("company-003","Icy Mountain","London",
                "", Branch.SERVICES, new ArrayList<>());
        Company company4 = new Company("company-004","Gravy Table","Moscu",
                "", Branch.EDUCATION, new ArrayList<>());
        Company company5 = new Company("company-005","Flappy Touch","Brazil",
                "", Branch.HEALTH, new ArrayList<>());
        Company company6 = new Company("company-006","AMD","Some Place",
                "", Branch.INDUSTRY, new ArrayList<>());
        //jobposts
        JobPost jb1 = new JobPost("jobpost-001",Branch.INDUSTRY,2000,Status.OPEN,"default jobpost",
                company1.getId(), new ArrayList<>(), Scholarship.PHD, Specialty.SCIENTIST);
        company1.addJobPostToList(jb1.getId());
        JobPost jb2 = new JobPost("jobpost-002",Branch.SERVICES,1900,Status.APPLICATION_ACTIVE,"default jobpost",
                company2.getId(), new ArrayList<>(),Scholarship.PHD, Specialty.ACCOUNTANT);
        company2.addJobPostToList(jb2.getId());
        JobPost jb3 = new JobPost("jobpost-003",Branch.EDUCATION,20000,Status.CLOSED,"default jobpost",
                company3.getId(), new ArrayList<>(), Scholarship.GRADE, Specialty.MANAGER);
        company3.addJobPostToList(jb3.getId());
        JobPost jb4 = new JobPost("jobpost-004",Branch.HEALTH,1000,Status.OPEN,"default jobpost",
                company4.getId(), new ArrayList<>(), Scholarship.MASTER, Specialty.ENGINEER);
        company4.addJobPostToList(jb4.getId());
        JobPost jb5 = new JobPost("jobpost-005",Branch.TOURISM,999,Status.OPEN,"default jobpost",
                company5.getId(), new ArrayList<>(), Scholarship.PHD, Specialty.SCIENTIST);
        company5.addJobPostToList(jb5.getId());
        JobPost jb6 = new JobPost("jobpost-006",Branch.AGRICULTURE,10000,Status.OPEN,"default jobpost",
                company6.getId(), new ArrayList<>(), Scholarship.PHD, Specialty.TRANSLATOR);
        company6.addJobPostToList(jb6.getId());

        //interviews
        Interview interview1 = new Interview("interview-001",new Date(2022,Calendar.DECEMBER,12),candidate1.getId(),company1.getId(),jb1.getId());
        jb1.addInterview(interview1.getId());
        Interview interview2 = new Interview("interview-002",new Date(2022,Calendar.JULY,13),candidate2.getId(),company2.getId(),jb2.getId());
        jb2.addInterview(interview2.getId());
        Interview interview3 = new Interview("interview-003",new Date(2022,Calendar.NOVEMBER,14),candidate3.getId(),company3.getId(),jb3.getId());
        jb3.addInterview(interview3.getId());
        Interview interview4 = new Interview("interview-004",new Date(2022,Calendar.AUGUST,15),candidate4.getId(),company4.getId(),jb4.getId());
        jb4.addInterview(interview4.getId());
        Interview interview5 = new Interview("interview-005",new Date(2022,Calendar.SEPTEMBER,16),candidate5.getId(),company5.getId(),jb5.getId());
        jb5.addInterview(interview5.getId());
        Interview interview6 = new Interview("interview-006",new Date(2022,Calendar.DECEMBER,17),candidate6.getId(),company6.getId(),jb6.getId());
        jb6.addInterview(interview6.getId());

        //add all to models
        models.add(candidate1);
        models.add(candidate2);
        models.add(candidate3);
        models.add(candidate4);
        models.add(candidate5);
        models.add(candidate6);
        models.add(company1);
        models.add(company2);
        models.add(company3);
        models.add(company4);
        models.add(company5);
        models.add(company6);
        models.add(interview1);
        models.add(interview2);
        models.add(interview3);
        models.add(interview4);
        models.add(interview5);
        models.add(interview6);
        models.add(jb1);
        models.add(jb2);
        models.add(jb3);
        models.add(jb4);
        models.add(jb5);
        models.add(jb6);

        //add models to agency
        this.setModels(models);//setModels sends everything to its corresponding list
    }


}