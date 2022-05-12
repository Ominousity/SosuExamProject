package UI.MVC.Model;

import BE.Citizen;
import BE.Student;
import BE.User;
import BLL.UserManager;
import BLL.Utility.Encryptor;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserModel {

    private UserManager userManager;
    private Encryptor encryptor;

    ObservableList<User> userList;
    ObservableList<Student> studentList;

    public UserModel() throws IOException {
        userManager = new UserManager();
        encryptor = new Encryptor();
    }

    public ObservableList<User> getAllUsers() throws SQLException
    {
        userList.clear();
        userList.addAll(userManager.getAllUsers());
        return userList;
    }

    public ObservableList<Student> getAllStudents() throws SQLException
    {
        studentList.clear();
        userList.addAll(userManager.getAllStudents());
        return studentList;
    }

    public void createUser(String fName, String lName, String email, String password, String userType, int schoolID) throws SQLException, IOException
    {
        userManager.createUser(fName, lName , email, encryptor.Encrypt(password), schoolID, userType);
    }

    public void deleteUser(int userID) throws SQLServerException {
        userManager.deleteUser(userID);
    }

    public void updateUser(User user) throws SQLException{
        userManager.updateUser(user);
    }

}
