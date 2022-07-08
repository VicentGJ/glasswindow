package quantity.glasswindow.utils;

public class InterviewDay {
    private final String candidate;
    private final String jobPost;
    private final int day;

    public InterviewDay(String candidate, String jobPost, int day){
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

    public int getDay() {
        return day;
    }
}
