package BE;

public class Teacher
{
    private String FName;
    private String LName;
    private String Email;
    private String Password;
    private int ID;
    private int SchoolID;

    public Teacher(int ID, String FName,String LName,String Email,String Password,int SchoolID){
        this.ID = ID;
        setFName(FName);
        setLName(LName);
        setEmail(Email);
        setPassword(Password);
        setSchoolID(SchoolID);
    }

    public int getID() {
        return ID;
    }

    public void setSchoolID(int schoolID)
    {
        SchoolID = schoolID;
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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

}
