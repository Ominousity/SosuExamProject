package DAL;

import BE.Student;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    private DatabaseConnector connection;

    public LoginDAO() throws IOException {
        connection = new DatabaseConnector();
    }

    /**
     * The method makes a Student in the databse.
     * @param email
     * @return
     * @throws SQLException
     */
    public Student getStudent(String email) throws SQLException {
            try (Connection conn = connection.getConnection()) {
                String sql = "SELECT * FROM Student WHERE Email=?;";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1,email);
                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    int ID = rs.getInt("ID");
                    int SchoolID = rs.getInt("SchoolID");
                    String FName = rs.getString("FName");
                    String LName = rs.getString("LName");
                    String Email = rs.getString("Email");
                    String Password = rs.getString("Password");

                    Student student = new Student(ID, FName, LName, Email, Password, SchoolID);
                    return student;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
    /**
    public Admin getAdmin(String email) throws SQLException {
        try (Connection conn = connection.getConnection()) {
            String sql = "SELECT * FROM Student WHERE Email=?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,email);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int ID = rs.getInt("ID");
                int SchoolID = rs.getInt("SchoolID");
                String Email = rs.getString("Email");
                String Password = rs.getString("Password");

                Admin admin = new Admin(ID, Email, Password, SchoolID);
                return admin;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
     */
}