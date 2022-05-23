package BE;

public class User {
    private String fName;
    private String lName;
    private String email;
    private String password;
    private int ID;
    private int schoolID;
    private String userType;

    public User(int ID, String fName, String lName, String email, String password, int schoolID, String userType) {
        this.ID = ID;
        setSchoolID(schoolID);
        setFName(fName);
        setLName(lName);
        setEmail(email);
        setPassword(password);
        setUserType(userType);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getID() {
        return ID;
    }

    public int getSchoolID()
    {
        return schoolID;
    }

    public void setSchoolID(int schoolID)
    {
        this.schoolID = schoolID;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return fName + " " + lName;
    }
}
