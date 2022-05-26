package UI.MVC.Model;

import BE.User;
import BLL.UserManager;
import BLL.Utility.Encryptor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.IOException;
import java.sql.SQLException;

public class UserModel {

    private UserManager userManager;
    private Encryptor encryptor;

    ObservableList<User> userList;
    ObservableList<User> studentList;

    public UserModel() throws IOException {
        userManager = new UserManager();
        encryptor = new Encryptor();

        userList = FXCollections.observableArrayList();
        studentList = FXCollections.observableArrayList();
    }

    public ObservableList<User> getAllUsers() throws SQLException {
        userList.clear();
        userList.addAll(userManager.getAllUsers());
        return userList;
    }

    public ObservableList<User> getBoundUsers(int citizenID){
        userList.clear();
        userList.addAll(userManager.getBoundUsers(citizenID));
        return userList;
    }
    public ObservableList<User> getAllUsersFromSchool(int schoolID) throws SQLException {
        userList.clear();
        userList.addAll(userManager.getAllUsersFromSchool(schoolID));
        return userList;
    }

    public ObservableList<User> getAllStudentsFromSchool(int schoolID) throws SQLException {
        studentList.clear();
        studentList.addAll(userManager.getAllStudentsFromSchool(schoolID));
        return studentList;
    }

    public void createUser(String fName, String lName, String email, String password, String userType, int schoolID) throws SQLException, IOException {
        userManager.createUser(fName, lName , email, encryptor.Encrypt(password), schoolID, userType);
    }

    public void updateUser(User user) throws IOException {
        user.setPassword(encryptor.Encrypt(user.getPassword()));
        userManager.updateUser(user);
    }
    public void deleteUser(int userID) {
        userManager.deleteUser(userID);
    }
}
