package DAL;

import BE.*;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private DatabaseConnector connection;
    private Thread thread;
    public UserDAO() throws IOException
    {
        connection = new DatabaseConnector();
    }

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

    public ArrayList<Student> getAllStudents() throws SQLException {
        ArrayList<Student> students = new ArrayList<>();

        try(Connection conn = connection.getConnection()){
            String sql = "SELECT * FROM Student;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                int ID = rs.getInt("ID");
                int SchoolID = rs.getInt("SchoolID");
                String FName = rs.getString("FName");
                String LName = rs.getString("LName");
                String Email = rs.getString("Email");
                String Password = rs.getString("Password");

                Student student = new Student(ID, FName, LName, Email, Password, SchoolID);
                students.add(student);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return students;
    }

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

                preparedStatement.execute();
                preparedStatement.executeUpdate();

            } catch (SQLException e){
                e.getNextException();
            }
        }
    }

    public void updateUser(User user) throws SQLServerException {
        try(Connection conn = connection.getConnection()) {
            String sql = "UPDATE Users SET fName=?, lName=?, email=?, password=?, schoolID=?, userType=? WHERE ID=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,user.getFName());
            preparedStatement.setString(2,user.getLName());
            preparedStatement.setString(3,user.getEmail());
            preparedStatement.setString(4,user.getPassword());
            preparedStatement.setInt(5,user.getSchoolID());
            preparedStatement.setString(6,user.getUserType());

            if(preparedStatement.executeUpdate() != 1){
                throw new SQLException("Could not update User");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } ;
    }

    public void deleteUser(int userID) throws SQLServerException {
        try(Connection conn = connection.getConnection()) {
            String sql = "DELETE FROM Users WHERE ID=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } ;
    }
}
