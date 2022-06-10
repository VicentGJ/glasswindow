package quantity.glasswindow.core;

import java.util.Date;

public class Interview extends Model {
    private Date date;
    private Candidate candidate;
    private Company company;
    private JobPost jobPost;

    public Interview(String id, Date date, Candidate candidate, Company company, JobPost jobPost) {
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

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public JobPost getJobPost() {
        return jobPost;
    }

    public void setJobPost(JobPost jobPost) {
        this.jobPost = jobPost;
    }
}
