package UI.MVC.Model;

import BE.Admin;
import BE.Student;
import BE.Teacher;
import BLL.AdminManager;
import BLL.StudentManager;
import BLL.Utility.Encryptor;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminModel
{
    private AdminManager adminManager;
    private StudentManager studentManager;
    private Encryptor encryptor;

    public AdminModel() throws IOException {
        adminManager = new AdminManager();
        studentManager = new StudentManager();
        encryptor = new Encryptor();
    }

    public ArrayList<Object> threeToOne(ArrayList<Teacher> teachers,ArrayList<Student> students,ArrayList <Admin> admins){
        ArrayList<Object> threeToOneList = new ArrayList<>();

        for (Teacher teacher : teachers){
            threeToOneList.add(teacher);
        }
        for (Student student : students){
            threeToOneList.add(student);
        }
        for (Admin admin : admins){
            threeToOneList.add(admin);
        }
        return threeToOneList;
    }

    public ArrayList<Admin> getAllAdmins(){
        return adminManager.getAllAdmins();
    }

    public void createAdmin(String email, String password, int schoolID) throws SQLException, IOException
    {
        adminManager.createAdmin(email, encryptor.Encrypt(password), schoolID);
    }

    public void deleteAdmin(int adminID){
        adminManager.deleteAdmin(adminID);
    }

    public void updateAdmin(Admin admin){
        adminManager.updateAdmin(admin);
    }
}

