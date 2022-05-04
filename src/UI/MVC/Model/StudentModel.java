package UI.MVC.Model;

import BE.Student;
import BLL.StudentManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentModel
{
    private StudentManager studentManager;
    public ArrayList<Student> getAllStudents() throws SQLException {
        return studentManager.getAllStudents();
    }

    public void createStudent(String FName, String LName, String Email, String Password){
        studentManager.createStudent(FName, LName, Email, Password);
    }

    public void updateStudent(Student student) throws SQLException{
        studentManager.updateStudent(student);
    }

    public void deleteStudent(int StudentID) throws SQLException{
        studentManager.deleteStudent(StudentID);
    }
    }
