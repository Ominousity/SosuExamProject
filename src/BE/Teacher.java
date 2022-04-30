package BE;

public class Teacher
{
    private String TeacherID;
    private String FName;
    private String LName;
    private String Email;
    private String Password;
    private boolean isTeacher;
    private String SchoolID;

    public Teacher(String TeacherID, String FName,String LName,String Email,String Password,boolean isTeacher, String SchoolID){
        setTeacherID(TeacherID);
        setFName(FName);
        setLName(LName);
        setEmail(Email);
        setPassword(Password);
        setTeacher(isTeacher);
        setSchoolID(SchoolID);
    }

    public String getTeacherID() {
        return TeacherID;
    }

    public void setTeacherID(String teacherID) {
        TeacherID = teacherID;
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

    public boolean isTeacher() {
        return isTeacher;
    }

    public void setTeacher(boolean teacher) {
        isTeacher = teacher;
    }

    public String getSchoolID() {
        return SchoolID;
    }

    public void setSchoolID(String schoolID) {
        SchoolID = schoolID;
    }
}
