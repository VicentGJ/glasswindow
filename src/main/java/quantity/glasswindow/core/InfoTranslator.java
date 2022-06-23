package quantity.glasswindow.core;

import quantity.glasswindow.core.enumerations.Languages;
import quantity.glasswindow.core.enumerations.Specialty;

import java.util.ArrayList;

public class InfoTranslator implements IAdditionalInfo{
    private ArrayList<Languages> langs;

    public InfoTranslator(ArrayList<Languages> langs) {
        this.setLangs(langs);
    }

    public ArrayList<Languages> getLangs() {
        return langs;
    }

    public void setLangs(ArrayList<Languages> langs) {
        this.langs = langs;
    }

    public void addLang(Languages lang){
        langs.add(lang);
    }
    public void removeLang(Languages lang){
        langs.remove(lang);
    }

    @Override
    public Enum<Specialty> getType() {
        return Specialty.TRANSLATOR;
    }
}
