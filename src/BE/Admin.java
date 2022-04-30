package BE;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Admin
{
    private int id;
    private String Email;
    private String password;
    private int SchoolId;

    public Admin (int id, String Email, String password,int schoolId){
        setId(id);
        setEmail(Email);
        setPassword(password);
        setSchoolId(schoolId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getSchoolId() {
        return SchoolId;
    }

    public void setSchoolId(int schoolId) {
        this.SchoolId = schoolId;
    }
}
