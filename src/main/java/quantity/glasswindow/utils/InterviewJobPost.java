package quantity.glasswindow.utils;

import java.time.LocalDate;

public class InterviewJobPost {
    private String candidate;
    private String jobPost;
    private LocalDate day;

    public InterviewJobPost(String candidate, String jobPost, LocalDate day){
        this.candidate = candidate;
        this.day = day;
        this.jobPost = jobPost;
    }

    public String getCandidate() {
        return candidate;
    }

    public String getJobPost() {
        return jobPost;
    }

    public LocalDate getDay() {
        return day;
    }
}
