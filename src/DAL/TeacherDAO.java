package DAL;

import BE.Student;
import BE.Teacher;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAO
{
    private DatabaseConnector connection;

    public TeacherDAO() throws IOException {
        connection = new DatabaseConnector();
    }
    public ArrayList<Teacher> getAllTeachers() throws SQLException {
        ArrayList<Teacher> teachers = new ArrayList<>();

        try(Connection conn = connection.getConnection()){
            String sql = "SELECT * FROM Teacher;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                int ID = rs.getInt("ID");
                int SchoolID = rs.getInt("SchoolID");
                String FName = rs.getString("FName");
                String LName = rs.getString("LName");
                String Email = rs.getString("Email");
                String Password = rs.getString("Password");

                Teacher teacher = new Teacher(ID, FName, LName, Email, Password, SchoolID);
                teachers.add(teacher);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return teachers;
    }

    public void createTeacher(String FName, String LName, String Email, String Password, int schoolID) {

        try (Connection conn = connection.getConnection()) {
            String sqlStatement = "INSERT INTO Teacher(FName,LName, Email, Password, SchoolID) VALUES (?,?,?,?,?);";

            try(PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setString(1, FName);
                preparedStatement.setString(2, LName);
                preparedStatement.setString(3, Email);
                preparedStatement.setString(4, Password);
                preparedStatement.setInt(5, schoolID);

                preparedStatement.execute();
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTeacher(Teacher teacher) throws SQLException {

        try (Connection conn = connection.getConnection()) {
            String sqlStatement = "UPDATE Teacher SET FName, LName, Email, Password";

            try(PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setString(1, teacher.getFName());
                preparedStatement.setString(2, teacher.getLName());
                preparedStatement.setString(3, teacher.getEmail());
                preparedStatement.setString(4, teacher.getPassword());

                if(preparedStatement.executeUpdate() != 1){
                    throw new SQLException("Could not update Teacher");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteTeacher(int TeacherID) throws SQLException {
        try(Connection conn = connection.getConnection()){
            String sql1 = "DELETE FROM Teacher WHERE ID=?";

            PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);

            preparedStatement1.setInt(1,TeacherID);
            preparedStatement1.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
