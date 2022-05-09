package BE;

public class Citizen
{
    private int ID;
    private String FName;
    private String LName;
    private String Address;
    private String CPR;
    private int SchoolID;
    private String StudentFName;
    private String StudentLName;


    public Citizen(int ID, String FName, String LName,String Address,String CPR, String StudentFName, String StudentLName){
        ID = this.ID;
        setFName(FName);
        setLName(LName);
        setAddress(Address);
        setCPR(CPR);
        setStudentFName(StudentFName);
        setStudentLName(StudentLName);
    }

    public int getID() {
        return ID;
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

    public void setStudentFName(String StudentFName){this.StudentFName = StudentFName;}

    public String getStudentFName(){return StudentFName;}

    public void setStudentLName(String StudentLName){this.StudentLName = StudentLName;}

    public String getStudentLName(){return StudentLName;}

}
