package quantity.glasswindow.core;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

public class ProfileDeveloper {
    private ArrayList<ProgrammingLang> languages;

    public ProfileDeveloper(ArrayList<ProgrammingLang> languages) {
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
}
