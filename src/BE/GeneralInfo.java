package BE;

public class GeneralInfo
{
    private int id;
    private String mestring;
    private String motivation;
    private String ressourcer;
    private String roller;
    private String vaner;
    private String uddannelseJob;
    private String Livshistorie;
    private String Netværk;
    private String Helbredsoplysninger;
    private String Hjælpemidler;
    private String BoligIndretning;
    public GeneralInfo (int id, String mestring,String motivation,String ressourcer,String roller,String vaner, String uddannelseJob, String livshistorie,String netværk, String helbredsoplysninger, String hjælpemidler, String boligIndretning){
        setMestring(mestring);
        setMotivation(motivation);
        setRessourcer(ressourcer);
        setRoller(roller);
        setVaner(vaner);
        setUddannelseJob(uddannelseJob);
        setLivshistorie(livshistorie);
        setNetværk(netværk);
        setHelbredsoplysninger(helbredsoplysninger);
        setHjælpemidler(hjælpemidler);
        setBoligIndretning(boligIndretning);
        this.id = id;
    }

    public int getId()
    {
        return id;
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

    public String getRoller() {
        return roller;
    }

    public void setRoller(String roller) {
        this.roller = roller;
    }

    public String getVaner() {
        return vaner;
    }

    public void setVaner(String vaner) {
        this.vaner = vaner;
    }

    public String getUddannelseJob() {
        return uddannelseJob;
    }

    public void setUddannelseJob(String uddannelseJob) {
        this.uddannelseJob = uddannelseJob;
    }

    public String getLivshistorie() {
        return Livshistorie;
    }

    public void setLivshistorie(String livshistorie) {
        Livshistorie = livshistorie;
    }

    public String getNetværk() {
        return Netværk;
    }

    public void setNetværk(String netværk) {
        Netværk = netværk;
    }

    public String getHjælpemidler() {
        return Hjælpemidler;
    }

    public void setHjælpemidler(String hjælpemidler) {
        Hjælpemidler = hjælpemidler;
    }

    public String getHelbredsoplysninger() {
        return Helbredsoplysninger;
    }

    public void setHelbredsoplysninger(String helbredsoplysninger) {
        Helbredsoplysninger = helbredsoplysninger;
    }

    public String getBoligIndretning() {
        return BoligIndretning;
    }

    public void setBoligIndretning(String boligIndretning) {
        BoligIndretning = boligIndretning;
    }
}
