package DAL;

import BE.*;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class UserDAO {
    private DatabaseConnector connection = DatabaseConnector.getInstance();
    public UserDAO() throws IOException {
    }

    /**
     * The method helps to make a Arraylist of all the useres. The method makes the list in
     * the database so it's saved.
     * @return
     * @throws SQLException
     */
    public ArrayList<User> getAllUsers() throws SQLException {
        ArrayList<User> users = new ArrayList<>();

        try (Connection conn = connection.getConnection()){
            String sql = "SELECT * FROM Users";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                int ID = rs.getInt("ID");
                String fName = rs.getString("FName");
                String lName = rs.getString("LName");
                String email = rs.getString("Email");
                String password = rs.getString("Password");
                int schoolID = rs.getInt("SchoolID");
                String userType = rs.getString("UserType");


                User user = new User(ID, fName, lName, email, password, schoolID, userType);
                users.add(user);
            }
        } catch (SQLServerException e) {
            e.printStackTrace();
        }
        return users;
    }

    public ArrayList<User> getAllUsersFromSchool(int schoolID) throws SQLException {
        ArrayList<User> users = new ArrayList<>();

        try (Connection conn = connection.getConnection()){
            String sql = "SELECT * FROM Users WHERE SchoolID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, schoolID);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                int ID = rs.getInt("ID");
                String fName = rs.getString("FName");
                String lName = rs.getString("LName");
                String email = rs.getString("Email");
                String password = rs.getString("Password");
                int schoolId = rs.getInt("SchoolID");
                String userType = rs.getString("UserType");


                User user = new User(ID, fName, lName, email, password, schoolId, userType);
                users.add(user);
            }
        } catch (SQLServerException e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * The method helps make a Arraylist for the students in the database
     * @return
     */
    public ArrayList<User> getAllStudentsFromSchool(int schoolID) {
        ArrayList<User> students = new ArrayList<>();

        try(Connection conn = connection.getConnection()){
            String sql = "SELECT * FROM Users WHERE UserType = 'STUDENT' AND SchoolID = ?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, schoolID);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                int ID = rs.getInt("ID");
                int SchoolID = rs.getInt("SchoolID");
                String FName = rs.getString("FName");
                String LName = rs.getString("LName");
                String Email = rs.getString("Email");
                String Password = rs.getString("Password");

                User user = new User(ID, FName, LName, Email, Password, SchoolID, "STUDENT");
                students.add(user);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return students;
    }

    /**
     * The method creates a user in the database
     * @param fName
     * @param lName
     * @param email
     * @param password
     * @param schoolID
     * @param userType
     * @throws SQLException
     */

    public void createUser(String fName, String lName, String email, String password, int schoolID, String userType) throws SQLException
    {
        try(Connection conn = connection.getConnection()){
            String sql = "INSERT INTO Users(fName, lName, email, password, schoolID, userType) values(?,?,?,?,?,?);";

            try(PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setString(1, fName);
                preparedStatement.setString(2, lName);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, password);
                preparedStatement.setInt(5, schoolID);
                preparedStatement.setString(6, userType);
                preparedStatement.executeUpdate();

            } catch (SQLException e){
                e.getNextException();
            }
        }
    }

    /**
     * The method updates a User in the database
     * @param user
     */

    public void updateUser(User user) {
        try(Connection conn = connection.getConnection()) {
            String sql = "UPDATE Users SET fName=?, lName=?, email=?, password=?, schoolID=?, userType=? WHERE ID=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,user.getFName());
            preparedStatement.setString(2,user.getLName());
            preparedStatement.setString(3,user.getEmail());
            preparedStatement.setString(4,user.getPassword());
            preparedStatement.setInt(5,user.getSchoolID());
            preparedStatement.setString(6,user.getUserType());
            preparedStatement.setInt(7,user.getID());

            if(preparedStatement.executeUpdate() != 1){
                throw new SQLException("Could not update User");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * The method deletes a User in the database
     * @param userID
     */
    public void deleteUser(int userID) {
        try(Connection conn = connection.getConnection()) {
            String sql = "DELETE FROM Users WHERE ID=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, userID);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
