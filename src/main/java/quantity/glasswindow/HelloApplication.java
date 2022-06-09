package quantity.glasswindow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import quantity.glasswindow.core.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Glass Window");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        //agency
        Agency agency = Agency.create(); //create a singleton object
        ArrayList<Model> models = new ArrayList<>();
        //candidates
        Candidate candidate1 = new Candidate("candidate-001","Bruce Banner", Gender.MASCULINE,"New York",
                "05158899", Scholarship.PHD, Specialty.SCIENTIST,Branch.INDUSTRY);
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
                company1, new ArrayList<>());
        company1.addJobPostToList(jb1);
        JobPost jb2 = new JobPost("jobpost-002",Branch.SERVICES,1900,Status.APPLICATION_ACTIVE,"default jobpost",
                company2, new ArrayList<>());
        company2.addJobPostToList(jb2);
        JobPost jb3 = new JobPost("jobpost-003",Branch.EDUCATION,20000,Status.CLOSED,"default jobpost",
                company3, new ArrayList<>());
        company3.addJobPostToList(jb3);
        JobPost jb4 = new JobPost("jobpost-004",Branch.HEALTH,1000,Status.OPEN,"default jobpost",
                company4, new ArrayList<>());
        company4.addJobPostToList(jb4);
        JobPost jb5 = new JobPost("jobpost-005",Branch.TOURISM,999,Status.OPEN,"default jobpost",
                company5, new ArrayList<>());
        company5.addJobPostToList(jb5);
        JobPost jb6 = new JobPost("jobpost-006",Branch.AGRICULTURE,10000,Status.OPEN,"default jobpost",
                company6, new ArrayList<>());
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
        agency.setModels(models);
        launch();
    }
}