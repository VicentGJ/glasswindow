package quantity.glasswindow.core;
import quantity.glasswindow.core.customExceptions.DuplicatedIDException;
import quantity.glasswindow.core.customExceptions.InvalidDateException;
import quantity.glasswindow.core.customExceptions.InvalidIDException;

import java.util.Calendar;
import java.util.Date;

public class Interview extends Model {
    private Date date;
    private String candidate;
    private String company;
    private String jobPost;

    public Interview(String id, Date date, String candidate,
                     String company, String jobPost) throws InvalidIDException, InvalidDateException, DuplicatedIDException {
        super(id);
        this.setDate(date);
        this.setCandidate(candidate);
        this.setCompany(company);
        this.setJobPost(jobPost);
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) throws InvalidDateException {
        Calendar today = Calendar.getInstance();
        if(date.getYear() >= today.get(Calendar.YEAR)) {
            if (date.getMonth() >= today.get(Calendar.MONTH)) {
                if (date.getDay() >= today.get(Calendar.DAY_OF_MONTH)) {
                    this.date = date;
                } else throw new InvalidDateException("Invalid day: " + date.getDay());
            } else throw new InvalidDateException("Invalid month: " + date.getMonth());
        }else throw new InvalidDateException("Invalid year: " + date.getYear());
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
