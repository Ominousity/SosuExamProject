package BE;

public class Citizen
{
    private String ID;
    private String FName;
    private String LName;
    private String Address;
    private String CPR;
    private String SchoolID;
    private String GeneralinfoID;

    public Citizen(String ID, String FName, String LName,String Address,String CPR,String SchoolID, String GeneralinfoID){
        setID(ID);
        setFName(FName);
        setLName(LName);
        setAddress(Address);
        setCPR(CPR);
        setSchoolID(SchoolID);
        setGeneralinfoID(GeneralinfoID);
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCPR() {
        return CPR;
    }

    public void setCPR(String CPR) {
        this.CPR = CPR;
    }

    public String getSchoolID() {
        return SchoolID;
    }

    public void setSchoolID(String schoolID) {
        SchoolID = schoolID;
    }

    public String getGeneralinfoID() {
        return GeneralinfoID;
    }

    public void setGeneralinfoID(String generalinfoID) {
        GeneralinfoID = generalinfoID;
    }
}
