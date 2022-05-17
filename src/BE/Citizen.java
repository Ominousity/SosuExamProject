package BE;

public class Citizen
{
    private int ID;
    private String fName;
    private String lName;
    private String address;
    private String dob;
    private String sex;

    public Citizen(int ID, String fName, String lName, String address, String dob, String sex){
        this.ID = ID;
        setFName(fName);
        setLName(lName);
        setAddress(address);
        setDob(dob);
        setSex(sex);
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

}
