package BE;

public class Student {
    private String fName;
    private String lName;
    private String email;
    private String password;
    private int ID;
    private int schoolID;

    public Student(int ID, String fName, String lName, String email, String password, int schoolID){
        this.ID = ID;
        setSchoolID(schoolID);
        setfName(fName);
        setlName(lName);
        setEmail(email);
        setPassword(password);
    }

    public int getID() {
        return ID;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
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

    public int getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(int schoolID) {
        this.schoolID = schoolID;
    }
    @Override
    public String toString() {
        return fName + " " + lName;
    }
}
