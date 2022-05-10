package BE;

public class Citizen
{
    private int ID;
    private String fName;
    private String lName;
    private String address;
    private String dob;
    private String cpr;

    public Citizen(int ID, String fName, String lName, String address, String dob, String cpr){
        this.ID = ID;
        setFName(fName);
        setLName(lName);
        setAddress(address);
        setDob(dob);
        setCPR(cpr);
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

    public String getCPR() {
        return cpr;
    }

    public void setCPR(String cpr) {
        this.cpr = cpr;
    }

}
