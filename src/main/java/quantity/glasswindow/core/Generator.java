package quantity.glasswindow.core;

import quantity.glasswindow.core.customExceptions.InvalidTypeException;

public abstract class Generator{
    private int companyLastID;
    private int candidateLastID;
    private int jobPostLastID;
    private int interviewLastID;

    public String genID(String className) throws InvalidTypeException {
        String result = className.concat("-");
        switch (className.toLowerCase()) {
            case "candidate" -> {
                candidateLastID += 1;
                result = result.concat(String.valueOf(candidateLastID));
            }
            case "company" -> {
                companyLastID += 1;
                result = result.concat(String.valueOf(companyLastID));
            }
            case "jobpost" -> {
                jobPostLastID += 1;
                result = result.concat(String.valueOf(jobPostLastID));
            }
            case "interview" -> {
                interviewLastID += 1;
                result = result.concat(String.valueOf(interviewLastID));
            }
            default -> throw new InvalidTypeException(className);
        }
        return result;
    }

    public void reset(){
        candidateLastID = 0;
        companyLastID = 0;
        jobPostLastID = 0;
        interviewLastID = 0;
    }
}
