package quantity.glasswindow.core;
import quantity.glasswindow.core.customExceptions.DuplicatedIDException;
import quantity.glasswindow.core.customExceptions.InvalidDateException;
import quantity.glasswindow.core.customExceptions.InvalidIDException;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Calendar;
import java.util.Date;

public class Interview extends Model {
    private LocalDate date;
    private String candidate;
    private String company;
    private String jobPost;

    public Interview(String id, LocalDate date, String candidate,
                     String company, String jobPost) throws InvalidIDException, InvalidDateException, DuplicatedIDException {
        super(id);
        this.setDate(date);
        this.setCandidate(candidate);
        this.setCompany(company);
        this.setJobPost(jobPost);
    }

    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) throws InvalidDateException {
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

    private boolean validateDate(LocalDate date){
        LocalDate today_date = LocalDate.now();
        return date.isAfter(today_date);
    }
}
