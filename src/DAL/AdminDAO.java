package DAL;

import BE.Student;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO
{
    private DatabaseConnector connection;
    public AdminDAO() throws IOException
    {
        connection = new DatabaseConnector();
    }

    public List<Student> getAllStudents(int SchoolID){
        ArrayList<Student> students = new ArrayList<>();

        try(Connection conn = connection.getConnection())
        {
            String sql = "SELECT * FROM Student WHERE SchoolID=?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, SchoolID);
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
        } catch (SQLException e){
            e.printStackTrace();
        }
        return students;
    }

    public void createStudent(String FName, String LName, String Email, String Password) throws SQLException
    {
        try(Connection conn = connection.getConnection()){
            String sql = "INSERT INTO Admin(FName, LName, Email, Password) values(?,?,?,?);";

            try(PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setString(1, FName);
                preparedStatement.setString(2, LName);
                preparedStatement.setString(3, Email);
                preparedStatement.setString(4, Password);
                preparedStatement.executeUpdate();
            } catch (SQLException throwables){
                throwables.getNextException();
            }
        }
    }

    public void updateStudent(Student student){
        try(Connection conn = connection.getConnection()){
            String sql = "UPDATE Student SET FName=?, LName=?, Email=?, Password=? WHERE ID=?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, student.getFName());
            preparedStatement.setString(2, student.getLName());
            preparedStatement.setString(3, student.getEmail());
            preparedStatement.setString(4, student.getPassword());
            preparedStatement.setInt(5, student.getID());
            if(preparedStatement.executeUpdate() != 1){
                throw new SQLException("Could not update Student");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteStudent(int studentID){
        try(Connection conn = connection.getConnection())
        {
            String sql1 = "DELETE FROM StuCit WHERE StudentID=?;";
            String sql2 = "DELETE FROM Student WHERE ID=?;";
            PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
            preparedStatement1.setInt(1,studentID);
            preparedStatement1.execute();

            PreparedStatement preparedStatement2 = conn.prepareStatement(sql2);
            preparedStatement2.setInt(1, studentID);
            preparedStatement2.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
