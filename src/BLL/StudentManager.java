package BLL;

import BE.Category;
import BE.Citizen;
import BE.Student;
import DAL.DatabaseConnector;
import DAL.StudentDAO;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    StudentDAO studentDAO;

    public StudentManager() throws IOException {
        studentDAO = new StudentDAO();
    }
    public void createStudent(String FName, String LName, String Email, String Password){
        studentDAO.createStudent(FName, LName, Email, Password);
    }

    public void updateStudent(Student student) throws SQLException{
        studentDAO.updateStudent(student);
    }

    public void deleteStudent(int StudentID) throws SQLException{
        studentDAO.deleteStudent(StudentID);
    }

    public List<Student> getAllStudentsForOneCitizen(Citizen citizen) throws SQLException {
    return studentDAO.getAllStudentsForOneCitizen(citizen);}

    public ArrayList<Student> getAllStudents() throws SQLException
    {
        return studentDAO.getAllStudents();
    }

}
