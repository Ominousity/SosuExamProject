package UI.MVC.Model;

import BE.Student;
import BE.User;
import BLL.UserManager;
import BLL.Utility.BulkAdd;
import BLL.Utility.Encryptor;
import BLL.Utility.ExcelReading;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class UserModel {

    private UserManager userManager;
    private Encryptor encryptor;
    private BulkAdd bulkAdd;

    ObservableList<User> userList;
    ObservableList<Student> studentList;

    public UserModel()  {
        userManager = new UserManager();
        encryptor = new Encryptor();


        userList = FXCollections.observableArrayList();
        studentList = FXCollections.observableArrayList();
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
        studentList.addAll(userManager.getAllStudents());
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

    public void bulkAdd() {
        bulkAdd.bulkAdd123();
    }

}
