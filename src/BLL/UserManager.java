package BLL;

import BE.Citizen;
import DAL.UserDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserManager {

    private UserDAO userDAO;

    public UserManager() throws IOException {
        userDAO = new UserDAO();
    }

    public ArrayList<Admin> getAllAdmins(){
        return userDAO.getAllAdmin();
    }

    public void createAdmin(String email, String password, int schoolID) throws SQLException
    {
        userDAO.createAdmin(email, password, schoolID);
    }

    public void deleteAdmin(int adminID){
        userDAO.deleteAdmin(adminID);
    }

    public void updateAdmin(Admin admin){
        userDAO.updateAdmin(admin);
    }

    public void createStudent(String FName, String LName, String Email, String Password, int schoolID){
        userDAO.createStudent(FName, LName, Email, Password, schoolID);
    }

    public void updateStudent(Student student) throws SQLException{
        userDAO.updateStudent(student);
    }

    public void deleteStudent(int StudentID) throws SQLException{
        userDAO.deleteStudent(StudentID);
    }

    public List<Student> getAllStudentsForOneCitizen(Citizen citizen) throws SQLException {
        return userDAO.getAllStudentsForOneCitizen(citizen);}

    public ArrayList<Student> getAllStudents() throws SQLException
    {
        return userDAO.getAllStudents();
    }

    public ArrayList<Teacher> getAllTeachers() throws SQLException{
        return userDAO.getAllTeachers();
    }

    public void createTeacher(String FName, String LName, String Email, String Password, int schoolID){
        userDAO.createTeacher(FName, LName, Email, Password, schoolID);
    }

    public void updateTeacher(Teacher teacher) throws SQLException{
        userDAO.updateTeacher(teacher);
    }

    public void deleteTeacher(int TeacherID) throws SQLException{
        userDAO.deleteTeacher(TeacherID);
    }

}
