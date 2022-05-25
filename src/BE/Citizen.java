package BE;

public class Citizen
{
    private int ID;
    private String fName;
    private String lName;
    private int age;
    private boolean isTemplate;

    public Citizen(int ID, String fName, String lName, int age, boolean isTemplate){
        this.ID = ID;
        setFName(fName);
        setLName(lName);
        setAge(age);
        setTemplate(isTemplate);
    }

    public int getID() {
        return ID;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String FName) {
        this.fName = FName;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String LName) {
        this.lName = LName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isTemplate() {
        return isTemplate;
    }

    public void setTemplate(boolean template) {
        this.isTemplate = template;
    }
}
