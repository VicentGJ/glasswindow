package quantity.glasswindow.core;

import java.util.ArrayList;

public class Agency {
    //maybe add agency name and stuff like that
    private ArrayList<Candidate> candidateList;
    private ArrayList<Company> companyList;
    private ArrayList<JobPost> jobPostList;
    private ArrayList<Interview> interviewList;

    //constructor
    public Agency(){
        this.setCandidateList(new ArrayList<>());
        this.setCompanyList(new ArrayList<>());
        this.setJobPostList(new ArrayList<>());
        this.setInterviewList(new ArrayList<>());
    }

    //getters & setters
    public ArrayList<Candidate> getCandidateList() {
        return candidateList;
    }

    public void setCandidateList(ArrayList<Candidate> candidateList) {
        this.candidateList = candidateList;
    }

    public ArrayList<Company> getCompanyList() {
        return companyList;
    }

    public void setCompanyList(ArrayList<Company> companyList) {
        this.companyList = companyList;
    }

    public ArrayList<JobPost> getJobPostList() {
        return jobPostList;
    }

    public void setJobPostList(ArrayList<JobPost> jobPostList) {
        this.jobPostList = jobPostList;
    }

    public ArrayList<Interview> getInterviewList() {
        return interviewList;
    }

    public void setInterviewList(ArrayList<Interview> interviewList) {
        this.interviewList = interviewList;
    }

    //--------CRUD operations
    //Simple-add
    public void simpleAddToList(Object o){
        String classname = o.getClass().getSimpleName();
        if(classname.equalsIgnoreCase("Candidate"))
            addCandidateToList((Candidate) o);
        else if(classname.equalsIgnoreCase("Company"))
            addCompanyToList((Company) o);
        else if(classname.equalsIgnoreCase("JobPost"))
            addJobPostToList((JobPost) o);
        else if(classname.equalsIgnoreCase("Interview"))
            addInterviewToList((Interview) o);
        else
            System.out.println("No list for: "+ classname);
    }

    //Simple-remove
    public void simpleRemoveFromList(Object o){
        String classname = o.getClass().getSimpleName();
        if(classname.equalsIgnoreCase("Candidate"))
            removeCandidateFromList((Candidate) o);
        else if(classname.equalsIgnoreCase("Company"))
            removeCompanyFromList((Company) o);
        else if(classname.equalsIgnoreCase("JobPost"))
            removeJobPostFromList((JobPost) o);
        else if(classname.equalsIgnoreCase("Interview"))
            removeInterviewFromList((Interview) o);
        else
            System.out.println("No list for: "+ classname);

    }

    //Candidate
    private void addCandidateToList(Candidate c){
        this.candidateList.add(c);
    }
    private void removeCandidateFromList(Candidate c){
        if(!candidateList.isEmpty()){
            int i = 0;
            while(i < candidateList.size()){
                Candidate current = candidateList.get(i);
                if(current.getId().equals(c.getId())){
                    candidateList.remove(current);
                    break;
                }
                i++;
            }
        }
    }
    //Company
    private void addCompanyToList(Company c){
        this.companyList.add(c);
    }
    private void removeCompanyFromList(Company c){
        if(!companyList.isEmpty()){
            int i = 0;
            while(i < companyList.size()){
                Company current = companyList.get(i);
                if(current.getId().equals(c.getId())){
                    companyList.remove(current);
                    break;
                }
                i++;
            }
        }
    }
    //Job Post
    private void addJobPostToList(JobPost c){
        this.jobPostList.add(c);
    }
    private void removeJobPostFromList(JobPost c){
        if(!jobPostList.isEmpty()){
            int i = 0;
            while(i < jobPostList.size()){
                JobPost current = jobPostList.get(i);
                if(current.getId().equals(c.getId())){
                    jobPostList.remove(current);
                    break;
                }
                i++;
            }
        }
    }
    //Interview
    private void addInterviewToList(Interview c){
        this.interviewList.add(c);
    }
    private void removeInterviewFromList(Interview c){
        if(!interviewList.isEmpty()){
            int i = 0;
            while(i < interviewList.size()){
                Interview current = interviewList.get(i);
                if(current.getId().equals(c.getId())){
                    interviewList.remove(current);
                    break;
                }
                i++;
            }
        }
    }
}
