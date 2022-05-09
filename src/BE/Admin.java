package BE;

public class Admin
{
    private int id;
    private int schoolId;
    private String Email;
    private String password;
    private int SchoolId;
    private boolean isAdmin;

    public Admin (int id, String Email, String password, int SchoolId, boolean isAdmin){
        this.id = id;
        setSchoolId(schoolId);
        setEmail(Email);
        setPassword(password);
        setIsAdmin(isAdmin);
    }

    public boolean getIsAdmin(){return isAdmin;}

    public void setIsAdmin(boolean isAdmin){this.isAdmin = isAdmin;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSchoolId(int id){
        this.schoolId = id;
    }

    public int getSchoolId(){
        return schoolId;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
