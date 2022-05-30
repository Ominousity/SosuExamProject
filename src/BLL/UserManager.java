package BLL;

import BE.User;
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

    /**
     * This class passes data between the bll layer and the dal layer
     * @return
     * @throws SQLException
     */
    public ArrayList<User> getAllUsers() throws SQLException {
        return userDAO.getAllUsers();
    }

    /**
     * This class passes data between the bll layer and the dal layer
     * @param citizenID
     * @return
     */
    public ArrayList<User> getBoundUsers(int citizenID){
        return userDAO.getBoundUsers(citizenID);
    }

    /**
     * This class passes data between the bll layer and the dal layer
     * @param schoolID
     * @return
     * @throws SQLException
     */
    public List<User> getAllUsersFromSchool(int schoolID) throws SQLException {
        return userDAO.getAllUsersFromSchool(schoolID);
    }

    /**
     * This class passes data between the bll layer and the dal layer
     * @param schoolID
     * @return
     */
    public ArrayList<User> getAllStudentsFromSchool(int schoolID) {
        return userDAO.getAllStudentsFromSchool(schoolID);
    }

    /**
     * This class passes data between the bll layer and the dal layer
     * @param fName
     * @param lName
     * @param email
     * @param password
     * @param schoolID
     * @param userType
     * @throws SQLException
     */
    public void createUser(String fName, String lName, String email, String password, int schoolID, String userType) throws SQLException    {
        userDAO.createUser(fName, lName, email, password, schoolID, userType);
    }

    /**
     * This class passes data between the bll layer and the dal layer
     * @param userID
     */
    public void deleteUser(int userID) {
        userDAO.deleteUser(userID);
    }

    /**
     * This class passes data between the bll layer and the dal layer
     * @param user
     */
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }
}
