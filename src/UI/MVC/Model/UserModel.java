package UI.MVC.Model;

import BE.Citizen;
import BLL.UserManager;
import BLL.Utility.Encryptor;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserModel {

    private UserManager userManager;
    private Encryptor encryptor;

    public UserModel() throws IOException {
        userManager = new UserManager();
        encryptor = new Encryptor();
    }

    public ArrayList<Admin> getAllAdmins(){
        return userManager.getAllAdmins();
    }

    public void createAdmin(String email, String password, int schoolID) throws SQLException, IOException
    {
        userManager.createAdmin(email, encryptor.Encrypt(password), schoolID);
    }

    public void deleteAdmin(int adminID){
        userManager.deleteAdmin(adminID);
    }

    public void updateAdmin(Admin admin){
        userManager.updateAdmin(admin);
    }

    public void createStudent(String FName, String LName, String Email, String Password, int schoolID) throws IOException
    {
        userManager.createStudent(FName, LName, Email, encryptor.Encrypt(Password), schoolID);
    }

    public void updateStudent(Student student) throws SQLException{
        userManager.updateStudent(student);
    }

    public void deleteStudent(int StudentID) throws SQLException{
        userManager.deleteStudent(StudentID);
    }

    public List<Student> getAllStudentsForOneCitizen(Citizen citizen) throws SQLException{
        return userManager.getAllStudentsForOneCitizen(citizen);
    }

    public ArrayList<Student> getAllStudents() throws SQLException
    {
        return userManager.getAllStudents();
    }

    public ArrayList<Teacher> getAllTeachers() throws SQLException {
        return userManager.getAllTeachers();
    }

    public void createTeacher(String FName, String LName, String Email, String Password, int schoolID) throws IOException
    {
        userManager.createTeacher(FName, LName, Email, encryptor.Encrypt(Password), schoolID);
    }

    public void updateTeacher(Teacher teacher) throws SQLException{
        userManager.updateTeacher(teacher);
    }

    public void deleteTeacher(int TeacherID) throws SQLException{
        userManager.deleteTeacher(TeacherID);
    }

}
