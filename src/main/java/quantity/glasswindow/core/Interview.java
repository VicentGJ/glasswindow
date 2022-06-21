package quantity.glasswindow.core;

import quantity.glasswindow.core.customExceptions.InvalidIDException;

import java.util.Date;

public class Interview extends Model {
    private Date date;
    private String candidate;
    private String company;
    private String jobPost;

    public Interview(String id, Date date, String candidate, String company, String jobPost) throws InvalidIDException {
        super(id);
        this.setDate(date);
        this.setCandidate(candidate);
        this.setCompany(company);
        this.setJobPost(jobPost);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCandidate() {
        return candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getJobPost() {
        return jobPost;
    }

    public void setJobPost(String jobPost) {
        this.jobPost = jobPost;
    }
}
