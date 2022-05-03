package BLL;

import BE.Student;
import DAL.StudentDAO;

import java.sql.SQLException;
import java.util.List;

public class StudentManager
{
    StudentDAO studentDAO;

    public List<Student> getAllStudents(int SchoolID) throws SQLException{
        return studentDAO.getAllStudents(SchoolID);
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
