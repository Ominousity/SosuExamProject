package BLL;

import BE.Citizen;
import BE.User;
import DAL.UserDAO;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserManager {

    private UserDAO userDAO;

    public UserManager() throws IOException {
        userDAO = new UserDAO();
    }

    public ArrayList<User> getAllUsers() throws SQLException {
        return userDAO.getAllUsers();
    }

    public void createUser(String fName, String lName, String email, String password, int schoolID, String userType) throws SQLException    {
        userDAO.createUser(fName, lName, email, password, schoolID, userType);
    }

    public void deleteUser() throws SQLServerException {
        userDAO.deleteUser();
    }

    public void updateUser(User user) throws SQLServerException {
        userDAO.updateUser(user);
    }

    public ArrayList<Student> getAllStudents() throws SQLException
    {
        return userDAO.getAllStudents();
    }

}
