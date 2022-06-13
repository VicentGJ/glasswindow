package quantity.glasswindow.core;

import java.security.KeyException;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.*;

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
    public Model getObject(String id) throws Exception {
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
        @SuppressWarnings("unchecked") ArrayList<Model> result = (ArrayList<Model>) orderedList.clone();
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
                        // TODO: Right now this is specific to getAppliances(), need to refactor to work more generally later
                        int filter_month = (int) filter.get(key);
                        for (Model m : orderedList)
                            if (m instanceof Interview && !(((Interview) m).getDate().getMonth() == filter_month))
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

    public ArrayList<JobPost> getQualifiedJobPostList (Candidate candidate) {
        HashMap<String, Object> filter = new HashMap<>();
        ArrayList<JobPost> result = new ArrayList<>();
        filter.put("branch", candidate.getSector());
        filter.put("scholarship", candidate.getScholarship());
        filter.put("specialty", candidate.getSpecialty());
        filter.put("status", Status.OPEN);
        for (Model i: this.getObjectList("JobPost", OrderBy.ID, filter)) {
            result.add((JobPost) i);
        }
        return result;
    }

    public ArrayList<Candidate> getAppliances (Company company, int month) {
        month--;
        ArrayList<Candidate> result = new ArrayList<>();
        ArrayList<JobPost> jobPosts = new ArrayList<>(company.getJobPostList());
        ArrayList<Interview> interviews = new ArrayList<>();
        for (JobPost j: jobPosts) {
            interviews = j.getInterviewList();
            for (Interview i: interviews) {
                if (i.getDate().getMonth() == month) {
                    result.add(i.getCandidate());
                }
            }
        }
        return result;
    }
     public ArrayList<ArrayList<Interview>> getInterviewsMonth (String id, int month) {
        int lenght = Month.of(month).length(Year.now().isLeap());
        ArrayList<ArrayList<Interview>> result = new ArrayList<>(
                Collections.nCopies(lenght, (ArrayList<Interview>) null)
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
                    "Interview", OrderBy.ID, filter)
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
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        return result;
     }

     public ArrayList<ArrayList<Interview>> getCompanyInterviews(Company company) {
        ArrayList<ArrayList<Interview>> result = new ArrayList<>(
                Collections.nCopies(company.getJobPostList().size(), (ArrayList<Interview>) null)
        );
        for (int i = 0; i < company.getJobPostList().size(); i++) {
            for (Interview j: company.getJobPostList().get(i).getInterviewList()) {
                if (result.get(i) == null){
                    ArrayList<Interview> temp = new ArrayList<>();
                    temp.add(j);
                    result.set(i, temp);
                }
                else result.get(i).add(j);
            }
        }
        return result;
     }
    public void initTestData() {
        ArrayList<Model> models = new ArrayList<>();
        //candidates
        Candidate candidate1 = new Candidate("candidate-001","Bruce Banner", Gender.MASCULINE,"New York",
                "05158899", Scholarship.PHD, Specialty.SCIENTIST, Branch.INDUSTRY);
        Candidate candidate2 = new Candidate("candidate-002","Tonny Stark", Gender.MASCULINE,"New York",
                "05155229", Scholarship.PHD, Specialty.ECONOMIST,Branch.INDUSTRY);
        Candidate candidate3 = new Candidate("candidate-003","Clark Kent", Gender.MASCULINE,"Kansas",
                "33156899", Scholarship.BASIC, Specialty.ECONOMIST,Branch.SERVICES);
        Candidate candidate4 = new Candidate("candidate-004","Bruce Wayne", Gender.MASCULINE,"Gotham",
                "05675799", Scholarship.PHD, Specialty.ARCHITECT,Branch.INDUSTRY);
        Candidate candidate5 = new Candidate("candidate-005","Carol Danvers", Gender.FEMININE, "Outer Space",
                "05133339", Scholarship.MASTER, Specialty.ENGINEER,Branch.TOURISM);
        Candidate candidate6 = new Candidate("candidate-006","Felicia Hardy", Gender.FEMININE,"New York",
                "05158449", Scholarship.GRADE, Specialty.TRANSLATOR,Branch.TOURISM);
        //companies
        Company company1 = new Company("company-001","Last Quarter","Nebraska",
                new ArrayList<>(), Branch.INDUSTRY, new ArrayList<>());
        Company company2 = new Company("company-002","Avenue Studios","New York",
                new ArrayList<>(), Branch.TOURISM, new ArrayList<>());
        Company company3 = new Company("company-003","Icy Mountain","London",
                new ArrayList<>(), Branch.SERVICES, new ArrayList<>());
        Company company4 = new Company("company-004","Gravy Table","Moscu",
                new ArrayList<>(), Branch.EDUCATION, new ArrayList<>());
        Company company5 = new Company("company-005","Flappy Touch","Brazil",
                new ArrayList<>(), Branch.HEALTH, new ArrayList<>());
        Company company6 = new Company("company-006","AMD","Some Place",
                new ArrayList<>(), Branch.INDUSTRY, new ArrayList<>());
        //jobposts
        JobPost jb1 = new JobPost("jobpost-001",Branch.INDUSTRY,2000,Status.OPEN,"default jobpost",
                company1, new ArrayList<>(), Scholarship.PHD, Specialty.SCIENTIST);
        company1.addJobPostToList(jb1);
        JobPost jb2 = new JobPost("jobpost-002",Branch.SERVICES,1900,Status.APPLICATION_ACTIVE,"default jobpost",
                company2, new ArrayList<>(),Scholarship.PHD, Specialty.ACCOUNTANT);
        company2.addJobPostToList(jb2);
        JobPost jb3 = new JobPost("jobpost-003",Branch.EDUCATION,20000,Status.CLOSED,"default jobpost",
                company3, new ArrayList<>(), Scholarship.GRADE, Specialty.MANAGER);
        company3.addJobPostToList(jb3);
        JobPost jb4 = new JobPost("jobpost-004",Branch.HEALTH,1000,Status.OPEN,"default jobpost",
                company4, new ArrayList<>(), Scholarship.MASTER, Specialty.ENGINEER);
        company4.addJobPostToList(jb4);
        JobPost jb5 = new JobPost("jobpost-005",Branch.TOURISM,999,Status.OPEN,"default jobpost",
                company5, new ArrayList<>(), Scholarship.PHD, Specialty.SCIENTIST);
        company5.addJobPostToList(jb5);
        JobPost jb6 = new JobPost("jobpost-006",Branch.AGRICULTURE,10000,Status.OPEN,"default jobpost",
                company6, new ArrayList<>(), Scholarship.PHD, Specialty.TRANSLATOR);
        company6.addJobPostToList(jb6);

        //interviews
        Interview interview1 = new Interview("interview-001",new Date(),candidate1,company1,jb1);
        jb1.addInterview(interview1);
        Interview interview2 = new Interview("interview-002",new Date(),candidate2,company2,jb2);
        jb2.addInterview(interview2);
        Interview interview3 = new Interview("interview-003",new Date(),candidate3,company3,jb3);
        jb3.addInterview(interview3);
        Interview interview4 = new Interview("interview-004",new Date(),candidate4,company4,jb4);
        jb4.addInterview(interview4);
        Interview interview5 = new Interview("interview-005",new Date(),candidate5,company5,jb5);
        jb5.addInterview(interview5);
        Interview interview6 = new Interview("interview-006",new Date(),candidate6,company6,jb6);
        jb6.addInterview(interview6);

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
        this.setModels(models);
    }
}