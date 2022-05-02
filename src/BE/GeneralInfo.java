package BE;

public class GeneralInfo
{
    private String mestring;
    private String motivation;
    private String ressourcer;

    public GeneralInfo (String mestring,String motivation,String ressourcer){
        setMestring(mestring);
        setMotivation(motivation);
        setRessourcer(ressourcer);
    }

    public String getMestring() {
        return mestring;
    }

    public void setMestring(String mestring) {
        this.mestring = mestring;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public String getRessourcer() {
        return ressourcer;
    }

    public void setRessourcer(String ressourcer) {
        this.ressourcer = ressourcer;
    }
}
