package BLL;

import BE.Admin;
import BE.Student;
import BE.Teacher;
import DAL.LoginDAO;

import java.io.IOException;
import java.sql.SQLException;

public class LoginManager {
    private LoginDAO loginDAO;
    public LoginManager() throws IOException {
        loginDAO = new LoginDAO();
    }

    public Student getStudent(String email) throws SQLException {
        return loginDAO.getStudent(email);
    }
    public Teacher getTeacher(String email) throws SQLException {
        return loginDAO.getTeacher(email);
    }
    public Admin getAdmin(String email) throws SQLException {
        return loginDAO.getAdmin(email);
    }
}
