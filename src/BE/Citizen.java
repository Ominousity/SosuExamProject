package BE;

public class Citizen
{
    private int ID;
    private String fName;
    private String lName;
    private String address;
    private String dob;
    private String sex;
    private boolean isTemplate;

    public Citizen(int ID, String fName, String lName, String address, String dob, String sex, boolean isTemplate){
        this.ID = ID;
        setFName(fName);
        setLName(lName);
        setAddress(address);
        setDob(dob);
        setSex(sex);
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public boolean isTemplate() {
        return isTemplate;
    }

    public void setTemplate(boolean template) {
        this.isTemplate = template;
    }
}
