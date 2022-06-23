package quantity.glasswindow.core;

import quantity.glasswindow.core.enumerations.ProgrammingLang;
import quantity.glasswindow.core.enumerations.Specialty;

import java.util.ArrayList;

public class InfoDeveloper implements IAdditionalInfo{
    private ArrayList<ProgrammingLang> languages;

    public InfoDeveloper(ArrayList<ProgrammingLang> languages) {
        this.setLanguages(languages);
    }

    public ArrayList<ProgrammingLang> getLanguages() {
        return languages;
    }

    public void setLanguages(ArrayList<ProgrammingLang> languages) {
        this.languages = languages;
    }

    public void addLanguage(ProgrammingLang newLang){
        languages.add(newLang);
    }

    public void removeLanguage(ProgrammingLang lang){
        languages.remove(lang);
    }

    @Override
    public Enum<Specialty> getType() {
        return Specialty.DEVELOPER;
    }
}
