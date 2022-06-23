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
        if(validateDate(date)) {
            this.date = date;
        } else throw new InvalidDateException(date);
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

    private boolean validateDate(Date date){//TODO: add to uml
        Calendar today_calendar = Calendar.getInstance();
        Date today_date = new Date(today_calendar.get(Calendar.YEAR),today_calendar.get(Calendar.MONTH),today_calendar.get(Calendar.DAY_OF_MONTH));
        return date.after(today_date);
    }
}
