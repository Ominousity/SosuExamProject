package BLL;

import BE.Student;
import BE.User;
import DAL.UserDAO;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.collections.ObservableList;

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

    public List<User> getAllUsersFromSchool(int schoolID) throws SQLException {
        return userDAO.getAllUsersFromSchool(schoolID);
    }

    public ArrayList<Student> getAllStudentsFromSchool(int schoolID) {
        return userDAO.getAllStudentsFromSchool(schoolID);
    }

    public void createUser(String fName, String lName, String email, String password, int schoolID, String userType) throws SQLException    {
        userDAO.createUser(fName, lName, email, password, schoolID, userType);
    }

    public void deleteUser(int userID) {
        userDAO.deleteUser(userID);
    }

    public void updateUser(User user) {
        userDAO.updateUser(user);
    }
}
