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

    /**
     * This class takes a list and makes it an observable list for the mvc layer
     * @return
     * @throws SQLException
     */
    public ObservableList<User> getAllUsers() throws SQLException {
        userList.clear();
        userList.addAll(userManager.getAllUsers());
        return userList;
    }

    /**
     * This class takes a list and makes it an observable list for the mvc layer
     * @param citizenID
     * @return
     */
    public ObservableList<User> getBoundUsers(int citizenID){
        userList.clear();
        userList.addAll(userManager.getBoundUsers(citizenID));
        return userList;
    }

    /**
     * This class takes a list and makes it an observable list for the mvc layer
     * @param schoolID
     * @return
     * @throws SQLException
     */
    public ObservableList<User> getAllUsersFromSchool(int schoolID) throws SQLException {
        userList.clear();
        userList.addAll(userManager.getAllUsersFromSchool(schoolID));
        return userList;
    }

    /**
     * This class takes a list and makes it an observable list for the mvc layer
     * @param schoolID
     * @return
     * @throws SQLException
     */
    public ObservableList<User> getAllStudentsFromSchool(int schoolID) throws SQLException {
        studentList.clear();
        studentList.addAll(userManager.getAllStudentsFromSchool(schoolID));
        return studentList;
    }

    /**
     * This class passes data between the mvc layer and the bll layer
     * @param fName
     * @param lName
     * @param email
     * @param password
     * @param userType
     * @param schoolID
     * @throws SQLException
     * @throws IOException
     */
    public void createUser(String fName, String lName, String email, String password, String userType, int schoolID) throws SQLException, IOException {
        userManager.createUser(fName, lName , email, encryptor.Encrypt(password), schoolID, userType);
    }

    /**
     * This class passes data between the mvc layer and the bll layer
     * @param user
     * @throws IOException
     */
    public void updateUser(User user) throws IOException {
        user.setPassword(encryptor.Encrypt(user.getPassword()));
        userManager.updateUser(user);
    }

    /**
     * This class passes data between the mvc layer and the bll layer
     * @param userID
     */
    public void deleteUser(int userID) {
        userManager.deleteUser(userID);
    }
}
