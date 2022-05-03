package DAL;

import BE.Citizen;
import BE.School;
import BE.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO
{
    private DatabaseConnector connection;

    public List<Student> getAllStudents() throws SQLException {
        ArrayList<Student> students = new ArrayList<>();

        try(Connection conn = connection.getConnection()){
            String sql = "SELECT * FROM Student;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                int ID = rs.getInt("ID");
                String FName = rs.getString("FName");
                String LName = rs.getString("LName");
                String Email = rs.getString("Email");
                String Password = rs.getString("Password");

                Student student = new Student(ID, FName, LName, Email, Password);
                students.add(student);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return students;
    }


    public void createStudent(String FName, String LName, String Email, String Password) {

        try (Connection conn = connection.getConnection()) {
            String sqlStatement = "INSERT INTO Student(FName,LName, Email, Password) VALUES (?,?,?,?);";

            try(PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setString(1, FName);
                preparedStatement.setString(2, LName);
                preparedStatement.setString(3, Email);
                preparedStatement.setString(4, Password);

                preparedStatement.execute();
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(Student student) throws SQLException {

        try (Connection conn = connection.getConnection()) {
            String sqlStatement = "UPDATE Student SET FName, LName, Email, Password";

            try(PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setString(1, student.getFName());
                preparedStatement.setString(2, student.getLName());
                preparedStatement.setString(3, student.getEmail());
                preparedStatement.setString(4, student.getPassword());

                if(preparedStatement.executeUpdate() != 1){
                    throw new SQLException("Could not update Student");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteStudent(int StudentID) throws SQLException {
        try(Connection conn = connection.getConnection()){
            String sql1 = "DELETE FROM StuCit WHERE StudentID=?, DELETE FROM Student WHERE ID=?;";

            PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);

            preparedStatement1.setInt(1,StudentID);
            preparedStatement1.setInt(2,StudentID);
            preparedStatement1.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
