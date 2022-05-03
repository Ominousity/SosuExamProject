package BLL;

import BE.Student;
import DAL.StudentDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentManager
{
    StudentDAO studentDAO;

    public ArrayList<Student> getAllStudents() throws SQLException{
        return studentDAO.getAllStudents();
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

}
