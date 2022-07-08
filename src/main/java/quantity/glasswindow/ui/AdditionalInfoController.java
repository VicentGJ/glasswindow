package quantity.glasswindow.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import quantity.glasswindow.core.*;
import quantity.glasswindow.core.enumerations.*;

import java.util.ArrayList;

public class AdditionalInfoController {
    @FXML
    private GridPane grid, grid2;
    ComboBox comboBox;
    private ArrayList<CheckBox> checkBoxes;
    private Candidate candidate;
    private final ObservableList<String> developer = FXCollections.observableArrayList(
            "PYTHON",
            "C",
            "C++",
            "CSHARP",
            "JAVA",
            "GDSCRIPT",
            "PHP",
            "HTML",
            "CSS",
            "JS",
            "RUBY",
            "PERL",
            "RUST",
            "TYPESCRIPT",
            "SQL"
    );
    private final ObservableList<String> engineer = FXCollections.observableArrayList(
            "CIVIL",
            "INDUSTRIAL",
            "BIOMEDICAL",
            "TELECOMMUNICATIONS",
            "INFORMATIC",
            "AUTOMATIC",
            "ELECTRONIC",
            "MECHANIC"
    );
    private final ObservableList<String> doctor = FXCollections.observableArrayList(
            "ALLERGIST",
            "IMMUNOLOGIST",
            "DERMATOLOGIST",
            "RADIOLOGIST",
            "EMERGENCY_MED",
            "FAMILY_MED",
            "GENETICS",
            "NEUROLOGIST",
            "GYNECOLOGIST",
            "OPHTHALMOLOGIST",
            "PATHOLOGIST",
            "PREVENTIVE_MED",
            "REHAB_MED",
            "PSYCHIATRIST",
            "SURGEON",
            "UROLOGIST"
    );

    private final ObservableList<String> translator = FXCollections.observableArrayList(
            "ARABIC",
            "CHINESE",
            "DANISH",
            "DUTCH",
            "ENGLISH",
            "FRENCH",
            "GERMAN",
            "GREEK",
            "HEBREW",
            "HINDI",
            "HUNGARIAN",
            "ITALIAN",
            "JAPANESE",
            "KOREAN",
            "RUSSIAN",
            "SPANISH"
    );
    private final ObservableList<String> scientist = FXCollections.observableArrayList(
            "ECOLOGY",
            "BIOLOGY",
            "CHEMISTRY",
            "MATHEMATICIAN",
            "MEDICAL_SCI",
            "PSYCHOLOGY",
            "GEOLOGY",
            "PALEONTOLOGY",
            "STATISTIC",
            "PHARMACOLOGY",
            "NEUROSCIENCE",
            "COMPUTER_SCI",
            "OCEANOGRAPHY",
            "MICROBIOLOGY",
            "PHYSICS",
            "FORENSIC"
    );

    public void loadViewInfo(Candidate newcandidate) {
        this.candidate = newcandidate;
        Specialty specialty = candidate.getSpecialty();
        Branch branch = candidate.getSector();
        checkBoxes = new ArrayList<>() ;
        if (specialty.equals(Specialty.ENGINEER)) {
            grid.setVisible(true);
            comboBox = new ComboBox<>(engineer);
            grid.add(comboBox,1,1);
        }
        else if(specialty.equals(Specialty.DEVELOPER)){
            grid.setVisible(true);
            for(String s:developer) {
                CheckBox checkBox = new CheckBox(s);
                checkBoxes.add(checkBox);
            }
            int count = 0;
            for(int i = 0; i < 3; i++){
                for(int j=0; j < 4; j++){
                    if(count < checkBoxes.size())
                        grid.add(checkBoxes.get(count),i,j);
                    count++;
                }
            }
        }else if(specialty.equals(Specialty.SCIENTIST)){
            grid.setVisible(true);
            for(String s:scientist) {
                CheckBox checkBox = new CheckBox(s);
                checkBoxes.add(checkBox);
            }
            int count = 0;
            for(int i = 0; i < 5; i++){
                for(int j=0; j < 4; j++){
                    if(count < checkBoxes.size())
                        grid.add(checkBoxes.get(count),i,j);
                    count++;}
            }

        } else if (specialty.equals(Specialty.DOCTOR)) {
            grid.setVisible(true);
            comboBox = new ComboBox<>(doctor);
            grid.add(comboBox, 1, 1);
        }else if(specialty.equals(Specialty.TRANSLATOR)){
            grid.setVisible(true);
            for(String s:translator) {
                CheckBox checkBox = new CheckBox(s);
                checkBoxes.add(checkBox);
            }
            int count = 0;
            for(int i = 0; i < 5; i++){
                for(int j=0; j < 4; j++){
                    if(count < checkBoxes.size())
                        grid.add(checkBoxes.get(count),i,j);
                    count++;
                }
            }
        }else if(specialty.equals(Specialty.GUARD)){
            grid.setVisible(true);
            CheckBox checkBox = new CheckBox("Has efficiency tests correct");
            grid.add(checkBox,1,1);
        }

        if (branch.equals(Branch.HEALTH)){
            grid2.setVisible(true);
            CheckBox checkBox = new CheckBox("Has certificate correct");
            grid2.add(checkBox,1,1);
        } else if (branch.equals(Branch.TOURISM)) {
            grid2.setVisible(true);
            CheckBox checkBox = new CheckBox("Has licence correct");
            grid2.add(checkBox,1,1);

        }
    }

    @FXML
    protected void onSaveButton(ActionEvent event){
        Specialty specialty = candidate.getSpecialty();
        if(specialty.equals(Specialty.ENGINEER)){
            InfoEngineer infoEngineer = new InfoEngineer(EngineerSpec.values()[engineer.indexOf(comboBox.getValue())]);
            candidate.addAdditionalInfo(infoEngineer);
        }else if(specialty.equals(Specialty.DOCTOR)){
            InfoDoctor infoDoctor = new InfoDoctor(DoctorSpec.values()[doctor.indexOf(comboBox.getValue())]);
            candidate.addAdditionalInfo(infoDoctor);
        }else if(specialty.equals(Specialty.DEVELOPER)){
            ArrayList<ProgrammingLang> programmingLangs = new ArrayList<>();
            for(CheckBox checkBox : checkBoxes){
                if (checkBox.isSelected()){
                    programmingLangs.add(ProgrammingLang.values()[developer.indexOf(checkBox.getText())]);
                }
            }
            InfoDeveloper infoDeveloper = new InfoDeveloper(programmingLangs);
            candidate.addAdditionalInfo(infoDeveloper);
        }else if(specialty.equals(Specialty.SCIENTIST)){
            ArrayList<ScientistSpec> scientistSpecs = new ArrayList<>();
            for(CheckBox checkBox : checkBoxes){
                if (checkBox.isSelected()){
                    scientistSpecs.add(ScientistSpec.values()[scientist.indexOf(checkBox.getText())]);
                }
            }
            InfoScientist infoScientist = new InfoScientist(scientistSpecs);
            candidate.addAdditionalInfo(infoScientist);
        }
        Branch branch = candidate.getSector();
        if(branch.equals(Branch.HEALTH)){
            InfoHealth infoHealth = new InfoHealth(checkBoxes.get(0).isSelected());
            candidate.addAdditionalInfo(infoHealth);
        } else if (branch.equals(Branch.TOURISM)) {
            InfoTourism infoTourism = new InfoTourism(checkBoxes.get(0).isSelected());
            candidate.addAdditionalInfo(infoTourism);
        }
        System.out.println(candidate);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
