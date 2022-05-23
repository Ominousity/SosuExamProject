package BLL;

import BE.Student;
import BE.User;
import DAL.UserDAO;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserManager {

    private UserDAO userDAO;

    public UserManager()  {
        userDAO = new UserDAO();
    }

    public ArrayList<User> getAllUsers() throws SQLException {
        return userDAO.getAllUsers();
    }

    public void createUser(String fName, String lName, String email, String password, int schoolID, String userType) throws SQLException    {
        userDAO.createUser(fName, lName, email, password, schoolID, userType);
    }

    public void deleteUser(int userID) throws SQLServerException {
        userDAO.deleteUser(userID);
    }

    public void updateUser(User user) throws SQLServerException {
        userDAO.updateUser(user);
    }

    public ArrayList<Student> getAllStudents() throws SQLException
    {
        return userDAO.getAllStudents();
    }

}
